package com.example.android.smashupscorekeeper;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class PlayerSelect extends AppCompatActivity {

    ArrayList<PlayerClass> players = new ArrayList<PlayerClass>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_select);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);




    }

    @Override
    protected void onResume() {
        super.onResume();

        //players.add(new PlayerClass("Ayumi"));
        //players.add(new PlayerClass("Ayumi"));

        LinearLayout playerListRoot = (LinearLayout) findViewById(R.id.player_list_root);
        for(int i = 0; i < players.size(); i++) {

            Button playerTextView = new Button(this);
            playerTextView.setText(players.get(i).playerName);
            playerTextView.setBackgroundColor(Color.LTGRAY);
            playerTextView.setAlpha((float) 0.8);
            playerTextView.setTextColor(Color.BLACK);
            playerTextView.setGravity(Gravity.CENTER);
            playerTextView.setAllCaps(true);
            playerTextView.setPadding(0, getApplicationContext().getResources().getDimensionPixelSize(R.dimen.dp_20), 0, getApplicationContext().getResources().getDimensionPixelSize(R.dimen.dp_20));
            LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            param.setMargins(0, 0, 0, getApplicationContext().getResources().getDimensionPixelSize(R.dimen.dp_20));
            playerTextView.setLayoutParams(param);
            playerListRoot.addView(playerTextView);
        }

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PlayerSelect.this, PlayerEditor.class);
                startActivity(intent);
            }
        });

        //Test Listener setup
        TextView test = (TextView) findViewById(R.id.test_text);
        //Set Listener on the view
        test.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Toast.makeText(view.getContext(),"Click Click", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void openScoreBoard(View view) {
        Intent intent = new Intent(PlayerSelect.this, ScoreBoardTwoPlayer.class);
        startActivity(intent);
    }

    public void openChoosePlayer(View view){
        Intent intent = new Intent(PlayerSelect.this, ChoosePlayer.class);
        startActivity(intent);
    }
}