package c.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import c.example.myapplication.models.OptionsData;

/*
Options menu activity
 */

public class Options extends AppCompatActivity {

    private static final String ROW_PREFS = "RowPrefs";
    private static final String ROW_CHOICE = "rowChoice";
    private OptionsData optionsData = OptionsData.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        getSavedOptionRows();
        createRowsButtons();
        getSavedOptionMines();
        createMinesButtons();

    }


    private void createRowsButtons() {
        RadioGroup group = findViewById(R.id.options_rows);

        String[] rowOptions = getResources().getStringArray(R.array.row_options);

        for (final String option : rowOptions) {
            RadioButton button = new RadioButton(this);
            button.setText(option);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(Options.this, option + " selected", Toast.LENGTH_SHORT).show();
                    saveRowChoice(option);
                    getSavedOptionRows();
                }
            });

            group.addView(button);

            if (option.equals(getSavedOptionRows())) {
                button.setChecked(true);
            }
        }
    }

    private void saveRowChoice(String choice) {
        SharedPreferences prefs = this.getSharedPreferences(ROW_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(ROW_CHOICE, choice);
        editor.apply();
    }

    //Also updates the OptionsData
    private String getSavedOptionRows() {
        SharedPreferences prefs = this.getSharedPreferences(ROW_PREFS, MODE_PRIVATE);
        String text = prefs.getString(ROW_CHOICE, "4 ");
        switch (text.charAt(0)) {
            case '4':
                optionsData.setRowsAndCols(4, 6);
                return text;
            case '5':
                optionsData.setRowsAndCols(5,10);
                return text;
            case '6':
                optionsData.setRowsAndCols(6, 15);
                return text;
            default:
                Toast.makeText(Options.this, "wat", Toast.LENGTH_SHORT).show();
        }
        return getResources().getString(R.string.row_default);
    }


    @SuppressLint("DefaultLocale")
    private void createMinesButtons() {
        RadioGroup group = findViewById(R.id.mine_nums);

        int[] numOfMines = getResources().getIntArray(R.array.number_of_mines);
        for (final int mines : numOfMines) {
            RadioButton button = new RadioButton(this);
            button.setText(String.format("%d mines", mines));

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(Options.this, mines + " mines selected", Toast.LENGTH_SHORT).show();
                    saveMineChoice(mines);
                    getSavedOptionMines();
                }
            });

            group.addView(button);

            if (mines == getSavedOptionMines()) {
                button.setChecked(true);
            }
        }
    }

    private void saveMineChoice(int num) {
        SharedPreferences prefs = this.getSharedPreferences("NumPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("mines", num);
        editor.apply();
    }

    private int getSavedOptionMines() {
        SharedPreferences prefs = this.getSharedPreferences("NumPrefs", MODE_PRIVATE);
        int num = prefs.getInt("mines", 6);
        optionsData.setMines(num);
        return num;
    }

    public static Intent makeIntent(Context context) {
        return new Intent(context, Options.class);
    }

}