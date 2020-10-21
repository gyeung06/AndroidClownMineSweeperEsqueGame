package c.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;

import java.util.Timer;
import java.util.TimerTask;

/*
Welcome splash screen for the game
 */

public class WelcomeScreen extends AppCompatActivity {
    Timer timer = new Timer();
    private boolean ifSkipped = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);

        iniSkipButton();
        startTimer();
    }

    private void iniSkipButton() {
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = MainMenu.makeIntent(WelcomeScreen.this);
                startActivity(intent);
                finish();
                ifSkipped = true;
            }
        });
    }

    private void startTimer() {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (!ifSkipped) {
                    Intent intent = MainMenu.makeIntent(WelcomeScreen.this);
                    startActivity(intent);
                    finish();
                }
            }
        }, 5000);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}