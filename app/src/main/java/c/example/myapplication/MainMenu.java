package c.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;


/*
Main menu for game
 */
public class MainMenu extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main_menu);

        iniMenuButtons();

    }

    private void iniMenuButtons() {
        Button playButton = findViewById(R.id.menu_play);
        playButton.setOnClickListener(this);
        Button optionsButton = findViewById(R.id.menu_options);
        optionsButton.setOnClickListener(this);
        Button helpButton = findViewById(R.id.menu_help);
        helpButton.setOnClickListener(this);
    }

    public static Intent makeIntent(Context context) {
        return new Intent(context, MainMenu.class);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.menu_play:
                intent = Game.makeIntent(MainMenu.this);
                startActivity(intent);
                return;
            case R.id.menu_options:
                intent = Options.makeIntent(MainMenu.this);
                startActivity(intent);
                return;
            case R.id.menu_help:
                intent = Help.makeIntent(MainMenu.this);
                startActivity(intent);
                return;
        }
    }
}