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
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class FriendsActivity extends AppCompatActivity {

    private EditText FIRST_NAME, LAST_NAME;
    private String firstName, lastName;
    private ListView FRIENDLIST;
    private Context ctx = this;
    private DatabaseOperations db;
    private String msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);

        FIRST_NAME = (EditText)findViewById(R.id.tf_first_name);
        LAST_NAME = (EditText) findViewById(R.id.tf_last_name);
        FRIENDLIST = (ListView) findViewById(R.id.lst_friends);

        String friend;
        final ArrayList<String> friends = new ArrayList<String>();
        final int index;

        db = new DatabaseOperations(ctx);
        Cursor crs = db.getFriends(db);

        Integer anz = crs.getCount();
        msg = anz.toString() + " friends in table.";
        Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();

        crs.moveToFirst();
        index = crs.getColumnIndex(DatabaseInfo.COL_FRIEND_FIRSTNAME);
        do {
            friend = crs.getString(index);
            friends.add(friend);
        } while (crs.moveToNext());

        final ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, friends);

        FRIENDLIST.setAdapter(adapter);
    }

    // Button listener for the FriendsActivity
    public void buttonOnClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add:
                //Pressed button to add new friend to the database.
                firstName = FIRST_NAME.getText().toString();
                lastName = LAST_NAME.getText().toString();
                db.addFriend(db, firstName, lastName);

                msg = "Added: " + firstName + " " + lastName;
                Toast.makeText(getBaseContext(), msg,Toast.LENGTH_LONG).show();
                finish();
                break;
            case R.id.btn_delete:
                //Pressed button to delete friend from database.
                break;
        }
    }
}
