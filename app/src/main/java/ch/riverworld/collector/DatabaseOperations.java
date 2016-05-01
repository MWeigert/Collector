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
            Log.d("DOP", "Table authors successfully reseted.");
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
            String msg = "Table directors succesfull reseted.";
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

    // Method to return all entries in friends table. Returns a Cursor with all friends.
    public Cursor getFriends(DatabaseOperations dop) {

        if (debugMode) {
            Log.d("DOP", "Starting to receive all entries from table directors.");
        }

        SQLiteDatabase db;
        db = dop.getReadableDatabase();
        String[] friends = {DatabaseInfo.FRIENDS_FIRSTNAME_COL, DatabaseInfo.FRIENDS_LASTNAME_COL};
        Cursor cur = db.query(DatabaseInfo.FRIENDS_TABLE, friends, null, null, null, null, null);

        return cur;
    }

    // Method to return cursor with one row from friends table.
    public Cursor getFriendsRow(DatabaseOperations dop, String firstName, String lastName, Integer id) {

        SQLiteDatabase db;
        db = dop.getReadableDatabase();
        String[] columns = new String[]{DatabaseInfo.FRIENDS_ID_COL, DatabaseInfo.FRIENDS_FIRSTNAME_COL, DatabaseInfo
                .FRIENDS_LASTNAME_COL};

        if (firstName != null) {
            return db.query(DatabaseInfo.FRIENDS_TABLE, columns, DatabaseInfo.FRIENDS_FIRSTNAME_COL +
                    " like ?", new String[]{firstName + "%"}, null, null, null);
        } else {
            if (lastName != null) {
                return db.query(DatabaseInfo.FRIENDS_TABLE, columns, DatabaseInfo.FRIENDS_LASTNAME_COL +
                        " like ?", new String[]{lastName + "%"}, null, null, null);
            } else {
                return db.query(DatabaseInfo.FRIENDS_TABLE, columns, DatabaseInfo.FRIENDS_ID_COL +
                        " like ?", new String[]{id + "%"}, null, null, null);
            }
        }
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
    // *                                      ITEM TABLE                                          *
    // *                                                                                          *
    // ********************************************************************************************/
    // ********************************************************************************************/
    // *                                   NOT REFACTORED YET                                     *
    // ********************************************************************************************/

    // Method to add a new entry in item table
    public void addItem(DatabaseOperations dop, Item item) {

        SQLiteDatabase db = dop.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(DatabaseInfo.ITEMS_EAN_COL, item.getEAN());
        values.put(DatabaseInfo.ITEMS_TITLE_COL, item.getTitle());
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

    // Method to return all entries in items table. Returns a Cursor with all items.
    public Cursor getItemTitles(DatabaseOperations dop) {

        if (debugMode) {
            Log.d("DATABASE", "Starting --> getItems.");
        }

        SQLiteDatabase db = dop.getReadableDatabase();
        String[] titles = {DatabaseInfo.ITEMS_TITLE_COL};
        Cursor cur = db.query(DatabaseInfo.ITEMS_TABLE, titles, null, null, null, null, null);

        return cur;
    }

    // Method to return all data to one single item. Returns cursor.
    public Cursor getItem(DatabaseOperations dop, String title) {

        if (debugMode) {
            Log.d("DATABASE", "Starting --> getItem.");
        }

        SQLiteDatabase db = dop.getReadableDatabase();
        String[] columns = new String[]{DatabaseInfo.ITEMS_ID_COL, DatabaseInfo.ITEMS_EAN_COL, DatabaseInfo
                .ITEMS_TITLE_COL, DatabaseInfo.ITEMS_MEDIA_TYPE_COL, DatabaseInfo.ITEMS_GENRE_ID_COL, DatabaseInfo
                .ITEMS_LANGUAGE_ID_COL, DatabaseInfo.ITEMS_LAUNCH_YEAR_COL, DatabaseInfo.ITEMS_RENTAL_COL, DatabaseInfo
                .ITEMS_PUBLISHER_ID_COL, DatabaseInfo.ITEMS_AUTHOR_ID_COL, DatabaseInfo.ITEMS_SYSTEM_ID_COL,
                DatabaseInfo.ITEMS_DVD_COL, DatabaseInfo.ITEMS_BLURAY_COL, DatabaseInfo.ITEMS_STUDIO_ID_COL,
                DatabaseInfo.ITEMS_DIRECTOR_ID_COL, DatabaseInfo.ITEMS_PARENTAL_ID_COL};

        Cursor crs = db.query(DatabaseInfo.ITEMS_TABLE, columns, DatabaseInfo.ITEMS_TITLE_COL +
                " like ?", new String[]{title + "%"}, null, null, null);

        if (debugMode) {
            crs.moveToFirst();
            int index = crs.getColumnIndex(DatabaseInfo.ITEMS_ID_COL);
            String msg = "Finished --> getItem. With ID: " + String.valueOf(crs.getInt(index));
            Log.d("DATABASE", msg);
        }
        return crs;
    }

    public int getItemID(DatabaseOperations dop, String title) {

        SQLiteDatabase db = dop.getReadableDatabase();
        String[] columns = new String[]{DatabaseInfo.ITEMS_ID_COL};
        String selection = DatabaseInfo.ITEMS_TITLE_COL + " LIKE ?";
        String args[] = {title};
        Cursor crs = db.query(DatabaseInfo.ITEMS_TABLE, columns, selection, args, null, null, null);
        int index = crs.getColumnIndex(DatabaseInfo.ITEMS_ID_COL);
        crs.moveToFirst();

        if (debugMode) {
            String msg = "Title: " + title + " ID: " + crs.getInt(index);
            Log.d("DATABASE", msg);
        }

        return crs.getInt(index);
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

    // Method to reset complete authors table.
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
            Log.d("DOP", "Table publishers sucessfull reseted.");
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

    // Method to return all entries in studios table. Returns a Cursor with all studios.
    public Cursor getStudios(DatabaseOperations dop) {

        if (debugMode) {
            Log.d("DOP", "Starting to rceive all entries from studios table.");
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
