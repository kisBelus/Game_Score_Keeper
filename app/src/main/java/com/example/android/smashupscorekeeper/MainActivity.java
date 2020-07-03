package com.example.android.smashupscorekeeper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
}