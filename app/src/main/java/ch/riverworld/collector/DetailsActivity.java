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
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

public class DetailsActivity extends AppCompatActivity {

    private boolean debugMode = false;
    private String selectedItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        //Getting information regarding debug mode
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            debugMode = extras.getBoolean("debugMode");
            selectedItem=extras.getString("itemTitle");
        }

        // Set functionality and listener to lent switch
        Switch swLent = (Switch) findViewById(R.id.sw_lent);
        final Intent lentIntent = new Intent(this, LentActivity.class);
        lentIntent.putExtra("debugMode", debugMode);

        swLent.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // Changed status of lent switch to true (item is lent)
                    if (debugMode) {
                        String msg = "Item status was changed to lent.";
                        Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                        Log.d("USERACTION", msg);
                    }
                    startActivity(lentIntent);
                }
            }
        });

        // Getting data of choosed item from database and displaying these.
    }

    // Button listener for the DetailsActivity
    public void buttonOnClick(View v) {
        switch (v.getId()) {
            case R.id.btn_delete:
                //Button to delete item pressed.
                if (debugMode) {
                    String msg = "User deceided to delete this item.";
                    Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                    Log.d("USERACTION", msg);
                }
                //Security question before item delete
                final Intent deleteIntent = new Intent(this, DeleteActivity.class);
                deleteIntent.putExtra("debugMode", debugMode);
                startActivity(deleteIntent);
                break;
            case R.id.btn_edit:
                //Button to edit item pressed.
                if (debugMode) {
                    String msg = "User want to edit the information of this item.";
                    Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                    Log.d("USERACTION", msg);
                }
                break;
        }
    }
}
