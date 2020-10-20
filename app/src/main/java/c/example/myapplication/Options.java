package c.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

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

    private OptionsData optionsData = OptionsData.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        
        createRowsButtons();
        getSavedOption();
    }



    private void createRowsButtons() {
        RadioGroup group = findViewById(R.id.options_rows);

        String[] rowOptions = getResources().getStringArray(R.array.row_options);

        for (int i = 0; i < rowOptions.length; i++) {
            final String option = rowOptions[i];

            RadioButton button = new RadioButton(this);
            button.setText(option);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(Options.this, option + " selected", Toast.LENGTH_SHORT).show();

                    saveRowChoice(option);
                    getSavedOption();
                }
            });

            group.addView(button);

            if (option.equals(getSavedOption())) {
                button.setChecked(true);
            }
        }
    }

    private void saveRowChoice(String choice) {
        SharedPreferences prefs = this.getSharedPreferences("RowPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("rowChoice", choice);
        editor.apply();
    }

    //Also updates the OptionsData
    private String getSavedOption() {
        SharedPreferences prefs = this.getSharedPreferences("RowPrefs", MODE_PRIVATE);
        String text = prefs.getString("rowChoice", "4 ");
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



    public static Intent makeIntent(Context context) {
        return new Intent(context, Options.class);
    }

}