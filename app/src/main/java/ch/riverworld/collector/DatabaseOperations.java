package ch.riverworld.collector;

//*****************************************************************
//*                                                               *
//* Programmed by: Mathias Weigert                                *
//*       Version: 0.01                                           *
//*                                                               *
//*****************************************************************

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseOperations extends SQLiteOpenHelper {

    private static final String DATABASE_CREATE = "create table " + DatabaseInfo.TABLE_FRIENDS +
            " (" + DatabaseInfo.COL_FRIEND_ID + " integer primary key autoincrement, " + DatabaseInfo.COL_FRIEND_FIRSTNAME +
            " text, " + DatabaseInfo.COL_FRIEND_LASTNAME + " text not null);";

    // Main constructor
    public DatabaseOperations(Context context) {
        super(context, DatabaseInfo.DATABASE_NAME, null, DatabaseInfo.DATABASE_VERSION);
        Log.d("DATABASE", "ch.riverworld.collector.DatabaseOperations: CollectorDB created.");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
        Log.d("DATABASE", "CollectorDB --> table Friends created.");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    /********************************************************************************************
     *                                                                                          *
     *                           ALL DATABASE OPERATIONS REGARDING                              *
     *                                          THE                                             *
     *                                     FRIENDS TABLE                                        *
     *                                                                                          *
     ********************************************************************************************/

    // Method to add a new entry in friends table
    public void addFriend(DatabaseOperations dop, String firstName, String lastName) {

        SQLiteDatabase db = dop.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(DatabaseInfo.COL_FRIEND_FIRSTNAME, firstName);
        values.put(DatabaseInfo.COL_FRIEND_LASTNAME, lastName);

        db.insert(DatabaseInfo.TABLE_FRIENDS, null, values);
        Log.d("DATABASE", "Table friends --> added one line.");
    }

    // Method to return all entries in friends table
    public Cursor getFriends(DatabaseOperations dop) {

        Log.d("DATABASE", "Starting --> getFriends.");
        SQLiteDatabase db = dop.getReadableDatabase();
        String [] friends = {DatabaseInfo.COL_FRIEND_FIRSTNAME, DatabaseInfo.COL_FRIEND_LASTNAME};
        Cursor cur = db.query(DatabaseInfo.TABLE_FRIENDS, friends, null, null, null, null, null);

        return cur;
    }

    //Method to delete one entry in friends tabe
    public boolean deleteFriend(DatabaseOperations dop, String friend) {

        SQLiteDatabase db = dop.getWritableDatabase();
        String selection = DatabaseInfo.COL_FRIEND_LASTNAME + " LIKE ?";
        String coloumns[] = {DatabaseInfo.COL_FRIEND_ID};
        String args[] = {friend};

        Cursor cur = db.query(DatabaseInfo.TABLE_FRIENDS, coloumns, selection, args, null, null, null);

        return true;
    }
}
