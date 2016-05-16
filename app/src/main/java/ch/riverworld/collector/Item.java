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

import android.content.Context;
import android.database.Cursor;

public class Item {
    //private boolean debugMode = true;

    private int id = 0;
    private long ean = 0;
    private String title = "";
    private Float rating;
    private boolean book = false;
    private boolean movie = false;
    private boolean game = false;
    private String mediaType = "";
    private int genre_id = 0;
    private String genre = "";
    private int language_id = 0;
    private String language = "";
    private int year = 0;
    private boolean lent = false;
    private int publisher_id = 0;
    private String publisher = "";
    private int author_id = 0;
    private String author = "";
    private int system_id = 0;
    private String system = "";
    private boolean dvd = false;
    private boolean bluRay = false;
    private int studio_id = 0;
    private String studio = "";
    private int director_id = 0;
    private String director = "";
    private int fsk = 0;

    // Empty constructor
    public Item() {

    }

    // Standard constructor
    public Item(Context ctx, int id) {
        DatabaseOperations db = new DatabaseOperations(ctx, false);
        this.id = id;

        int index;
        Cursor crs = db.getItem(db, id);
        crs.moveToFirst();
        // EAN
        index = crs.getColumnIndex(DatabaseInfo.ITEMS_EAN_COL);
        this.ean = crs.getLong(index);
        // Title
        index = crs.getColumnIndex(DatabaseInfo.ITEMS_TITLE_COL);
        this.title = crs.getString(index);
        // Rating
        index = crs.getColumnIndex(DatabaseInfo.ITEMS_RATING_COL);
        this.rating = crs.getFloat(index);
        // MediaType
        index = crs.getColumnIndex(DatabaseInfo.ITEMS_MEDIA_TYPE_COL);
        this.mediaType = crs.getString(index);
        this.book = false;
        this.movie = false;
        this.game = false;
        switch (mediaType) {
            case "Book":
                this.book = true;
                break;
            case "Movie":
                this.movie = true;
                break;
            case "Game":
                this.game = true;
                break;
        }
        // Genre
        index = crs.getColumnIndex(DatabaseInfo.ITEMS_GENRE_ID_COL);
        this.genre_id = crs.getInt(index);
        this.genre = db.getGenreName(db, genre_id);

        // Language
        index = crs.getColumnIndex(DatabaseInfo.ITEMS_LANGUAGE_ID_COL);
        this.language_id = crs.getInt(index);
        this.language = db.getLanguageName(db, language_id);

        // Year
        index = crs.getColumnIndex(DatabaseInfo.ITEMS_LAUNCH_YEAR_COL);
        this.year = Integer.valueOf(crs.getString(index));
        index = crs.getColumnIndex(DatabaseInfo.ITEMS_RENTAL_COL);
        switch (crs.getInt(index)) {
            case 1:
                this.lent = true;
                break;
            default:
                this.lent = false;
                break;
        }
        // Publisher
        index = crs.getColumnIndex(DatabaseInfo.ITEMS_PUBLISHER_ID_COL);
        this.publisher_id = crs.getInt(index);
        this.publisher = db.getPublisher(db, publisher_id);

        // Author
        index = crs.getColumnIndex(DatabaseInfo.ITEMS_AUTHOR_ID_COL);
        this.author_id = crs.getInt(index);
        this.author = db.getAuthor(db, author_id);

        // Systems
        index = crs.getColumnIndex(DatabaseInfo.ITEMS_SYSTEM_ID_COL);
        this.system_id = crs.getInt(index);
        this.system = db.getSystem(db, system_id);

        // DVD
        index = crs.getColumnIndex(DatabaseInfo.ITEMS_DVD_COL);
        switch (crs.getInt(index)) {
            case 1:
                this.dvd = true;
                break;
            default:
                this.dvd = false;
                break;
        }
        // BluRay
        index = crs.getColumnIndex(DatabaseInfo.ITEMS_BLURAY_COL);
        switch (crs.getInt(index)) {
            case 1:
                this.bluRay = true;
                break;
            default:
                this.bluRay = false;
                break;
        }
        // Studio
        index = crs.getColumnIndex(DatabaseInfo.ITEMS_STUDIO_ID_COL);
        this.studio_id = crs.getInt(index);
        this.studio = db.getStudio(db, studio_id);

        // Director
        index = crs.getColumnIndex(DatabaseInfo.ITEMS_DIRECTOR_ID_COL);
        this.director_id = crs.getInt(index);
        director = db.getDirector(db, director_id);

        // Parental
        index = crs.getColumnIndex(DatabaseInfo.ITEMS_PARENTAL_ID_COL);
        this.fsk = crs.getInt(index);
        crs.close();
    }

    public void resetItem() {

        id = 0;
        ean = 0;
        title = "";
        rating = 0f;
        book = false;
        movie = false;
        game = false;
        mediaType = "";
        genre_id = 0;
        genre = "";
        language_id = 0;
        language = "";
        year = 0;
        lent = false;
        publisher_id = 0;
        publisher = "";
        author_id = 0;
        author = "";
        system_id = 0;
        system = "";
        dvd = false;
        bluRay = false;
        studio_id = 0;
        studio = "";
        director_id = 0;
        director = "";
        fsk = 0;
    }

    @Override
    public String toString() {
        return title;
    }

    // ********************************************************************************************
    // *                                                                                          *
    // *                                       GETTER                                             *
    // *                                                                                          *
    // ********************************************************************************************/

    public int getId() {
        return id;
    }

    public long getEAN() {
        return ean;
    }

    public String getTitle() {
        return title;
    }

    public Float getRating() {
        return rating;
    }

    public boolean isBook() {
        return book;
    }

    public boolean isMovie() {
        return movie;
    }

    public boolean isGame() {
        return game;
    }

    public String getMediaType() {
        return mediaType;
    }

    public int getGenre_id() {
        return genre_id;
    }

    public String getGenre() {
        return genre;
    }

    public int getLanguage_id() {
        return language_id;
    }

    public String getLanguage() {
        return language;
    }

    public int getYear() {
        return year;
    }

    public boolean isLent() {
        return lent;
    }

    public int getPublisher_id() {
        return publisher_id;
    }

    public String getPublisher() {
        return publisher;
    }

    public int getAuthor_id() {
        return author_id;
    }

    public String getAuthor() {
        return author;
    }

    public int getSystem_id() {
        return system_id;
    }

    public String getSystem() {
        return system;
    }

    public boolean isDvd() {
        return dvd;
    }

    public boolean isBluRay() {
        return bluRay;
    }

    public int getStudio_id() {
        return studio_id;
    }

    public String getStudio() {
        return studio;
    }

    public int getDirector_id() {
        return director_id;
    }

    public String getDirector() {
        return director;
    }

    public int getFsk() {
        return fsk;
    }

    // ********************************************************************************************
    // *                                                                                          *
    // *                                       SETTER                                             *
    // *                                                                                          *
    // ********************************************************************************************/


    public void setId(int id) {
        this.id = id;
    }

    public void setEAN(long ean) {
        this.ean = ean;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public void setBook(boolean book) {
        this.book = book;
    }

    public void setMovie(boolean movie) {
        this.movie = movie;
    }

    public void setGame(boolean game) {
        this.game = game;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public void setGenre_id(int genre_id) {
        this.genre_id = genre_id;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setLanguage_id(int language_id) {
        this.language_id = language_id;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setLent(boolean lent) {
        this.lent = lent;
    }

    public void setPublisher_id(int publisher_id) {
        this.publisher_id = publisher_id;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setSystem_id(int system_id) {
        this.system_id = system_id;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public void setDvd(boolean dvd) {
        this.dvd = dvd;
    }

    public void setBluRay(boolean bluRay) {
        this.bluRay = bluRay;
    }

    public void setStudio_id(int studio_id) {
        this.studio_id = studio_id;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public void setDirector_id(int director_id) {
        this.director_id = director_id;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setFsk(int fsk) {
        this.fsk = fsk;
    }
}
