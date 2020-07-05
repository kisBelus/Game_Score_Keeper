package com.example.android.smashupscorekeeper.data;

import android.content.ContentResolver;
import android.provider.BaseColumns;
import android.net.Uri;

public final class PlayerContract {

    public PlayerContract(){
    }//empty constructor

    public static final String CONTENT_AUTHORITY = "com.example.android.smashupscorekeeper";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://"+CONTENT_AUTHORITY);
    public static final String PATH_PLAYERS = "players";
    public static final String PATH_GAME = "game";

    //entry fields for player db
    public static abstract class PlayerEntry implements BaseColumns{

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_PLAYERS);
        public static final String CONTENT_LIST_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PLAYERS;
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PLAYERS;

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

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_GAME);

        public static final String CONTENT_LIST_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_GAME;
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_GAME;

        public static final String GAME_TABLE_NAME = "game";

        public static final String _ID = BaseColumns._ID;

        public static final String COLUMN_PLAYER_NAME = "name";
        public static final String COLUMN_PLAYER_SCORE = "score";
    }
}
