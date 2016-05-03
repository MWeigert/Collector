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
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private boolean debugMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Set debug mode to true to activate debug output in logcat and debug toast
        debugMode = true;
    }

    // Button listener for the MainActivity
    public void buttonOnClick(View v) {
        switch (v.getId()) {
            case R.id.btn_newItem:
                //Pressed button to add new item to the collection.
                final Intent addIntent = new Intent(this, ItemActivity.class);
                addIntent.putExtra("debugMode", debugMode);
                startActivity(addIntent);
                break;
            case R.id.btn_collection:
                //Pressed button do show total or filtered collection.
                final Intent codeIntent = new Intent(this, CollectionActivity.class);
                codeIntent.putExtra("debugMode", debugMode);
                startActivity(codeIntent);
                break;
            case R.id.btn_rental:
                //Pressed button to enter lend administration.
                final Intent rentalIntent=new Intent(this, RentalActivity.class);
                rentalIntent.putExtra("debugMode",debugMode);
                startActivity(rentalIntent);
                break;
            case R.id.btn_info:
                //Pressed button to display informations regarding this app.
                final Intent infoIntent = new Intent(this, InfoActivity.class);
                infoIntent.putExtra("debugMode", debugMode);
                startActivity(infoIntent);
                break;
        }
    }
}
