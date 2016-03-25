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
    public static final String TABLE_FRIENDS = "friends";
    public static final String COL_FRIEND_ID = "FRIEND_ID";
    public static final String COL_FRIEND_FIRSTNAME = "FIRST_NAME";
    public static final String COL_FRIEND_LASTNAME = "LAST_NAME";

    //SQL commando to create table friends in database
    public static final String CREATE_FRIENDS = "create table " + DatabaseInfo.TABLE_FRIENDS +
            " (" + DatabaseInfo.COL_FRIEND_ID + " integer primary key autoincrement, " +
            DatabaseInfo.COL_FRIEND_FIRSTNAME + " text, " + DatabaseInfo.COL_FRIEND_LASTNAME +
            " text not null);";

    // ********************************************************************************************
    // *                                                                                          *
    // *                           ALL DATABASE INFORMATION REGARDING                             *
    // *                                          THE                                             *
    // *                                      GENRE TABLE                                         *
    // *                                                                                          *
    // ********************************************************************************************/

    public static final String GENRES_TABLE = "genres";
    public static final String GENRE_ID_COL = "GENRE_ID";
    public static final String GENRES_GENRE_COL = "GENRE";

    //SQL commando to create table genres in database
    public static final String CREATE_GENRES = "create table " + DatabaseInfo.GENRES_TABLE +
            " (" + DatabaseInfo.GENRE_ID_COL + " integer primary key autoincrement, " +
            DatabaseInfo.GENRES_GENRE_COL + " text not null);";

    // ********************************************************************************************
    // *                                                                                          *
    // *                           ALL DATABASE INFORMATION REGARDING                             *
    // *                                          THE                                             *
    // *                                     HISTORY TABLE                                        *
    // *                                                                                          *
    // ********************************************************************************************/

    // Table name and column informations from history table
    public static final String TABLE_HISTORY = "history";

    // ********************************************************************************************
    // *                                                                                          *
    // *                           ALL DATABASE INFORMATION REGARDING                             *
    // *                                          THE                                             *
    // *                                      ITEMS TABLE                                         *
    // *                                                                                          *
    // ********************************************************************************************/

    // Table name and column informations from items table
    public static final String TABLE_ITEMS = "items";

    // ********************************************************************************************
    // *                                                                                          *
    // *                           ALL DATABASE INFORMATION REGARDING                             *
    // *                                          THE                                             *
    // *                                    LANGUAGE TABLE                                        *
    // *                                                                                          *
    // ********************************************************************************************/

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

    public static final String SYSTEMS_TABLE = "systems";
    public static final String SYSTEM_ID_COL = "SYSTEM_ID";
    public static final String SYSTEMS_SYSTEM_COL = "SYSTEM";

    //SQL commando to create table genres in database
    public static final String CREATE_SYSTEMS = "create table " + DatabaseInfo.SYSTEMS_TABLE +
            " (" + DatabaseInfo.SYSTEM_ID_COL + " integer primary key autoincrement, " +
            DatabaseInfo.SYSTEMS_SYSTEM_COL + " text not null);";

    //Empty constructor
    public DatabaseInfo() {
    }
}
