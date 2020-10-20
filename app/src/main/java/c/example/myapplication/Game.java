package c.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;


import c.example.myapplication.models.GameLogic;
import c.example.myapplication.models.OptionsData;

public class Game extends AppCompatActivity {

    private OptionsData optionsData = OptionsData.getInstance();
    private int rows = optionsData.getRows();
    private int cols = optionsData.getCols();
    private int mines = optionsData.getMines();
    private boolean freshStart = true;

    private GameLogic gameLogic = new GameLogic(rows, cols, mines, freshStart);
    int [][] board;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        //TODO Make a true and false thing to see if its a fresh start
        makeBoard();
        populateButtons();
    }

    private void makeBoard() {
        board = new int[rows][cols];
    }

    private void populateButtons() {
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j++){
                //TODO Set up the buttons
                //TODO Set up saver, getter is done below 
            }
        }

    }

    private int getStateOfButton(int i, int j) {
        SharedPreferences prefs = this.getSharedPreferences("buttonStates", MODE_PRIVATE);
        int state = prefs.getInt(""+(i*cols + j), 0);
        return state;
    }

    public static Intent makeIntent(Context context) {
        return new Intent(context, Game.class);
    }

}