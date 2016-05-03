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
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class LentActivity extends AppCompatActivity {

    private boolean debugMode = false;
    private int id;
    private boolean isLent;
    private Spinner friendsSPINNER;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lent);

        Context ctx = this;
        DatabaseOperations db = new DatabaseOperations(ctx, debugMode);

        //Getting information regarding debug mode
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            debugMode = extras.getBoolean("debugMode");
            id = extras.getInt("itemID");
            isLent = extras.getBoolean("isLent");
        }

        if (debugMode) {
            Log.d("LENAC", "Starting rental administration.");
        }

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
        switch (v.getId()) {
            case R.id.btn_set:
                //Button pressed to enter configuration mode of the app.
                if (debugMode) {
                    String msg = "Entering configuration mode.";
                    Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                    Log.d("USERACTION", msg);
                }
                //Insert logic for item rental here
                break;
        }
    }
}
