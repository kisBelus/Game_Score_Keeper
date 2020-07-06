package com.example.android.smashupscorekeeper;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.android.smashupscorekeeper.data.PlayerContract.PlayerEntry;

import org.w3c.dom.Text;

public class PlayerCursorAdapter extends CursorAdapter {

    public PlayerCursorAdapter(Context context, Cursor cursor){
        super(context, cursor, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        //Grab the text view from the xml we want (name)
        TextView nameTextView = (TextView) view.findViewById(R.id.name_text_view);

        //get the index and name at index from cursor we want (name)
        int nameColumnIndex = cursor.getColumnIndex(PlayerEntry.COLUMN_PLAYER_NAME);
        String playerName = cursor.getString(nameColumnIndex);

        //set the name into the text view
        nameTextView.setText(playerName);
    }
}
