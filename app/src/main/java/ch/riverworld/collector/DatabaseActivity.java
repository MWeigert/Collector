// ********************************************************************************************
// *                                                                                          *
// *                                        SEMESTERARBEIT                                    *
// *                                             ZHAW                                         *
// *                                                                                          *
// * Programmed by: Mathias Weigert                                                           *
// *       Version: 0.01                                                                      *
// *          Year: 2016                                                                      *
// *                                                                                          *
// ********************************************************************************************/

package ch.riverworld.collector;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
            case R.id.btn_add:
                //Pressed button to add new friend to the database.

                if (debugMode) {
                    String msg = "Added: ";
                    Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                }
                finish();
                break;
        }
    }
}
