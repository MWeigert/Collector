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

        Context ctx = this;
        DatabaseBase spInfo = new DatabaseBase();

        //Getting information regarding debug mode
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            debugMode = extras.getBoolean("debugMode");
        }

        //Filling genre spinner with information.
        //HAVE TO CHANCE THIS TO SQLITE TABLE (S.H. LANGUAGE SPINNER)
        Spinner spGenre = (Spinner) findViewById(R.id.spn_genre);
        ArrayAdapter<String> adapterGenre = new ArrayAdapter<String>(this, android.R.layout
                .simple_spinner_dropdown_item, spInfo.genreData);
        spGenre.setAdapter(adapterGenre);

        //Filling language spinner with information
        Spinner spLanguage = (Spinner) findViewById(R.id.spn_language);

        DatabaseOperations db = new DatabaseOperations(ctx, debugMode);
        Cursor crs = db.getLanguages(db);
        ArrayList<String> language = new ArrayList<String>();

        crs.moveToFirst();
        int index = crs.getColumnIndex(DatabaseInfo.LANGUAGE_LANGUAGE_COL);

        do {
            language.add(crs.getString(index));
        } while (crs.moveToNext());

        ArrayAdapter<String> adapterLanguage = new ArrayAdapter<String>(this, android.R.layout
                .simple_spinner_dropdown_item, language);
        spLanguage.setAdapter(adapterLanguage);

        //Filling year spinner with information
        Spinner spYear = (Spinner) findViewById(R.id.spn_year);
        ArrayAdapter<Integer>  adapterYear = new ArrayAdapter<Integer>(this, android.R.layout
                .simple_spinner_dropdown_item,spInfo.yearData);
        spYear.setAdapter(adapterYear);
    }
}