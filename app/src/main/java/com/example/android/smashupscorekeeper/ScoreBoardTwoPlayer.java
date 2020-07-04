package com.example.android.smashupscorekeeper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class ScoreBoardTwoPlayer extends AppCompatActivity {

    PlayerClass playerOne;
    PlayerClass playerTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.score_board_two_player);

        playerOne = new PlayerClass("Ayumi", 1);
        playerTwo = new PlayerClass("Bela", 2);

        TextView playerOneName = (TextView) findViewById(R.id.player_one_name);
        TextView playerTwoName = (TextView) findViewById(R.id.player_two_name);
        playerOneName.setText(playerOne.playerName);
        playerTwoName.setText(playerTwo.playerName);
    }

    /**
     * Options Menu Items
     * TODO: add new player, player select...
     */
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
            case R.id.save_results:
                /**
                 * To be done:
                 * Should push results to database
                 */
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void reset(){
        playerOne.reset();
        playerTwo.reset();
        scoreUpdater();
    }

    public void increasePlayerOne(View v){
        TextView points = (TextView) findViewById(R.id.player_one_score_change);
        playerOne.points++;
        if(playerOne.points>0){
            points.setText("+"+playerOne.points);
        }else{
            points.setText(""+playerOne.points);
        }
    }

    public void increasePlayerTwo(View v){
        TextView points = (TextView) findViewById(R.id.player_two_score_change);
        playerTwo.points++;
        if(playerTwo.points>0){
            points.setText("+"+playerTwo.points);
        }else{
            points.setText(""+playerTwo.points);
        }
    }

    public void decreasePlayerOne(View v){
        TextView points = (TextView) findViewById(R.id.player_one_score_change);
        playerOne.points--;
        if(playerOne.points>0){
            points.setText("+"+playerOne.points);
        }else{
            points.setText(""+playerOne.points);
        }
    }

    public void decreasePlayerTwo(View v){
        TextView points = (TextView) findViewById(R.id.player_two_score_change);
        playerTwo.points--;
        if(playerTwo.points>0){
            points.setText("+"+playerTwo.points);
        }else{
            points.setText(""+playerTwo.points);
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
        playerOne.score+=playerOne.points;
        playerTwo.score+=playerTwo.points;
        playerOne.points=0;
        playerTwo.points=0;
        pointsA.setText(""+playerOne.points);
        scoreA.setText(""+playerOne.score);
        pointsB.setText(""+playerTwo.points);
        scoreB.setText(""+playerTwo.score);
    }

}