package com.example.android.smashupscorekeeper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.android.smashupscorekeeper.data.PlayerContract;
import com.example.android.smashupscorekeeper.data.PlayerContract.GameEntry;
import com.example.android.smashupscorekeeper.data.PlayerContract.PlayerEntry;

import java.util.ArrayList;

public class PlayerInfoSelect extends AppCompatActivity {

    ArrayList<PlayerClass> players = new ArrayList<PlayerClass>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_info_select);

    }

    @Override
    protected void onResume() {
        super.onResume();
        createPlayerList();
    }

    private void createPlayerList() {

        String[] projection = {
                PlayerEntry._ID,
                PlayerEntry.COLUMN_PLAYER_NAME
        };

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
        PlayerAdapter adapter = new PlayerAdapter(PlayerInfoSelect.this, players);
        //here we hook our ListView from xml to listView by referencing its id in choose_player.xml
        ListView listView = (ListView) findViewById(R.id.list_root);
        //here we set the adapter
        listView.setAdapter(adapter);

        //here we set the onClickListener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id){

                //Toast.makeText(view.getContext(),"1:"+players.get(position).playerID, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(PlayerInfoSelect.this, PlayerInfoScreen.class );
                intent.putExtra("PLAYER_ID_EXTRA", players.get(position).playerID);
                startActivity(intent);
                finish();
            }
        });
    }
}