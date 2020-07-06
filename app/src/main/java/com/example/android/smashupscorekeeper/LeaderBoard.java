package com.example.android.smashupscorekeeper;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;

import com.example.android.smashupscorekeeper.data.PlayerContract.PlayerEntry;

public class LeaderBoard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.leader_board);

        setMostWins();
        setMostLoss();
        setWinningStreak();
        //setBestRatio();
    }

    private void setMostWins() {
        TextView[] mostWins = new TextView[3];
        mostWins[0] = (TextView) findViewById(R.id.most_wins_1);
        mostWins[1] = (TextView) findViewById(R.id.most_wins_2);
        mostWins[2] = (TextView) findViewById(R.id.most_wins_3);
        setMost(PlayerEntry.COLUMN_PLAYER_WINS, mostWins);
    }

    /**private void setBestRatio() {
        TextView[] mostWins = new TextView[3];
        mostWins[0] = (TextView) findViewById(R.id.most_wins_1);
        mostWins[1] = (TextView) findViewById(R.id.most_wins_2);
        mostWins[2] = (TextView) findViewById(R.id.most_wins_3);
        setMost(PlayerEntry.COLUMN_PLAYER_WINS, mostWins);
    }*/

    private void setWinningStreak() {
        TextView longestStreak = (TextView)findViewById(R.id.longest_streak);
        String[] projection = {
                PlayerEntry.COLUMN_PLAYER_NAME,
                PlayerEntry.COLUMN_PLAYER_STREAK
        };
        Cursor cursor = getContentResolver().query(
                PlayerEntry.CONTENT_URI,
                projection,
                null,
                null,
                PlayerEntry.COLUMN_PLAYER_STREAK+" DESC");
        int nameIndex = cursor.getColumnIndex(PlayerEntry.COLUMN_PLAYER_NAME);
        cursor.moveToFirst();
        String name = cursor.getString(nameIndex);
        longestStreak.setText(name);
        cursor.close();

    }

    private void setMostLoss() {
        TextView[] mostLosses = new TextView[3];
        mostLosses[0] = (TextView) findViewById(R.id.most_loss_1);
        mostLosses[1] = (TextView) findViewById(R.id.most_loss_2);
        mostLosses[2] = (TextView) findViewById(R.id.most_loss_3);
        setMost(PlayerEntry.COLUMN_PLAYER_LOSSES, mostLosses);
    }

    private void setMost(String category, TextView[] mostWins) {
        String[] projection = {
                PlayerEntry.COLUMN_PLAYER_NAME,
                category
        };
        Cursor cursor = getContentResolver().query(
                PlayerEntry.CONTENT_URI,
                projection,
                null,
                null,
                category+" DESC");
        int nameIndex = cursor.getColumnIndex(PlayerEntry.COLUMN_PLAYER_NAME);
        int i=0;
        while (cursor.moveToNext() && i<3){
            String currentName = cursor.getString(nameIndex);
            mostWins[i].setText(currentName);
            i++;
        }
        cursor.close();
    }


}