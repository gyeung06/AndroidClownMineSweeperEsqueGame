package c.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;


// Credits https://www.flaticon.com/free-icon/cotton-candy_472693
//  https://www.flaticon.com/free-icon/clown_472683
// https://freesound.org/people/Higgs01/sounds/428156/
//https://unsplash.com/photos/dnkM5wPjVdg
public class Help extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
    }

    public static Intent makeIntent(Context context) {
        return new Intent(context, Help.class);
    }

}