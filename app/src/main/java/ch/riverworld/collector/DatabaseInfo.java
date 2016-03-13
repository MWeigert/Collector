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

    //Unused constructor
    public DatabaseInfo() {
    }
}
