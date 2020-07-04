package com.example.android.smashupscorekeeper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.android.smashupscorekeeper.data.PlayerContract;
import com.example.android.smashupscorekeeper.data.PlayerDbHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initDatabase();
    }

    public void openPlayerSelect(View view){
        Intent intent = new Intent(MainActivity.this, PlayerSelect.class);
        startActivity(intent);
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
        PlayerDbHelper mDbHelper = new PlayerDbHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        //ContentValues values = new ContentValues();
        //values.put(PlayerContract.PlayerEntry.COLUMN_PLAYER_NAME, "Ayumi");
        //db.insert(PlayerContract.PlayerEntry.PLAYERS_TABLE_NAME, null, values);


        Cursor cursor = db.rawQuery("SELECT * FROM "+ PlayerContract.PlayerEntry.PLAYERS_TABLE_NAME, null);
        try{
            TextView displayView = (TextView) findViewById(R.id.db_temp);
            int nameIndex = cursor.getColumnIndex(PlayerContract.PlayerEntry.COLUMN_PLAYER_NAME);
            cursor.moveToNext();
            displayView.setText("Db rows: "+cursor.getString(nameIndex));
        }finally {
            cursor.close();
        }
    }
}