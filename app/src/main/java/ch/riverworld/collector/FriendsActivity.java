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

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class FriendsActivity extends AppCompatActivity {

    private boolean debugMode = false;
    private EditText FIRST_NAME, LAST_NAME;
    private ListView FRIENDLIST;
    private DatabaseOperations db;
    private Object selectedItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);

        //Getting information regarding debug mode
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            debugMode = extras.getBoolean("debugMode");
        }

        FIRST_NAME = (EditText) findViewById(R.id.tf_first_name);
        LAST_NAME = (EditText) findViewById(R.id.tf_last_name);

        FRIENDLIST = (ListView) findViewById(R.id.lst_friends);
        FRIENDLIST.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                selectedItem = FRIENDLIST.getItemAtPosition(position);
                if (debugMode) {
                    Log.d("USERACTION", selectedItem.toString() + " chose.");
                }
            }
        });

        // Fill friends from database to list item
        String friend;
        final ArrayList<String> friends = new ArrayList<String>();
        final int indexFirstName;
        final int indexLastName;

        Context ctx = this;
        db = new DatabaseOperations(ctx, debugMode);
        Cursor crs = db.getFriends(db);

        Integer anz = crs.getCount();
        if (debugMode) {
            Log.d("FRAC", "DATABASE: " + anz.toString() + " friends in table.");
        }
        if (anz > 0) {
            crs.moveToFirst();
            indexFirstName = crs.getColumnIndex(DatabaseInfo.FRIENDS_FIRSTNAME_COL);
            indexLastName = crs.getColumnIndex(DatabaseInfo.FRIENDS_LASTNAME_COL);
            do {
                friend = crs.getString(indexFirstName) + " " + crs.getString(indexLastName);
                friends.add(friend);
            } while (crs.moveToNext());

            final ArrayAdapter adapter = new ArrayAdapter(this,
                    android.R.layout.simple_list_item_1, friends);

            FRIENDLIST.setAdapter(adapter);
        }
    }

    // Button listener for the FriendsActivity
    public void buttonOnClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add:
                // Pressed button to add new friend to the database.
                String firstName = FIRST_NAME.getText().toString();
                String lastName = LAST_NAME.getText().toString();
                if (lastName.compareTo("Last name") == 0) {
                    Toast.makeText(getBaseContext(), "Please enter an last name.", Toast.LENGTH_SHORT).show();
                } else {
                    db.addFriend(db, firstName, lastName);
                    if (debugMode) {
                        Log.d("FRAC", "Added: " + firstName + " " + lastName);
                    }
                }
                finish();
                break;
            case R.id.btn_remove:
                //Pressed button to delete friend from database.

                AlertDialog.Builder langBuilder = new AlertDialog.Builder(FriendsActivity.this);
                langBuilder.setMessage("Do you really want to delete " + selectedItem.toString());

                langBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //dialog.cancel();
                        if (debugMode) {
                            Log.d("USERACTION", "User chose yes.");
                        }
                        String[] name = selectedItem.toString().split(" ");
                        Cursor crs = db.getFriendsRow(db, name[0], null, null);
                        int index = crs.getColumnIndex(DatabaseInfo.FRIENDS_ID_COL);
                        crs.moveToFirst();
                        if (debugMode){
                            Log.d("FRAC", "Count: " + crs.getCount());
                            Log.d("FRAC", "TYPE: "+crs.getType(index));
                        }
                        int id = crs.getInt(index);
                        boolean success = db.deleteFriend(db, id);
                        if (success) {
                            Toast.makeText(getBaseContext(), selectedItem.toString() + " is successfully deleted from" +
                                    " " + "database.", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getBaseContext(), "Could not delete: " + selectedItem.toString() + " from " +
                                    "database.", Toast.LENGTH_SHORT).show();
                        }
                        finish();
                    }
                });
                langBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //dialog.cancel();
                        if (debugMode) {
                            Log.d("USERACTION","User chose no.");
                        }
                    }
                });
                AlertDialog langAlert = langBuilder.create();
                langAlert.show();
                break;
        }
    }
}
