//*****************************************************************
//*                                                               *
//* Programmed by: Mathias Weigert                                *
//*       Version: 0.01                                           *
//*                                                               *
//*****************************************************************

package ch.riverworld.collector;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class FriendsActivity extends AppCompatActivity {

    private EditText FIRST_NAME, LAST_NAME;
    private String firstName, lastName;
    private Context ctx = this;
    private int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);

        FIRST_NAME = (EditText)findViewById(R.id.tf_first_name);
        LAST_NAME = (EditText) findViewById(R.id.tf_last_name);
    }

    // Button listener for the FriendsActivity
    public void buttonOnClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add:
                //Pressed button to add new friend to the database.
                firstName = FIRST_NAME.getText().toString();
                lastName = LAST_NAME.getText().toString();
                DatabaseOperations db = new DatabaseOperations(ctx);
                db.addFriend(db, firstName, lastName);
                final String msg = "Added: " + firstName + " " + lastName;
                Toast.makeText(getBaseContext(), msg,Toast.LENGTH_LONG).show();
                finish();
                break;
            case R.id.btn_delete:
                //Pressed button to delete friend from database.
                break;
        }
    }
}
