//*****************************************************************
//*                                                               *
//* Programmed by: Mathias Weigert                                *
//*       Version: 0.01                                           *
//*                                                               *
//*****************************************************************

package ch.riverworld.collector;

import android.content.Context;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);

        //Getting information regarding debug mode
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            debugMode = extras.getBoolean("debugMode");
        }

        FIRST_NAME = (EditText)findViewById(R.id.tf_first_name);
        LAST_NAME = (EditText) findViewById(R.id.tf_last_name);

        FRIENDLIST = (ListView) findViewById(R.id.lst_friends);
        FRIENDLIST.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Object selectedItem = FRIENDLIST.getItemAtPosition(position);
                if (debugMode) {
                    String msg = selectedItem.toString() + " ausgewählt.";
                    Log.d("USERACTION", msg);
                    Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                }
            }
        });

        String friend;
        final ArrayList<String> friends = new ArrayList<String>();
        final int indexFirstName;
        final int indexLastName;

        Context ctx = this;
        db = new DatabaseOperations(ctx);
        Cursor crs = db.getFriends(db);

        Integer anz = crs.getCount();
        if (debugMode){
            String msg = "DATABASE: " + anz.toString() + " friends in table.";
            Log.d("DATABASE", msg);
            Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
        }

        if(anz>0) {
            crs.moveToFirst();
            indexFirstName = crs.getColumnIndex(DatabaseInfo.COL_FRIEND_FIRSTNAME);
            indexLastName = crs.getColumnIndex(DatabaseInfo.COL_FRIEND_LASTNAME);

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
                //Pressed button to add new friend to the database.
                String firstName = FIRST_NAME.getText().toString();
                String lastName = LAST_NAME.getText().toString();
                db.addFriend(db, firstName, lastName);

                if (debugMode){
                    String msg = "Added: " + firstName + " " + lastName;
                    Toast.makeText(getBaseContext(), msg,Toast.LENGTH_LONG).show();
                }
                finish();
                break;
            case R.id.btn_delete:
                //Pressed button to delete friend from database.
                break;
        }
    }
}
