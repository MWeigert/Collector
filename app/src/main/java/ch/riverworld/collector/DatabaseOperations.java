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

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DatabaseInfo.CREATE_FRIENDS);
        if (debugMode) {
            Log.d("DATABASE", "CollectorDB --> table Friends created.");
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

        values.put(DatabaseInfo.COL_FRIEND_FIRSTNAME, firstName);
        values.put(DatabaseInfo.COL_FRIEND_LASTNAME, lastName);

        if (debugMode) {
            db.insert(DatabaseInfo.TABLE_FRIENDS, null, values);
            Log.d("DATABASE", "Table friends --> added one line.");
        }
    }

    // Method to return all entries in friends table. Returns a Cursor with all friends.
    public Cursor getFriends(DatabaseOperations dop) {

        if (debugMode) {
            Log.d("DATABASE", "Starting --> getFriends.");
        }

        SQLiteDatabase db = dop.getReadableDatabase();
        String[] friends = {DatabaseInfo.COL_FRIEND_FIRSTNAME, DatabaseInfo.COL_FRIEND_LASTNAME};
        Cursor cur = db.query(DatabaseInfo.TABLE_FRIENDS, friends, null, null, null, null, null);

        return cur;
    }

    //Method to delete one entry in friends table. Returns boolean with information regarding success of delete.
    public boolean deleteFriend(DatabaseOperations dop, String friend) {

        SQLiteDatabase db = dop.getWritableDatabase();
        String selection = DatabaseInfo.COL_FRIEND_LASTNAME + " LIKE ?";
        String coloumns[] = {DatabaseInfo.COL_FRIEND_ID};
        String args[] = {friend};

        db.delete(DatabaseInfo.TABLE_FRIENDS, selection, args);
        if (debugMode) {
            String msg = friend + " is deleted from table friends.";
            Log.d("DATABASE", msg);
            return true;
        } else return false;
    }

    //Method to reset complete friends table.
    public void resetFriends(DatabaseOperations dop) {
        SQLiteDatabase db = dop.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + DatabaseInfo.TABLE_FRIENDS);
        db.execSQL(DatabaseInfo.CREATE_FRIENDS);
    }
}
