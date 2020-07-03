package com.example.android.smashupscorekeeper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ChoosePlayer extends AppCompatActivity {

    ArrayList<PlayerClass> players = new ArrayList<PlayerClass>();
    ArrayList<String> names = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_player);

        players.add(new PlayerClass("Ayumi"));
        players.add(new PlayerClass("Bela"));

    }

    @Override
    protected void onStart() {
        super.onStart();

        //custom ArrayAdapter for playerClass is used to create our adapter for the list view
        PlayerAdapter adapter = new PlayerAdapter(ChoosePlayer.this, players);
        //here we hook our ListView from xml to listView by referencing its id in choose_player.xml
        ListView listView = (ListView) findViewById(R.id.list);
        //here we set the adapter
        listView.setAdapter(adapter);
        //here we set the onClickListener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id){
                //Here we should add the clicked player to current players, the following is for testing
                //Toast.makeText(ChoosePlayer.this, Integer.toString(position), Toast.LENGTH_SHORT).show();
                //players.remove(position);
                finish();
            }
        });

    }
}