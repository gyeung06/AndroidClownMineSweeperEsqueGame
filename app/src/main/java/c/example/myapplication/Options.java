package c.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

/*
Options menu activity
 */

public class Options extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        
        createRowsButtons();
    }

    private void createRowsButtons() {
        RadioGroup group = findViewById(R.id.options_rows);

        String[] rowOptions = getResources().getStringArray(R.array.row_options);

        for (int i = 0; i < rowOptions.length; i++) {
            String option = rowOptions[i];

            RadioButton button = new RadioButton(this);
            button.setText(option);

            group.addView(button);
        }
    }

    public static Intent makeIntent(Context context) {
        return new Intent(context, Options.class);
    }

}