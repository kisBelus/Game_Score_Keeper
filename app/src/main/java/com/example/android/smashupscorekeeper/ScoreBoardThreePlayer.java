package com.example.android.smashupscorekeeper;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.android.smashupscorekeeper.data.PlayerContract;
import com.example.android.smashupscorekeeper.data.PlayerContract.GameEntry;
import com.example.android.smashupscorekeeper.data.PlayerContract.PlayerEntry;

public class ScoreBoardThreePlayer extends AppCompatActivity {

    PlayerClass[] players = new PlayerClass[3];
    Boolean checkOnce = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.score_board_three_player);

        players[0] = new PlayerClass("default 1", 1);
        players[1] = new PlayerClass("default 2", 2);
        players[2] = new PlayerClass("default 3", 3);

        getPlayerData();
        drawPlayerData();
    }

    private void getPlayerData(){
        String[] projection = {
                GameEntry._ID,
                GameEntry.COLUMN_PLAYER_NAME,
                GameEntry.COLUMN_PLAYER_SCORE
        };
        Cursor cursor = getContentResolver().query(
                GameEntry.CONTENT_URI,
                projection,
                null,
                null,
                null);
        try{
            int idIndex = cursor.getColumnIndex(GameEntry._ID);
            int nameIndex = cursor.getColumnIndex(GameEntry.COLUMN_PLAYER_NAME);
            int scoreIndex = cursor.getColumnIndex(GameEntry.COLUMN_PLAYER_SCORE);
            int i = 0;
            while (cursor.moveToNext()){
                int currentID = cursor.getInt(idIndex);
                String currentName = cursor.getString(nameIndex);
                int scoreValue = cursor.getInt(scoreIndex);
                players[i].playerName = currentName;
                players[i].playerID = currentID;
                players[i].score = scoreValue;
                i++;
            }
        }finally {
            cursor.close();
        }
    }

    private void drawPlayerData(){
        TextView playerOneName = (TextView) findViewById(R.id.player_one_name);
        TextView playerTwoName = (TextView) findViewById(R.id.player_two_name);
        TextView playerThreeName = (TextView) findViewById(R.id.player_three_name);
        playerOneName.setText(players[0].playerName);
        playerTwoName.setText(players[1].playerName);
        playerThreeName.setText(players[2].playerName);
        scoreUpdater();
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
            case R.id.save_results:
                saveGame(players);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void reset(){
        players[0].reset();
        players[1].reset();
        players[2].reset();
        scoreUpdater();
    }

    public void increasePlayerOne(View v){
        TextView points = (TextView) findViewById(R.id.player_one_score_change);
        players[0].points++;
        if(players[0].points>0){
            points.setText("+"+players[0].points);
        }else{
            points.setText(""+players[0].points);
        }
    }

    public void increasePlayerTwo(View v){
        TextView points = (TextView) findViewById(R.id.player_two_score_change);
        players[1].points++;
        if(players[1].points>0){
            points.setText("+"+players[1].points);
        }else{
            points.setText(""+players[1].points);
        }
    }

    public void increasePlayerThree(View v){
        TextView points = (TextView) findViewById(R.id.player_three_score_change);
        players[2].points++;
        if(players[2].points>0){
            points.setText("+"+players[2].points);
        }else{
            points.setText(""+players[2].points);
        }
    }

    public void decreasePlayerOne(View v){
        TextView points = (TextView) findViewById(R.id.player_one_score_change);
        players[0].points--;
        if(players[0].points>0){
            points.setText("+"+players[0].points);
        }else{
            points.setText(""+players[0].points);
        }
    }

    public void decreasePlayerTwo(View v){
        TextView points = (TextView) findViewById(R.id.player_two_score_change);
        players[1].points--;
        if(players[1].points>0){
            points.setText("+"+players[1].points);
        }else{
            points.setText(""+players[1].points);
        }
    }

    public void decreasePlayerThree(View v){
        TextView points = (TextView) findViewById(R.id.player_three_score_change);
        players[2].points--;
        if(players[2].points>0){
            points.setText("+"+players[2].points);
        }else{
            points.setText(""+players[2].points);
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
        TextView scoreC = (TextView) findViewById(R.id.player_three_score);
        TextView pointsC = (TextView) findViewById(R.id.player_three_score_change);
        players[0].score+=players[0].points;
        players[1].score+=players[1].points;
        players[2].score+=players[2].points;
        players[0].points=0;
        players[1].points=0;
        players[2].points=0;
        pointsA.setText(""+players[0].points);
        scoreA.setText(""+players[0].score);
        pointsB.setText(""+players[1].points);
        scoreB.setText(""+players[1].score);
        pointsC.setText(""+players[2].points);
        scoreC.setText(""+players[2].score);
        updateGameDb();
        if(checkOnce){
            if(players[0].score>14 || players[1].score>14 || players[2].score>14){
                checkOnce = false;
                AlertDialog.Builder builder = new AlertDialog.Builder(ScoreBoardThreePlayer.this);
                builder.setMessage("Victory!! \n Save your results?");
                builder.setCancelable(true);

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        saveGame(players);
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            }
        }
    }

    private void updateGameDb(){

        for(int i=0; i<3; i++){
            updatePlayerScore(players[i].score, players[i].playerID);
        }

    }

    private void updatePlayerScore(int score, int id){

        ContentValues values = new ContentValues();
        values.put(GameEntry.COLUMN_PLAYER_SCORE, score);
        String selection = GameEntry._ID+"=?";
        String[] selectionArgs = {Integer.toString(id)};

        int cursor = getContentResolver().update(
                GameEntry.CONTENT_URI,
                values,
                selection,
                selectionArgs);
    }

    private void saveGame(PlayerClass[] players){
        int count = players.length;
        int maxScore = 0;

        for(int i = 0; i<count; i++){
            if(players[i].score>maxScore){
                maxScore = players[i].score;
            }
        }

        for (int i = 0; i<count; i++){
            if(players[i].score == maxScore){
                players[i].win = true;
            }
        }

        for(int i = 0; i<count; i++){
            if(players[i].win){
                //UPDATE AS WINNER
                givePointsForVictory(players[i]);

            }else {
                //UPDATE AS LOSS
                shameOnYouForLosing(players[i]);

            }
        }
        getContentResolver().delete(GameEntry.CONTENT_URI, null, null);
        finish();
    }

    private void givePointsForVictory(PlayerClass player){
        String[] projection = {
                PlayerEntry._ID,
                PlayerEntry.COLUMN_PLAYER_WINS,
                PlayerEntry.COLUMN_PLAYER_TOTAL,
                PlayerEntry.COLUMN_PLAYER_STREAK
        };

        String selection = PlayerEntry._ID + "=?";
        String[] selectionArgs = {Integer.toString(player.playerID)};

        Cursor cursor = getContentResolver().query(
                PlayerEntry.CONTENT_URI,
                projection,
                selection,
                selectionArgs,
                null);

        try{
            int winIndex = cursor.getColumnIndex(PlayerEntry.COLUMN_PLAYER_WINS);
            int totalIndex = cursor.getColumnIndex(PlayerEntry.COLUMN_PLAYER_TOTAL);
            int streakIndex = cursor.getColumnIndex(PlayerEntry.COLUMN_PLAYER_STREAK);

            cursor.moveToFirst();

            player.victories=cursor.getInt(winIndex)+1;
            player.total=cursor.getInt(totalIndex)+1;
            player.streak=cursor.getInt(streakIndex)+1;
        }finally {
            cursor.close();
        }

        ContentValues values = new ContentValues();
        values.put(PlayerEntry.COLUMN_PLAYER_WINS, player.victories);
        values.put(PlayerEntry.COLUMN_PLAYER_TOTAL, player.total);
        values.put(PlayerEntry.COLUMN_PLAYER_STREAK, player.streak);
        //selection and selectionArgs are the same as above so we don't set them here again
        getContentResolver().update(
                PlayerEntry.CONTENT_URI,
                values,
                selection,
                selectionArgs);
    }

    private void shameOnYouForLosing(PlayerClass player){
        String[] projection = {
                PlayerEntry._ID,
                PlayerEntry.COLUMN_PLAYER_LOSSES,
                PlayerEntry.COLUMN_PLAYER_TOTAL};
        String selection = PlayerEntry._ID + "=?";
        String[] selectionArgs = {Integer.toString(player.playerID)};

        Cursor cursor = getContentResolver().query(
                PlayerEntry.CONTENT_URI,
                projection,
                selection,
                selectionArgs,
                null);

        try {
            int lossIndex = cursor.getColumnIndex(PlayerEntry.COLUMN_PLAYER_LOSSES);
            int totalIndex = cursor.getColumnIndex(PlayerEntry.COLUMN_PLAYER_TOTAL);

            cursor.moveToFirst();

            player.losses = cursor.getInt(lossIndex)+1;
            player.total = cursor.getInt(totalIndex)+1;
            player.streak = 0;
        }finally {
            cursor.close();
        }

        ContentValues values = new ContentValues();
        values.put(PlayerEntry.COLUMN_PLAYER_LOSSES, player.losses);
        values.put(PlayerEntry.COLUMN_PLAYER_TOTAL, player.total);
        values.put(PlayerEntry.COLUMN_PLAYER_STREAK, player.streak);
        //selection and selectionArgs are the same as above so we don't set them here again
        getContentResolver().update(
                PlayerEntry.CONTENT_URI,
                values,
                selection,
                selectionArgs);
    }

}