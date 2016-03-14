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
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class DatabaseActivity extends AppCompatActivity {

    private boolean debugMode = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        //Getting information regarding debug mode
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            debugMode = extras.getBoolean("debugMode");
        }
    }

    //Button listener for the database activity
    public void buttonOnClick(View v) {
        switch (v.getId()) {
            case R.id.btn_friends:
                if (debugMode) {
                    String msg = "Entering friends administration.";
                    Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                    Log.d("USERACTION", msg);
                }

                final Intent friendsIntent = new Intent(this, FriendsActivity.class);
                friendsIntent.putExtra("debugMode", debugMode);
                startActivity(friendsIntent);
                break;
            case R.id.btn_reset_friends:
                //Pressed button to add new friend to the database.

                if (debugMode) {
                    String msg = "Table friends reseted.";
                    Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                    Log.d("USERACTION", msg);
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(DatabaseActivity.this);

                builder.setMessage("Do you really want to reset the complete friends table?");
                //builder.setCancelable(true);

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //dialog.cancel();
                        if (debugMode) {
                            String msg = "User choosed yes.";
                            Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                            Log.d("USERACTION", msg);
                        }
                        DatabaseOperations db = new DatabaseOperations(DatabaseActivity.this, debugMode);
                        db.resetFriends(db);
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //dialog.cancel();
                        if (debugMode) {
                            String msg = "User choosed no.";
                            Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                            Log.d("USERACTION", msg);
                        }
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();
                break;
            case R.id.btn_reset_language:
                //Button reset language pressed by user.
                AlertDialog.Builder langBuilder = new AlertDialog.Builder(DatabaseActivity.this);
                langBuilder.setMessage("Do you really want to reset the complete language table?");
                String msg;

                langBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //dialog.cancel();
                        if (debugMode) {
                            String msg = "User choosed yes.";
                            Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                            Log.d("USERACTION", msg);
                        }
                        String msg = "TRUE";
                        Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                    }
                });

                langBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //dialog.cancel();
                        if (debugMode) {
                            String msg = "User choosed no.";
                            Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                            Log.d("USERACTION", msg);
                        }
                        String msg = "FALSE";
                        Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                    }
                });

                AlertDialog langAlert = langBuilder.create();
                langAlert.show();
                break;
        }
    }
}
