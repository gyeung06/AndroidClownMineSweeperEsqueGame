package c.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;


import c.example.myapplication.models.GameLogic;
import c.example.myapplication.models.OptionsData;

public class Game extends AppCompatActivity {

    public static final String BUTTON_STATES = "buttonStates";
    public static final String NEW_GAME_CHECK = "newGame";
    public static final String GAME_KEY = "game";

    private OptionsData optionsData = OptionsData.getInstance();
    private int rows = optionsData.getRows();
    private int cols = optionsData.getCols();
    private int mines = optionsData.getMines();

    private GameLogic gameLogic = new GameLogic(rows, cols, mines);
    Button[][] buttons = new Button[rows][cols];



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        populateButtons();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(!getNewGameCheck()){
            resetVisuals();
            boardUpdate();
        }
    }

    private void resetVisuals() {

        lockButtonSizes();

        for(int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j++){
                Button button = buttons[i][j];
                int newWidth = 0;
                int newHeight = 0;
                Bitmap originalBitmap = null;
                boolean skip = false;
                switch (getStateOfButton(i, j)){
                    case 0:
                        gameLogic.changeState(i, j, GameLogic.HIDDEN_SCAN);
                        skip = true;
                        break;
                    case 1:
                        gameLogic.changeState(i, j, GameLogic.HIDDEN_BOMB);
                        skip = true;
                        break;
                    case 2:
                        newWidth = button.getWidth();
                        newHeight = button.getHeight();
                        originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.clown);
                        break;
                    case 3:
                    case 4:
                        newWidth = button.getWidth();
                        newHeight = button.getHeight();
                        originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.cotton_candy);
                        break;
                }
                if (!skip) {
                    Bitmap scaledBitmap = Bitmap.createScaledBitmap(originalBitmap, newWidth, newHeight, true);
                    Resources resource = getResources();
                    button.setBackground(new BitmapDrawable(resource, scaledBitmap));
                }
            }
        }
    }


    private void populateButtons() {
        TableLayout table = findViewById(R.id.game_table);
        //gameLogic.initialize();


        final boolean isFresh = getNewGameCheck();
        if (isFresh) {
            gameLogic.initialize();
        } else {
            refreshBoard();
        }

        for (int i = 0; i < rows; i++){
            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.MATCH_PARENT,
                    1.0f));
            table.addView(tableRow);

            for (int j = 0; j < cols; j++){
                Button button = new Button(this);
                button.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.MATCH_PARENT,
                        1.0f));

                final int butCol = j;
                final int butRow = i;
                button.setPadding(0, 0, 0, 0);

                saveButtonState(butRow,butCol,gameLogic.getState(butRow,butCol));

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        buttonClicked(butRow, butCol);
                    }
                });

                tableRow.addView(button);
                buttons[i][j] = button;
            }

        }
        saveNewGameCheck(false);
    }

    private void refreshBoard() {
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j++){
                gameLogic.changeState(i,j,getStateOfButton(i,j));
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private void buttonClicked(int buttonRow, int buttonCol) {

        Button button = buttons[buttonRow][buttonCol];

        lockButtonSizes();

        int newWidth = 0;
        int newHeight = 0;
        Bitmap originalBitmap = null;

        switch (gameLogic.getState(buttonRow, buttonCol)){
            case 0:
                newWidth = button.getWidth();
                newHeight = button.getHeight();
                originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.clown);
                button.setText("" + gameLogic.scanValue(buttonRow, buttonCol));
                saveButtonState(buttonRow, buttonCol, GameLogic.SHOW_SCAN);
                gameLogic.changeState(buttonRow, buttonCol, GameLogic.SHOW_SCAN);
                break;
            case 1:
                newWidth = button.getWidth();
                newHeight = button.getHeight();
                originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.cotton_candy);
                saveButtonState(buttonRow, buttonCol, GameLogic.SHOW_BOMB);
                gameLogic.changeState(buttonRow, buttonCol, GameLogic.SHOW_BOMB);
                boardUpdate();
                break;
            case 2:
                newWidth = button.getWidth();
                newHeight = button.getHeight();
                originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.clown);
                break;
            case 3:
                newWidth = button.getWidth();
                newHeight = button.getHeight();
                originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.cotton_candy);
                button.setText("" + gameLogic.scanValue(buttonRow, buttonCol));
                saveButtonState(buttonRow, buttonCol, GameLogic.SHOW_BOMB_SCAN);
                gameLogic.changeState(buttonRow, buttonCol, GameLogic.SHOW_BOMB_SCAN);
                break;
            case 4:
                newWidth = button.getWidth();
                newHeight = button.getHeight();
                originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.cotton_candy);
        }

        Bitmap scaledBitmap = Bitmap.createScaledBitmap(originalBitmap, newWidth, newHeight, true);
        Resources resource = getResources();
        button.setBackground(new BitmapDrawable(resource, scaledBitmap));

    }

    private void boardUpdate() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (gameLogic.getState(i,j) == 2 || gameLogic.getState(i,j) == 4){
                    buttons[i][j].setText("" + gameLogic.scanValue(i,j));
                }
            }
        }
    }

    private void lockButtonSizes() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                Button button = buttons[row][col];

                int width = button.getWidth();
                button.setMinWidth(width);
                button.setMaxWidth(width);

                int height = button.getHeight();
                button.setMinHeight(height);
                button.setMaxHeight(height);
            }
        }
    }

    private void saveNewGameCheck(boolean newGame){
        SharedPreferences pref = this.getSharedPreferences(NEW_GAME_CHECK, MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean(GAME_KEY, newGame);
        editor.apply();




    }

    private boolean getNewGameCheck(){
          SharedPreferences pref = this.getSharedPreferences(NEW_GAME_CHECK, MODE_PRIVATE);
          return pref.getBoolean(GAME_KEY, true);
    }

    private void saveButtonState(int currentRow, int currentCol, int state) {
        SharedPreferences pref = this.getSharedPreferences(BUTTON_STATES, MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(""+currentRow+""+currentCol, state);
        editor.apply();


    }

    private int getStateOfButton(int currentRow, int currentCol) {
        SharedPreferences pref = this.getSharedPreferences(BUTTON_STATES, MODE_PRIVATE);
        return pref.getInt(""+currentRow+""+currentCol, GameLogic.HIDDEN_SCAN);
    }

    public static Intent makeIntent(Context context) {
        return new Intent(context, Game.class);
    }

}