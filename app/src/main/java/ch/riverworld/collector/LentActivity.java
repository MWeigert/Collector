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
            int indexFriendId = friendsCrs.getColumnIndex(DatabaseInfo.FRIENDS_ID_COL);
            do {
                int id = friendsCrs.getInt(indexFriendId);
                Friend friend = db.getFriend(db, id);
                friends.add(friend);
            } while (friendsCrs.moveToNext());
            friendsCrs.close();

            if (debugMode) {
                Log.d("LENAC", "Putting data in Adapter");
            }

            final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, friends);
            friendsSPINNER.setAdapter(adapter);
        }

        // Setting title of activity
        Cursor crs = db.getItem(db, itemId);
        crs.moveToFirst();
        int index = crs.getColumnIndex(DatabaseInfo.ITEMS_TITLE_COL);
        String msg = crs.getString(index);
        if (isLent) {
            msg = "give '" + msg + "' back on";
            isLent = (false);
            crs = db.getOpenHistory(db, itemId);
            crs.moveToFirst();
            index = crs.getColumnIndex(DatabaseInfo.HISTORY_FRIEND_ID_COL);
            int id = crs.getInt(index);
            Friend friend = db.getFriend(db, id);
            int pos = this.getIndex(friendsSPINNER, friend);
            friendsSPINNER.setSelection(pos);
        } else {
            msg = "take '" + msg + "' on";
            isLent = (true);
        }
        crs.close();
        if (title != null) {
            title.setText(msg);
        }
    }

    // Button listener for the LentActivity
    public void buttonOnClick(View v) {
        //Button pressed to update rental history.
        if (debugMode) {
            Log.d("USERACTION", "User chose: " + date.getDayOfMonth() + "." + date.getMonth() + "." + date.getYear());
            Log.d("USERACTION", "Friend: " + friendsSPINNER.getSelectedItem().toString());
            Log.d("LENAC", "Lent status is: " + isLent);
        }

        String dateStr = "";
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
                Friend friend = (Friend) friendsSPINNER.getSelectedItem();
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

                if (debugMode) {
                    Log.d("LENAC", "ITEM: " + itemId + " Back: " + dateStr);
                }

                boolean updateStatus = db.updateHistory(db, id, dateStr);
                if (updateStatus) {
                    Toast.makeText(getBaseContext(), "Lent item is back.", Toast.LENGTH_SHORT).show();
                    finish();
                } else Toast.makeText(getBaseContext(), "Ups something went wrong. Please try it again.", Toast
                        .LENGTH_SHORT).show();
            }
            finish();
        } else Log.d("LENAC", "Unable to update rental status of " + itemId + ".");
    }

    // Method which returns index of spinner item by value
    private int getIndex(Spinner spinner, Friend friend) {

        int index = 0;

        for (int i = 0; i < spinner.getCount(); i++) {
            if (spinner.getItemAtPosition(i).equals(friend)) {
                index = i;
            }
        }
        return index;
    }
}
