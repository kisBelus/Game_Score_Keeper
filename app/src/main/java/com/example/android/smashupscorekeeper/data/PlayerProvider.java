package com.example.android.smashupscorekeeper.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.android.smashupscorekeeper.data.PlayerContract.PlayerEntry;
import com.example.android.smashupscorekeeper.data.PlayerContract.GameEntry;


public class PlayerProvider extends ContentProvider {

    //db helper object
    private PlayerDbHelper mDbHelper;

    private static final int PLAYERS = 100;
    private static final int PLAYER_ID = 101;
    private static final int GAME = 200;
    private static final int GAME_ID = 201;

    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        sUriMatcher.addURI(PlayerContract.CONTENT_AUTHORITY, PlayerContract.PATH_PLAYERS, PLAYERS);
        sUriMatcher.addURI(PlayerContract.CONTENT_AUTHORITY, PlayerContract.PATH_PLAYERS + "/#", PLAYER_ID);
        sUriMatcher.addURI(PlayerContract.CONTENT_AUTHORITY, PlayerContract.PATH_GAME, GAME);
        sUriMatcher.addURI(PlayerContract.CONTENT_AUTHORITY, PlayerContract.PATH_GAME+ "/#", GAME_ID);
    }

    @Override
    public boolean onCreate() {
        mDbHelper = new PlayerDbHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

        SQLiteDatabase myDataBase = mDbHelper.getReadableDatabase();
        Cursor resultCursor;

        int match = sUriMatcher.match(uri);
        switch (match){
            case PLAYERS:
                resultCursor = myDataBase.query(PlayerEntry.PLAYERS_TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case PLAYER_ID:
                selection = PlayerEntry._ID + "=?";
                selectionArgs = new String[]{ String.valueOf(ContentUris.parseId(uri))};
                resultCursor = myDataBase.query(PlayerEntry.PLAYERS_TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case GAME:
                resultCursor = myDataBase.query(GameEntry.GAME_TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case GAME_ID:
                selection = GameEntry._ID + "=?";
                selectionArgs = new String[]{ String.valueOf(ContentUris.parseId(uri))};
                resultCursor = myDataBase.query(GameEntry.GAME_TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            default:
                throw new IllegalArgumentException("Cannot query unknown URI "+uri);
        }

        return resultCursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        final int match = sUriMatcher.match(uri);
        switch (match){
            case PLAYERS:
                return PlayerEntry.CONTENT_LIST_TYPE;
            case PLAYER_ID:
                return PlayerEntry.CONTENT_ITEM_TYPE;
            case GAME:
                return GameEntry.CONTENT_LIST_TYPE;
            case GAME_ID:
                return GameEntry.CONTENT_ITEM_TYPE;
            default:
                throw new IllegalStateException("Unknown URI "+uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        int match = sUriMatcher.match(uri);
        switch (match){
            case PLAYERS:
                return insertPlayer(uri, contentValues);
            case GAME:
                return insertGame(uri, contentValues);
            default:
                throw new IllegalArgumentException("Insertion is not supported for "+uri);
        }
    }

    private Uri insertPlayer(Uri uri, ContentValues values){
        String name = values.getAsString(PlayerEntry.COLUMN_PLAYER_NAME);
        if(name==null){
            throw new IllegalArgumentException("Player requires a name");
        }

        SQLiteDatabase myDataBase = mDbHelper.getWritableDatabase();
        long id = myDataBase.insert(PlayerEntry.PLAYERS_TABLE_NAME, null, values);
        if(id==-1){
            return null;
        }
        return ContentUris.withAppendedId(uri, id);
    }

    private Uri insertGame(Uri uri, ContentValues values){
        String name = values.getAsString(GameEntry.COLUMN_PLAYER_NAME);
        if(name==null){
            throw new IllegalArgumentException("Player requires a name");
        }

        SQLiteDatabase myDataBase = mDbHelper.getWritableDatabase();
        long id = myDataBase.insert(GameEntry.GAME_TABLE_NAME, null, values);
        if(id == -1){
            return null;
        }
        return ContentUris.withAppendedId(uri, id);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase myDataBase = mDbHelper.getWritableDatabase();
        int match = sUriMatcher.match(uri);
        switch (match){
            case PLAYERS:
                return myDataBase.delete(PlayerEntry.PLAYERS_TABLE_NAME, selection, selectionArgs);
            case PLAYER_ID:
                selection = PlayerEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                return myDataBase.delete(PlayerEntry.PLAYERS_TABLE_NAME, selection, selectionArgs);
            case GAME:
                return myDataBase.delete(GameEntry.GAME_TABLE_NAME, selection, selectionArgs);
            case GAME_ID:
                selection = GameEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                return myDataBase.delete(GameEntry.GAME_TABLE_NAME, selection, selectionArgs);
            default:
                throw new IllegalArgumentException("Can't delete "+uri);
        }
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String selection, String[] selectionArgs) {
        int match = sUriMatcher.match(uri);
        switch (match){
            case PLAYERS:
                return updatePlayer(uri, contentValues, selection, selectionArgs);
            case PLAYER_ID:
                selection = PlayerEntry._ID + "=?";
                selectionArgs = new String[] {String.valueOf(ContentUris.parseId(uri))};
                return updatePlayer(uri, contentValues, selection, selectionArgs);
            case GAME:
                return updateGame(uri, contentValues, selection, selectionArgs);
            case GAME_ID:
                selection = GameEntry._ID + "=?";
                selectionArgs = new String[]{ String.valueOf(ContentUris.parseId(uri))};
                return  updateGame(uri, contentValues, selection, selectionArgs);
            default:
                throw new IllegalArgumentException("Can't update "+uri);
        }
    }

    private int updatePlayer(Uri uri, ContentValues values, String selection, String[] selectionArgs){
        if(values.containsKey(PlayerEntry.COLUMN_PLAYER_NAME)){
            String name = values.getAsString(PlayerEntry.COLUMN_PLAYER_NAME);
            if(name == null){
                throw new IllegalArgumentException("Need name");
            }
        }
        if(values.size() == 0){
            return 0;
        }

        SQLiteDatabase myDataBase = mDbHelper.getWritableDatabase();
        return myDataBase.update(PlayerEntry.PLAYERS_TABLE_NAME, values, selection, selectionArgs);
    }

    private int updateGame(Uri uri, ContentValues values, String selection, String[] selectionArgs){
        if(values.containsKey(GameEntry.COLUMN_PLAYER_NAME)){
            String name = values.getAsString(GameEntry.COLUMN_PLAYER_NAME);
            if(name == null){
                throw new IllegalArgumentException("Need name");
            }
        }
        if(values.size() == 0){
            return 0;
        }

        SQLiteDatabase myDataBase = mDbHelper.getWritableDatabase();
        return myDataBase.update(GameEntry.GAME_TABLE_NAME, values, selection, selectionArgs);
    }
}
