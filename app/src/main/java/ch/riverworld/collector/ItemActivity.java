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
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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
        DatabaseOperations db = new DatabaseOperations(ctx, debugMode);
        Cursor crs;
        int index;

        //Getting information regarding debug mode
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            debugMode = extras.getBoolean("debugMode");
        }

        //Filling genre spinner with information.
        Spinner spGenre = (Spinner) findViewById(R.id.spn_genre);
        crs = db.getGenres(db);
        ArrayList<String> genres = new ArrayList<String>();

        crs.moveToFirst();
        index = crs.getColumnIndex(DatabaseInfo.GENRES_GENRE_COL);

        do {
            genres.add(crs.getString(index));
        } while (crs.moveToNext());

        ArrayAdapter<String> adapterGenre = new ArrayAdapter<String>(this, android.R.layout
                .simple_spinner_dropdown_item, genres);
        spGenre.setAdapter(adapterGenre);

        //Filling language spinner with information
        Spinner spLanguage = (Spinner) findViewById(R.id.spn_language);
        crs = db.getLanguages(db);
        ArrayList<String> language = new ArrayList<String>();

        crs.moveToFirst();
        index = crs.getColumnIndex(DatabaseInfo.LANGUAGE_LANGUAGE_COL);

        do {
            language.add(crs.getString(index));
        } while (crs.moveToNext());

        ArrayAdapter<String> adapterLanguage = new ArrayAdapter<String>(this, android.R.layout
                .simple_spinner_dropdown_item, language);
        spLanguage.setAdapter(adapterLanguage);

        //Filling system spinner with information
        Spinner spSystem = (Spinner) findViewById(R.id.spn_system);
        crs = db.getSystems(db);
        ArrayList<String> systems = new ArrayList<String>();

        crs.moveToFirst();
        index = crs.getColumnIndex(DatabaseInfo.SYSTEMS_SYSTEM_COL);

        do {
            systems.add(crs.getString(index));
        } while (crs.moveToNext());

        ArrayAdapter<String> adapterSystems = new ArrayAdapter<String>(this, android.R.layout
                .simple_spinner_dropdown_item, systems);
        spSystem.setAdapter(adapterSystems);

        //Filling year spinner with information
        Spinner spYear = (Spinner) findViewById(R.id.spn_year);
        ArrayAdapter<Integer>  adapterYear = new ArrayAdapter<Integer>(this, android.R.layout
                .simple_spinner_dropdown_item,spInfo.yearData);
        spYear.setAdapter(adapterYear);
    }

    // Button listener for the ItemActivity
    public void buttonOnClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add:
                //Pressed button to add new item to the collection.
                break;
            case R.id.btn_search:
                //Pressed button do show total or filtered collection.
                final Intent searchIntent = new Intent(this, FilterResult.class);
                searchIntent.putExtra("debugMode", debugMode);
                startActivity(searchIntent);
                break;
        }
    }
}