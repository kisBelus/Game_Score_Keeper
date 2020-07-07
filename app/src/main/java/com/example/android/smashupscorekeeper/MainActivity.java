package com.example.android.smashupscorekeeper;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.smashupscorekeeper.data.PlayerContract;
import com.example.android.smashupscorekeeper.data.PlayerContract.GameEntry;
import com.example.android.smashupscorekeeper.data.PlayerDbHelper;

public class MainActivity extends AppCompatActivity {

    int numPlayers = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    public void openPlayerSelect(View view){

        initDatabase();

        if(numPlayers == 0){
            Intent intent = new Intent(MainActivity.this, PlayerSelect.class);
            startActivity(intent);
        }else {

            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setMessage("Do you want to continue your last game?");
            builder.setCancelable(true);

            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    switch (numPlayers) {
                        case 2:
                            Intent intentTwo = new Intent(MainActivity.this, ScoreBoardTwoPlayer.class);
                            startActivity(intentTwo);
                            break;
                        case 3:
                            Intent intentThree = new Intent(MainActivity.this, ScoreBoardThreePlayer.class);
                            startActivity(intentThree);
                            break;
                        case 4:
                            Intent intentFour = new Intent(MainActivity.this, ScoreBoardFourPlayer.class);
                            startActivity(intentFour);
                            break;
                        default:
                            break;
                    }
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    getContentResolver().delete(GameEntry.CONTENT_URI, null, null);
                    Intent intent = new Intent(MainActivity.this, PlayerSelect.class);
                    startActivity(intent);
                }
            });
            AlertDialog alert = builder.create();
            alert.show();
        }
    }

    public void openPlayerInfoSelect(View view){
        Intent intent = new Intent(MainActivity.this, PlayerInfoSelect.class);
        startActivity(intent);
    }

    public void openLeaderBoard(View view){
        Intent intent = new Intent(MainActivity.this, LeaderBoard.class);
        startActivity(intent);
    }

    //creating our database and getting a readable db
    private void initDatabase(){
        String[] projection = {
                GameEntry._ID,
                GameEntry.COLUMN_PLAYER_NAME
        };
        Cursor cursor = getContentResolver().query(
                GameEntry.CONTENT_URI,
                projection,
                null,
                null,
                null);

        numPlayers = cursor.getCount();

        //Toast.makeText(this, "db: "+cursor.getCount()+"players: "+numPlayers, Toast.LENGTH_SHORT).show();

        cursor.close();
    }
}