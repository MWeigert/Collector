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
        db.execSQL(DatabaseInfo.CREATE_AUTHORS);
        if (debugMode) {
            Log.d("DATABASE", "CollectorDB --> table authors created.");
        }
        db.execSQL(DatabaseInfo.CREATE_DIRECTORS);
        if (debugMode) {
            Log.d("DATABASE", "CollectorDB --> table directors created.");
        }
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
        db.execSQL(DatabaseInfo.CREATE_PUBLISHERS);
        if (debugMode) {
            Log.d("DATABASE", "CollectorDB --> table publishers created.");
        }
        db.execSQL(DatabaseInfo.CREATE_STUDIOS);
        if (debugMode) {
            Log.d("DATABASE", "CollectorDB --> table studios created.");
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
    // *                                     AUTHORS TABLE                                        *
    // *                                                                                          *
    // ********************************************************************************************/

    // Method to add a new entry in authors table
    public void addAuthor(DatabaseOperations dop, String author) {

        SQLiteDatabase db = dop.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(DatabaseInfo.AUTHORS_AUTHOR_COL, author);
        db.insert(DatabaseInfo.AUTHORS_TABLE, null, values);

        if (debugMode) {
            Log.d("DATABASE", "Table authors --> added one line.");
        }
    }

    // Method to return all entries in authors table. Returns a Cursor with all authors.
    public Cursor getAuthors(DatabaseOperations dop) {

        if (debugMode) {
            Log.d("DATABASE", "Starting --> getAuthors.");
        }

        SQLiteDatabase db = dop.getReadableDatabase();
        String[] authors = {DatabaseInfo.AUTHORS_AUTHOR_COL};
        Cursor cur = db.query(DatabaseInfo.AUTHORS_TABLE, authors, null, null, null, null, null);

        return cur;
    }

    //Method to delete one entry in authors table. Returns boolean with information regarding success of delete.
    public boolean deleteAuthor(DatabaseOperations dop, String author) {

        SQLiteDatabase db = dop.getWritableDatabase();
        String selection = DatabaseInfo.AUTHORS_AUTHOR_COL + " LIKE ?";
        String coloumns[] = {DatabaseInfo.AUTHORS_ID_COL};
        String args[] = {author};

        db.delete(DatabaseInfo.AUTHORS_TABLE, selection, args);
        if (debugMode) {
            String msg = author + " is deleted from table authors.";
            Log.d("DATABASE", msg);
            return true;
        } else return false;
    }

    //Method to reset complete authors table.
    public void resetAuthors(DatabaseOperations dop) {
        SQLiteDatabase db = dop.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + DatabaseInfo.AUTHORS_TABLE);
        db.execSQL(DatabaseInfo.CREATE_AUTHORS);

        String[] authors = DatabaseBase.authorData;
        ContentValues values = new ContentValues();

        for (String author : authors) {
            values.put(DatabaseInfo.AUTHORS_AUTHOR_COL, author);
            db.insert(DatabaseInfo.AUTHORS_TABLE, null, values);
        }

        if (debugMode) {
            String msg = "Table authors sucessfull reseted.";
            Log.d("DATABASE", msg);
        }
    }

    // Method to return cursor with ID of given author.
    public Cursor getAuthorID(DatabaseOperations dop, String author) {
        SQLiteDatabase db = dop.getReadableDatabase();
        String[] columns = new String[]{DatabaseInfo.AUTHORS_ID_COL, DatabaseInfo.AUTHORS_AUTHOR_COL};

        Cursor crs = db.query(DatabaseInfo.AUTHORS_TABLE, columns, DatabaseInfo.AUTHORS_AUTHOR_COL +
                " like ?", new String[]{author + "%"}, null, null, null);
        return crs;
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

        SQLiteDatabase db = dop.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(DatabaseInfo.DIRECTORS_DIRECTOR_COL, director);
        db.insert(DatabaseInfo.DIRECTORS_TABLE, null, values);

        if (debugMode) {
            Log.d("DATABASE", "Table directors --> added one line.");
        }
    }

    // Method to return all entries in directors table. Returns a Cursor with all authors.
    public Cursor getDirectors(DatabaseOperations dop) {

        if (debugMode) {
            Log.d("DATABASE", "Starting --> getDirectors.");
        }

        SQLiteDatabase db = dop.getReadableDatabase();
        String[] directors = {DatabaseInfo.DIRECTORS_DIRECTOR_COL};
        Cursor cur = db.query(DatabaseInfo.DIRECTORS_TABLE, directors, null, null, null, null, null);

        return cur;
    }

    //Method to delete one entry in directors table. Returns boolean with information regarding success of delete.
    public boolean deleteDirector(DatabaseOperations dop, String director) {

        SQLiteDatabase db = dop.getWritableDatabase();
        String selection = DatabaseInfo.DIRECTORS_DIRECTOR_COL + " LIKE ?";
        String coloumns[] = {DatabaseInfo.DIRECTORS_ID_COL};
        String args[] = {director};

        db.delete(DatabaseInfo.DIRECTORS_TABLE, selection, args);
        if (debugMode) {
            String msg = director + " is deleted from table directors.";
            Log.d("DATABASE", msg);
            return true;
        } else return false;
    }

    //Method to reset complete directors table.
    public void resetDirectors(DatabaseOperations dop) {
        SQLiteDatabase db = dop.getWritableDatabase();
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
            Log.d("DATABASE", msg);
        }
    }

    // Method to return cursor with ID of given director.
    public Cursor getDirectorID(DatabaseOperations dop, String director) {
        SQLiteDatabase db = dop.getReadableDatabase();
        String[] columns = new String[]{DatabaseInfo.DIRECTORS_ID_COL, DatabaseInfo.DIRECTORS_DIRECTOR_COL};

        Cursor crs = db.query(DatabaseInfo.DIRECTORS_TABLE, columns, DatabaseInfo.DIRECTORS_DIRECTOR_COL +
                " like ?", new String[]{director + "%"}, null, null, null);
        return crs;
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
    // *                                      GENRE TABLE                                         *
    // *                                                                                          *
    // ********************************************************************************************/

    // Method to add a new entry in genres table
    public void addGenre(DatabaseOperations dop, String genre) {

        SQLiteDatabase db = dop.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(DatabaseInfo.GENRES_GENRE_COL, genre);
        db.insert(DatabaseInfo.GENRES_TABLE, null, values);

        if (debugMode) {
            Log.d("DATABASE", "Table genre --> added one line.");
        }
    }

    // Method to return all entries in genrestable. Returns a Cursor with all genres.
    public Cursor getGenres(DatabaseOperations dop) {

        if (debugMode) {
            Log.d("DATABASE", "Starting --> getGenres.");
        }

        SQLiteDatabase db = dop.getReadableDatabase();
        String[] genres = {DatabaseInfo.GENRES_GENRE_COL};
        //String[] genres={DatabaseInfo.GENRES_ID_COL};
        Cursor cur = db.query(DatabaseInfo.GENRES_TABLE, genres, null, null, null, null, null);

        return cur;
    }

    //Method to delete one entry in genres table. Returns boolean with information regarding success of delete.
    public boolean deleteGenre(DatabaseOperations dop, String genre) {

        SQLiteDatabase db = dop.getWritableDatabase();
        String selection = DatabaseInfo.GENRES_GENRE_COL + " LIKE ?";
        String args[] = {genre};

        db.delete(DatabaseInfo.GENRES_TABLE, selection, args);
        if (debugMode) {
            String msg = genre + " is deleted from table genres.";
            Log.d("DATABASE", msg);
            return true;
        } else return false;
    }

    //Method to reset complete genres table.
    public void resetGenres(DatabaseOperations dop) {
        SQLiteDatabase db = dop.getWritableDatabase();

        db.execSQL("DROP TABLE IF EXISTS " + DatabaseInfo.GENRES_TABLE);
        db.execSQL(DatabaseInfo.CREATE_GENRES);

        String[] genres = DatabaseBase.genreData;
        ContentValues values = new ContentValues();

        for (String genre : genres) {
            values.put(DatabaseInfo.GENRES_GENRE_COL, genre);
            db.insert(DatabaseInfo.GENRES_TABLE, null, values);
        }

        if (debugMode) {
            String msg = "Table genres sucessfull reseted.";
            Log.d("DATABASE", msg);
        }
    }

    // Method to return Cursor with ID of given genre.
    public Cursor getGenreID(DatabaseOperations dop, String genre) {
        SQLiteDatabase db = dop.getReadableDatabase();
        String[] columns = new String[]{DatabaseInfo.GENRES_ID_COL, DatabaseInfo.GENRES_GENRE_COL};

        Cursor crs = db.query(DatabaseInfo.GENRES_TABLE, columns, DatabaseInfo.GENRES_GENRE_COL +
                " like ?", new String[]{genre + "%"}, null, null, null);
        return crs;
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
        values.put(DatabaseInfo.ITEMS_MEDIA_TYPE_COL, item.getMediaType());
        values.put(DatabaseInfo.ITEMS_GENRE_ID_COL, item.getGenre_id());
        values.put(DatabaseInfo.ITEMS_LANGUAGE_ID_COL, item.getLanguage_id());
        values.put(DatabaseInfo.ITEMS_LAUNCH_COL, item.getYear());
        values.put(DatabaseInfo.ITEMS_RENTAL_COL, item.isLent());
        values.put(DatabaseInfo.ITEMS_PUBLISHER_COL, item.getPublisher_id());
        values.put(DatabaseInfo.ITEMS_AUTHOR_COL, item.getAuthor_id());
        values.put(DatabaseInfo.ITEMS_SYSTEM_ID_COL, item.getSystem_id());
        values.put(DatabaseInfo.ITEMS_DVD_COL, item.isDvd());
        values.put(DatabaseInfo.ITEMS_BLUERAY_COL, item.isBlueRay());
        values.put(DatabaseInfo.ITEMS_STUDIO_COL, item.getStudio_id());
        values.put(DatabaseInfo.ITEMS_DIRECTOR_COL, item.getDirector_id());
        values.put(DatabaseInfo.ITEMS_PARENTAL_ID_COL, item.getFsk());
        db.insert(DatabaseInfo.ITEMS_TABLE, null, values);

        if (debugMode) {
            Log.d("DATABASE", "Table items --> added one line.");
            String msg = "EAN: " + item.getEAN() + ", Title: " + item.getTitle() + ", MediaType: " + item
                    .getMediaType() + ", " + "Genre: " + item.getGenre_id() + ", Language: " + item.getLanguage_id()
                    + ", Year: " + item.getYear() + "," + " lent: " + item.isLent() + ", Publisher: " + item
                    .getPublisher_id() + ", Author: " + item.getAuthor_id() + ", System: " + item.getSystem_id() + "," +
                    " DVD: " + item.isDvd() + ", BlueRay: " + item.isBlueRay() + ", " + "Studio: " + item
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

    // Method to return all data to one single item. Returns one item.
    public Item getItem(DatabaseOperations dop, String title) {
        Item item = new Item();
        Integer index;

        if (debugMode) {
            Log.d("DATABASE", "Starting --> getItem.");
        }

        SQLiteDatabase db = dop.getReadableDatabase();
        String[] columns = new String[]{DatabaseInfo.ITEMS_EAN_COL, DatabaseInfo.ITEMS_TITLE_COL, DatabaseInfo
                .ITEMS_MEDIA_TYPE_COL, DatabaseInfo.ITEMS_GENRE_ID_COL, DatabaseInfo.ITEMS_LANGUAGE_ID_COL,
                DatabaseInfo.ITEMS_LAUNCH_COL, DatabaseInfo.ITEMS_RENTAL_COL, DatabaseInfo.ITEMS_PUBLISHER_COL,
                DatabaseInfo.ITEMS_AUTHOR_COL, DatabaseInfo.ITEMS_SYSTEM_ID_COL, DatabaseInfo.ITEMS_DVD_COL,
                DatabaseInfo.ITEMS_BLUERAY_COL, DatabaseInfo.ITEMS_STUDIO_COL, DatabaseInfo.ITEMS_DIRECTOR_COL,
                DatabaseInfo.ITEMS_PARENTAL_ID_COL};

        Cursor crs = db.query(DatabaseInfo.ITEMS_TABLE, columns, DatabaseInfo.ITEMS_TITLE_COL +
                " like ?", new String[]{title + "%"}, null, null, null);

        index=crs.getColumnIndex(DatabaseInfo.ITEMS_EAN_COL);
        item.setEAN(crs.getLong(index));
        index=crs.getColumnIndex(DatabaseInfo.ITEMS_TITLE_COL);
        item.setTitle(crs.getString(index));
        index=crs.getColumnIndex(DatabaseInfo.ITEMS_MEDIA_TYPE_COL);
        switch (crs.getString(index)){
            case "Book":
                item.setBook(true);
                break;
            case "Movie":
                item.setMovie(true);
                break;
            case  "Game":
                item.setGame(true);
                break;
        }
        index=crs.getColumnIndex(DatabaseInfo.ITEMS_GENRE_ID_COL);
        item.setGenre_id(crs.getInt(index));
        index=crs.getColumnIndex(DatabaseInfo.ITEMS_LANGUAGE_ID_COL);
        item.setLanguage_id(crs.getInt(index));

        if (debugMode) {
            Log.d("DATABASE", "Finished --> getItem.");
        }
        return item;
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

        SQLiteDatabase db = dop.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(DatabaseInfo.LANGUAGE_LANGUAGE_COL, language);
        db.insert(DatabaseInfo.LANGUAGE_TABLE, null, values);

        if (debugMode) {
            Log.d("DATABASE", "Table language --> added one line.");
        }
    }

    // Method to return all entries in language table. Returns a Cursor with all languages.
    public Cursor getLanguages(DatabaseOperations dop) {

        if (debugMode) {
            Log.d("DATABASE", "Starting --> getLanguages.");
        }

        SQLiteDatabase db = dop.getReadableDatabase();
        String[] languages = {DatabaseInfo.LANGUAGE_LANGUAGE_COL};
        Cursor cur = db.query(DatabaseInfo.LANGUAGE_TABLE, languages, null, null, null, null, null);

        return cur;
    }

    //Method to delete one entry in language table. Returns boolean with information regarding success of delete.
    public boolean deleteLanguage(DatabaseOperations dop, String language) {

        SQLiteDatabase db = dop.getWritableDatabase();
        String selection = DatabaseInfo.LANGUAGE_LANGUAGE_COL + " LIKE ?";
        String args[] = {language};

        db.delete(DatabaseInfo.LANGUAGE_TABLE, selection, args);
        if (debugMode) {
            String msg = language + " is deleted from table languages.";
            Log.d("DATABASE", msg);
            return true;
        } else return false;
    }

    //Method to reset complete languages table.
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

    // Method to return cursor with ID of given language.
    public Cursor getLanguageID(DatabaseOperations dop, String language) {
        SQLiteDatabase db = dop.getReadableDatabase();
        String[] columns = new String[]{DatabaseInfo.LANGUAGE_ID_COL, DatabaseInfo.LANGUAGE_LANGUAGE_COL};

        Cursor crs = db.query(DatabaseInfo.LANGUAGE_TABLE, columns, DatabaseInfo.LANGUAGE_LANGUAGE_COL +
                " like ?", new String[]{language + "%"}, null, null, null);
        return crs;
    }

    // ********************************************************************************************
    // *                                                                                          *
    // *                           ALL DATABASE OPERATIONS REGARDING                              *
    // *                                          THE                                             *
    // *                                    PARENTAL TABLE                                        *
    // *                                                                                          *
    // ********************************************************************************************/

    // Method to add a new entry in parental table
    public void addParental(DatabaseOperations dop, String parental) {

        SQLiteDatabase db = dop.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(DatabaseInfo.PARENTAL_PARENTAL_COL, parental);
        db.insert(DatabaseInfo.PARENTAL_TABLE, null, values);

        if (debugMode) {
            Log.d("DATABASE", "Table parental --> added one line.");
        }
    }

    // Method to return all entries in parental table. Returns a Cursor with all parental items.
    public Cursor getParental(DatabaseOperations dop) {

        if (debugMode) {
            Log.d("DATABASE", "Starting --> getParental.");
        }

        SQLiteDatabase db = dop.getReadableDatabase();
        String[] parental = {DatabaseInfo.PARENTAL_PARENTAL_COL};
        Cursor cur = db.query(DatabaseInfo.PARENTAL_TABLE, parental, null, null, null, null, null);

        return cur;
    }

    //Method to delete one entry in parental table. Returns boolean with information regarding success of delete.
    public boolean deleteParental(DatabaseOperations dop, String parental) {

        SQLiteDatabase db = dop.getWritableDatabase();
        String selection = DatabaseInfo.PARENTAL_PARENTAL_COL + " LIKE ?";
        String args[] = {parental};

        db.delete(DatabaseInfo.PARENTAL_TABLE, selection, args);
        if (debugMode) {
            String msg = parental + " is deleted from table parental.";
            Log.d("DATABASE", msg);
            return true;
        } else return false;
    }

    //Method to reset complete parental table.
    public void resetParental(DatabaseOperations dop) {
        SQLiteDatabase db = dop.getWritableDatabase();

        db.execSQL("DROP TABLE IF EXISTS " + DatabaseInfo.PARENTAL_TABLE);
        db.execSQL(DatabaseInfo.CREATE_PARENTAL);

        String[] parentals = DatabaseBase.parentalData;
        ContentValues values = new ContentValues();

        for (String parental : parentals) {
            values.put(DatabaseInfo.PARENTAL_PARENTAL_COL, parental);
            db.insert(DatabaseInfo.PARENTAL_TABLE, null, values);
        }

        if (debugMode) {
            String msg = "Table parental sucessfull reseted.";
            Log.d("DATABASE", msg);
        }
    }

    // ********************************************************************************************
    // *                                                                                          *
    // *                           ALL DATABASE OPERATIONS REGARDING                              *
    // *                                          THE                                             *
    // *                                     PUBLISHERS TABLE                                        *
    // *                                                                                          *
    // ********************************************************************************************/

    // Method to add a new entry in publishers table
    public void addPublisher(DatabaseOperations dop, String publisher) {

        SQLiteDatabase db = dop.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(DatabaseInfo.PUBLISHERS_PUBLISHER_COL, publisher);
        db.insert(DatabaseInfo.PUBLISHERS_TABLE, null, values);

        if (debugMode) {
            Log.d("DATABASE", "Table publishers --> added one line.");
        }
    }

    // Method to return all entries in publishers table. Returns a Cursor with all publishers.
    public Cursor getPublishers(DatabaseOperations dop) {

        if (debugMode) {
            Log.d("DATABASE", "Starting --> getPublishers.");
        }

        SQLiteDatabase db = dop.getReadableDatabase();
        String[] publishers = {DatabaseInfo.PUBLISHERS_PUBLISHER_COL};
        Cursor cur = db.query(DatabaseInfo.PUBLISHERS_TABLE, publishers, null, null, null, null, null);

        return cur;
    }

    //Method to delete one entry in publishers table. Returns boolean with information regarding success of delete.
    public boolean deletePublisher(DatabaseOperations dop, String publisher) {

        SQLiteDatabase db = dop.getWritableDatabase();
        String selection = DatabaseInfo.PUBLISHERS_PUBLISHER_COL + " LIKE ?";
        String coloumns[] = {DatabaseInfo.PUBLISHERS_ID_COL};
        String args[] = {publisher};

        db.delete(DatabaseInfo.PUBLISHERS_TABLE, selection, args);
        if (debugMode) {
            String msg = publisher + " is deleted from table publishers.";
            Log.d("DATABASE", msg);
            return true;
        } else return false;
    }

    //Method to reset complete authors table.
    public void resetPublishers(DatabaseOperations dop) {
        SQLiteDatabase db = dop.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + DatabaseInfo.PUBLISHERS_TABLE);
        db.execSQL(DatabaseInfo.CREATE_PUBLISHERS);

        String[] publishers = DatabaseBase.publisherData;
        ContentValues values = new ContentValues();

        for (String publisher : publishers) {
            values.put(DatabaseInfo.PUBLISHERS_PUBLISHER_COL, publisher);
            db.insert(DatabaseInfo.PUBLISHERS_TABLE, null, values);
        }

        if (debugMode) {
            String msg = "Table publishers sucessfull reseted.";
            Log.d("DATABASE", msg);
        }
    }

    // Method to return cursor with ID of given publisher.
    public Cursor getPublisherID(DatabaseOperations dop, String publisher) {
        SQLiteDatabase db = dop.getReadableDatabase();
        String[] columns = new String[]{DatabaseInfo.PUBLISHERS_ID_COL, DatabaseInfo.PUBLISHERS_PUBLISHER_COL};

        Cursor crs = db.query(DatabaseInfo.PUBLISHERS_TABLE, columns, DatabaseInfo.PUBLISHERS_PUBLISHER_COL +
                " like ?", new String[]{publisher + "%"}, null, null, null);
        return crs;
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

        SQLiteDatabase db = dop.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(DatabaseInfo.STUDIOS_STUDIO_COL, studio);
        db.insert(DatabaseInfo.STUDIOS_TABLE, null, values);

        if (debugMode) {
            Log.d("DATABASE", "Table studios --> added one line.");
        }
    }

    // Method to return all entries in studios table. Returns a Cursor with all studios.
    public Cursor getStudios(DatabaseOperations dop) {

        if (debugMode) {
            Log.d("DATABASE", "Starting --> getStudios.");
        }

        SQLiteDatabase db = dop.getReadableDatabase();
        String[] studios = {DatabaseInfo.STUDIOS_STUDIO_COL};
        Cursor cur = db.query(DatabaseInfo.STUDIOS_TABLE, studios, null, null, null, null, null);

        return cur;
    }

    //Method to delete one entry in studios table. Returns boolean with information regarding success of delete.
    public boolean deleteStudio(DatabaseOperations dop, String studio) {

        SQLiteDatabase db = dop.getWritableDatabase();
        String selection = DatabaseInfo.STUDIOS_STUDIO_COL + " LIKE ?";
        String coloumns[] = {DatabaseInfo.STUDIOS_ID_COL};
        String args[] = {studio};

        db.delete(DatabaseInfo.STUDIOS_TABLE, selection, args);
        if (debugMode) {
            String msg = studio + " is deleted from table studios.";
            Log.d("DATABASE", msg);
            return true;
        } else return false;
    }

    //Method to reset complete studios table.
    public void resetStudios(DatabaseOperations dop) {
        SQLiteDatabase db = dop.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + DatabaseInfo.STUDIOS_TABLE);
        db.execSQL(DatabaseInfo.CREATE_STUDIOS);

        String[] studios = DatabaseBase.studioData;
        ContentValues values = new ContentValues();

        for (String studio : studios) {
            values.put(DatabaseInfo.STUDIOS_STUDIO_COL, studio);
            db.insert(DatabaseInfo.STUDIOS_TABLE, null, values);
        }

        if (debugMode) {
            String msg = "Table studios sucessfull reseted.";
            Log.d("DATABASE", msg);
        }
    }

    // Method to return cursor with ID of given studio.
    public Cursor getStudioID(DatabaseOperations dop, String studio) {
        SQLiteDatabase db = dop.getReadableDatabase();
        String[] columns = new String[]{DatabaseInfo.STUDIOS_ID_COL, DatabaseInfo.STUDIOS_STUDIO_COL};

        Cursor crs = db.query(DatabaseInfo.STUDIOS_TABLE, columns, DatabaseInfo.STUDIOS_STUDIO_COL +
                " like ?", new String[]{studio + "%"}, null, null, null);
        return crs;
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

        SQLiteDatabase db = dop.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(DatabaseInfo.SYSTEMS_SYSTEM_COL, system);
        db.insert(DatabaseInfo.SYSTEMS_TABLE, null, values);

        if (debugMode) {
            Log.d("DATABASE", "Table systems --> added one line.");
        }
    }

    // Method to return all entries in systems table. Returns a Cursor with all systems.
    public Cursor getSystems(DatabaseOperations dop) {

        if (debugMode) {
            Log.d("DATABASE", "Starting --> getSystems.");
        }

        SQLiteDatabase db = dop.getReadableDatabase();
        String[] systems = {DatabaseInfo.SYSTEMS_SYSTEM_COL};
        Cursor cur = db.query(DatabaseInfo.SYSTEMS_TABLE, systems, null, null, null, null, null);

        return cur;
    }

    //Method to delete one entry in system table. Returns boolean with information regarding success of delete.
    public boolean deleteSystem(DatabaseOperations dop, String system) {

        SQLiteDatabase db = dop.getWritableDatabase();
        String selection = DatabaseInfo.SYSTEMS_SYSTEM_COL + " LIKE ?";
        String args[] = {system};

        db.delete(DatabaseInfo.SYSTEMS_TABLE, selection, args);
        if (debugMode) {
            String msg = system + " is deleted from table systems.";
            Log.d("DATABASE", msg);
            return true;
        } else return false;
    }

    //Method to reset complete systems table.
    public void resetSystems(DatabaseOperations dop) {
        SQLiteDatabase db = dop.getWritableDatabase();

        db.execSQL("DROP TABLE IF EXISTS " + DatabaseInfo.SYSTEMS_TABLE);
        db.execSQL(DatabaseInfo.CREATE_SYSTEMS);

        String[] systems = DatabaseBase.SystemData;
        ContentValues values = new ContentValues();

        for (String system : systems) {
            values.put(DatabaseInfo.SYSTEMS_SYSTEM_COL, system);
            db.insert(DatabaseInfo.SYSTEMS_TABLE, null, values);
        }

        if (debugMode) {
            String msg = "Table systems sucessfull reseted.";
            Log.d("DATABASE", msg);
        }
    }

    // Method to return cursor with ID of given system.
    public Cursor getSystemID(DatabaseOperations dop, String system) {
        SQLiteDatabase db = dop.getReadableDatabase();
        String[] columns = new String[]{DatabaseInfo.SYSTEMS_ID_COL, DatabaseInfo.SYSTEMS_SYSTEM_COL};

        Cursor crs = db.query(DatabaseInfo.SYSTEMS_TABLE, columns, DatabaseInfo.SYSTEMS_SYSTEM_COL +
                " like ?", new String[]{system + "%"}, null, null, null);
        return crs;
    }
}
