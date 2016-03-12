package ch.riverworld.collector;

import android.provider.BaseColumns;

/**
 * Created by Harleaquin on 12.03.2016.
 */

public class DatabaseInfo {

    // Database name and version
    public static final String DATABASE_NAME = "CollectorDB";
    public static final int DATABASE_VERSION = 1;

    // Table name and column informations from friends table
    public static final String TABLE_FRIENDS = "friends";
    public static final String COL_FRIEND_ID = "FRIEND_ID";
    public static final String COL_FRIEND_FIRSTNAME = "FIRST_NAME";
    public static final String COL_FRIEND_LASTNAME = "LAST_NAME";

    // Table name and column informations from items table
    public static final String TABLE_ITEMS = "items";

    // Table name and column informations from history table
    public static final String TABLE_HISTORY = "history";

    public DatabaseInfo(){
    }

}
