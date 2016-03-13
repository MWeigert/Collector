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

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class SettingActivity extends AppCompatActivity {

    private boolean debugMode = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        //Getting information regarding debug mode
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            debugMode = extras.getBoolean("debugMode");
        }
    }

    // Button listener for the SettingActivity
    public void buttonOnClick(View v) {
        switch (v.getId()) {
            case R.id.btn_friends:
                //Button for friends administration pressed.
                if (debugMode) {
                    String msg = "Entering friends administration.";
                    Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                    Log.d("USERACTION", msg);
                }

                final Intent friendsIntent = new Intent(this, FriendsActivity.class);
                friendsIntent.putExtra("debugMode", debugMode);
                startActivity(friendsIntent);
                break;
            case R.id.btn_database:
                //Button for database administration pressed.
                if (debugMode) {
                    String msg = "Entering database administration.";
                    Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                    Log.d("USERACTION", msg);
                }

                final Intent databaseIntent = new Intent(this, DatabaseActivity.class);
                databaseIntent.putExtra("debugMode", debugMode);
                startActivity(databaseIntent);
                break;
        }
    }
}
