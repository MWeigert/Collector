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


import android.content.Context;
import android.database.Cursor;


public class Item {

    private int id;
    private long ean;
    private String title;
    private boolean book;
    private boolean movie;
    private boolean game;
    private String mediaType;
    private int genre_id;
    private String genre;
    private int language_id;
    private String language;
    private int year;
    private boolean lent;
    private int publisher_id;
    private String publisher;
    private int author_id;
    private String author;
    private int system_id;
    private String system;
    private boolean dvd;
    private boolean blueRay;
    private int studio_id;
    private String studio;
    private int director_id;
    private String director;
    private int fsk;

    private Context ctx;

    // Empty Constructor
    public Item(Context ctx) {
        this.ctx=ctx;
    }

    // Put data from one single item in object.
    public void putItem(Cursor crs) {
        DatabaseOperations db = new DatabaseOperations(ctx, false);

        int index;

        crs.moveToFirst();

        index = crs.getColumnIndex(DatabaseInfo.ITEMS_ID_COL);
        id = crs.getInt(index);
        index = crs.getColumnIndex(DatabaseInfo.ITEMS_EAN_COL);
        ean = crs.getLong(index);
        index = crs.getColumnIndex(DatabaseInfo.ITEMS_TITLE_COL);
        title = crs.getString(index);
        index = crs.getColumnIndex(DatabaseInfo.ITEMS_MEDIA_TYPE_COL);
        mediaType = crs.getString(index);
        book = false;
        movie = false;
        game = false;
        switch (mediaType) {
            case "Book":
                book = true;
                break;
            case "Movie":
                movie = true;
                break;
            case "Game":
                game = true;
                break;
        }
        index = crs.getColumnIndex(DatabaseInfo.ITEMS_GENRE_ID_COL);
        genre_id = crs.getInt(index);
        // CHECK FOR GENRE
        index = crs.getColumnIndex(DatabaseInfo.ITEMS_LANGUAGE_ID_COL);
        language_id = crs.getInt(index);
        // CHECK FOR LANGUAGE
        crs = db.getLanguage(db, language_id);
        if (crs != null) {
            index = crs.getColumnIndex(DatabaseInfo.LANGUAGE_LANGUAGE_COL);
            //NULLPOINTER IN NEXT LINE
            language=crs.getString(index);
        }
        index = crs.getColumnIndex(DatabaseInfo.ITEMS_LAUNCH_COL);
        year = Integer.valueOf(crs.getString(index));
        index = crs.getColumnIndex(DatabaseInfo.ITEMS_RENTAL_COL);
        switch (crs.getInt(index)) {
            case 1:
                lent = true;
                break;
            default:
                lent = false;
                break;
        }
        index = crs.getColumnIndex(DatabaseInfo.ITEMS_PUBLISHER_ID_COL);
        publisher_id = crs.getInt(index);
        // CHECK FOR PUBLISHER
        index = crs.getColumnIndex(DatabaseInfo.ITEMS_AUTHOR_ID_COL);
        author_id = crs.getInt(index);
        // CHECK FOR AUTHOR
        index = crs.getColumnIndex(DatabaseInfo.ITEMS_SYSTEM_ID_COL);
        system_id = crs.getInt(index);
        // CHECK FOR SYSTEM
        index = crs.getColumnIndex(DatabaseInfo.ITEMS_DVD_COL);
        switch (crs.getInt(index)) {
            case 1:
                dvd = true;
                break;
            default:
                dvd = false;
                break;
        }
        index = crs.getColumnIndex(DatabaseInfo.ITEMS_BLUERAY_COL);
        switch (crs.getInt(index)) {
            case 1:
                blueRay = true;
                break;
            default:
                blueRay = false;
                break;
        }
        index = crs.getColumnIndex(DatabaseInfo.ITEMS_STUDIO_ID_COL);
        studio_id = crs.getInt(index);
        // CHECK FOR STUDIO
        index = crs.getColumnIndex(DatabaseInfo.ITEMS_DIRECTOR_ID_COL);
        director_id = crs.getInt(index);
        // CHECK FOR DIRECTOR
        index = crs.getColumnIndex(DatabaseInfo.ITEMS_PARENTAL_ID_COL);
        fsk = crs.getInt(index);
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

    public boolean isBlueRay() {
        return blueRay;
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

    public void setBlueRay(boolean blueRay) {
        this.blueRay = blueRay;
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
