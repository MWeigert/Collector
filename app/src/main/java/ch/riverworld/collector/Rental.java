// ********************************************************************************************
// *                                                                                          *
// *                                        SEMESTERARBEIT                                    *
// *                                             ZHAW                                         *
// *                                                                                          *
// * Programmed by: Mathias Weigert                                                           *
// *       Version: 0.1                                                                       *
// *          Year: 2016                                                                      *
// *                                                                                          *
// ********************************************************************************************/


package ch.riverworld.collector;

public class Rental {

    private int id;
    private String itemName;
    private String friend;
    private String start;
    private String back;

    // Constructor
    public Rental(int id, String itemName, String friend, String start, String back) {
        this.id = id;
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

    public int getID() {
        return id;
    }

    public void setID(int ID) {
        this.id = ID;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getFriend() {
        return friend;
    }

    public void setFriend(String friend) {
        this.friend = friend;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getBack() {
        return back;
    }

    public void setBack(String back) {
        this.back = back;
    }
}
