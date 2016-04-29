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
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ItemActivity extends AppCompatActivity {

    private boolean debugMode = false;

    private EditText txtEAN;
    private EditText txtTitle;
    private RadioButton rbBook;
    private RadioButton rbMovie;
    private RadioButton rbGame;
    private Spinner spGenre;
    private Spinner spLanguage;
    private Spinner spYear;
    private CheckBox cbLent;
    private Spinner spPublisher;
    private Spinner spAuthor;
    private Spinner spSystem;
    private CheckBox cbDVD;
    private CheckBox cbBlueRay;
    private Spinner spStudio;
    private Spinner spDirector;
    private CheckBox cbFSK0;
    private CheckBox cbFSK6;
    private CheckBox cbFSK12;
    private CheckBox cbFSK16;
    private CheckBox cbFSK18;

    private DatabaseOperations db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        //Getting information regarding debug mode
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            debugMode = extras.getBoolean("debugMode");
        }

        txtEAN = (EditText) findViewById(R.id.tf_ean);
        txtTitle = (EditText) findViewById(R.id.tf_title);
        rbBook = (RadioButton) findViewById(R.id.rb_book);
        rbMovie = (RadioButton) findViewById(R.id.rb_movie);
        rbGame = (RadioButton) findViewById(R.id.rb_game);
        spGenre = (Spinner) findViewById(R.id.spn_genre);
        spLanguage = (Spinner) findViewById(R.id.spn_language);
        spYear = (Spinner) findViewById(R.id.spn_year);
        cbLent = (CheckBox) findViewById(R.id.cb_lend);
        spPublisher = (Spinner) findViewById(R.id.spn_publisher);
        spAuthor = (Spinner) findViewById(R.id.spn_author);
        spSystem = (Spinner) findViewById(R.id.spn_system);
        cbDVD = (CheckBox) findViewById(R.id.cb_dvd);
        cbBlueRay = (CheckBox) findViewById(R.id.cb_blueray);
        spStudio = (Spinner) findViewById(R.id.spn_studio);
        spDirector = (Spinner) findViewById(R.id.spn_director);
        cbFSK0 = (CheckBox) findViewById(R.id.cb_fsk0);
        cbFSK6 = (CheckBox) findViewById(R.id.cb_fsk6);
        cbFSK12 = (CheckBox) findViewById(R.id.cb_fsk12);
        cbFSK16 = (CheckBox) findViewById(R.id.cb_fsk16);
        cbFSK18 = (CheckBox) findViewById(R.id.cb_fsk18);

        Context ctx = this;
        DatabaseBase spInfo = new DatabaseBase();
        db = new DatabaseOperations(ctx, debugMode);
        Cursor crs;
        int index;

        // Filling author spinner with information
        crs = db.getAuthors(db);
        ArrayList<String> authors = new ArrayList<String>();

        crs.moveToFirst();
        index = crs.getColumnIndex(DatabaseInfo.AUTHORS_AUTHOR_COL);

        do {
            authors.add(crs.getString(index));
        } while (crs.moveToNext());

        ArrayAdapter<String> adapterAuthor = new ArrayAdapter<String>(this, android.R.layout
                .simple_spinner_dropdown_item, authors);
        spAuthor.setAdapter(adapterAuthor);

        // Filling director spinner with information
        crs = db.getDirectors(db);
        ArrayList<String> directors = new ArrayList<String>();

        crs.moveToFirst();
        index = crs.getColumnIndex(DatabaseInfo.DIRECTORS_DIRECTOR_COL);

        do {
            directors.add(crs.getString(index));
        } while (crs.moveToNext());

        ArrayAdapter<String> adapterDirector = new ArrayAdapter<String>(this, android.R.layout
                .simple_spinner_dropdown_item, directors);
        spDirector.setAdapter(adapterDirector);

        //Filling genre spinner with information.
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

        // Filling publisher spinner with information
        crs = db.getPublishers(db);
        ArrayList<String> publishers = new ArrayList<String>();

        crs.moveToFirst();
        index = crs.getColumnIndex(DatabaseInfo.PUBLISHERS_PUBLISHER_COL);

        do {
            publishers.add(crs.getString(index));
        } while (crs.moveToNext());

        ArrayAdapter<String> adapterPublisher = new ArrayAdapter<String>(this, android.R.layout
                .simple_spinner_dropdown_item, publishers);
        spPublisher.setAdapter(adapterPublisher);

        //Filling studio spinner with information
        crs = db.getStudios(db);
        ArrayList<String> studios = new ArrayList<String>();

        crs.moveToFirst();
        index = crs.getColumnIndex(DatabaseInfo.STUDIOS_STUDIO_COL);

        do {
            studios.add(crs.getString(index));
        } while (crs.moveToNext());

        ArrayAdapter<String> adapterStudios = new ArrayAdapter<String>(this, android.R.layout
                .simple_spinner_dropdown_item, studios);
        spStudio.setAdapter(adapterStudios);

        //Filling system spinner with information
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
        ArrayAdapter<Integer> adapterYear = new ArrayAdapter<Integer>(this, android.R.layout
                .simple_spinner_dropdown_item, spInfo.yearData);
        spYear.setAdapter(adapterYear);
    }

    // Button listener for the ItemActivity
    public void buttonOnClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add:
                //Pressed button to add new item to the collection.
                if (debugMode) {
                    String msg = "User pressed add button and entered add item.";
                    Log.d("USER", msg);
                }
                Item item = new Item();

                if (debugMode) {
                    String msg = "EAN Code: " + txtEAN.getText().toString();
                    Log.d("CODE", msg);
                    msg = "Title: " + txtTitle.getText().toString();
                    Log.d("CODE", msg);
                }

                // Check if either title or EAN is not null.
                String eanStr = txtEAN.getText().toString();
                String title = txtTitle.getText().toString();

                if (eanStr.isEmpty() && title.isEmpty()) {
                    String msg = "Either title or EAN code should have an value.";
                    Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                } else {

                    // Input check: Set EAN to 0 or value
                    if (eanStr.isEmpty()) {
                        item.setEAN(0);
                    } else {
                        item.setEAN(Long.parseLong(eanStr));
                    }

                    item.setTitle(txtTitle.getText().toString());
                    if (rbBook.isChecked()) {
                        item.setBook(true);
                        item.setMediaType("Book");
                    } else {
                        item.setBook(false);
                    }
                    if (rbMovie.isChecked()) {
                        item.setMovie(true);
                        item.setMediaType("Movie");
                    } else {
                        item.setMovie(false);
                    }
                    if (rbGame.isChecked()) {
                        item.setGame(true);
                        item.setMediaType("Game");
                    } else {
                        item.setGame(false);
                        item.setMediaType("NULL");
                    }

                    if (debugMode) {
                        String msg = "MediaType: " + item.getMediaType();
                        Log.d("DATABASE", msg);
                    }

                    // Get ID of choosen genre
                    String genre = spGenre.getSelectedItem().toString();
                    Cursor crs = db.getGenreID(db, genre);
                    crs.moveToFirst();
                    Integer index = crs.getColumnIndex(DatabaseInfo.GENRES_ID_COL);
                    Integer genreID = Integer.parseInt(crs.getString(index));

                    if (debugMode) {
                        String msg = "Genre: " + genre + " ID: " + genreID;
                        Log.d("DATABASE", msg);
                    }

                    item.setGenre_id(genreID);

                    // Get ID of choosen language
                    String language = spLanguage.getSelectedItem().toString();
                    crs = db.getLanguageID(db, language);
                    crs.moveToFirst();
                    index = crs.getColumnIndex(DatabaseInfo.LANGUAGE_ID_COL);
                    Integer languageId = Integer.parseInt(crs.getString(index));

                    if (debugMode) {
                        String msg = "Language: " + language + "ID: " + languageId;
                        Log.d("DATABASE", msg);
                    }

                    item.setLanguage_id(languageId);

                    // Get year from spinner
                    Integer year = Integer.parseInt(spYear.getSelectedItem().toString());

                    if (debugMode) {
                        String msg = "Year: " + year;
                        Log.d("DATABASE", msg);
                    }

                    item.setYear(year);

                    // Get lent status
                    if (cbLent.isChecked()) {
                        item.setLent(true);
                    } else {
                        item.setLent(false);
                    }

                    // Get ID of choosen publisher
                    String publisher = spPublisher.getSelectedItem().toString();
                    crs = db.getPublisherID(db, publisher);
                    crs.moveToFirst();
                    index = crs.getColumnIndex(DatabaseInfo.PUBLISHERS_ID_COL);
                    Integer publisherId = Integer.parseInt(crs.getString(index));

                    if (debugMode) {
                        String msg = "Publisher: " + publisher + "ID: " + publisherId;
                        Log.d("DATABASE", msg);
                    }

                    item.setPublisher_id(publisherId);

                    // Get ID of choosen author
                    String author = spAuthor.getSelectedItem().toString();
                    crs = db.getAuthorID(db, author);
                    crs.moveToFirst();
                    index = crs.getColumnIndex(DatabaseInfo.AUTHORS_ID_COL);
                    Integer authorId = Integer.parseInt(crs.getString(index));

                    if (debugMode) {
                        String msg = "Author: " + author + "ID: " + authorId;
                        Log.d("DATABASE", msg);
                    }

                    item.setAuthor_id(authorId);

                    // Get ID of choosen system
                    String system = spSystem.getSelectedItem().toString();
                    crs = db.getSystemID(db, system);
                    crs.moveToFirst();
                    index = crs.getColumnIndex(DatabaseInfo.SYSTEMS_ID_COL);
                    Integer systemId = Integer.parseInt(crs.getString(index));

                    if (debugMode) {
                        String msg = "System: " + system + "ID: " + systemId;
                        Log.d("DATABASE", msg);
                    }

                    item.setLanguage_id(systemId);

                    // Get status of DVD checkbox
                    if (cbDVD.isChecked()) {
                        item.setDvd(true);
                    } else {
                        item.setDvd(false);
                    }

                    // Get status of BlueRay checkbox
                    if (cbBlueRay.isChecked()) {
                        item.setBlueRay(true);
                    } else {
                        item.setBlueRay(false);
                    }

                    // Get ID of choosen studio
                    String studio = spStudio.getSelectedItem().toString();
                    crs = db.getStudioID(db, studio);
                    crs.moveToFirst();
                    index = crs.getColumnIndex(DatabaseInfo.STUDIOS_ID_COL);
                    Integer studioId = Integer.parseInt(crs.getString(index));

                    if (debugMode) {
                        String msg = "Studio: " + studio + "ID: " + studioId;
                        Log.d("DATABASE", msg);
                    }

                    item.setStudio_id(studioId);

                    // Get ID of choosen director
                    String director = spDirector.getSelectedItem().toString();
                    crs = db.getDirectorID(db, director);
                    crs.moveToFirst();
                    index = crs.getColumnIndex(DatabaseInfo.DIRECTORS_ID_COL);
                    Integer directorId = Integer.parseInt(crs.getString(index));

                    if (debugMode) {
                        String msg = "Director: " + director + "ID: " + directorId;
                        Log.d("DATABASE", msg);
                    }

                    item.setDirector_id(directorId);

                    // Get status of parental checkboxes and set maximum
                    if (cbFSK0.isChecked()) {
                        item.setFsk(0);
                    }
                    if (cbFSK6.isChecked()) {
                        item.setFsk(6);
                    }
                    if (cbFSK12.isChecked()) {
                        item.setFsk(12);
                    }
                    if (cbFSK16.isChecked()) {
                        item.setFsk(16);
                    }
                    if (cbFSK18.isChecked()) {
                        item.setFsk(18);
                    }

                    db.addItem(db, item);
                    finish();
                }
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