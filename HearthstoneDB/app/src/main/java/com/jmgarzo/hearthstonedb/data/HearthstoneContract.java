package com.jmgarzo.hearthstonedb.data;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by jmgarzo on 14/08/2016.
 */
public class HearthstoneContract {

    public static final String CONTENT_AUTHORITY = "com.jmgarzo.hearthstonedb";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_CARD = "card";
    public static final String PATH_CARDBACK = "cardback";

    public static final class CardEntry implements BaseColumns{
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_CARD).build();

        public static final String CONTENT_DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_CARD;
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_CARD;

        public static final String TABLE_NAME = "card";

        public static final String COLUMN_CARD_ID = "card_id";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_CARD_SET= "card_set";
        public static final String COLUMN_TYPE ="type";
        public static final String COLUMN_FACTION = "faction";
        public static final String COLUMN_RARITY = "rarity";
        public static final String COLUMN_COST = "cost";
        public static final String COLUMN_ATTACK = "attack";
        public static final String COLUMN_HEALTH = "health";
        public static final String COLUMN_TEXT = "text";
        public static final String COLUMN_FLAVOR = "flavor";
        public static final String COLUMN_ARTIST = "artist";
        public static final String COLUMN_COLLECTIBLE = "collectible";
        public static final String COLUMN_IMG = "img";
        public static final String COLUMN_IMG_GOLD = "img_gold";
        public static final String COLUMN_LOCALE = "locale";
        public static final String COLUMN_MECHANICS_KEY = "mechanics_key";

        public static Uri buildCardEntryUri (long id){
            return ContentUris.withAppendedId(CONTENT_URI,id);
        }

    }

}
