// ********************************************************************************************
// *                                                                                          *
// *                                        SEMESTERARBEIT                                    *
// *                                             ZHAW                                         *
// *                                                                                          *
// * Programmed by: Mathias Weigert                                                           *
// *       Version: 1.00                                                                      *
// *          Year: 2016                                                                      *
// *                                                                                          *
// ********************************************************************************************/

package ch.riverworld.collector;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;

import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class DatabaseOperations extends SQLiteOpenHelper {

    private boolean debugMode = true;

    // Main constructor
    public DatabaseOperations(Context context, boolean debugMode) {
        super(context, DatabaseInfo.DATABASE_NAME, null, DatabaseInfo.DATABASE_VERSION);
        this.debugMode = debugMode;
        if (debugMode) {
            Log.d("DOP", "Finished DatabaseOperations constructor.");
        }
    }

    // Create all tables of database
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DatabaseInfo.CREATE_AUTHORS);
        if (debugMode) {
            Log.d("DOP", "CollectorDB --> table authors created.");
        }
        db.execSQL(DatabaseInfo.CREATE_DIRECTORS);
        if (debugMode) {
            Log.d("DOP", "CollectorDB --> table directors created.");
        }
        db.execSQL(DatabaseInfo.CREATE_FRIENDS);
        if (debugMode) {
            Log.d("DOP", "CollectorDB --> table friends created.");
        }
        db.execSQL(DatabaseInfo.CREATE_GENRES);
        if (debugMode) {
            Log.d("DOP", "CollectorDB --> table genres created.");
        }
        db.execSQL(DatabaseInfo.CREATE_HISTORY);
        if (debugMode) {
            Log.d("DOP", "CollectorDB --> table history created.");
        }
        db.execSQL(DatabaseInfo.CREATE_ITEMS);
        if (debugMode) {
            Log.d("DOP", "CollectorDB --> table items created.");
        }
        db.execSQL(DatabaseInfo.CREATE_LANGUAGES);
        if (debugMode) {
            Log.d("DOP", "CollectorDB --> table languages created.");
        }
        db.execSQL(DatabaseInfo.CREATE_PUBLISHERS);
        if (debugMode) {
            Log.d("DOP", "CollectorDB --> table publishers created.");
        }
        db.execSQL(DatabaseInfo.CREATE_STUDIOS);
        if (debugMode) {
            Log.d("DOP", "CollectorDB --> table studios created.");
        }
        db.execSQL(DatabaseInfo.CREATE_SYSTEMS);
        if (debugMode) {
            Log.d("DOP", "CollectorDB --> table system created.");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Need to insert stuff here if i need a database upgrade.
        // Not used at the moment.
    }

    // Export method of the database
    public void exportDatabaseCSV(DatabaseOperations dop, String whereClause) throws IOException {

        SQLiteDatabase db = dop.getReadableDatabase();
        File exportDir = new File(Environment.getExternalStorageDirectory(), "");
        if (!exportDir.exists()) {
            exportDir.mkdirs();
        }

        File file = new File(exportDir, "collector.csv");
        try {
            file.createNewFile();
            Log.d("DOP", "File: " + file);
            CSVWriter csvWrite = new CSVWriter(new FileWriter(file));
            Cursor curCSV = null;
            if (whereClause != null) {
                curCSV = db.rawQuery(whereClause, null);
            } else {
                curCSV = db.rawQuery("SELECT * FROM " + DatabaseInfo.ITEMS_TABLE, null);
            }
            csvWrite.writeNext(curCSV.getColumnNames());
            while (curCSV.moveToNext()) {
                //Which column you want to export
                String arrStr[] = {curCSV.getString(0), curCSV.getString(1), curCSV.getString(2), curCSV.getString(3)
                        , curCSV.getString(4), curCSV.getString(5), curCSV.getString(6), curCSV.getString(7), curCSV
                        .getString(8), curCSV.getString(9), curCSV.getString(10), curCSV.getString(11), curCSV
                        .getString(12), curCSV.getString(13), curCSV.getString(14), curCSV.getString(15), curCSV
                        .getString(16), curCSV.getString(17)};
                csvWrite.writeNext(arrStr);
            }
            csvWrite.close();
            curCSV.close();
        } catch (Exception sqlEx) {
            Log.e("MainActivity", sqlEx.getMessage(), sqlEx);
        }
    }

    // ********************************************************************************************
    // *                                                                                          *
    // *                           ALL DATABASE OPERATIONS REGARDING                              *
    // *                                          THE                                             *
    // *                                     AUTHORS TABLE                                        *
    // *                                                                                          *
    // ********************************************************************************************/

    // Method to add a new entry in authors table
    public void addAuthor(DatabaseOperations dop, String author) {

        SQLiteDatabase db;
        db = dop.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(DatabaseInfo.AUTHORS_AUTHOR_COL, author);
        db.insert(DatabaseInfo.AUTHORS_TABLE, null, values);

        if (debugMode) {
            Log.d("DOP", "Table authors --> added " + author + ".");
        }
    }

    // Method which returns string with name of given author id.
    public String getAuthor(DatabaseOperations dop, int id) {
        SQLiteDatabase db = dop.getReadableDatabase();
        Cursor crs = db.query(DatabaseInfo.AUTHORS_TABLE, null, DatabaseInfo.AUTHORS_ID_COL +
                " like ?", new String[]{id + "%"}, null, null, null);
        crs.moveToFirst();
        int index = crs.getColumnIndex(DatabaseInfo.AUTHORS_AUTHOR_COL);
        String author = crs.getString(index);
        crs.close();
        return author;
    }

    // Method to return all entries in authors table. Returns a Cursor with all authors.
    public Cursor getAuthors(DatabaseOperations dop) {

        if (debugMode) {
            Log.d("DOP", "Starting to receive all entries from authors table.");
        }

        SQLiteDatabase db;
        db = dop.getReadableDatabase();
        String[] authors = {DatabaseInfo.AUTHORS_AUTHOR_COL};

        return db.query(DatabaseInfo.AUTHORS_TABLE, authors, null, null, null, null, null);
    }

    // Method to return cursor with one row from authors table.
    public Cursor getAuthorRow(DatabaseOperations dop, String author, Integer id) {

        if (debugMode) {
            Log.d("DOP", "Starting to extract row from authors table.");
        }

        SQLiteDatabase db;
        db = dop.getReadableDatabase();
        String[] columns = new String[]{DatabaseInfo.AUTHORS_ID_COL, DatabaseInfo.AUTHORS_AUTHOR_COL};

        if (author != null) {
            return db.query(DatabaseInfo.AUTHORS_TABLE, columns, DatabaseInfo.AUTHORS_AUTHOR_COL +
                    " like ?", new String[]{author + "%"}, null, null, null);
        } else {
            return db.query(DatabaseInfo.AUTHORS_TABLE, columns, DatabaseInfo.AUTHORS_ID_COL +
                    " like ?", new String[]{id + "%"}, null, null, null);
        }
    }

    // Method to delete one entry in authors table. Returns boolean with information regarding success of delete.
    public boolean deleteAuthor(DatabaseOperations dop, int id) {

        SQLiteDatabase db;
        db = dop.getWritableDatabase();
        String selection = DatabaseInfo.AUTHORS_ID_COL + " LIKE ?";
        String args[] = {Integer.toString(id)};

        if (debugMode) {
            Log.d("DOP", "Author with ID " + id + " will deleted from table authors.");
        }
        return db.delete(DatabaseInfo.AUTHORS_TABLE, selection, args) > 0;
    }

    // Method to reset complete authors table.
    public void resetAuthors(DatabaseOperations dop) {

        SQLiteDatabase db;
        db = dop.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + DatabaseInfo.AUTHORS_TABLE);
        db.execSQL(DatabaseInfo.CREATE_AUTHORS);

        String[] authors = DatabaseBase.authorData;
        ContentValues values = new ContentValues();

        for (String author : authors) {
            values.put(DatabaseInfo.AUTHORS_AUTHOR_COL, author);
            db.insert(DatabaseInfo.AUTHORS_TABLE, null, values);
        }

        if (debugMode) {
            Log.d("DOP", "Table authors successfully reset.");
        }
    }

    // ********************************************************************************************
    // *                                                                                          *
    // *                           ALL DATABASE OPERATIONS REGARDING                              *
    // *                                          THE                                             *
    // *                                    DIRECTORS TABLE                                       *
    // *                                                                                          *
    // ********************************************************************************************/

    // Method to add a new entry in directors table
    public void addDirector(DatabaseOperations dop, String director) {

        SQLiteDatabase db;
        db = dop.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(DatabaseInfo.DIRECTORS_DIRECTOR_COL, director);
        db.insert(DatabaseInfo.DIRECTORS_TABLE, null, values);

        if (debugMode) {
            Log.d("DOP", "Table directors --> added " + director + ".");
        }
    }

    // Method which returns string with director name from given director id.
    public String getDirector(DatabaseOperations dop, int id) {
        SQLiteDatabase db = dop.getReadableDatabase();
        Cursor crs = db.query(DatabaseInfo.DIRECTORS_TABLE, null, DatabaseInfo.DIRECTORS_ID_COL + " like ?", new
                String[]{id + "%"}, null, null, null);
        crs.moveToFirst();
        int index = crs.getColumnIndex(DatabaseInfo.DIRECTORS_DIRECTOR_COL);
        String director = crs.getString(index);
        crs.close();

        return director;
    }

    // Method to return all entries in directors table. Returns a Cursor with all authors.
    public Cursor getDirectors(DatabaseOperations dop) {

        if (debugMode) {
            Log.d("DOP", "Starting to receive all entries from directors table.");
        }

        SQLiteDatabase db;
        db = dop.getReadableDatabase();
        String[] directors = {DatabaseInfo.DIRECTORS_DIRECTOR_COL};

        return db.query(DatabaseInfo.DIRECTORS_TABLE, directors, null, null, null, null, null);
    }

    // Method to return cursor with one row from directors table.
    public Cursor getDirectorRow(DatabaseOperations dop, String director, Integer id) {

        SQLiteDatabase db;
        db = dop.getReadableDatabase();
        String[] columns = new String[]{DatabaseInfo.DIRECTORS_ID_COL, DatabaseInfo.DIRECTORS_DIRECTOR_COL};

        if (director != null) {
            return db.query(DatabaseInfo.DIRECTORS_TABLE, columns, DatabaseInfo.DIRECTORS_DIRECTOR_COL +
                    " like ?", new String[]{director + "%"}, null, null, null);
        } else {
            return db.query(DatabaseInfo.DIRECTORS_TABLE, columns, DatabaseInfo.DIRECTORS_ID_COL +
                    " like ?", new String[]{id + "%"}, null, null, null);
        }
    }


    // Method to delete one entry in directors table. Returns boolean with information regarding success of delete.
    public boolean deleteDirector(DatabaseOperations dop, int id) {

        SQLiteDatabase db;
        db = dop.getWritableDatabase();
        String selection = DatabaseInfo.DIRECTORS_ID_COL + " LIKE ?";
        String args[] = {Integer.toString(id)};

        if (debugMode) {
            Log.d("DOP", id + " is deleted from table directors.");
        }
        return db.delete(DatabaseInfo.DIRECTORS_TABLE, selection, args) > 0;
    }

    // Method to reset complete directors table.
    public void resetDirectors(DatabaseOperations dop) {
        SQLiteDatabase db;
        db = dop.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + DatabaseInfo.DIRECTORS_TABLE);
        db.execSQL(DatabaseInfo.CREATE_DIRECTORS);

        String[] directors = DatabaseBase.directorData;
        ContentValues values = new ContentValues();

        for (String director : directors) {
            values.put(DatabaseInfo.DIRECTORS_DIRECTOR_COL, director);
            db.insert(DatabaseInfo.DIRECTORS_TABLE, null, values);
        }

        if (debugMode) {
            String msg = "Table directors successfully reset.";
            Log.d("DOP", msg);
        }
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
        db.insert(DatabaseInfo.FRIENDS_TABLE, null, values);

        if (debugMode) {
            Log.d("DOP", "Table friends --> added " + firstName + " " + lastName + ".");
        }
    }

    // Method which returns a friend object regarding given id.
    public Friend getFriend(DatabaseOperations dop, int id) {
        SQLiteDatabase db = dop.getReadableDatabase();
        String whereClause = DatabaseInfo.FRIENDS_ID_COL + " = ?";
        String[] whereArgs = new String[]{Integer.toString(id)};

        Cursor crs = db.query(DatabaseInfo.FRIENDS_TABLE, null, whereClause, whereArgs, null, null, null);
        crs.moveToFirst();

        int index = crs.getColumnIndex(DatabaseInfo.FRIENDS_FIRSTNAME_COL);
        String first = crs.getString(index);
        index = crs.getColumnIndex(DatabaseInfo.FRIENDS_LASTNAME_COL);
        String last = crs.getString(index);
        crs.close();

        return new Friend(id, first, last);
    }

    // Method to return all entries in friends table. Returns a Cursor with all friends.
    public Cursor getFriends(DatabaseOperations dop) {

        if (debugMode) {
            Log.d("DOP", "Starting to receive all entries from table directors.");
        }

        SQLiteDatabase db;
        db = dop.getReadableDatabase();

        return db.query(DatabaseInfo.FRIENDS_TABLE, null, null, null, null, null, null);
    }

    // Method to delete one entry in friends table. Returns boolean with information regarding success of delete.
    public boolean deleteFriend(DatabaseOperations dop, int id) {

        SQLiteDatabase db;
        db = dop.getWritableDatabase();
        String selection = DatabaseInfo.FRIENDS_ID_COL + " LIKE ?";
        String args[] = {Integer.toString(id)};

        return db.delete(DatabaseInfo.FRIENDS_TABLE, selection, args) > 0;
    }

    // Method to reset complete friends table.
    public void resetFriends(DatabaseOperations dop) {
        SQLiteDatabase db;
        db = dop.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + DatabaseInfo.FRIENDS_TABLE);
        db.execSQL(DatabaseInfo.CREATE_FRIENDS);
    }

    // ********************************************************************************************
    // *                                                                                          *
    // *                           ALL DATABASE OPERATIONS REGARDING                              *
    // *                                          THE                                             *
    // *                                      GENRE TABLE                                         *
    // *                                                                                          *
    // ********************************************************************************************/

    // Method to add a new entry in genres table
    public void addGenre(DatabaseOperations dop, String genre) {

        SQLiteDatabase db;
        db = dop.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(DatabaseInfo.GENRES_GENRE_COL, genre);
        db.insert(DatabaseInfo.GENRES_TABLE, null, values);

        if (debugMode) {
            Log.d("DOP", "Table genre --> added " + genre + ".");
        }
    }

    // Method which return genre name of given genre id.
    public String getGenreName(DatabaseOperations dop, int id) {
        SQLiteDatabase db = dop.getReadableDatabase();
        Cursor crs = db.query(DatabaseInfo.GENRES_TABLE, null, DatabaseInfo.GENRES_ID_COL +
                " like ?", new String[]{id + "%"}, null, null, null);
        crs.moveToFirst();
        int index = crs.getColumnIndex(DatabaseInfo.GENRES_GENRE_COL);
        String name = crs.getString(index);
        crs.close();
        return name;
    }

    // Method to return all entries in genres table. Returns a Cursor with all genres.
    public Cursor getGenres(DatabaseOperations dop) {

        if (debugMode) {
            Log.d("DATABASE", "Starting to receive all entries from genres table.");
        }

        SQLiteDatabase db;
        db = dop.getReadableDatabase();
        String[] genres = {DatabaseInfo.GENRES_GENRE_COL};

        return db.query(DatabaseInfo.GENRES_TABLE, genres, null, null, null, null, null);
    }

    // Method to return Cursor with one row from genres table.
    public Cursor getGenreRow(DatabaseOperations dop, String genre, Integer id) {

        SQLiteDatabase db;
        db = dop.getReadableDatabase();
        String[] columns = new String[]{DatabaseInfo.GENRES_ID_COL, DatabaseInfo.GENRES_GENRE_COL};

        if (genre != null) {
            return db.query(DatabaseInfo.GENRES_TABLE, columns, DatabaseInfo.GENRES_GENRE_COL +
                    " like ?", new String[]{genre + "%"}, null, null, null);
        } else return db.query(DatabaseInfo.GENRES_TABLE, columns, DatabaseInfo.GENRES_ID_COL +
                " like ?", new String[]{id + "%"}, null, null, null);
    }

    // Method to delete one entry in genres table. Returns boolean with information regarding success of delete.
    public boolean deleteGenre(DatabaseOperations dop, int id) {

        SQLiteDatabase db;
        db = dop.getWritableDatabase();
        String selection = DatabaseInfo.GENRES_ID_COL + " LIKE ?";
        String args[] = {Integer.toString(id)};

        return db.delete(DatabaseInfo.GENRES_TABLE, selection, args) > 0;
    }

    // Method to reset complete genres table.
    public void resetGenres(DatabaseOperations dop) {

        SQLiteDatabase db;
        db = dop.getWritableDatabase();

        db.execSQL("DROP TABLE IF EXISTS " + DatabaseInfo.GENRES_TABLE);
        db.execSQL(DatabaseInfo.CREATE_GENRES);

        String[] genres = DatabaseBase.genreData;
        ContentValues values = new ContentValues();

        for (String genre : genres) {
            values.put(DatabaseInfo.GENRES_GENRE_COL, genre);
            db.insert(DatabaseInfo.GENRES_TABLE, null, values);
        }

        if (debugMode) {
            Log.d("DOP", "Table genres successfully reset.");
        }
    }

    // ********************************************************************************************
    // *                                                                                          *
    // *                           ALL DATABASE OPERATIONS REGARDING                              *
    // *                                          THE                                             *
    // *                                    HISTORY TABLE                                         *
    // *                                                                                          *
    // ********************************************************************************************/

    // Method to add a new entry in history table.
    public void addHistory(DatabaseOperations dop, int itemId, int friendId, String start) {

        SQLiteDatabase db = dop.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(DatabaseInfo.HISTORY_ITEM_ID_COL, itemId);
        values.put(DatabaseInfo.HISTORY_FRIEND_ID_COL, friendId);
        values.put(DatabaseInfo.HISTORY_START_COL, start);
        values.put(DatabaseInfo.HISTORY_RETURN_COL, "NULL");

        db.insert(DatabaseInfo.HISTORY_TABLE, null, values);
        if (debugMode) {
            Log.d("DOP", "Added ITEM: " + itemId + " FRIEND: " + friendId + " START: " + start);
        }
    }

    // Method which returns a cursor with all entries in history table.
    public Cursor getHistories(DatabaseOperations dop) {

        SQLiteDatabase db = dop.getReadableDatabase();

        return db.rawQuery("select * from " + DatabaseInfo.HISTORY_TABLE, null);
    }

    // Method which returns open history entry of given item id.
    public Cursor getOpenHistory(DatabaseOperations dop, int itemId) {

        SQLiteDatabase db = dop.getReadableDatabase();
        String whereClause = DatabaseInfo.HISTORY_ITEM_ID_COL + " = ? AND " + DatabaseInfo.HISTORY_RETURN_COL + " = ?";
        String[] whereArgs = new String[]{Integer.toString(itemId), "NULL"};

        return db.query(DatabaseInfo.HISTORY_TABLE, null, whereClause, whereArgs, null, null, null);
    }

    // Method wich returns a cursor with all open history entries.
    public Cursor getOpenHistories(DatabaseOperations dop) {
        SQLiteDatabase db = dop.getReadableDatabase();
        String whereClause = DatabaseInfo.HISTORY_RETURN_COL + " = ?";
        String[] args = new String[]{"NULL"};

        return db.query(DatabaseInfo.HISTORY_TABLE, null, whereClause, args, null, null, null);
    }

    public boolean updateHistory(DatabaseOperations dop, int id, String back) {
        if (debugMode) {
            Log.d("DOP", "Starting update return of item date.");
            Log.d("DOP", "Item ID: " + id + " Date: " + back);
        }

        SQLiteDatabase db = dop.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseInfo.HISTORY_RETURN_COL, back);

        return db.update(DatabaseInfo.HISTORY_TABLE, values, DatabaseInfo.HISTORY_ID_COL + "=" + id, null) > 0;
    }

    // Clears the complete history table.
    public void resetHistory(DatabaseOperations dop) {

        SQLiteDatabase db;
        db = dop.getWritableDatabase();

        db.execSQL("DROP TABLE IF EXISTS " + DatabaseInfo.HISTORY_TABLE);
        db.execSQL(DatabaseInfo.CREATE_HISTORY);

        // Set lent status of all items to false
        ContentValues values = new ContentValues();
        values.put(DatabaseInfo.ITEMS_RENTAL_COL, 0);
        db.update(DatabaseInfo.ITEMS_TABLE, values, null, null);
    }

    // ********************************************************************************************
    // *                                                                                          *
    // *                           ALL DATABASE OPERATIONS REGARDING                              *
    // *                                          THE                                             *
    // *                                      ITEM TABLE                                          *
    // *                                                                                          *
    // ********************************************************************************************/


    // Method to add a new entry in item table
    public void addItem(DatabaseOperations dop, Item item) {

        SQLiteDatabase db = dop.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(DatabaseInfo.ITEMS_EAN_COL, item.getEAN());
        values.put(DatabaseInfo.ITEMS_TITLE_COL, item.getTitle());
        values.put(DatabaseInfo.ITEMS_RATING_COL, item.getRating());
        values.put(DatabaseInfo.ITEMS_MEDIA_TYPE_COL, item.getMediaType());
        values.put(DatabaseInfo.ITEMS_GENRE_ID_COL, item.getGenre_id());
        values.put(DatabaseInfo.ITEMS_LANGUAGE_ID_COL, item.getLanguage_id());
        values.put(DatabaseInfo.ITEMS_LAUNCH_YEAR_COL, item.getYear());
        values.put(DatabaseInfo.ITEMS_RENTAL_COL, item.isLent());
        values.put(DatabaseInfo.ITEMS_PUBLISHER_ID_COL, item.getPublisher_id());
        values.put(DatabaseInfo.ITEMS_AUTHOR_ID_COL, item.getAuthor_id());
        values.put(DatabaseInfo.ITEMS_SYSTEM_ID_COL, item.getSystem_id());
        values.put(DatabaseInfo.ITEMS_DVD_COL, item.isDvd());
        values.put(DatabaseInfo.ITEMS_BLURAY_COL, item.isBluRay());
        values.put(DatabaseInfo.ITEMS_STUDIO_ID_COL, item.getStudio_id());
        values.put(DatabaseInfo.ITEMS_DIRECTOR_ID_COL, item.getDirector_id());
        values.put(DatabaseInfo.ITEMS_PARENTAL_ID_COL, item.getFsk());
        db.insert(DatabaseInfo.ITEMS_TABLE, null, values);

        if (debugMode) {
            Log.d("DATABASE", "Table items --> added one line.");
            String msg = "EAN: " + item.getEAN() + ", Title: " + item.getTitle() + ", MediaType: " + item
                    .getMediaType() + ", " + "Genre: " + item.getGenre_id() + ", Language: " + item.getLanguage_id()
                    + ", Year: " + item.getYear() + "," + " lent: " + item.isLent() + ", Publisher: " + item
                    .getPublisher_id() + ", Author: " + item.getAuthor_id() + ", System: " + item.getSystem_id() + "," +
                    " DVD: " + item.isDvd() + ", BlueRay: " + item.isBluRay() + ", " + "Studio: " + item
                    .getStudio_id() + ", Director: " + item.getDirector_id() + ", Parental: " + item.getFsk();
            Log.d("DATABASE", msg);
        }
    }

    // Method to return a string with item title of given id.
    public String getItemTitle(DatabaseOperations dop, int id) {
        SQLiteDatabase db = dop.getReadableDatabase();

        String whereClause = DatabaseInfo.ITEMS_ID_COL + " = ?";
        String[] whereArgs = new String[]{Integer.toString(id)};

        Cursor crs = db.query(DatabaseInfo.ITEMS_TABLE, null, whereClause, whereArgs, null, null, null);
        crs.moveToFirst();
        int index = crs.getColumnIndex(DatabaseInfo.ITEMS_TITLE_COL);
        String title = crs.getString(index);
        crs.close();

        return title;
    }

    // Method to return all entries in items table. Returns a Cursor with all items.
    public Cursor getItems(DatabaseOperations dop) {

        if (debugMode) {
            Log.d("DATABASE", "Starting --> getItems.");
        }

        SQLiteDatabase db = dop.getReadableDatabase();

        return db.query(DatabaseInfo.ITEMS_TABLE, null, null, null, null, null, null);
    }

    // Method to return all entries in items table. Returns a Cursor with all items.
    public Cursor getFilteredItems(DatabaseOperations dop, String select) {

        if (debugMode) {
            Log.d("DATABASE", "Starting --> getFilteredItems.");
        }

        SQLiteDatabase db = dop.getReadableDatabase();

        return db.rawQuery(select, null);
    }

    // Method to return all data to one single item. Returns cursor.
    public Cursor getItem(DatabaseOperations dop, Integer id) {

        SQLiteDatabase db = dop.getReadableDatabase();

        return db.query(DatabaseInfo.ITEMS_TABLE, null, DatabaseInfo.ITEMS_ID_COL +
                " like ?", new String[]{id + "%"}, null, null, null);
    }

    // Method which updates data of an existing item with given item and id.
    public boolean updateItem(DatabaseOperations dop, Item item, int id) {

        SQLiteDatabase db = dop.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(DatabaseInfo.ITEMS_EAN_COL, item.getEAN());
        values.put(DatabaseInfo.ITEMS_TITLE_COL, item.getTitle());
        values.put(DatabaseInfo.ITEMS_RATING_COL, item.getRating());
        values.put(DatabaseInfo.ITEMS_MEDIA_TYPE_COL, item.getMediaType());
        values.put(DatabaseInfo.ITEMS_GENRE_ID_COL, item.getGenre_id());
        values.put(DatabaseInfo.ITEMS_LANGUAGE_ID_COL, item.getLanguage_id());
        values.put(DatabaseInfo.ITEMS_LAUNCH_YEAR_COL, item.getYear());
        values.put(DatabaseInfo.ITEMS_RENTAL_COL, item.isLent());
        values.put(DatabaseInfo.ITEMS_PUBLISHER_ID_COL, item.getPublisher_id());
        values.put(DatabaseInfo.ITEMS_AUTHOR_ID_COL, item.getAuthor_id());
        values.put(DatabaseInfo.ITEMS_SYSTEM_ID_COL, item.getSystem_id());
        values.put(DatabaseInfo.ITEMS_DVD_COL, item.isDvd());
        values.put(DatabaseInfo.ITEMS_BLURAY_COL, item.isBluRay());
        values.put(DatabaseInfo.ITEMS_STUDIO_ID_COL, item.getStudio_id());
        values.put(DatabaseInfo.ITEMS_DIRECTOR_ID_COL, item.getDirector_id());
        values.put(DatabaseInfo.ITEMS_PARENTAL_ID_COL, item.getFsk());

        if (debugMode) {
            Log.d("DATABASE", "Table items --> added one line.");
            String msg = "ID:" + id + "EAN: " + item.getEAN() + ", Title: " + item.getTitle() + ", MediaType: " + item
                    .getMediaType() + ", " + "Genre: " + item.getGenre_id() + ", Language: " + item.getLanguage_id()
                    + ", Year: " + item.getYear() + "," + " lent: " + item.isLent() + ", Publisher: " + item
                    .getPublisher_id() + ", Author: " + item.getAuthor_id() + ", System: " + item.getSystem_id() + "," +
                    " DVD: " + item.isDvd() + ", BlueRay: " + item.isBluRay() + ", " + "Studio: " + item
                    .getStudio_id() + ", Director: " + item.getDirector_id() + ", Parental: " + item.getFsk();
            Log.d("DATABASE", msg);
        }

        return db.update(DatabaseInfo.ITEMS_TABLE, values, DatabaseInfo.ITEMS_ID_COL + "=" + id, null) > 0;
    }

    // Method which updates the rental status of item with given id and new status.
    public boolean updateItemRentalStatus(DatabaseOperations dop, int id, boolean status) {

        if (debugMode) {
            Log.d("DOP", "Starting update rental status.");
            Log.d("DOP", "Item ID: " + id + " Status: " + status);
        }

        SQLiteDatabase db = dop.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseInfo.ITEMS_RENTAL_COL, status);

        return db.update(DatabaseInfo.ITEMS_TABLE, values, DatabaseInfo.ITEMS_ID_COL + "=" + id, null) > 0;
    }

    // Method to delete item with given id. Returns boolean with information regarding success of delete.
    public boolean deleteItem(DatabaseOperations dop, int id) {

        SQLiteDatabase db;
        db = dop.getWritableDatabase();
        String selection = DatabaseInfo.ITEMS_ID_COL + " LIKE ?";
        String args[] = {Integer.toString(id)};

        return db.delete(DatabaseInfo.ITEMS_TABLE, selection, args) > 0;
    }

    // Method which returns a string with an SQL select statement from given item data
    public String getSelectStatement(Item item) {

        String whereClause = "SELECT " + DatabaseInfo.ITEMS_ID_COL + " FROM " + DatabaseInfo.ITEMS_TABLE + " WHERE ";

        if (item.getEAN() > 0) whereClause += DatabaseInfo.ITEMS_EAN_COL + "=" + Long.toString(item.getEAN()) + " AND ";
        if (item.getTitle().length() > 1)
            whereClause += DatabaseInfo.ITEMS_TITLE_COL + " LIKE'%" + item.getTitle() + "%' AND ";
        if (item.isBook()) whereClause += DatabaseInfo.ITEMS_MEDIA_TYPE_COL + "='Book' AND ";
        if (item.isMovie()) whereClause += DatabaseInfo.ITEMS_MEDIA_TYPE_COL + "='Movie' AND ";
        if (item.isGame()) whereClause += DatabaseInfo.ITEMS_MEDIA_TYPE_COL + "='Game' AND ";
        if (!item.getGenre().equals("GENRE")) whereClause += DatabaseInfo.ITEMS_GENRE_ID_COL + "=" + Integer.toString
                (item.getGenre_id()) + " AND ";
        if (!item.getLanguage().equals("LANGUAGE")) whereClause += DatabaseInfo.ITEMS_LANGUAGE_ID_COL + "=" + Integer
                .toString(item.getLanguage_id()) + " AND ";
        if (item.getYear() < 2020)
            whereClause += DatabaseInfo.ITEMS_LAUNCH_YEAR_COL + "='" + Integer.toString(item.getYear())
                    + "' AND ";
        if (!item.getPublisher().equals("PUBLISHER")) whereClause += DatabaseInfo.ITEMS_PUBLISHER_ID_COL + "=" + Integer
                .toString(item.getPublisher_id()) + " AND ";
        if (!item.getAuthor().equals("AUTHOR")) whereClause += DatabaseInfo.ITEMS_AUTHOR_ID_COL + "=" + Integer
                .toString(item.getAuthor_id()) + " AND ";
        if (!item.getSystem().equals("SYSTEM")) whereClause += DatabaseInfo.ITEMS_SYSTEM_ID_COL + "=" + Integer
                .toString(item.getSystem_id()) + " AND ";
        if (item.isDvd()) whereClause += DatabaseInfo.ITEMS_DVD_COL + "=1 AND ";
        if (item.isBluRay()) whereClause += DatabaseInfo.ITEMS_BLURAY_COL + "=1 AND ";
        if (!item.getStudio().equals("STUDIO")) whereClause += DatabaseInfo.ITEMS_STUDIO_ID_COL + "=" + Integer
                .toString(item.getStudio_id()) + " AND ";
        if (!item.getDirector().equals("DIRECTOR")) whereClause += DatabaseInfo.ITEMS_DIRECTOR_ID_COL + "=" + Integer
                .toString(item.getDirector_id()) + " AND ";
        if (item.getFsk() != 42)
            whereClause += DatabaseInfo.ITEMS_PARENTAL_ID_COL + "=" + Integer.toString(item.getFsk()) + " " +
                    "AND ";
        whereClause = whereClause.substring(0, whereClause.length() - 4) + ";";
        return whereClause;
    }


    // ********************************************************************************************
    // *                                                                                          *
    // *                           ALL DATABASE OPERATIONS REGARDING                              *
    // *                                          THE                                             *
    // *                                    LANGUAGE TABLE                                        *
    // *                                                                                          *
    // ********************************************************************************************/

    // Method to add a new entry in language table
    public void addLanguage(DatabaseOperations dop, String language) {

        SQLiteDatabase db;
        db = dop.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(DatabaseInfo.LANGUAGES_LANGUAGE_COL, language);
        db.insert(DatabaseInfo.LANGUAGES_TABLE, null, values);

        if (debugMode) {
            Log.d("DOP", "Table language --> added " + language + ".");
        }
    }

    // Method to return string with name of given language id.
    public String getLanguageName(DatabaseOperations dop, int id) {
        SQLiteDatabase db = dop.getReadableDatabase();
        Cursor crs = db.query(DatabaseInfo.LANGUAGES_TABLE, null, DatabaseInfo.LANGUAGES_ID_COL +
                " like ?", new String[]{id + "%"}, null, null, null);
        crs.moveToFirst();
        int index = crs.getColumnIndex(DatabaseInfo.LANGUAGES_LANGUAGE_COL);
        String language = crs.getString(index);
        crs.close();

        return language;
    }

    // Method to return all entries in language table. Returns a Cursor with all languages.
    public Cursor getLanguages(DatabaseOperations dop) {

        if (debugMode) {
            Log.d("DATABASE", "Starting to receive all entries from language table.");
        }

        SQLiteDatabase db;
        db = dop.getReadableDatabase();
        String[] languages = {DatabaseInfo.LANGUAGES_LANGUAGE_COL};

        return db.query(DatabaseInfo.LANGUAGES_TABLE, languages, null, null, null, null, null);
    }

    // Method to return Cursor with one row from languages table.
    public Cursor getLanguageRow(DatabaseOperations dop, String language, Integer id) {
        if (debugMode) {
            Log.d("DOP", "Language: " + language + " ID: " + id);
        }

        SQLiteDatabase db;
        db = dop.getReadableDatabase();
        String[] columns = new String[]{DatabaseInfo.LANGUAGES_ID_COL, DatabaseInfo.LANGUAGES_LANGUAGE_COL};

        if (language != null) {
            return db.query(DatabaseInfo.LANGUAGES_TABLE, columns, DatabaseInfo.LANGUAGES_LANGUAGE_COL +
                    " like ?", new String[]{language + "%"}, null, null, null);
        } else return db.query(DatabaseInfo.LANGUAGES_TABLE, columns, DatabaseInfo.LANGUAGES_ID_COL +
                " like ?", new String[]{id + "%"}, null, null, null);
    }

    // Method to delete one entry in language table. Returns boolean with information regarding success of delete.
    public boolean deleteLanguage(DatabaseOperations dop, int id) {

        SQLiteDatabase db;
        db = dop.getWritableDatabase();
        String selection = DatabaseInfo.LANGUAGES_ID_COL + " LIKE ?";
        String args[] = {Integer.toString(id)};

        return db.delete(DatabaseInfo.LANGUAGES_TABLE, selection, args) > 0;
    }

    //Method to reset complete languages table.
    public void resetLanguage(DatabaseOperations dop) {

        SQLiteDatabase db;
        db = dop.getWritableDatabase();

        db.execSQL("DROP TABLE IF EXISTS " + DatabaseInfo.LANGUAGES_TABLE);
        db.execSQL(DatabaseInfo.CREATE_LANGUAGES);

        String[] languages = DatabaseBase.languageData;
        ContentValues values = new ContentValues();

        for (String language : languages) {
            values.put(DatabaseInfo.LANGUAGES_LANGUAGE_COL, language);
            db.insert(DatabaseInfo.LANGUAGES_TABLE, null, values);
        }

        if (debugMode) {
            Log.d("DOP", "Table languages successfully reset.");
        }
    }

    // ********************************************************************************************
    // *                                                                                          *
    // *                           ALL DATABASE OPERATIONS REGARDING                              *
    // *                                          THE                                             *
    // *                                   PUBLISHERS TABLE                                       *
    // *                                                                                          *
    // ********************************************************************************************/

    // Method to add a new entry in publishers table
    public void addPublisher(DatabaseOperations dop, String publisher) {

        SQLiteDatabase db;
        db = dop.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(DatabaseInfo.PUBLISHERS_PUBLISHER_COL, publisher);
        db.insert(DatabaseInfo.PUBLISHERS_TABLE, null, values);

        if (debugMode) {
            Log.d("DOP", "Table publishers --> added " + publisher + ".");
        }
    }

    // Method which returns string of publisher name from given id.
    public String getPublisher(DatabaseOperations dop, int id) {
        SQLiteDatabase db = dop.getReadableDatabase();
        Cursor crs = db.query(DatabaseInfo.PUBLISHERS_TABLE, null, DatabaseInfo.PUBLISHERS_ID_COL +
                " like ?", new String[]{id + "%"}, null, null, null);
        crs.moveToFirst();
        int index = crs.getColumnIndex(DatabaseInfo.PUBLISHERS_PUBLISHER_COL);
        String publisher = crs.getString(index);
        crs.close();
        return publisher;
    }

    // Method to return all entries in publishers table. Returns a Cursor with all publishers.
    public Cursor getPublishers(DatabaseOperations dop) {

        if (debugMode) {
            Log.d("DOP", "Starting receiving all entries from publishers table.");
        }

        SQLiteDatabase db;
        db = dop.getReadableDatabase();
        String[] publishers = {DatabaseInfo.PUBLISHERS_PUBLISHER_COL};

        return db.query(DatabaseInfo.PUBLISHERS_TABLE, publishers, null, null, null, null, null);
    }

    // Method to return cursor with one row from publishers table.
    public Cursor getPublisherRow(DatabaseOperations dop, String publisher, Integer id) {

        SQLiteDatabase db;
        db = dop.getReadableDatabase();
        String[] columns = new String[]{DatabaseInfo.PUBLISHERS_ID_COL, DatabaseInfo.PUBLISHERS_PUBLISHER_COL};

        if (publisher != null) {
            return db.query(DatabaseInfo.PUBLISHERS_TABLE, columns, DatabaseInfo.PUBLISHERS_PUBLISHER_COL +
                    " like ?", new String[]{publisher + "%"}, null, null, null);
        } else return db.query(DatabaseInfo.PUBLISHERS_TABLE, columns, DatabaseInfo.PUBLISHERS_ID_COL +
                " like ?", new String[]{id + "%"}, null, null, null);
    }

    // Method to delete one entry in publishers table. Returns boolean with information regarding success of delete.
    public boolean deletePublisher(DatabaseOperations dop, int id) {

        SQLiteDatabase db;
        db = dop.getWritableDatabase();
        String selection = DatabaseInfo.PUBLISHERS_ID_COL + " LIKE ?";
        String args[] = {Integer.toString(id)};

        return db.delete(DatabaseInfo.PUBLISHERS_TABLE, selection, args) > 0;
    }

    // Method to reset complete publishers table.
    public void resetPublishers(DatabaseOperations dop) {

        SQLiteDatabase db;
        db = dop.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + DatabaseInfo.PUBLISHERS_TABLE);
        db.execSQL(DatabaseInfo.CREATE_PUBLISHERS);

        String[] publishers = DatabaseBase.publisherData;
        ContentValues values = new ContentValues();

        for (String publisher : publishers) {
            values.put(DatabaseInfo.PUBLISHERS_PUBLISHER_COL, publisher);
            db.insert(DatabaseInfo.PUBLISHERS_TABLE, null, values);
        }

        if (debugMode) {
            Log.d("DOP", "Table publishers successfully reset.");
        }
    }

    // ********************************************************************************************
    // *                                                                                          *
    // *                           ALL DATABASE OPERATIONS REGARDING                              *
    // *                                          THE                                             *
    // *                                     STUDIOS TABLE                                        *
    // *                                                                                          *
    // ********************************************************************************************/

    // Method to add a new entry in studios table
    public void addStudio(DatabaseOperations dop, String studio) {

        SQLiteDatabase db;
        db = dop.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(DatabaseInfo.STUDIOS_STUDIO_COL, studio);
        db.insert(DatabaseInfo.STUDIOS_TABLE, null, values);

        if (debugMode) {
            Log.d("DOP", "Table studios --> added " + studio + ".");
        }
    }

    // Method which returns a string with name of given studio id.
    public String getStudio(DatabaseOperations dop, int id) {
        SQLiteDatabase db = dop.getReadableDatabase();
        Cursor crs = db.query(DatabaseInfo.STUDIOS_TABLE, null, DatabaseInfo.STUDIOS_ID_COL + " like ?", new
                String[]{id + "%"}, null, null, null);
        crs.moveToFirst();
        int index = crs.getColumnIndex(DatabaseInfo.STUDIOS_STUDIO_COL);
        String studio = crs.getString(index);
        crs.close();

        return studio;
    }

    // Method to return all entries in studios table. Returns a Cursor with all studios.
    public Cursor getStudios(DatabaseOperations dop) {

        if (debugMode) {
            Log.d("DOP", "Starting to receive all entries from studios table.");
        }

        SQLiteDatabase db;
        db = dop.getReadableDatabase();
        String[] studios = {DatabaseInfo.STUDIOS_STUDIO_COL};

        return db.query(DatabaseInfo.STUDIOS_TABLE, studios, null, null, null, null, null);
    }

    // Method to return cursor with one row from studios table.
    public Cursor getStudioRow(DatabaseOperations dop, String studio, Integer id) {

        SQLiteDatabase db;
        db = dop.getReadableDatabase();
        String[] columns = new String[]{DatabaseInfo.STUDIOS_ID_COL, DatabaseInfo.STUDIOS_STUDIO_COL};

        if (studio != null) {
            return db.query(DatabaseInfo.STUDIOS_TABLE, columns, DatabaseInfo.STUDIOS_STUDIO_COL +
                    " like ?", new String[]{studio + "%"}, null, null, null);
        } else {
            return db.query(DatabaseInfo.STUDIOS_TABLE, columns, DatabaseInfo.STUDIOS_ID_COL +
                    " like ?", new String[]{id + "%"}, null, null, null);
        }
    }

    // Method to delete one entry in studios table. Returns boolean with information regarding success of delete.
    public boolean deleteStudio(DatabaseOperations dop, int id) {

        SQLiteDatabase db;
        db = dop.getWritableDatabase();
        String selection = DatabaseInfo.STUDIOS_ID_COL + " LIKE ?";
        String args[] = {Integer.toString(id)};

        return db.delete(DatabaseInfo.STUDIOS_TABLE, selection, args) > 0;
    }

    // Method to reset complete studios table.
    public void resetStudios(DatabaseOperations dop) {

        SQLiteDatabase db;
        db = dop.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + DatabaseInfo.STUDIOS_TABLE);
        db.execSQL(DatabaseInfo.CREATE_STUDIOS);

        String[] studios = DatabaseBase.studioData;
        ContentValues values = new ContentValues();

        for (String studio : studios) {
            values.put(DatabaseInfo.STUDIOS_STUDIO_COL, studio);
            db.insert(DatabaseInfo.STUDIOS_TABLE, null, values);
        }

        if (debugMode) {
            Log.d("DOP", "Table studios successfully reset.");
        }
    }

    // ********************************************************************************************
    // *                                                                                          *
    // *                           ALL DATABASE OPERATIONS REGARDING                              *
    // *                                          THE                                             *
    // *                                      SYSTEM TABLE                                        *
    // *                                                                                          *
    // ********************************************************************************************/

    // Method to add a new entry in systems table
    public void addSystem(DatabaseOperations dop, String system) {

        SQLiteDatabase db;
        db = dop.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(DatabaseInfo.SYSTEMS_SYSTEM_COL, system);
        db.insert(DatabaseInfo.SYSTEMS_TABLE, null, values);

        if (debugMode) {
            Log.d("DOP", "Table systems --> added " + system + ".");
        }
    }

    public String getSystem(DatabaseOperations dop, int id) {
        SQLiteDatabase db = dop.getReadableDatabase();
        Cursor crs = db.query(DatabaseInfo.SYSTEMS_TABLE, null, DatabaseInfo.SYSTEMS_ID_COL + " like ?", new
                String[]{id + "%"}, null, null, null);
        crs.moveToFirst();
        int index = crs.getColumnIndex(DatabaseInfo.SYSTEMS_SYSTEM_COL);
        String system = crs.getString(index);
        crs.close();

        return system;
    }

    // Method to return all entries in systems table. Returns a Cursor with all systems.
    public Cursor getSystems(DatabaseOperations dop) {

        if (debugMode) {
            Log.d("DOP", "Starting to receive all entries from systems table.");
        }

        SQLiteDatabase db;
        db = dop.getReadableDatabase();
        String[] systems = {DatabaseInfo.SYSTEMS_SYSTEM_COL};

        return db.query(DatabaseInfo.SYSTEMS_TABLE, systems, null, null, null, null, null);
    }

    // Method to return cursor with one row from systems table.
    public Cursor getSystemRow(DatabaseOperations dop, String system, Integer id) {

        SQLiteDatabase db;
        db = dop.getReadableDatabase();
        String[] columns = new String[]{DatabaseInfo.SYSTEMS_ID_COL, DatabaseInfo.SYSTEMS_SYSTEM_COL};

        if (system != null) {
            return db.query(DatabaseInfo.SYSTEMS_TABLE, columns, DatabaseInfo.SYSTEMS_SYSTEM_COL +
                    " like ?", new String[]{system + "%"}, null, null, null);
        } else return db.query(DatabaseInfo.SYSTEMS_TABLE, columns, DatabaseInfo.SYSTEMS_ID_COL +
                " like ?", new String[]{id + "%"}, null, null, null);
    }

    // Method to delete one entry in system table. Returns boolean with information regarding success of delete.
    public boolean deleteSystem(DatabaseOperations dop, int id) {

        SQLiteDatabase db;
        db = dop.getWritableDatabase();
        String selection = DatabaseInfo.SYSTEMS_ID_COL + " LIKE ?";
        String args[] = {Integer.toString(id)};

        return db.delete(DatabaseInfo.SYSTEMS_TABLE, selection, args) > 0;
    }

    // Method to reset complete systems table.
    public void resetSystems(DatabaseOperations dop) {

        SQLiteDatabase db;
        db = dop.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + DatabaseInfo.SYSTEMS_TABLE);
        db.execSQL(DatabaseInfo.CREATE_SYSTEMS);

        String[] systems = DatabaseBase.SystemData;
        ContentValues values = new ContentValues();

        for (String system : systems) {
            values.put(DatabaseInfo.SYSTEMS_SYSTEM_COL, system);
            db.insert(DatabaseInfo.SYSTEMS_TABLE, null, values);
        }

        if (debugMode) {
            Log.d("DOP", "Table systems successfully reset.");
        }
    }
}
