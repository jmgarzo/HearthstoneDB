package com.jmgarzo.hearthstonedb.data;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;

/**
 * Created by jmgarzo on 14/08/2016.
 */
public class HearthstoneProvider extends ContentProvider {


    private static final UriMatcher sUriMatcher = buildUriMatcher();
    private HearthstoneDbHelper mHearthstoneDbHelper;
    static final int CARD = 100;





    static UriMatcher buildUriMatcher() {



        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = HearthstoneContract.CONTENT_AUTHORITY;

        // For each type of URI you want to add, create a corresponding code.
        matcher.addURI(authority, HearthstoneContract.PATH_CARD, CARD);
//        matcher.addURI(authority, WeatherContract.PATH_WEATHER + "/*", WEATHER_WITH_LOCATION);
//        matcher.addURI(authority, WeatherContract.PATH_WEATHER + "/*/#", WEATHER_WITH_LOCATION_AND_DATE);
//
//        matcher.addURI(authority, WeatherContract.PATH_LOCATION, LOCATION);
        return matcher;
    }

    @Override
    public boolean onCreate() {
        mHearthstoneDbHelper = new HearthstoneDbHelper(getContext());
        return true;
    }


    @Nullable
    @Override
    public String getType(Uri uri) {
        final int match = sUriMatcher.match((uri));

        switch (match){
            case CARD:
                return HearthstoneContract.CardEntry.CONTENT_DIR_TYPE;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
    }


    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Cursor retCursor;

        switch ( sUriMatcher.match(uri)){
            case CARD: {
                retCursor = mHearthstoneDbHelper.getReadableDatabase().query(
                        HearthstoneContract.CardEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
            }
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }


    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        final SQLiteDatabase db = mHearthstoneDbHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        Uri returnUri;

        switch (match){
            case CARD:{
                long _id = db.insert(HearthstoneContract.CardEntry.TABLE_NAME, null, contentValues);
                if ( _id > 0 )
                    returnUri = HearthstoneContract.CardEntry.buildCardEntryUri(_id);
                else
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                break;
            }
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);

        }
        getContext().getContentResolver().notifyChange(uri, null);
        return returnUri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        final SQLiteDatabase db = mHearthstoneDbHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        int rowsDeleted;
        // this makes delete all rows return the number of rows deleted
        if ( null == selection ) selection = "1";

        switch (match){
            case CARD:
                rowsDeleted = db.delete(
                        HearthstoneContract.CardEntry.TABLE_NAME, selection, selectionArgs);
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        // Because a null deletes all rows
        if (rowsDeleted != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return rowsDeleted;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {

            final SQLiteDatabase db = mHearthstoneDbHelper.getWritableDatabase();
            final int match = sUriMatcher.match(uri);
            int rowsUpdated;

        switch (match) {
            case CARD:
                rowsUpdated = db.update(HearthstoneContract.CardEntry.TABLE_NAME, values, selection,
                        selectionArgs);
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        if (rowsUpdated != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return rowsUpdated;
    }


    @Override
    public int bulkInsert(Uri uri, ContentValues[] values) {
        final SQLiteDatabase db = mHearthstoneDbHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case CARD:
                db.beginTransaction();
                int returnCount = 0;
                try {
                    for (ContentValues value : values) {
                        long _id = db.insert(HearthstoneContract.CardEntry.TABLE_NAME, null, value);
                        if (_id != -1) {
                            returnCount++;
                        }
                    }
                    db.setTransactionSuccessful();
                } finally {
                    db.endTransaction();
                }
                getContext().getContentResolver().notifyChange(uri, null);
                return returnCount;
            default:
                return super.bulkInsert(uri, values);
        }
    }
}
