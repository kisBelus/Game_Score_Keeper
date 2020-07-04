package com.example.android.smashupscorekeeper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.smashupscorekeeper.data.PlayerContract;
import com.example.android.smashupscorekeeper.data.PlayerDbHelper;


public class PlayerEditor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_editor);
    }

    //Get Name from EditText box
    public void submitName(View view) {
        EditText text = (EditText) findViewById(R.id.name_field);
        String nameInput = text.getText().toString().trim();
        //UPDATE DB
        PlayerDbHelper mDbHelper = new PlayerDbHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PlayerContract.PlayerEntry.COLUMN_PLAYER_NAME, nameInput);
        long newID = db.insert(PlayerContract.PlayerEntry.PLAYERS_TABLE_NAME, null, values);

        //check that it inserted name
        if(newID == -1){
            Toast.makeText(this, "Error occured while saving", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Name Saved", Toast.LENGTH_SHORT).show();
        }
        finish();
    }
}