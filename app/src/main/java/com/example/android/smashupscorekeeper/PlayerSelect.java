package com.example.android.smashupscorekeeper;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import com.example.android.smashupscorekeeper.data.PlayerContract;
import com.example.android.smashupscorekeeper.data.PlayerContract.GameEntry;
import com.example.android.smashupscorekeeper.data.PlayerDbHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.style.BackgroundColorSpan;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.graphics.Color.*;

public class PlayerSelect extends AppCompatActivity {

    ArrayList<PlayerClass> players = new ArrayList<PlayerClass>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_select);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PlayerSelect.this, PlayerEditor.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        buildPlayerList();

        generateSelectedPlayersList();
    }

    private void buildPlayerList() {

        players.clear();

        //PlayerDbHelper mDbHelper = new PlayerDbHelper(this);
        //SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] projection = {
                GameEntry._ID,
                GameEntry.COLUMN_PLAYER_NAME
        };

        /**Cursor cursor = db.query(
                GameEntry.GAME_TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null);*/

        Cursor cursor = getContentResolver().query(
                GameEntry.CONTENT_URI,
                projection,
                null,
                null,
                null);

        try {
            //get Index for columns
            int idIndex = cursor.getColumnIndex(GameEntry._ID);
            int nameIndex = cursor.getColumnIndex(GameEntry.COLUMN_PLAYER_NAME);

            //loop through table getting names
            while (cursor.moveToNext()) {
                int currentID = cursor.getInt(idIndex);
                String currentName = cursor.getString(nameIndex);
                players.add(new PlayerClass(currentName, currentID));
            }
        } finally {
            cursor.close();
        }
    }

    private void generateSelectedPlayersList() {
        LinearLayout playerListRoot = (LinearLayout) findViewById(R.id.player_list_root);
        playerListRoot.removeAllViews();
        for(int i = 0; i < players.size(); i++) {

            final Button playerTextView = new Button(this);
            playerTextView.setId(players.get(i).playerID);
            playerTextView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    //Toast.makeText(view.getContext(),"Click Click: "+ view.getId(), Toast.LENGTH_LONG).show();
                    removeFromDb(view.getId());
                    buildPlayerList();
                    generateSelectedPlayersList();
                }
            });
            playerTextView.setText(players.get(i).playerName);
            playerTextView.setBackgroundColor(LTGRAY);
            playerTextView.setAlpha((float) 0.8);
            playerTextView.setTextColor(BLACK);
            playerTextView.setGravity(Gravity.CENTER);
            playerTextView.setAllCaps(true);
            playerTextView.setPadding(0, getApplicationContext().getResources().getDimensionPixelSize(R.dimen.dp_20), 0, getApplicationContext().getResources().getDimensionPixelSize(R.dimen.dp_20));
            LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            param.setMargins(0, 0, 0, getApplicationContext().getResources().getDimensionPixelSize(R.dimen.dp_20));
            playerTextView.setLayoutParams(param);
            playerListRoot.addView(playerTextView);
        }

        buttonFlipper();
    }

    public void openScoreBoard(View view) {
        switch (players.size()) {
            case 2:
                Intent intentTwo = new Intent(PlayerSelect.this, ScoreBoardTwoPlayer.class);
                startActivity(intentTwo);
                break;
            case 3:
                Intent intentThree = new Intent(PlayerSelect.this, ScoreBoardThreePlayer.class);
                startActivity(intentThree);
                break;
            case 4:
                Intent intentFour = new Intent(PlayerSelect.this, ScoreBoardFourPlayer.class);
                startActivity(intentFour);
                break;
            default:
                break;
        }
        finish();
    }

    public void openChoosePlayer(View view){
        Intent intent = new Intent(PlayerSelect.this, ChoosePlayer.class);
        startActivity(intent);
    }

    private void removeFromDb(int id){
        getContentResolver().delete(
                GameEntry.CONTENT_URI,
                GameEntry._ID+"=?",
                new String[]{Integer.toString(id)}

        );
    }

    private void buttonFlipper(){
        Button startButton = (Button) findViewById(R.id.start_button);
        Button startButtonOff = (Button) findViewById(R.id.start_button_off);
        Button selectPlayerButton = (Button) findViewById(R.id.select_players_button);
        if(players.size()>1){
            startButton.setVisibility(View.VISIBLE);
            startButtonOff.setVisibility(View.INVISIBLE);
        }else{
            startButton.setVisibility(View.INVISIBLE);
            startButtonOff.setVisibility(View.VISIBLE);
        }
        if(players.size()>3){
            selectPlayerButton.setVisibility(View.INVISIBLE);
        }else{
            selectPlayerButton.setVisibility(View.VISIBLE);
        }

    }
}