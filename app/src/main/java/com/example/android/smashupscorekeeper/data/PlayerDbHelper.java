package com.example.android.smashupscorekeeper.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.AdapterView;

import com.example.android.smashupscorekeeper.data.PlayerContract.PlayerEntry;
import com.example.android.smashupscorekeeper.data.PlayerContract.GameEntry;


public class PlayerDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "users.db";
    private static final int DATABASE_VERSION = 1;

    public PlayerDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        /** CREATE TABLE players(_id INTEGER PRIMARY KEY, name TEXT, wins INTEGER,
         *                       losses INTEGER, total INTEGER, streak INTEGER); */
         String SQL_CREATE_PLAYERS_TABLE =
                 "CREATE TABLE " + PlayerEntry.PLAYERS_TABLE_NAME + "("
                        + PlayerEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + PlayerEntry.COLUMN_PLAYER_NAME + " TEXT NOT NULL, "
                        + PlayerEntry.COLUMN_PLAYER_WINS + " INTEGER NOT NULL DEFAULT 0, "
                        + PlayerEntry.COLUMN_PLAYER_LOSSES + " INTEGER NOT NULL DEFAULT 0, "
                        + PlayerEntry.COLUMN_PLAYER_TOTAL + " INTEGER NOT NULL DEFAULT 0, "
                        + PlayerEntry.COLUMN_PLAYER_STREAK + " INTEGER NOT NULL DEFAULT 0);";

         db.execSQL(SQL_CREATE_PLAYERS_TABLE);

         /** CREATE TABLE game(_id INTEGER PRIMARY KEY, name TEXT, score INTEGER); */
        String SQL_CREATE_GAME_TABLE =
                "CREATE TABLE " + GameEntry.GAME_TABLE_NAME + "("
                        + GameEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + GameEntry.COLUMN_PLAYER_NAME + " TEXT NOT NULL, "
                        + GameEntry.COLUMN_PLAYER_SCORE + " INTEGER NOT NULL DEFAULT 0);";

        db.execSQL(SQL_CREATE_GAME_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
