package com.example.android.smashupscorekeeper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int pointPlayerA = 0, pointPlayerB = 0;
    int scorePlayerA = 0, scorePlayerB = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_editor.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_editor, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Save" menu option
            case R.id.reset:
                reset();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void reset(){

        scorePlayerA = 0;
        scorePlayerB = 0;
        pointPlayerA = 0;
        pointPlayerB = 0;
        scoreUpdater();
    }

    public void increasePlayerOne(View v){
        TextView points = (TextView) findViewById(R.id.player_one_score_change);
        pointPlayerA++;
        if(pointPlayerA>0){
            points.setText("+"+pointPlayerA);
        }else{
            points.setText(""+pointPlayerA);
        }
    }

    public void increasePlayerTwo(View v){
        TextView points = (TextView) findViewById(R.id.player_two_score_change);
        pointPlayerB++;
        if(pointPlayerB>0){
            points.setText("+"+pointPlayerB);
        }else{
            points.setText(""+pointPlayerB);
        }
    }

    public void decreasePlayerOne(View v){
        TextView points = (TextView) findViewById(R.id.player_one_score_change);
        pointPlayerA--;
        if(pointPlayerA>0){
            points.setText("+"+pointPlayerA);
        }else{
            points.setText(""+pointPlayerA);
        }
    }

    public void decreasePlayerTwo(View v){
        TextView points = (TextView) findViewById(R.id.player_two_score_change);
        pointPlayerB--;
        if(pointPlayerB>0){
            points.setText("+"+pointPlayerB);
        }else{
            points.setText(""+pointPlayerB);
        }
    }
    public void updateScore(View v){
        scoreUpdater();
    }

    public void scoreUpdater(){
        TextView scoreA = (TextView) findViewById(R.id.player_one_score);
        TextView pointsA = (TextView) findViewById(R.id.player_one_score_change);
        TextView scoreB = (TextView) findViewById(R.id.player_two_score);
        TextView pointsB = (TextView) findViewById(R.id.player_two_score_change);
        scorePlayerA+=pointPlayerA;
        scorePlayerB+=pointPlayerB;
        pointPlayerA=0;
        pointPlayerB=0;
        pointsA.setText(""+pointPlayerA);
        scoreA.setText(""+scorePlayerA);
        pointsB.setText(""+pointPlayerB);
        scoreB.setText(""+scorePlayerB);
    }

}