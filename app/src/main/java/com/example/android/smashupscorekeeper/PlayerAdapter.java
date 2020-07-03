package com.example.android.smashupscorekeeper;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class PlayerAdapter extends ArrayAdapter<PlayerClass> {

    public PlayerAdapter(Activity context, ArrayList<PlayerClass> players){
        super(context, 0, players);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        PlayerClass currentPlayer = getItem(position);

        TextView nameTextView = (TextView) listItemView.findViewById(R.id.name_text_view);
        nameTextView.setText(currentPlayer.playerName);

        return listItemView;
    }
}
