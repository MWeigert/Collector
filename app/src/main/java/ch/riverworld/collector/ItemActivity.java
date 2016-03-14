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

import android.content.Context;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

public class ItemActivity extends AppCompatActivity {

    private boolean debugMode = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        //Getting information regarding debug mode
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            debugMode = extras.getBoolean("debugMode");
        }

        //Filling genre spinner with information.
        DatabaseBase spInfo = new DatabaseBase();

        Spinner spGenre = (Spinner) findViewById(R.id.spn_genre);
        ArrayAdapter<String> adapterGenre = new ArrayAdapter<String>(this, android.R.layout
                .simple_spinner_dropdown_item, spInfo.genreData);
        spGenre.setAdapter(adapterGenre);

        Spinner spLanguage = (Spinner) findViewById(R.id.spn_language);

        //Filling language spinner with information
        Context ctx = this;
        DatabaseOperations db = new DatabaseOperations(ctx, debugMode);
        Cursor crs = db.getFriends(db);
        ArrayList<String> language = new ArrayList<String>();

        crs.moveToFirst();
        int indexFirstName = crs.getColumnIndex(DatabaseInfo.COL_FRIEND_FIRSTNAME);
        int indexLastName = crs.getColumnIndex(DatabaseInfo.COL_FRIEND_LASTNAME);

        do {
            language.add(crs.getString(indexLastName)+", "+crs.getString(indexFirstName));
        } while (crs.moveToNext());

        ArrayAdapter<String> adapterLanguage = new ArrayAdapter<String>(this, android.R.layout
                .simple_spinner_dropdown_item, language);

        spLanguage.setAdapter(adapterLanguage);
    }
}
