package ch.riverworld.collector;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class TableActivity extends AppCompatActivity {

    private boolean debugMode = false;
    private String table;
    private ListView LIST;
    private EditText INPUT;
    private DatabaseOperations db;
    private Object selectedItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);

        //Getting information regarding debug mode
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            debugMode = extras.getBoolean("debugMode");
            table = extras.getString("tableName");
        }

        LIST = (ListView) findViewById(R.id.lst_table);
        LIST.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                selectedItem = LIST.getItemAtPosition(position);
                if (debugMode) {
                    String msg = selectedItem.toString() + " ausgewählt.";
                    Log.d("USERACTION", msg);
                    Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                }
            }
        });

        INPUT = (EditText) findViewById(R.id.txt_item);

        Context ctx = this;
        db = new DatabaseOperations(ctx, debugMode);
        Integer anz;

        // Fill list item
        switch (table.toUpperCase()) {
            case "AUTHOR":
                if (debugMode) {
                    String msg = "Table: Authors";
                    Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                    Log.d("USERACTION", msg);
                }

                // Fill authors from database to list item
                String author;
                Integer indexAuthor;

                final ArrayList<String> authors = new ArrayList<String>();

                Cursor authorCrs = db.getAuthors(db);

                anz = authorCrs.getCount();
                if (debugMode) {
                    String msg = "DATABASE: " + anz.toString() + " authors in table.";
                    Log.d("DATABASE", msg);
                    Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                }

                if (anz > 0) {
                    authorCrs.moveToFirst();
                    indexAuthor = authorCrs.getColumnIndex(DatabaseInfo.AUTHORS_AUTHOR_COL);

                    do {
                        author = authorCrs.getString(indexAuthor);
                        if (debugMode) {
                            String msg = "DATABASE: Get " + author;
                            Log.d("DATABASE", msg);
                        }
                        authors.add(author);
                        if (debugMode) {
                            String msg = "Size of authors = " + authors.size();
                            Log.d("CODE", msg);
                        }
                    } while (authorCrs.moveToNext());

                    if (debugMode) {
                        String msg = "Putting data in Adapter";
                        Log.d("ACTIVITY", msg);
                    }
                    final ArrayAdapter adapter = new ArrayAdapter(this,
                            android.R.layout.simple_list_item_1, authors);

                    LIST.setAdapter(adapter);
                }
                break;
            case "DIRECTOR":
                if (debugMode) {
                    String msg = "Table: Directors";
                    Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                    Log.d("USERACTION", msg);
                }

                // Fill directors from database to list item
                String director;
                Integer indexDirector;

                final ArrayList<String> directors = new ArrayList<String>();

                Cursor directorCrs = db.getDirectors(db);

                anz = directorCrs.getCount();
                if (debugMode) {
                    String msg = "DATABASE: " + anz.toString() + " directors in table.";
                    Log.d("DATABASE", msg);
                    Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                }

                if (anz > 0) {
                    directorCrs.moveToFirst();
                    indexDirector = directorCrs.getColumnIndex(DatabaseInfo.DIRECTORS_DIRECTOR_COL);

                    do {
                        director = directorCrs.getString(indexDirector);
                        if (debugMode) {
                            String msg = "DATABASE: Get " + director;
                            Log.d("DATABASE", msg);
                        }
                        directors.add(director);
                        if (debugMode) {
                            String msg = "Size of authors = " + directors.size();
                            Log.d("CODE", msg);
                        }
                    } while (directorCrs.moveToNext());

                    if (debugMode) {
                        String msg = "Putting data in Adapter";
                        Log.d("ACTIVITY", msg);
                    }
                    final ArrayAdapter adapter = new ArrayAdapter(this,
                            android.R.layout.simple_list_item_1, directors);

                    LIST.setAdapter(adapter);
                }
                break;
            case "GENRE":
                if (debugMode) {
                    String msg = "Table: Genre";
                    Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                    Log.d("USERACTION", msg);
                }
                // Fill genres from database to list item
                String genre;
                Integer indexGenre;

                final ArrayList<String> genres = new ArrayList<String>();

                Cursor genreCrs = db.getGenres(db);

                anz = genreCrs.getCount();
                if (debugMode) {
                    String msg = "DATABASE: " + anz.toString() + " genres in table.";
                    Log.d("DATABASE", msg);
                    Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                }

                if (anz > 0) {
                    genreCrs.moveToFirst();
                    indexGenre = genreCrs.getColumnIndex(DatabaseInfo.GENRES_GENRE_COL);

                    do {
                        genre = genreCrs.getString(indexGenre);
                        if (debugMode) {
                            String msg = "DATABASE: Get " + genre;
                            Log.d("DATABASE", msg);
                        }
                        genres.add(genre);
                        if (debugMode) {
                            String msg = "Size of genres = " + genres.size();
                            Log.d("CODE", msg);
                        }
                    } while (genreCrs.moveToNext());

                    if (debugMode) {
                        String msg = "Putting data in Adapter";
                        Log.d("ACTIVITY", msg);
                    }
                    final ArrayAdapter adapter = new ArrayAdapter(this,
                            android.R.layout.simple_list_item_1, genres);

                    LIST.setAdapter(adapter);
                }
                break;
            case "LANGUAGE":
                if (debugMode) {
                    String msg = "Table: Language";
                    Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                    Log.d("USERACTION", msg);
                }

                // Fill languages from database to list item
                String language;
                Integer indexLanguage;

                final ArrayList<String> languages = new ArrayList<String>();

                Cursor languageCrs = db.getLanguages(db);

                anz = languageCrs.getCount();
                if (debugMode) {
                    String msg = "DATABASE: " + anz.toString() + " languages in table.";
                    Log.d("DATABASE", msg);
                    Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                }

                if (anz > 0) {
                    languageCrs.moveToFirst();
                    indexLanguage = languageCrs.getColumnIndex(DatabaseInfo.LANGUAGE_LANGUAGE_COL);

                    do {
                        language = languageCrs.getString(indexLanguage);
                        if (debugMode) {
                            String msg = "DATABASE: Get " + language;
                            Log.d("DATABASE", msg);
                        }
                        languages.add(language);
                        if (debugMode) {
                            String msg = "Size of languages = " + languages.size();
                            Log.d("CODE", msg);
                        }
                    } while (languageCrs.moveToNext());

                    if (debugMode) {
                        String msg = "Putting data in Adapter";
                        Log.d("ACTIVITY", msg);
                    }
                    final ArrayAdapter adapter = new ArrayAdapter(this,
                            android.R.layout.simple_list_item_1, languages);

                    LIST.setAdapter(adapter);
                }
                break;
            case "PARENTAL":
                if (debugMode) {
                    String msg = "Table: Parental";
                    Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                    Log.d("USERACTION", msg);
                }
                // Fill parental items from database to list item
                String parental;
                Integer indexParental;

                final ArrayList<String> parentals = new ArrayList<String>();

                Cursor parentalCrs = db.getParental(db);

                anz = parentalCrs.getCount();
                if (debugMode) {
                    String msg = "DATABASE: " + anz.toString() + " parental informations in table.";
                    Log.d("DATABASE", msg);
                    Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                }
                if (anz > 0) {
                    parentalCrs.moveToFirst();
                    indexParental = parentalCrs.getColumnIndex(DatabaseInfo.PARENTAL_PARENTAL_COL);

                    do {
                        parental = parentalCrs.getString(indexParental);
                        if (debugMode) {
                            String msg = "DATABASE: Get " + parental;
                            Log.d("DATABASE", msg);
                        }
                        parentals.add(parental);
                        if (debugMode) {
                            String msg = "Size of parental = " + parentals.size();
                            Log.d("CODE", msg);
                        }
                    } while (parentalCrs.moveToNext());

                    if (debugMode) {
                        String msg = "Putting data in Adapter";
                        Log.d("ACTIVITY", msg);
                    }
                    final ArrayAdapter adapter = new ArrayAdapter(this,
                            android.R.layout.simple_list_item_1, parentals);

                    LIST.setAdapter(adapter);
                }
                break;
            case "PUBLISHER":
                if (debugMode) {
                    String msg = "Table: Publisher";
                    Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                    Log.d("USERACTION", msg);
                }
                // Fill publishers from database to list item
                String publisher;
                Integer indexPublisher;

                final ArrayList<String> publishers = new ArrayList<String>();

                Cursor publisherCrs = db.getPublishers(db);

                anz = publisherCrs.getCount();
                if (debugMode) {
                    String msg = "DATABASE: " + anz.toString() + " publishers in table.";
                    Log.d("DATABASE", msg);
                    Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                }

                if (anz > 0) {
                    publisherCrs.moveToFirst();
                    indexPublisher = publisherCrs.getColumnIndex(DatabaseInfo.PUBLISHERS_PUBLISHER_COL);

                    do {
                        publisher = publisherCrs.getString(indexPublisher);
                        if (debugMode) {
                            String msg = "DATABASE: Get " + publisher;
                            Log.d("DATABASE", msg);
                        }
                        publishers.add(publisher);
                        if (debugMode) {
                            String msg = "Size of systems = " + publishers.size();
                            Log.d("CODE", msg);
                        }
                    } while (publisherCrs.moveToNext());

                    if (debugMode) {
                        String msg = "Putting data in Adapter";
                        Log.d("ACTIVITY", msg);
                    }
                    final ArrayAdapter adapter = new ArrayAdapter(this,
                            android.R.layout.simple_list_item_1, publishers);

                    LIST.setAdapter(adapter);
                }
                break;
            case "STUDIO":
                if (debugMode) {
                    String msg = "Table: Studio";
                    Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                    Log.d("USERACTION", msg);
                }
                // Fill studios from database to list item
                String studio;
                Integer indexStudio;

                final ArrayList<String> studios = new ArrayList<String>();

                Cursor studioCrs = db.getStudios(db);

                anz = studioCrs.getCount();
                if (debugMode) {
                    String msg = "DATABASE: " + anz.toString() + " studios in table.";
                    Log.d("DATABASE", msg);
                    Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                }

                if (anz > 0) {
                    studioCrs.moveToFirst();
                    indexStudio = studioCrs.getColumnIndex(DatabaseInfo.STUDIOS_STUDIO_COL);

                    do {
                        studio = studioCrs.getString(indexStudio);
                        if (debugMode) {
                            String msg = "DATABASE: Get " + studio;
                            Log.d("DATABASE", msg);
                        }
                        studios.add(studio);
                        if (debugMode) {
                            String msg = "Size of studios = " + studios.size();
                            Log.d("CODE", msg);
                        }
                    } while (studioCrs.moveToNext());

                    if (debugMode) {
                        String msg = "Putting data in Adapter";
                        Log.d("ACTIVITY", msg);
                    }
                    final ArrayAdapter adapter = new ArrayAdapter(this,
                            android.R.layout.simple_list_item_1, studios);

                    LIST.setAdapter(adapter);
                }
                break;
            case "SYSTEM":
                if (debugMode) {
                    String msg = "Table: System";
                    Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                    Log.d("USERACTION", msg);
                }
                // Fill systems from database to list item
                String system;
                Integer indexSystem;

                final ArrayList<String> systems = new ArrayList<String>();

                Cursor systemCrs = db.getSystems(db);

                anz = systemCrs.getCount();
                if (debugMode) {
                    String msg = "DATABASE: " + anz.toString() + " systems in table.";
                    Log.d("DATABASE", msg);
                    Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                }

                if (anz > 0) {
                    systemCrs.moveToFirst();
                    indexSystem = systemCrs.getColumnIndex(DatabaseInfo.SYSTEMS_SYSTEM_COL);

                    do {
                        system = systemCrs.getString(indexSystem);
                        if (debugMode) {
                            String msg = "DATABASE: Get " + system;
                            Log.d("DATABASE", msg);
                        }
                        systems.add(system);
                        if (debugMode) {
                            String msg = "Size of systems = " + systems.size();
                            Log.d("CODE", msg);
                        }
                    } while (systemCrs.moveToNext());

                    if (debugMode) {
                        String msg = "Putting data in Adapter";
                        Log.d("ACTIVITY", msg);
                    }
                    final ArrayAdapter adapter = new ArrayAdapter(this,
                            android.R.layout.simple_list_item_1, systems);

                    LIST.setAdapter(adapter);
                }
                break;
        }
    }

    // Button Listener for the table activity
    public void buttonOnClick(View v) {
        String msg;

        if (debugMode) {
            msg = "Entered button listener.";
            Log.d("CODE", msg);
            Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
        }

        String item;

        switch (v.getId()) {
            case R.id.btn_add_item:
                switch (table.toUpperCase()) {
                    case "AUTHOR":
                        if (debugMode) {
                            msg = "User choosed add item to authors table.";
                            Log.d("USER", msg);
                            Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                        }

                        //Pressed button to add new author to the database.
                        item = INPUT.getText().toString();
                        if (item.length() <= 0) {
                            msg = "Please enter author.";
                            Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                        } else {
                            db.addAuthor(db, item);

                            if (debugMode) {
                                msg = "Added: " + item;
                                Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                            }
                        }
                        finish();
                        break;
                    case "DIRECTOR":
                        if (debugMode) {
                            msg = "User choosed add item to directors table.";
                            Log.d("USER", msg);
                            Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                        }

                        //Pressed button to add new director to the database.
                        item = INPUT.getText().toString();
                        if (item.length() <= 0) {
                            msg = "Please enter director.";
                            Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                        } else {
                            db.addDirector(db, item);

                            if (debugMode) {
                                msg = "Added: " + item;
                                Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                            }
                        }
                        finish();
                        break;
                    case "GENRE":
                        if (debugMode) {
                            msg = "User choosed add item to genre table.";
                            Log.d("USER", msg);
                            Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                        }

                        //Pressed button to add new genre to the database.
                        item = INPUT.getText().toString();
                        if (item.length() <= 0) {
                            msg = "Please enter genre.";
                            Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                        } else {
                            db.addGenre(db, item);

                            if (debugMode) {
                                msg = "Added: " + item;
                                Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                            }
                        }
                        finish();
                        break;
                    case "LANGUAGE":
                        if (debugMode) {
                            msg = "User choosed add item to languages table.";
                            Log.d("USER", msg);
                            Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                        }

                        //Pressed button to add new language to the database.
                        item = INPUT.getText().toString();
                        if (item.length() <= 0) {
                            msg = "Please enter a language.";
                            Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                        } else {
                            db.addLanguage(db, item);

                            if (debugMode) {
                                msg = "Added: " + item;
                                Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                            }
                        }
                        finish();
                        break;
                    case "PARENTAL":
                        if (debugMode) {
                            msg = "User choosed add item to parental table.";
                            Log.d("USER", msg);
                            Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                        }

                        //Pressed button to add new parental information to the database.
                        item = INPUT.getText().toString();
                        if (item.length() <= 0) {
                            msg = "Please enter a parental information.";
                            Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                        } else {
                            db.addParental(db, item);

                            if (debugMode) {
                                msg = "Added: " + item;
                                Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                            }
                        }
                        finish();
                        break;
                    case "PUBLISHER":
                        if (debugMode) {
                            msg = "User choosed add item to publishers table.";
                            Log.d("USER", msg);
                            Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                        }

                        //Pressed button to add new publisher to the database.
                        item = INPUT.getText().toString();
                        if (item.length() <= 0) {
                            msg = "Please enter a publisher.";
                            Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                        } else {
                            db.addPublisher(db, item);

                            if (debugMode) {
                                msg = "Added: " + item;
                                Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                            }
                        }
                        finish();
                        break;
                    case "STUDIO":
                        if (debugMode) {
                            msg = "User choosed add item to studios table.";
                            Log.d("USER", msg);
                            Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                        }

                        //Pressed button to add new studio to the database.
                        item = INPUT.getText().toString();
                        if (item.length() <= 0) {
                            msg = "Please enter a studio.";
                            Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                        } else {
                            db.addStudio(db, item);

                            if (debugMode) {
                                msg = "Added: " + item;
                                Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                            }
                        }
                        finish();
                        break;
                    case "SYSTEM":
                        if (debugMode) {
                            msg = "User choosed add item to systems table.";
                            Log.d("USER", msg);
                            Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                        }

                        //Pressed button to add new system to the database.
                        item = INPUT.getText().toString();
                        if (item.length() <= 0) {
                            msg = "Please enter a system.";
                            Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                        } else {
                            db.addSystem(db, item);

                            if (debugMode) {
                                msg = "Added: " + item;
                                Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                            }
                        }
                        finish();
                        break;
                }
                break;
            case R.id.btn_remove_item:
                switch (table.toUpperCase()) {
                    case "AUTHOR":
                        if (debugMode) {
                            msg = "User choosed remove item from authors table.";
                            Log.d("USER", msg);
                            Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                        }

                        //Pressed button to delete author from database
                        AlertDialog.Builder authorBuilder = new AlertDialog.Builder(TableActivity.this);
                        msg = "Do you really want to delete " + selectedItem.toString();
                        authorBuilder.setMessage(msg);


                        authorBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //dialog.cancel();
                                if (debugMode) {
                                    String msg = "User choosed yes.";
                                    Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                                    Log.d("USERACTION", msg);
                                }
                                String author = selectedItem.toString();
                                boolean sucess = db.deleteAuthor(db, author);
                                if (sucess) {
                                    String msg = selectedItem.toString() + " is sucessfully deleted from database.";
                                    Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                                } else {
                                    String msg = "Could not delete: " + selectedItem.toString() + " from database.";
                                    Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                                }
                                finish();
                            }
                        });

                        authorBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //dialog.cancel();
                                if (debugMode) {
                                    String msg = "User choosed no.";
                                    Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                                    Log.d("USERACTION", msg);
                                }
                                String msg = "FALSE";
                                Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                            }
                        });

                        AlertDialog authorAlert = authorBuilder.create();
                        authorAlert.show();
                        break;
                    case "DIRECTOR":
                        if (debugMode) {
                            msg = "User choosed remove item from directors table.";
                            Log.d("USER", msg);
                            Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                        }

                        //Pressed button to delete genre from database
                        AlertDialog.Builder directorBuilder = new AlertDialog.Builder(TableActivity.this);
                        msg = "Do you really want to delete " + selectedItem.toString();
                        directorBuilder.setMessage(msg);


                        directorBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //dialog.cancel();
                                if (debugMode) {
                                    String msg = "User choosed yes.";
                                    Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                                    Log.d("USERACTION", msg);
                                }
                                String director = selectedItem.toString();
                                boolean sucess = db.deleteDirector(db, director);
                                if (sucess) {
                                    String msg = selectedItem.toString() + " is sucessfully deleted from database.";
                                    Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                                } else {
                                    String msg = "Could not delete: " + selectedItem.toString() + " from database.";
                                    Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                                }
                                finish();
                            }
                        });

                        directorBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //dialog.cancel();
                                if (debugMode) {
                                    String msg = "User choosed no.";
                                    Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                                    Log.d("USERACTION", msg);
                                }
                                String msg = "FALSE";
                                Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                            }
                        });

                        AlertDialog directorAlert = directorBuilder.create();
                        directorAlert.show();
                        break;
                    case "GENRE":
                        if (debugMode) {
                            msg = "User choosed remove item from genres table.";
                            Log.d("USER", msg);
                            Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                        }

                        //Pressed button to delete genre from database
                        AlertDialog.Builder genreBuilder = new AlertDialog.Builder(TableActivity.this);
                        msg = "Do you really want to delete " + selectedItem.toString();
                        genreBuilder.setMessage(msg);


                        genreBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //dialog.cancel();
                                if (debugMode) {
                                    String msg = "User choosed yes.";
                                    Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                                    Log.d("USERACTION", msg);
                                }
                                String genre = selectedItem.toString();
                                boolean sucess = db.deleteGenre(db, genre);
                                if (sucess) {
                                    String msg = selectedItem.toString() + " is sucessfully deleted from database.";
                                    Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                                } else {
                                    String msg = "Could not delete: " + selectedItem.toString() + " from database.";
                                    Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                                }
                                finish();
                            }
                        });

                        genreBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //dialog.cancel();
                                if (debugMode) {
                                    String msg = "User choosed no.";
                                    Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                                    Log.d("USERACTION", msg);
                                }
                                String msg = "FALSE";
                                Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                            }
                        });

                        AlertDialog genreAlert = genreBuilder.create();
                        genreAlert.show();
                        break;
                    case "LANGUAGE":
                        if (debugMode) {
                            msg = "User choosed remove item from languages table.";
                            Log.d("USER", msg);
                            Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                        }

                        //Pressed button to delete language from database.
                        AlertDialog.Builder langBuilder = new AlertDialog.Builder(TableActivity.this);
                        msg = "Do you really want to delete " + selectedItem.toString();
                        langBuilder.setMessage(msg);


                        langBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //dialog.cancel();
                                if (debugMode) {
                                    String msg = "User choosed yes.";
                                    Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                                    Log.d("USERACTION", msg);
                                }
                                String language = selectedItem.toString();
                                boolean sucess = db.deleteLanguage(db, language);
                                if (sucess) {
                                    String msg = selectedItem.toString() + " is sucessfully deleted from database.";
                                    Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                                } else {
                                    String msg = "Could not delete: " + selectedItem.toString() + " from database.";
                                    Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                                }
                                finish();
                            }
                        });

                        langBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //dialog.cancel();
                                if (debugMode) {
                                    String msg = "User choosed no.";
                                    Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                                    Log.d("USERACTION", msg);
                                }
                                String msg = "FALSE";
                                Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                            }
                        });

                        AlertDialog langAlert = langBuilder.create();
                        langAlert.show();

                        break;
                    case "PARENTAL":
                        if (debugMode) {
                            msg = "User choosed remove item from parental table.";
                            Log.d("USER", msg);
                            Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                        }

                        //Pressed button to delete language from database.
                        AlertDialog.Builder parentalBuilder = new AlertDialog.Builder(TableActivity.this);
                        msg = "Do you really want to delete " + selectedItem.toString();
                        parentalBuilder.setMessage(msg);


                        parentalBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //dialog.cancel();
                                if (debugMode) {
                                    String msg = "User choosed yes.";
                                    Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                                    Log.d("USERACTION", msg);
                                }
                                String parental = selectedItem.toString();
                                boolean sucess = db.deleteParental(db, parental);
                                if (sucess) {
                                    String msg = selectedItem.toString() + " is sucessfully deleted from database.";
                                    Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                                } else {
                                    String msg = "Could not delete: " + selectedItem.toString() + " from database.";
                                    Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                                }
                                finish();
                            }
                        });

                        parentalBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //dialog.cancel();
                                if (debugMode) {
                                    String msg = "User choosed no.";
                                    Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                                    Log.d("USERACTION", msg);
                                }
                                String msg = "FALSE";
                                Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                            }
                        });

                        AlertDialog parentalAlert = parentalBuilder.create();
                        parentalAlert.show();
                        break;
                    case "PUBLISHER":
                        if (debugMode) {
                            msg = "User choosed remove item from publishers table.";
                            Log.d("USER", msg);
                            Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                        }

                        //Pressed button to delete publisher from database.
                        AlertDialog.Builder publisherBuilder = new AlertDialog.Builder(TableActivity.this);
                        msg = "Do you really want to delete " + selectedItem.toString();
                        publisherBuilder.setMessage(msg);


                        publisherBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //dialog.cancel();
                                if (debugMode) {
                                    String msg = "User choosed yes.";
                                    Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                                    Log.d("USERACTION", msg);
                                }
                                String publisher = selectedItem.toString();
                                boolean sucess = db.deletePublisher(db, publisher);
                                if (sucess) {
                                    String msg = selectedItem.toString() + " is sucessfully deleted from database.";
                                    Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                                } else {
                                    String msg = "Could not delete: " + selectedItem.toString() + " from database.";
                                    Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                                }
                                finish();
                            }
                        });

                        publisherBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //dialog.cancel();
                                if (debugMode) {
                                    String msg = "User choosed no.";
                                    Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                                    Log.d("USERACTION", msg);
                                }
                                String msg = "FALSE";
                                Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                            }
                        });

                        AlertDialog publisherAlert = publisherBuilder.create();
                        publisherAlert.show();
                        break;
                    case "STUDIO":
                        if (debugMode) {
                            msg = "User choosed remove item from studios table.";
                            Log.d("USER", msg);
                            Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                        }

                        //Pressed button to delete studio from database.
                        AlertDialog.Builder studioBuilder = new AlertDialog.Builder(TableActivity.this);
                        msg = "Do you really want to delete " + selectedItem.toString();
                        studioBuilder.setMessage(msg);

                        studioBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //dialog.cancel();
                                if (debugMode) {
                                    String msg = "User choosed yes.";
                                    Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                                    Log.d("USERACTION", msg);
                                }
                                String studio = selectedItem.toString();
                                boolean sucess = db.deleteStudio(db, studio);
                                if (sucess) {
                                    String msg = selectedItem.toString() + " is sucessfully deleted from database.";
                                    Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                                } else {
                                    String msg = "Could not delete: " + selectedItem.toString() + " from database.";
                                    Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                                }
                                finish();
                            }
                        });

                        studioBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //dialog.cancel();
                                if (debugMode) {
                                    String msg = "User choosed no.";
                                    Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                                    Log.d("USERACTION", msg);
                                }
                                String msg = "FALSE";
                                Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                            }
                        });

                        AlertDialog studioAlert = studioBuilder.create();
                        studioAlert.show();
                        break;
                    case "SYSTEM":
                        if (debugMode) {
                            msg = "User choosed remove item from systems table.";
                            Log.d("USER", msg);
                            Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                        }

                        //Pressed button to delete system from database.
                        AlertDialog.Builder sysBuilder = new AlertDialog.Builder(TableActivity.this);
                        msg = "Do you really want to delete " + selectedItem.toString();
                        sysBuilder.setMessage(msg);


                        sysBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //dialog.cancel();
                                if (debugMode) {
                                    String msg = "User choosed yes.";
                                    Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                                    Log.d("USERACTION", msg);
                                }
                                String system = selectedItem.toString();
                                boolean sucess = db.deleteSystem(db, system);
                                if (sucess) {
                                    String msg = selectedItem.toString() + " is sucessfully deleted from database.";
                                    Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                                } else {
                                    String msg = "Could not delete: " + selectedItem.toString() + " from database.";
                                    Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                                }
                                finish();
                            }
                        });

                        sysBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //dialog.cancel();
                                if (debugMode) {
                                    String msg = "User choosed no.";
                                    Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                                    Log.d("USERACTION", msg);
                                }
                                String msg = "FALSE";
                                Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                            }
                        });

                        AlertDialog sysAlert = sysBuilder.create();
                        sysAlert.show();
                        break;
                }
                break;
        }
    }
}