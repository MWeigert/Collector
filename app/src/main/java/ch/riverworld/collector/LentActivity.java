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
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

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

        if (debugMode) {
            Log.d("LENAC", "Starting rental administration.");
        }

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
        title.setText(msg);

        // Fill friends from database to spinner item
        friendsSPINNER = (Spinner) findViewById(R.id.spn_friends_rental);
        String friend;
        final ArrayList<String> friends = new ArrayList<String>();
        Cursor friendsCrs = db.getFriends(db);
        friendsCrs.moveToFirst();

        if (debugMode) {
            Integer anz = friendsCrs.getCount();
            Log.d("LENAC", "DATABASE: " + anz.toString() + " friends in table.");
        }

        if (friendsCrs.getCount() > 0) {
            friendsCrs.moveToFirst();
            int indexFirstName = friendsCrs.getColumnIndex(DatabaseInfo.FRIENDS_FIRSTNAME_COL);
            int indexLastName = friendsCrs.getColumnIndex(DatabaseInfo.FRIENDS_LASTNAME_COL);
            do {
                friend = friendsCrs.getString(indexFirstName) + " " + friendsCrs.getString(indexLastName);
                if (debugMode) {
                    Log.d("LENAC", "DATABASE: Get " + friend);
                }
                friends.add(friend);
                if (debugMode) {
                    Log.d("LENAC", "Size of friends = " + friends.size());
                }
            } while (friendsCrs.moveToNext());

            if (debugMode) {
                Log.d("LENAC", "Putting data in Adapter");
            }

            final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, friends);
            friendsSPINNER.setAdapter(adapter);
        }
    }

    // Button listener for the LentActivity
    public void buttonOnClick(View v) {
        //   switch (v.getId()) {
        // case R.id.btn_set:
        //Button pressed to update rental history.
        if (debugMode) {
            String msg = "User chose: " + date.getDayOfMonth() + "." + date.getMonth() + "." + date.getYear();
            Log.d("USERACTION", msg);
            msg = "Friend: " + friendsSPINNER.getSelectedItem().toString();
            Log.d("USERACTION", msg);
        }

        boolean success = db.updateItemRentalStatus(db, itemId, isLent);

        if (success) {
            Log.d("LENAC", "Rental status of " + itemId + " successfully updated.");
            if (isLent) {
                String start = "";
                if (date.getDayOfMonth() < 10) {
                    start += "0" + Integer.toString(date.getDayOfMonth());
                } else start += Integer.toString(date.getDayOfMonth());
                if (date.getMonth() < 9) {
                    start += "0" + Integer.toString(date.getMonth() + 1);
                } else Integer.toString(date.getMonth() + 1);
                start += Integer.toString(date.getYear());
                String[] name = friendsSPINNER.getSelectedItem().toString().split(" ");
                if (debugMode) {
                    Log.d("LENAC", "0: " + name[0] + " 1:" + name[1]);
                }
                String firstName = name[0];
                String lastName = name[1];
                int friendId = db.getFriendId(db, firstName, lastName);
                if (debugMode) {
                    Log.d("LENAC", " Adding: ITEM: " + itemId + " FRIEND: " + friendId + " START: " + start);
                }
                db.addHistory(db, itemId, friendId, start);
            } else {
                // UPDATE HISTORY TABLE HERE
            }
            finish();
        } else Log.d("LENAC", "Unable to update rental status of " + itemId + ".");
        //    break;
        //  }
    }
}
