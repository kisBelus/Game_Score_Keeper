package com.example.android.smashupscorekeeper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.smashupscorekeeper.data.PlayerContract;
import com.example.android.smashupscorekeeper.data.PlayerContract.PlayerEntry;

import java.text.DecimalFormat;

public class PlayerInfoScreen extends AppCompatActivity {

    private static DecimalFormat df = new DecimalFormat("0.0");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_info_screen);

        createPlayerStats();
    }

    private void createPlayerStats(){
        Intent intentExtra = getIntent();
        int id = intentExtra.getIntExtra("PLAYER_ID_EXTRA", -1);

        Toast.makeText(PlayerInfoScreen.this,""+id, Toast.LENGTH_LONG).show();

        String[] projection = {
                PlayerEntry.COLUMN_PLAYER_NAME,
                PlayerEntry.COLUMN_PLAYER_WINS,
                PlayerEntry.COLUMN_PLAYER_LOSSES,
                PlayerEntry.COLUMN_PLAYER_TOTAL,
                PlayerEntry.COLUMN_PLAYER_STREAK
        };

        String selection = PlayerEntry._ID + "=?";
        String[] selectionArgs = {Integer.toString(id)};

        Cursor cursor = getContentResolver().query(
                PlayerEntry.CONTENT_URI,
                projection,
                selection,
                selectionArgs,
                null);


        int nameIndex = cursor.getColumnIndex(PlayerEntry.COLUMN_PLAYER_NAME);
        int winIndex = cursor.getColumnIndex(PlayerEntry.COLUMN_PLAYER_WINS);
        int lossIndex = cursor.getColumnIndex(PlayerEntry.COLUMN_PLAYER_LOSSES);
        int totalIndex = cursor.getColumnIndex(PlayerEntry.COLUMN_PLAYER_TOTAL);
        int streakIndex = cursor.getColumnIndex(PlayerEntry.COLUMN_PLAYER_STREAK);
        cursor.moveToFirst();
        Toast.makeText(PlayerInfoScreen.this,""+cursor.getColumnCount(), Toast.LENGTH_LONG).show();

        String name = cursor.getString(nameIndex);
        int wins = cursor.getInt(winIndex);
        int loss = cursor.getInt(lossIndex);
        int total = cursor.getInt(totalIndex);
        int streak = cursor.getInt(streakIndex);

        TextView nameView = (TextView) findViewById(R.id.name);
        nameView.setText(name);

        TextView winView = (TextView) findViewById(R.id.wins);
        winView.setText("Victory: "+wins);

        TextView lossView = (TextView) findViewById(R.id.losses);
        lossView.setText("loss:"+loss);

        TextView ratioView = (TextView) findViewById(R.id.ratio);
        float ratio;
        if(loss>0){
            ratio = wins/loss;
            ratioView.setText("Ratio: "+df.format(ratio));
        }else{
            ratioView.setText("Ratio: "+wins);
        }

        TextView streakView = (TextView) findViewById(R.id.streak);
        streakView.setText("Streak: "+streak);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_editor.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_editor2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Save" menu option
            case R.id.delete:
                //delete();
                return true;
            case R.id.edit_name:
                //editName();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}