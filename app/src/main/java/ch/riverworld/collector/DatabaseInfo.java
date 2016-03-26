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

public class DatabaseInfo {

    // Database name and version
    public static final String DATABASE_NAME = "CollectorDB";
    public static final int DATABASE_VERSION = 1;

    // ********************************************************************************************
    // *                                                                                          *
    // *                           ALL DATABASE INFORMATION REGARDING                             *
    // *                                          THE                                             *
    // *                                     FRIENDS TABLE                                        *
    // *                                                                                          *
    // ********************************************************************************************/

    // Table name and column informations for friends table
    public static final String FRIENDS_TABLE = "friends";
    public static final String FRIENDS_ID_COL = "FRIEND_ID";            //Integer
    public static final String FRIENDS_FIRSTNAME_COL = "FIRST_NAME";    //Text
    public static final String FRIENDS_LASTNAME_COL = "LAST_NAME";      //Text

    //SQL commando to create table friends in database
    public static final String CREATE_FRIENDS = "create table " + DatabaseInfo.FRIENDS_TABLE +
            " (" + DatabaseInfo.FRIENDS_ID_COL + " integer primary key autoincrement, " +
            DatabaseInfo.FRIENDS_FIRSTNAME_COL + " text, " + DatabaseInfo.FRIENDS_LASTNAME_COL +
            " text not null);";

    // ********************************************************************************************
    // *                                                                                          *
    // *                           ALL DATABASE INFORMATION REGARDING                             *
    // *                                          THE                                             *
    // *                                      GENRE TABLE                                         *
    // *                                                                                          *
    // ********************************************************************************************/

    // Table name and column informations from genre table
    public static final String GENRES_TABLE = "genres";
    public static final String GENRES_ID_COL = "GENRE_ID";          //Integer
    public static final String GENRES_GENRE_COL = "GENRE";          //Text

    //SQL commando to create table genres in database
    public static final String CREATE_GENRES = "create table " + DatabaseInfo.GENRES_TABLE +
            " (" + DatabaseInfo.GENRES_ID_COL + " integer primary key autoincrement, " +
            DatabaseInfo.GENRES_GENRE_COL + " text not null);";

    // ********************************************************************************************
    // *                                                                                          *
    // *                           ALL DATABASE INFORMATION REGARDING                             *
    // *                                          THE                                             *
    // *                                     HISTORY TABLE                                        *
    // *                                                                                          *
    // ********************************************************************************************/

    // Table name and column informations from history table
    public static final String HISTORY_TABLE = "history";
    public static final String HISTORY_ID_COL = "HISTORY_ID";           //Integer
    public static final String HISTORY_ITEM_ID_COL = "ITEM_ID";         //Integer
    public static final String HISTORY_FRIEND_ID_COL = "FRIEND_ID";     //Integer
    public static final String HISTORY_START_COL = "START";             //Text
    public static final String HISTORY_RETURN_COL = "RETURN";           //Text

    //SQL commando to create table history in database
    public static final String CREATE_HISTORY = "create table " + DatabaseInfo.HISTORY_TABLE + " (" + DatabaseInfo
            .HISTORY_ID_COL + " integer primary key autoincrement, " + DatabaseInfo.HISTORY_ITEM_ID_COL + " integer " +
            "not null, " + DatabaseInfo.HISTORY_FRIEND_ID_COL + " integer not null, " + DatabaseInfo
            .HISTORY_START_COL + " text, " + DatabaseInfo.HISTORY_RETURN_COL + " text);";

    // ********************************************************************************************
    // *                                                                                          *
    // *                           ALL DATABASE INFORMATION REGARDING                             *
    // *                                          THE                                             *
    // *                                      ITEMS TABLE                                         *
    // *                                                                                          *
    // ********************************************************************************************/

    // Table name and column informations from items table
    public static final String ITEMS_TABLE = "items";
    public static final String ITEMS_ID_COL = "ITEM_ID";                    //Integer
    public static final String ITEMS_EAN_COL = "EAN";                       //Integer
    public static final String ITEMS_TITLE_COL = "TITLE";                   //Text
    public static final String ITEMS_MEDIA_TYPE_COL = "MEDIA_TYPE";         //Text
    public static final String ITEMS_GENRE_ID_COL = "GENRE_ID";             //Integer
    public static final String ITEMS_LANGUAGE_ID_COL = "LANGUAGE_ID";       //Integer
    public static final String ITEMS_LAUNCH_COL = "LAUNCH";                 //Text
    public static final String ITEMS_RENTAL_COL = "RENTAL";                 //Integer
    public static final String ITEMS_PUBLISHER_COL = "PUBLISHER";           //Text
    public static final String ITEMS_AUTHOR_COL = "AUTHOR";                 //Text
    public static final String ITEMS_EDITION_COL = "EDITION";               //Text
    public static final String ITEMS_SYSTEM_ID_COL = "SYSTEM_ID";           //Integer
    public static final String ITEMS_DVD_COL = "DVD";                       //Integer
    public static final String ITEMS_BLUERAY_COL = "BLUERAY";               //Integer
    public static final String ITEMS_STUDIO_COL = "STUDIO";                   //Text
    public static final String ITEMS_DIRECTOR_COL = "DIRECTOR";               //Text
    public static final String ITEMS_PARENTAL_ID_COL = "PARENTAL_ID";         //Integer
    public static final String ITEMS_RATING_COL = "RATING";                   //Real
    public static final String ITEMS_REMARKS_COL = "REMARKS";                 //Text

    //SQL commando to create table items in database
    public static final String CREATE_ITEMS = "create table " + DatabaseInfo.ITEMS_TABLE + " (" + DatabaseInfo
            .ITEMS_ID_COL + " integer primary key autoincrement, " + DatabaseInfo.ITEMS_EAN_COL + " integer, " +
            DatabaseInfo.ITEMS_TITLE_COL + " text, " + DatabaseInfo.ITEMS_MEDIA_TYPE_COL + " text, " + DatabaseInfo
            .ITEMS_GENRE_ID_COL + " integer, " + DatabaseInfo.ITEMS_LANGUAGE_ID_COL + " integer, " + DatabaseInfo
            .ITEMS_LAUNCH_COL + " text, " + DatabaseInfo.ITEMS_RENTAL_COL + " integer, " + DatabaseInfo
            .ITEMS_PUBLISHER_COL + " text, " + DatabaseInfo.ITEMS_AUTHOR_COL + " text, " + DatabaseInfo
            .ITEMS_EDITION_COL + " text, " + DatabaseInfo.ITEMS_SYSTEM_ID_COL + " integer, " + DatabaseInfo
            .ITEMS_DVD_COL + " integer, " + DatabaseInfo.ITEMS_BLUERAY_COL + " integer, " + DatabaseInfo
            .ITEMS_STUDIO_COL + " text, " + DatabaseInfo.ITEMS_DIRECTOR_COL + " text, " + DatabaseInfo
            .ITEMS_PARENTAL_ID_COL + " integer," + DatabaseInfo.ITEMS_RATING_COL + " real, " + DatabaseInfo
            .ITEMS_REMARKS_COL + " text);";

    // ********************************************************************************************
    // *                                                                                          *
    // *                           ALL DATABASE INFORMATION REGARDING                             *
    // *                                          THE                                             *
    // *                                    LANGUAGE TABLE                                        *
    // *                                                                                          *
    // ********************************************************************************************/

    // Table name and column informations from language table
    public static final String LANGUAGE_TABLE = "language";
    public static final String LANGUAGE_ID_COL = "LANGUAGE_ID";
    public static final String LANGUAGE_LANGUAGE_COL = "LANGUAGE";

    //SQL commando to create table language in database
    public static final String CREATE_LANGUAGE = "create table " + DatabaseInfo.LANGUAGE_TABLE +
            " (" + DatabaseInfo.LANGUAGE_ID_COL + " integer primary key autoincrement, " +
            DatabaseInfo.LANGUAGE_LANGUAGE_COL + " text not null);";

    // ********************************************************************************************
    // *                                                                                          *
    // *                           ALL DATABASE INFORMATION REGARDING                             *
    // *                                          THE                                             *
    // *                                    PARENTAL TABLE                                        *
    // *                                                                                          *
    // ********************************************************************************************/

    // Table name and column informations from parental table
    public static final String PARENTAL_TABLE = "parental";
    public static final String PARENTAL_ID_COL = "PARENTAL_ID";
    public static final String PARENTAL_PARENTAL_COL = "PARENTAL";

    //SQL commando to create table genres in database
    public static final String CREATE_PARENTAL = "create table " + DatabaseInfo.PARENTAL_TABLE +
            " (" + DatabaseInfo.PARENTAL_ID_COL + " integer primary key autoincrement, " +
            DatabaseInfo.PARENTAL_PARENTAL_COL + " text not null);";

    // ********************************************************************************************
    // *                                                                                          *
    // *                           ALL DATABASE INFORMATION REGARDING                             *
    // *                                          THE                                             *
    // *                                     SYSTEMS TABLE                                        *
    // *                                                                                          *
    // ********************************************************************************************/

    // Table name and column informations from systems table
    public static final String SYSTEMS_TABLE = "systems";
    public static final String SYSTEMS_ID_COL = "SYSTEM_ID";
    public static final String SYSTEMS_SYSTEM_COL = "SYSTEM";

    //SQL commando to create table genres in database
    public static final String CREATE_SYSTEMS = "create table " + DatabaseInfo.SYSTEMS_TABLE +
            " (" + DatabaseInfo.SYSTEMS_ID_COL + " integer primary key autoincrement, " +
            DatabaseInfo.SYSTEMS_SYSTEM_COL + " text not null);";

    //Empty constructor
    public DatabaseInfo() {
    }
}
