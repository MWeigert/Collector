// ********************************************************************************************
// *                                                                                          *
// *                                        SEMESTERARBEIT                                    *
// *                                             ZHAW                                         *
// *                                                                                          *
// * Programmed by: Mathias Weigert                                                           *
// *       Version: 0.01                                                                      *
// *          Year: 2016                                                                      *
// *                                                                                          *
// ********************************************************************************************/

package ch.riverworld.collector;

public class DatabaseBase {

    // ********************************************************************************************
    // *                                                                                          *
    // *                                    BASE TO FILL THE                                      *
    // *                                      GENRE TABLE                                         *
    // *                                                                                          *
    // ********************************************************************************************/

    public static final String[] genreData = new String[]{"Action", "Adventure", "Animated", "Arcade", "Comedies",
            "Drama", "Documentary", "Fantasy", "Family", "Film Noir", "First Person Shooter", "Horror", "Romantic",
            "Science Fiction", "Simulation", "Strategy", "Thriller", "Western"};

    // ********************************************************************************************
    // *                                                                                          *
    // *                                    BASE TO FILL THE                                      *
    // *                                     LANGUAGE TABLE                                       *
    // *                                                                                          *
    // ********************************************************************************************/

    public static final String[] languageData = new String[]{"Arabic", "English", "French", "German", "Hindi",
            "Italian", "Japanese", "Mandarin", "Portuguese", "Russian", "Spanish"};

    // ********************************************************************************************
    // *                                                                                          *
    // *                                    BASE TO FILL THE                                      *
    // *                                      YEAR SPINNER                                        *
    // *                                                                                          *
    // ********************************************************************************************/

    public static final Integer[] yearData = new Integer[]{2016,2015,2014,2013,2012,2011,2010,2009,2008,2007,2006,
            2005,2004,
            2003,2002,2001,2000,1999,1998,1997,1996,1995,1994,1993,1992,1991,1990,1989,1988,1987,1986,1985,1984,1983,
            1982,1981,1980,1979,1978,1977,1976,1975,1974,1973,1972,1970};

    //Empty constructor
    public DatabaseBase() {
    }
}
