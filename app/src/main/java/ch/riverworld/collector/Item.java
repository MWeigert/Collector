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


public class Item {

    private long ean;
    private String title;
    private boolean book;
    private boolean movie;
    private boolean game;
    private String mediaType;
    private int genre_id;
    private int language_id;
    private int year;
    private boolean lent;
    private int publisher_id;
    private int author_id;
    private int system_id;
    private boolean dvd;
    private boolean blueRay;
    private int studio_id;
    private int director_id;
    private int fsk;

    // Constructor
    public Item() {

    }

    // Getter
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

    public int getLanguage_id() {
        return language_id;
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

    public int getAuthor_id() {
        return author_id;
    }

    public int getSystem_id() {
        return system_id;
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

    public int getDirector_id() {
        return director_id;
    }

    public int getFsk() {
        return fsk;
    }

    // Setter

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

    public void setLanguage_id(int language_id) {
        this.language_id = language_id;
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

    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
    }

    public void setSystem_id(int system_id) {
        this.system_id = system_id;
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

    public void setDirector_id(int director_id) {
        this.director_id = director_id;
    }

    public void setFsk(int fsk) {
        this.fsk = fsk;
    }
}
