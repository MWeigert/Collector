// ********************************************************************************************
// *                                                                                          *
// *                                        SEMESTERARBEIT                                    *
// *                                             ZHAW                                         *
// *                                                                                          *
// * Programmed by: Mathias Weigert                                                           *
// *       Version: 1.00                                                                      *
// *          Year: 2016                                                                      *
// *                                                                                          *
// ********************************************************************************************/

package ch.riverworld.collector;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

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
                // Pressed button to add new item to the collection.
                final Intent addIntent = new Intent(this, ItemActivity.class);
                addIntent.putExtra("debugMode", debugMode);
                addIntent.putExtra("Mode", "NewItem");
                startActivity(addIntent);
                break;
            case R.id.btn_collection:
                // Pressed button do show total or filtered collection.
                final Intent codeIntent = new Intent(this, CollectionActivity.class);
                codeIntent.putExtra("debugMode", debugMode);
                codeIntent.putExtra("Mode","FULL");
                startActivity(codeIntent);
                break;
            case R.id.btn_rental:
                // Pressed button to enter lend administration.
                final Intent rentalIntent = new Intent(this, RentalActivity.class);
                rentalIntent.putExtra("debugMode", debugMode);
                rentalIntent.putExtra("Mode", "Open");
                startActivity(rentalIntent);
                break;
            case R.id.btn_export:
                // Pressed button to enter database export.
                DatabaseOperations db = new DatabaseOperations(this, debugMode);
                try {
                    db.exportDatabaseCSV(db);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                // Starting file export via mail
                String[] TO = {""};
                String[] CC = {""};
                File exportDir = new File(Environment.getExternalStorageDirectory(), "");
                File file = new File(exportDir.getPath()+"/collector.csv");
                Uri uri = Uri.parse("file://" + file);

                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setData(Uri.parse("mailto:"));
                emailIntent.setType("text/plain");
                emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
                emailIntent.putExtra(Intent.EXTRA_CC, CC);
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Export Collector Database");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Email message goes here");
                emailIntent .putExtra(Intent.EXTRA_STREAM, uri);

                try {
                    startActivity(Intent.createChooser(emailIntent, "Send mail..."));
                    finish();
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(MainActivity.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.btn_info:
                // Pressed button to display information regarding this app.
                final Intent infoIntent = new Intent(this, InfoActivity.class);
                infoIntent.putExtra("debugMode", debugMode);
                startActivity(infoIntent);
                break;
        }
    }
}
