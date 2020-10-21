package c.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;


// Credits https://www.flaticon.com/free-icon/cotton-candy_472693
//  https://www.flaticon.com/free-icon/clown_472683
// https://freesound.org/people/Higgs01/sounds/428156/
//https://unsplash.com/photos/dnkM5wPjVdg
public class Help extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        TextView text = findViewById(R.id.about);
        text.setText("Written by Gabriel Yeung\n" +
                "About the Author https://opencoursehub.cs.sfu.ca/bfraser/grav-cms/cmpt276/course-info\n\n" +
                "How to play:\n" +
                "Click on a button to reveal either a cotton candy or clown\n" +
                "Clowns help you find cotton candy by the number. The number indicates how many cotton candies are in the same row or column as the clown\n" +
                "You can also click on revealed cotton candy to do the same thing\n" +
                "Anytime a number is revealed that is the use of a scab\n" +
                "\n" +
                "Citation for pictures and sounds\n" +
                "https://www.flaticon.com/free-icon/cotton-candy_472693\n" +
                "https://www.flaticon.com/free-icon/clown_472683\n" +
                "https://freesound.org/people/Higgs01/sounds/428156/\n" +
                "https://unsplash.com/photos/dnkM5wPjVdg");
    }

    public static Intent makeIntent(Context context) {
        return new Intent(context, Help.class);
    }

}