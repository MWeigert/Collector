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

import android.content.Context;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class LentActivity extends AppCompatActivity {

    private boolean debugMode = false;
    private int itemId;
    private boolean isLent;
    private Spinner friendsSPINNER;
    private DatePicker date;
    private DatabaseOperations db;
    private int historyId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lent);

        //Getting information from calling activity
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            debugMode = extras.getBoolean("debugMode");
            itemId = extras.getInt("itemID");
            isLent = extras.getBoolean("isLent");
        }

        Context ctx = this;
        db = new DatabaseOperations(ctx, debugMode);
        date = (DatePicker) findViewById(R.id.dp_lent);
        TextView title = (TextView) findViewById(R.id.txt_lent_title);

        // Setting title of activity
        Cursor crs = db.getItemRow(db, itemId);
        crs.moveToFirst();
        int index = crs.getColumnIndex(DatabaseInfo.ITEMS_TITLE_COL);
        String msg;
        ;
        if (isLent) {
            msg = "give '" + crs.getString(index) + "' back on";
            isLent = (false);
            // get history id here to update entry.
        } else {
            msg = "take '" + crs.getString(index) + "' on";
            isLent = (true);
        }
        crs.close();
        title.setText(msg);

        // Fill friends from database to spinner item
        friendsSPINNER = (Spinner) findViewById(R.id.spn_friends_rental);

        final ArrayList<Friend> friends = new ArrayList<Friend>();
        Cursor friendsCrs = db.getFriends(db);
        friendsCrs.moveToFirst();

        if (debugMode) {
            Integer anz = friendsCrs.getCount();
            Log.d("LENAC", "DATABASE: " + anz.toString() + " friends in table.");
        }

        if (friendsCrs.getCount() > 0) {
            int indexFriendId=friendsCrs.getColumnIndex(DatabaseInfo.FRIENDS_ID_COL);
            int indexFirstName = friendsCrs.getColumnIndex(DatabaseInfo.FRIENDS_FIRSTNAME_COL);
            int indexLastName = friendsCrs.getColumnIndex(DatabaseInfo.FRIENDS_LASTNAME_COL);
            do {
                int id = friendsCrs.getInt(indexFriendId);
                String first = friendsCrs.getString(indexFirstName);
                String last = friendsCrs.getString(indexLastName);
                Friend friend = new Friend(id,first,last);
                if (debugMode) {
                    Log.d("LENAC", "DATABASE: Get " + friend);
                }
                friends.add(friend);
                if (debugMode) {
                    Log.d("LENAC", "Size of friends = " + friends.size());
                }
            } while (friendsCrs.moveToNext());
            friendsCrs.close();
            
            if (debugMode) {
                Log.d("LENAC", "Putting data in Adapter");
            }

            final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, friends);
            friendsSPINNER.setAdapter(adapter);
        }
    }

    // Button listener for the LentActivity
    public void buttonOnClick(View v) {
        //Button pressed to update rental history.
        if (debugMode) {
            String msg = "User chose: " + date.getDayOfMonth() + "." + date.getMonth() + "." + date.getYear();
            Log.d("USERACTION", msg);
            msg = "Friend: " + friendsSPINNER.getSelectedItem().toString();
            Log.d("USERACTION", msg);
        }

        String dateStr ="";
        if (date.getDayOfMonth() < 10) {
            dateStr += "0" + Integer.toString(date.getDayOfMonth());
        } else dateStr += Integer.toString(date.getDayOfMonth());
        if (date.getMonth() < 9) {
            dateStr += "0" + Integer.toString(date.getMonth() + 1);
        } else Integer.toString(date.getMonth() + 1);
        dateStr += Integer.toString(date.getYear());

        boolean success = db.updateItemRentalStatus(db, itemId, isLent);

        if (success) {
            Log.d("LENAC", "Rental status of " + itemId + " successfully updated.");
            if (isLent) {
                Friend friend =(Friend) friendsSPINNER.getSelectedItem();
                if (debugMode) {
                    Log.d("LENAC", " Adding: ITEM: " + itemId + " FRIEND: " + friend.getId() + " START: " + dateStr);
                }
                db.addHistory(db, itemId, friend.getId(), dateStr);
            } else {
                Cursor crs = db.getOpenHistory(db, itemId);
                int index = crs.getColumnIndex(DatabaseInfo.HISTORY_ID_COL);
                crs.moveToFirst();
                int id = crs.getInt(index);
                crs.close();
                
                if (debugMode){
                    Log.d("LENAC", "ITEM: "+itemId+" Back: "+ dateStr);
                }

                boolean updateStatus = db.updateHistory(db, id, dateStr);
                if (updateStatus) {
                    Toast.makeText(getBaseContext(),"Lent item is back.",Toast.LENGTH_SHORT).show();
                    finish();
                } else Toast.makeText(getBaseContext(),"Ups something went wrong. Please try it again.",Toast
                        .LENGTH_SHORT).show();
            }
            finish();
        } else Log.d("LENAC", "Unable to update rental status of " + itemId + ".");
    }
}
