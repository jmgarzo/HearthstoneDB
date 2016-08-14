package com.jmgarzo.hearthstonedb.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by jmgarzo on 14/08/2016.
 */
public class HearthstoneDbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    static final String DATABASE_NAME = "hearthstone.db";

    public HearthstoneDbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }




    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        final String SQL_CREATE_CARD_TABLE = "CREATE_TABLE" + HearthstoneContract.CardEntry.TABLE_NAME +
                " ( "+
                HearthstoneContract.CardEntry._ID + " INTEGER PRIMARY KEY," +
                HearthstoneContract.CardEntry.COLUMN_CARD_ID  + " TEXT NOT NULL, " +
                HearthstoneContract.CardEntry.COLUMN_NAME + " TEXT NOT NULL, " +
                HearthstoneContract.CardEntry.COLUMN_CARD_SET + " TEXT NOT NULL, " +
                HearthstoneContract.CardEntry.COLUMN_TYPE + " TEXT NOT NULL, " +
                HearthstoneContract.CardEntry.COLUMN_FACTION + " TEXT NOT NULL, " +
                HearthstoneContract.CardEntry.COLUMN_RARITY + " TEXT NOT NULL, " +
                HearthstoneContract.CardEntry.COLUMN_COST + " TEXT NOT NULL, " +
                HearthstoneContract.CardEntry.COLUMN_ATTACK + " TEXT NOT NULL, " +
                HearthstoneContract.CardEntry.COLUMN_HEALTH + " TEXT NOT NULL, " +
                HearthstoneContract.CardEntry.COLUMN_TEXT + " TEXT NOT NULL, " +
                HearthstoneContract.CardEntry.COLUMN_FLAVOR + " TEXT NOT NULL, " +
                HearthstoneContract.CardEntry.COLUMN_ARTIST + " TEXT NOT NULL, " +
                HearthstoneContract.CardEntry.COLUMN_COLLECTIBLE + " TEXT NOT NULL, " +
                HearthstoneContract.CardEntry.COLUMN_IMG + " TEXT NOT NULL, " +
                HearthstoneContract.CardEntry.COLUMN_IMG_GOLD + " TEXT NOT NULL, " +
                HearthstoneContract.CardEntry.COLUMN_LOCALE + " TEXT NOT NULL, " +
                HearthstoneContract.CardEntry.COLUMN_MECHANICS_KEY + " TEXT NOT NULL " +
                " );";
        sqLiteDatabase.execSQL(SQL_CREATE_CARD_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + HearthstoneContract.CardEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
