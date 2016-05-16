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

public class Rental {

    private String itemName;
    private String friend;
    private String start;
    private String back;

    // Constructor
    public Rental(String itemName, String friend, String start, String back) {
        this.itemName = itemName;
        this.friend = friend;
        this.start = start;
        if (back != null) {
            this.back = back;
        } else this.back = "";

    }

    @Override
    public String toString() {
        return itemName;
    }

    public String getInformation() {
        if (back.length() <= 0) {
            return itemName + " was lent from " + friend + " since " + start;
        } else return itemName + " is lend from " + friend + " between " + start + " and " + back;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

}
