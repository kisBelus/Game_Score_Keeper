package com.example.android.smashupscorekeeper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.android.smashupscorekeeper.data.PlayerContract;
import com.example.android.smashupscorekeeper.data.PlayerContract.GameEntry;
import com.example.android.smashupscorekeeper.data.PlayerContract.PlayerEntry;
import com.example.android.smashupscorekeeper.data.PlayerDbHelper;

import java.util.ArrayList;

public class ChoosePlayer extends AppCompatActivity {

    ArrayList<PlayerClass> players = new ArrayList<PlayerClass>();
    ArrayList<String> names = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_player);

        createPlayerList();
    }

    private void createPlayerList(){

        //PlayerDbHelper mDbHelper = new PlayerDbHelper(this);
        //SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] projection = {
                PlayerEntry._ID,
                PlayerEntry.COLUMN_PLAYER_NAME
        };

        /**Cursor cursor = db.query(
                PlayerEntry.PLAYERS_TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null);*/

        //new
        Cursor cursor = getContentResolver().query(
                PlayerEntry.CONTENT_URI,
                projection,
                null,
                null,
                null);

        try {
            //get Index for columns
            int idIndex = cursor.getColumnIndex(PlayerEntry._ID);
            int nameIndex = cursor.getColumnIndex(PlayerEntry.COLUMN_PLAYER_NAME);

            //loop through table getting names
            while (cursor.moveToNext()) {
                int currentID = cursor.getInt(idIndex);
                String currentName = cursor.getString(nameIndex);
                players.add(new PlayerClass(currentName, currentID));
            }
        } finally {
            cursor.close();
        }

        //custom ArrayAdapter for playerClass is used to create our adapter for the list view
        PlayerAdapter adapter = new PlayerAdapter(ChoosePlayer.this, players);
        //here we hook our ListView from xml to listView by referencing its id in choose_player.xml
        ListView listView = (ListView) findViewById(R.id.list_choose_player);
        //here we set the adapter
        listView.setAdapter(adapter);
        //here we set the onClickListener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id){

                //PlayerDbHelper mDbHelper = new PlayerDbHelper(view.getContext());
                //SQLiteDatabase db = mDbHelper.getWritableDatabase();

                ContentValues values = new ContentValues();
                values.put(GameEntry._ID, players.get(position).playerID);
                values.put(GameEntry.COLUMN_PLAYER_NAME, players.get(position).playerName);
                //Long newID = db.insert(GameEntry.GAME_TABLE_NAME, null, values);
                Uri newUri = getContentResolver().insert(GameEntry.CONTENT_URI, values);

                if(newUri == null){
                    Toast.makeText(view.getContext(), "Sorry something went wrong", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(view.getContext(),"Welcome "+players.get(position).playerName, Toast.LENGTH_LONG).show();
                }

                finish();
            }
        });
    }
}