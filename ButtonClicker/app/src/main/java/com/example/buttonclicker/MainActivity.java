package com.example.buttonclicker;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView textViewScore;
    private int score;
    private ConstraintLayout constraintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // wires the widget: conects the XML to the Java
        textViewScore = findViewById(R.id.textview_main_score);

        constraintLayout = findViewById(R.id.constraintlayout_main);

        score = 0;
    }

    // matches the name of the method in the button's onClick attribute in the xml layout
    public void onGoalClick(View view) {
        // update score
        score++;
        // update the textView displaying the score
        textViewScore.setText("Score: " + score);
        // change color of text
        int r = (int)(Math.random()*256);
        int b = (int)(Math.random()*256);
        int g = (int)(Math.random()*256);

        constraintLayout.setBackgroundColor(Color.rgb(r,g,b));
        textViewScore.setTextColor(Color.rgb(255-r, 255-g, 255-b));

        if(score == 20) {
            Toast.makeText(this, "Hooray!", Toast.LENGTH_SHORT).show();
        }
    }
}
