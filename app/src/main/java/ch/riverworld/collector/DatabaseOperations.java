// ********************************************************************************************
// *                                                                                          *
// *                                        SEMESTERARBEIT                                    *
// *                                             ZHAW                                         *
// *                                                                                          *
// * Programmed by: Mathias Weigert                                                           *
// *       Version: 0.02                                                                      *
// *          Year: 2016                                                                      *
// *                                                                                          *
// ********************************************************************************************/

package ch.riverworld.collector;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseOperations extends SQLiteOpenHelper {

    private boolean debugMode = false;

    // Main constructor
    public DatabaseOperations(Context context, boolean debugMode) {
        super(context, DatabaseInfo.DATABASE_NAME, null, DatabaseInfo.DATABASE_VERSION);
        this.debugMode = debugMode;
        if (debugMode) {
            Log.d("DATABASE", "ch.riverworld.collector.DatabaseOperations: CollectorDB created.");
        }
    }

    // Create all tables of database
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DatabaseInfo.CREATE_FRIENDS);
        if (debugMode) {
            Log.d("DATABASE", "CollectorDB --> table friends created.");
        }
        db.execSQL(DatabaseInfo.CREATE_GENRES);
        if (debugMode) {
            Log.d("DATABASE", "CollectorDB --> table genres created.");
        }
        db.execSQL(DatabaseInfo.CREATE_HISTORY);
        if (debugMode) {
            Log.d("DATABASE", "CollectorDB --> table history created.");
        }
        db.execSQL(DatabaseInfo.CREATE_ITEMS);
        if (debugMode) {
            Log.d("DATABASE", "CollectorDB --> table items created.");
        }
        db.execSQL(DatabaseInfo.CREATE_LANGUAGE);
        if (debugMode) {
            Log.d("DATABASE", "CollectorDB --> table language created.");
        }
        db.execSQL(DatabaseInfo.CREATE_PARENTAL);
        if (debugMode) {
            Log.d("DATABASE", "CollectorDB --> table parental created.");
        }
        db.execSQL(DatabaseInfo.CREATE_SYSTEMS);
        if (debugMode) {
            Log.d("DATABASE", "CollectorDB --> table system created.");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Need to insert stuff here if i need a database upgrade
    }


    // ********************************************************************************************
    // *                                                                                          *
    // *                           ALL DATABASE OPERATIONS REGARDING                              *
    // *                                          THE                                             *
    // *                                     FRIENDS TABLE                                        *
    // *                                                                                          *
    // ********************************************************************************************/

    // Method to add a new entry in friends table
    public void addFriend(DatabaseOperations dop, String firstName, String lastName) {

        SQLiteDatabase db = dop.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(DatabaseInfo.FRIENDS_FIRSTNAME_COL, firstName);
        values.put(DatabaseInfo.FRIENDS_LASTNAME_COL, lastName);

        if (debugMode) {
            db.insert(DatabaseInfo.FRIENDS_TABLE, null, values);
            Log.d("DATABASE", "Table friends --> added one line.");
        }
    }

    // Method to return all entries in friends table. Returns a Cursor with all friends.
    public Cursor getFriends(DatabaseOperations dop) {

        if (debugMode) {
            Log.d("DATABASE", "Starting --> getFriends.");
        }

        SQLiteDatabase db = dop.getReadableDatabase();
        String[] friends = {DatabaseInfo.FRIENDS_FIRSTNAME_COL, DatabaseInfo.FRIENDS_LASTNAME_COL};
        Cursor cur = db.query(DatabaseInfo.FRIENDS_TABLE, friends, null, null, null, null, null);

        return cur;
    }

    //Method to delete one entry in friends table. Returns boolean with information regarding success of delete.
    public boolean deleteFriend(DatabaseOperations dop, String friend) {

        SQLiteDatabase db = dop.getWritableDatabase();
        String selection = DatabaseInfo.FRIENDS_LASTNAME_COL + " LIKE ?";
        String coloumns[] = {DatabaseInfo.FRIENDS_ID_COL};
        String args[] = {friend};

        db.delete(DatabaseInfo.FRIENDS_TABLE, selection, args);
        if (debugMode) {
            String msg = friend + " is deleted from table friends.";
            Log.d("DATABASE", msg);
            return true;
        } else return false;
    }

    //Method to reset complete friends table.
    public void resetFriends(DatabaseOperations dop) {
        SQLiteDatabase db = dop.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + DatabaseInfo.FRIENDS_TABLE);
        db.execSQL(DatabaseInfo.CREATE_FRIENDS);
    }

    // ********************************************************************************************
    // *                                                                                          *
    // *                           ALL DATABASE OPERATIONS REGARDING                              *
    // *                                          THE                                             *
    // *                                    LANGUAGE TABLE                                        *
    // *                                                                                          *
    // ********************************************************************************************/

    // Method to return all entries in friends table. Returns a Cursor with all friends.
    public Cursor getLanguages(DatabaseOperations dop) {

        if (debugMode) {
            Log.d("DATABASE", "Starting --> getFriends.");
        }

        SQLiteDatabase db = dop.getReadableDatabase();
        String[] languages = {DatabaseInfo.LANGUAGE_LANGUAGE_COL};
        Cursor cur = db.query(DatabaseInfo.LANGUAGE_TABLE, languages, null, null, null, null, null);

        return cur;
    }

    //Method to reset complete friends table.
    public void resetLanguage(DatabaseOperations dop) {
        SQLiteDatabase db = dop.getWritableDatabase();

        db.execSQL("DROP TABLE IF EXISTS " + DatabaseInfo.LANGUAGE_TABLE);
        db.execSQL(DatabaseInfo.CREATE_LANGUAGE);

        String[] languages = DatabaseBase.languageData;
        ContentValues values = new ContentValues();

        for (String language : languages) {
            values.put(DatabaseInfo.LANGUAGE_LANGUAGE_COL, language);
            db.insert(DatabaseInfo.LANGUAGE_TABLE, null, values);
        }

        if (debugMode) {
            String msg = "Table languages sucessfull reseted.";
            Log.d("DATABASE", msg);
        }
    }
}
