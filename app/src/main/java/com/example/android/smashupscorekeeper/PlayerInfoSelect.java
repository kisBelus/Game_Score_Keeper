package com.example.android.smashupscorekeeper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class PlayerInfoSelect extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_info_select);
    }

    public void openPlayerInfoScreen(View view){
        Intent intent = new Intent(PlayerInfoSelect.this, PlayerInfoScreen.class );
        startActivity(intent);
    }
}