package com.example.android.smashupscorekeeper.data;

import android.provider.BaseColumns;

public final class PlayerContract {

    public PlayerContract(){
    }//empty constructor

    //entry fields for player db
    public static abstract class PlayerEntry implements BaseColumns{

        public static final String PLAYERS_TABLE_NAME = "players";

        public static final String _ID = BaseColumns._ID;

        public static final String COLUMN_PLAYER_NAME = "name";
        public static final String COLUMN_PLAYER_WINS = "wins";
        public static final String COLUMN_PLAYER_LOSSES = "losses";
        public static final String COLUMN_PLAYER_TOTAL = "total";
        public static final String COLUMN_PLAYER_STREAK = "streak";

    }

    //entry fields for game db
    public static abstract class GameEntry implements BaseColumns{

        public static final String GAME_TABLE_NAME = "game";

        public static final String _ID = BaseColumns._ID;

        public static final String COLUMN_PLAYER_NAME = "name";
        public static final String COLUMN_PLAYER_SCORE = "score";
    }
}
