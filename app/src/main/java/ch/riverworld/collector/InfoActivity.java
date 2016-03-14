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

public class InfoActivity extends AppCompatActivity {

    private boolean debugMode = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        //Getting information regarding debug mode
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            debugMode = extras.getBoolean("debugMode");
        }
    }

    // Button listener for the InfoActivity
    public void buttonOnClick(View v) {
        switch (v.getId()) {
            case R.id.btn_settings:
                //Button pressed to enter configuration mode of the app.
                if (debugMode) {
                    String msg = "Entering database administration mode.";
                    Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                    Log.d("USERACTION", msg);
                }

                final Intent databaseIntent=new Intent(this,DatabaseActivity.class);
                databaseIntent.putExtra("debugMode", debugMode);
                startActivity(databaseIntent);
                break;
        }
    }
}
