package com.example.android.smashupscorekeeper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class PlayerEditor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_editor);
    }

    //Get Name from EditText box
    public void submitName(View view) {
        EditText text = (EditText) findViewById(R.id.name_field);
        String name = text.getText().toString();
        //UPDATE DB
    }
}