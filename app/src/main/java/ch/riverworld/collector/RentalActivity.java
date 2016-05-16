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

// For higher version of this app I should try to use an expandable ListView

package ch.riverworld.collector;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class RentalActivity extends AppCompatActivity {

    private boolean debugMode = false;
    private String mode = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rental);

        //Getting information regarding debug mode
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            debugMode = extras.getBoolean("debugMode");
            mode = extras.getString("Mode");
        }

        final ListView rentalLIST = (ListView) findViewById(R.id.lst_rental);
        assert rentalLIST != null;
        rentalLIST.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Object selectedItem = rentalLIST.getItemAtPosition(position);
                Rental rental = (Rental) selectedItem;
                String msg = rental.getInformation();
                Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
            }
        });

        // Fill items from database to list item
        final ArrayList<Rental> rentals = new ArrayList<Rental>();

        Context ctx = this;
        DatabaseOperations db = new DatabaseOperations(ctx, debugMode);
        Integer anz;

        Cursor historyCrs = null;
        switch (mode) {
            case "Open":
                // Get only items which not back yet.
                historyCrs = db.getOpenHistories(db);

                break;
            case "All":
                // Get all entries in history table. Doesn't matter if item is back or not
                historyCrs = db.getHistories(db);
                break;
        }
        if (historyCrs != null) {
            anz = historyCrs.getCount();
        } else anz = 0;

        if (debugMode) {
            Log.d("RENAC", "DATABASE: " + anz.toString() + " items in table.");
        }

        if (anz > 0) {
            historyCrs.moveToFirst();
            int idIndex = historyCrs.getColumnIndex(DatabaseInfo.HISTORY_ID_COL);
            int itemIndex = historyCrs.getColumnIndex(DatabaseInfo.HISTORY_ITEM_ID_COL);
            int friendIndex = historyCrs.getColumnIndex(DatabaseInfo.HISTORY_FRIEND_ID_COL);
            int startIndex = historyCrs.getColumnIndex(DatabaseInfo.HISTORY_START_COL);
            int backIndex = historyCrs.getColumnIndex(DatabaseInfo.HISTORY_RETURN_COL);

            do {
                int id = historyCrs.getInt(idIndex);
                String itemName = db.getItemTitle(db, historyCrs.getInt(itemIndex));
                Friend friend = db.getFriend(db, historyCrs.getInt(friendIndex));
                String start = historyCrs.getString(startIndex);
                String back = historyCrs.getString(backIndex);

                if (debugMode) {
                    Log.d("RENAC", "ID: " + id + " ITEM: " + itemName + " FRIEND: " + friend + " START " + start + " " +
                            "RETURN: " + back);
                }

                if (back.equals("NULL")) {
                    Rental rental = new Rental(itemName, friend.toString(), start, null);
                    rentals.add(rental);
                } else {
                    Rental rental = new Rental(itemName, friend.toString(), start, back);
                    rentals.add(rental);
                }
            } while (historyCrs.moveToNext());

            if (debugMode) {
                Log.d("RENAC", rentals.size() + " items in ArrayList.");
                Log.d("RENAC", "Putting data in Adapter");
            }
            final ArrayAdapter adapter = new ArrayAdapter(this,
                    android.R.layout.simple_list_item_1, rentals);

            rentalLIST.setAdapter(adapter);
        }
    }
}