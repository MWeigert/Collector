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
                    String msg = selectedItem.toString() + " chose.";
                    Log.d("USERACTION", msg);
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
                    Log.d("USERACTION", "Table: Authors");
                }

                // Fill authors from database to list item
                String author;
                final ArrayList<String> authors = new ArrayList<String>();
                Cursor authorCrs = db.getAuthors(db);
                anz = authorCrs.getCount();

                if (debugMode) {
                    Log.d("TABAC", "DATABASE: " + anz.toString() + " authors in table.");
                }
                if (anz > 0) {
                    authorCrs.moveToFirst();
                    do {
                        int index = authorCrs.getColumnIndex(DatabaseInfo.AUTHORS_AUTHOR_COL);
                        if (debugMode) {
                            Log.d("TABAC", "Index (AUTHORS_AUTHOR): " + index);
                        }
                        author = authorCrs.getString(index);
                        if (debugMode) {
                            Log.d("TABAC", "DATABASE: Get " + author);
                        }
                        authors.add(author);
                        if (debugMode) {
                            Log.d("TABAC", "Size of authors = " + authors.size());
                        }
                    } while (authorCrs.moveToNext());

                    if (debugMode) {
                        Log.d("TABAC", "Putting data in Adapter");
                    }
                    final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, authors);

                    LIST.setAdapter(adapter);
                }
                break;
            case "DIRECTOR":
                if (debugMode) {
                    Log.d("USERACTION", "Table: Directors");
                }

                // Fill directors from database to list item
                String director;
                final ArrayList<String> directors = new ArrayList<String>();
                Cursor directorCrs = db.getDirectors(db);
                anz = directorCrs.getCount();

                if (debugMode) {
                    Log.d("TABAC", "DATABASE: " + anz.toString() + " directors in table.");
                }
                if (anz > 0) {
                    directorCrs.moveToFirst();
                    do {
                        int index = directorCrs.getColumnIndex(DatabaseInfo.DIRECTORS_DIRECTOR_COL);
                        director = directorCrs.getString(index);
                        if (debugMode) {
                            Log.d("TABAC", "DATABASE: Get " + director);
                        }
                        directors.add(director);
                        if (debugMode) {
                            Log.d("TABAC", "Size of authors = " + directors.size());
                        }
                    } while (directorCrs.moveToNext());
                    if (debugMode) {
                        Log.d("TABAC", "Putting data in Adapter");
                    }

                    final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, directors);
                    LIST.setAdapter(adapter);
                }
                break;
            case "GENRE":
                if (debugMode) {
                    Log.d("USERACTION", "Table: Genre");
                }
                // Fill genres from database to list item
                String genre;
                final ArrayList<String> genres = new ArrayList<String>();
                Cursor genreCrs = db.getGenres(db);
                anz = genreCrs.getCount();

                if (debugMode) {
                    Log.d("TABAC", "DATABASE: " + anz.toString() + " genres in table.");
                }

                if (anz > 0) {
                    genreCrs.moveToFirst();
                    do {
                        int index = genreCrs.getColumnIndex(DatabaseInfo.GENRES_GENRE_COL);
                        genre = genreCrs.getString(index);
                        if (debugMode) {
                            Log.d("TABAC", "DATABASE: Get " + genre);
                        }
                        genres.add(genre);
                        if (debugMode) {
                            Log.d("TABAC", "Size of genres = " + genres.size());
                        }
                    } while (genreCrs.moveToNext());
                    if (debugMode) {
                        Log.d("TABAC", "Putting data in Adapter");
                    }

                    final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, genres);
                    LIST.setAdapter(adapter);
                }
                break;
            case "LANGUAGE":
                if (debugMode) {
                    Log.d("USERACTION", "Table: Language");
                }

                // Fill languages from database to list item
                String language;
                final ArrayList<String> languages = new ArrayList<String>();
                Cursor languageCrs = db.getLanguages(db);
                anz = languageCrs.getCount();

                if (debugMode) {
                    Log.d("TABAC", "DATABASE: " + anz.toString() + " languages in table.");
                }
                if (anz > 0) {
                    languageCrs.moveToFirst();
                    do {
                        int index = languageCrs.getColumnIndex(DatabaseInfo.LANGUAGES_LANGUAGE_COL);
                        language = languageCrs.getString(index);
                        if (debugMode) {
                            Log.d("TABAC", "DATABASE: Get " + language);
                        }
                        languages.add(language);
                        if (debugMode) {
                            Log.d("TABAC", "Size of languages = " + languages.size());
                        }
                    } while (languageCrs.moveToNext());

                    if (debugMode) {
                        Log.d("TABAC", "Putting data in Adapter");
                    }

                    final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, languages);
                    LIST.setAdapter(adapter);
                }
                break;
            case "PUBLISHER":
                if (debugMode) {
                    Log.d("USERACTION", "Table: Publisher");
                }

                // Fill publishers from database to list item
                String publisher;
                final ArrayList<String> publishers = new ArrayList<String>();
                Cursor publisherCrs = db.getPublishers(db);
                anz = publisherCrs.getCount();

                if (debugMode) {
                    Log.d("TABAC", "DATABASE: " + anz.toString() + " publishers in table.");
                }
                if (anz > 0) {
                    publisherCrs.moveToFirst();
                    do {
                        int index = publisherCrs.getColumnIndex(DatabaseInfo.PUBLISHERS_PUBLISHER_COL);
                        publisher = publisherCrs.getString(index);
                        if (debugMode) {
                            Log.d("TABAC", "DATABASE: Get " + publisher);
                        }
                        publishers.add(publisher);
                        if (debugMode) {
                            Log.d("TABAC", "Size of systems = " + publishers.size());
                        }
                    } while (publisherCrs.moveToNext());

                    if (debugMode) {
                        Log.d("TABAC", "Putting data in Adapter");
                    }
                    final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,
                            publishers);
                    LIST.setAdapter(adapter);
                }
                break;
            case "STUDIO":
                if (debugMode) {
                    Log.d("USERACTION", "Table: Studio");
                }

                // Fill studios from database to list item
                String studio;
                final ArrayList<String> studios = new ArrayList<String>();
                Cursor studioCrs = db.getStudios(db);
                anz = studioCrs.getCount();

                if (debugMode) {
                    Log.d("TABAC", "DATABASE: " + anz.toString() + " studios in table.");
                }
                if (anz > 0) {
                    studioCrs.moveToFirst();
                    do {
                        int index = studioCrs.getColumnIndex(DatabaseInfo.STUDIOS_STUDIO_COL);
                        studio = studioCrs.getString(index);
                        if (debugMode) {
                            Log.d("TABAC", "DATABASE: Get " + studio);
                        }
                        studios.add(studio);
                        if (debugMode) {
                            Log.d("TABAC", "Size of studios = " + studios.size());
                        }
                    } while (studioCrs.moveToNext());
                    if (debugMode) {
                        Log.d("TABAC", "Putting data in Adapter");
                    }

                    final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, studios);
                    LIST.setAdapter(adapter);
                }
                break;
            case "SYSTEM":
                if (debugMode) {
                    Log.d("USERACTION", "Table: System");
                }
                // Fill systems from database to list item
                String system;
                final ArrayList<String> systems = new ArrayList<String>();
                Cursor systemCrs = db.getSystems(db);
                anz = systemCrs.getCount();

                if (debugMode) {
                    Log.d("TABAC", "DATABASE: " + anz.toString() + " systems in table.");
                }
                if (anz > 0) {
                    systemCrs.moveToFirst();
                    do {
                        int index = systemCrs.getColumnIndex(DatabaseInfo.SYSTEMS_SYSTEM_COL);
                        system = systemCrs.getString(index);
                        if (debugMode) {
                            Log.d("TABAC", "DATABASE: Get " + system);
                        }
                        systems.add(system);
                        if (debugMode) {
                            Log.d("TABAC", "Size of systems = " + systems.size());
                        }
                    } while (systemCrs.moveToNext());
                    if (debugMode) {
                        Log.d("TABAC", "Putting data in Adapter");
                    }

                    final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, systems);
                    LIST.setAdapter(adapter);
                }
                break;
        }
    }

    // Button Listener for the table activity
    public void buttonOnClick(View v) {

        if (debugMode) {
            Log.d("TABAC", "Entered button listener.");
        }

        String item;
        switch (v.getId()) {
            case R.id.btn_add_item:
                switch (table.toUpperCase()) {
                    case "AUTHOR":
                        //Pressed button to add new author to the database.
                        if (debugMode) {
                            Log.d("USERACTION", "User chose add item to authors table.");
                        }

                        item = INPUT.getText().toString();
                        if (item.length() <= 0) {
                            Toast.makeText(getBaseContext(), "Please enter author.", Toast.LENGTH_SHORT).show();
                        } else {
                            db.addAuthor(db, item);
                            if (debugMode) {
                                Log.d("TABAC", "Added: " + item);
                            }
                        }
                        finish();
                        break;
                    case "DIRECTOR":
                        //Pressed button to add new director to the database.
                        if (debugMode) {
                            Log.d("USERACTION", "User chose add item to directors table.");
                        }

                        item = INPUT.getText().toString();
                        if (item.length() <= 0) {
                            Toast.makeText(getBaseContext(), "Please enter director.", Toast.LENGTH_SHORT).show();
                        } else {
                            db.addDirector(db, item);
                            if (debugMode) {
                                Log.d("TABAC", "Added: " + item);
                            }
                        }
                        finish();
                        break;
                    case "GENRE":
                        //Pressed button to add new genre to the database.
                        if (debugMode) {
                            Log.d("USERACTION", "User chose add item to genre table.");
                        }

                        item = INPUT.getText().toString();
                        if (item.length() <= 0) {
                            Toast.makeText(getBaseContext(), "Please enter genre.", Toast.LENGTH_SHORT).show();
                        } else {
                            db.addGenre(db, item);
                            if (debugMode) {
                                Log.d("TABAC", "Added: " + item);
                            }
                        }
                        finish();
                        break;
                    case "LANGUAGE":
                        //Pressed button to add new language to the database.
                        if (debugMode) {
                            Log.d("USERACTION", "User chose add item to languages table.");
                        }

                        item = INPUT.getText().toString();
                        if (item.length() <= 0) {
                            Toast.makeText(getBaseContext(), "Please enter a language.", Toast.LENGTH_SHORT).show();
                        } else {
                            db.addLanguage(db, item);
                            if (debugMode) {
                                Log.d("TABAC", "Added: " + item);
                            }
                        }
                        finish();
                        break;
                    case "PUBLISHER":
                        //Pressed button to add new publisher to the database.
                        if (debugMode) {
                            Log.d("USERACTION", "User chose add item to publishers table.");
                        }

                        item = INPUT.getText().toString();
                        if (item.length() <= 0) {
                            Toast.makeText(getBaseContext(), "Please enter a publisher.", Toast.LENGTH_SHORT).show();
                        } else {
                            db.addPublisher(db, item);
                            if (debugMode) {
                                Log.d("TABAC", "Added: " + item);
                            }
                        }
                        finish();
                        break;
                    case "STUDIO":
                        //Pressed button to add new studio to the database.
                        if (debugMode) {
                            Log.d("USERACTION", "User chose add item to studios table.");
                        }

                        item = INPUT.getText().toString();
                        if (item.length() <= 0) {
                            Toast.makeText(getBaseContext(), "Please enter a studio.", Toast.LENGTH_SHORT).show();
                        } else {
                            db.addStudio(db, item);
                            if (debugMode) {
                                Log.d("TABAC", "Added: " + item);
                            }
                        }
                        finish();
                        break;
                    case "SYSTEM":
                        //Pressed button to add new system to the database.
                        if (debugMode) {
                            Log.d("USERACTION", "User chose add item to systems table.");
                        }

                        item = INPUT.getText().toString();
                        if (item.length() <= 0) {
                            Toast.makeText(getBaseContext(), "Please enter a system.", Toast.LENGTH_SHORT).show();
                        } else {
                            db.addSystem(db, item);
                            if (debugMode) {
                                Log.d("TABAC", "Added: " + item);
                            }
                        }
                        finish();
                        break;
                }
                break;
            case R.id.btn_remove_item:
                switch (table.toUpperCase()) {
                    case "AUTHOR":
                        //Pressed button to delete author from database
                        if (debugMode) {
                            Log.d("USERACTION", "User chose remove item from authors table.");
                        }

                        AlertDialog.Builder authorBuilder = new AlertDialog.Builder(TableActivity.this);
                        authorBuilder.setMessage("Do you really want to delete " + selectedItem.toString());

                        authorBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //dialog.cancel();
                                if (debugMode) {
                                    Log.d("USERACTION", "User chose yes.");
                                }
                                String author = selectedItem.toString();
                                Cursor crs = db.getAuthorRow(db, author, null);
                                crs.moveToFirst();
                                int id = crs.getInt(DatabaseInfo.TABLE_ID_COL);

                                boolean success = db.deleteAuthor(db, id);
                                if (success) {
                                    String msg = id + selectedItem.toString() + " is successfully deleted from " +
                                            "database.";
                                    Toast.makeText(getBaseContext(), msg, Toast.LENGTH_SHORT).show();
                                } else {
                                    String msg = "Could not delete: " + id + selectedItem.toString() + " from " +
                                            "database.";
                                    Toast.makeText(getBaseContext(), msg, Toast.LENGTH_SHORT).show();
                                }
                                finish();
                            }
                        });
                        authorBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //dialog.cancel();
                                if (debugMode) {
                                    Log.d("USERACTION", "User chose no.");
                                }
                            }
                        });

                        AlertDialog authorAlert = authorBuilder.create();
                        authorAlert.show();
                        break;
                    case "DIRECTOR":
                        //Pressed button to delete genre from database
                        if (debugMode) {
                            Log.d("USERACTION", "User chose remove item from directors table.");
                        }

                        AlertDialog.Builder directorBuilder = new AlertDialog.Builder(TableActivity.this);
                        directorBuilder.setMessage("Do you really want to delete " + selectedItem.toString());


                        directorBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //dialog.cancel();
                                if (debugMode) {
                                    Log.d("USERACTION", "User chose yes.");
                                }
                                String director = selectedItem.toString();
                                Cursor crs = db.getDirectorRow(db, director, null);
                                crs.moveToFirst();
                                int id = crs.getInt(DatabaseInfo.TABLE_ID_COL);
                                boolean success = db.deleteDirector(db, id);
                                if (success) {
                                    Toast.makeText(getBaseContext(), selectedItem.toString() + " is successfully " +
                                            "deleted from database.", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(getBaseContext(), "Could not delete: " + selectedItem.toString() +
                                            " from database.", Toast.LENGTH_SHORT).show();
                                }
                                finish();
                            }
                        });
                        directorBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //dialog.cancel();
                                if (debugMode) {
                                    Log.d("USERACTION", "User chose no.");
                                }
                            }
                        });
                        AlertDialog directorAlert = directorBuilder.create();
                        directorAlert.show();
                        break;
                    case "GENRE":
                        //Pressed button to delete genre from database
                        if (debugMode) {
                            Log.d("USERACTION", "User chose remove item from genres table.");
                        }

                        AlertDialog.Builder genreBuilder = new AlertDialog.Builder(TableActivity.this);
                        genreBuilder.setMessage("Do you really want to delete " + selectedItem.toString());

                        genreBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //dialog.cancel();
                                if (debugMode) {
                                    Log.d("USERACTION", "User chose yes.");
                                }
                                String genre = selectedItem.toString();
                                Cursor crs = db.getGenreRow(db, genre, null);
                                crs.moveToFirst();
                                int id = crs.getInt(DatabaseInfo.TABLE_ID_COL);
                                boolean success = db.deleteGenre(db, id);
                                if (success) {
                                    Toast.makeText(getBaseContext(), selectedItem.toString() + " is successfully " +
                                            "deleted from database.", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(getBaseContext(), "Could not delete: " + selectedItem.toString() +
                                            " from database.", Toast.LENGTH_SHORT).show();
                                }
                                finish();
                            }
                        });
                        genreBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //dialog.cancel();
                                if (debugMode) {
                                    Log.d("USERACTION", "User chose no.");
                                }
                            }
                        });
                        AlertDialog genreAlert = genreBuilder.create();
                        genreAlert.show();
                        break;
                    case "LANGUAGE":
                        //Pressed button to delete language from database.
                        if (debugMode) {
                            Log.d("USERACTION", "User chose remove item from languages table.");
                        }

                        AlertDialog.Builder langBuilder = new AlertDialog.Builder(TableActivity.this);
                        langBuilder.setMessage("Do you really want to delete " + selectedItem.toString());
                        langBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //dialog.cancel();
                                if (debugMode) {
                                    Log.d("USERACTION", "User chose yes.");
                                }
                                String language = selectedItem.toString();
                                Cursor crs = db.getLanguageRow(db, language, null);
                                int index = crs.getColumnIndex(DatabaseInfo.LANGUAGES_ID_COL);
                                crs.moveToFirst();
                                if (debugMode) {
                                    Log.d("TABAC", "Count: " + crs.getCount());
                                    Log.d("TABAC", "TYPE: " + crs.getType(index));
                                }
                                int id = crs.getInt(index);
                                boolean success = db.deleteLanguage(db, id);
                                if (success) {
                                    Toast.makeText(getBaseContext(), selectedItem.toString() + " is successfully " +
                                            "deleted from database.", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(getBaseContext(), "Could not delete: " + selectedItem.toString() +
                                            " from database.", Toast.LENGTH_SHORT).show();
                                }
                                finish();
                            }
                        });
                        langBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //dialog.cancel();
                                if (debugMode) {
                                    Log.d("USERACTION", "User chose no.");
                                }
                            }
                        });
                        AlertDialog langAlert = langBuilder.create();
                        langAlert.show();
                        break;
                    case "PUBLISHER":
                        //Pressed button to delete publisher from database.
                        if (debugMode) {
                            Log.d("USERACTION", "User chose remove item from publishers table.");
                        }

                        AlertDialog.Builder publisherBuilder = new AlertDialog.Builder(TableActivity.this);
                        publisherBuilder.setMessage("Do you really want to delete " + selectedItem.toString());

                        publisherBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //dialog.cancel();
                                if (debugMode) {
                                    Log.d("USERACTION", "User chose yes.");
                                }
                                String publisher = selectedItem.toString();
                                Cursor crs = db.getPublisherRow(db, publisher, null);
                                crs.moveToFirst();
                                int id = crs.getInt(DatabaseInfo.TABLE_ID_COL);
                                boolean success = db.deletePublisher(db, id);
                                if (success) {
                                    String msg = selectedItem.toString() + " is successfully deleted from database.";
                                    Toast.makeText(getBaseContext(), msg, Toast.LENGTH_SHORT).show();
                                } else {
                                    String msg = "Could not delete: " + selectedItem.toString() + " from database.";
                                    Toast.makeText(getBaseContext(), msg, Toast.LENGTH_SHORT).show();
                                }
                                finish();
                            }
                        });
                        publisherBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //dialog.cancel();
                                if (debugMode) {
                                    Log.d("USERACTION", "User chose no.");
                                }
                                String msg = "FALSE";
                                Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                            }
                        });

                        AlertDialog publisherAlert = publisherBuilder.create();
                        publisherAlert.show();
                        break;
                    case "STUDIO":
                        //Pressed button to delete studio from database.
                        if (debugMode) {
                            Log.d("USERACTION", "User chose remove item from studios table.");
                        }

                        AlertDialog.Builder studioBuilder = new AlertDialog.Builder(TableActivity.this);
                        studioBuilder.setMessage("Do you really want to delete " + selectedItem.toString());

                        studioBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //dialog.cancel();
                                if (debugMode) {
                                    Log.d("USERACTION", "User chose yes.");
                                }
                                String studio = selectedItem.toString();
                                Cursor crs = db.getStudioRow(db, studio, null);
                                crs.moveToFirst();
                                int id = crs.getInt(DatabaseInfo.TABLE_ID_COL);
                                boolean success = db.deleteStudio(db, id);
                                if (success) {
                                    Toast.makeText(getBaseContext(), selectedItem.toString() + " is successfully " +
                                            "deleted from database.", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(getBaseContext(), "Could not delete: " + selectedItem.toString() +
                                            " from database.", Toast.LENGTH_SHORT).show();
                                }
                                finish();
                            }
                        });
                        studioBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //dialog.cancel();
                                if (debugMode) {
                                    Log.d("USERACTION", "User chose no.");
                                }
                            }
                        });
                        AlertDialog studioAlert = studioBuilder.create();
                        studioAlert.show();
                        break;
                    case "SYSTEM":
                        //Pressed button to delete system from database.
                        if (debugMode) {
                            Log.d("USERACTION", "User chose remove item from systems table.");
                        }

                        AlertDialog.Builder sysBuilder = new AlertDialog.Builder(TableActivity.this);
                        sysBuilder.setMessage("Do you really want to delete " + selectedItem.toString());
                        sysBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //dialog.cancel();
                                if (debugMode) {
                                    Log.d("USERACTION", "User chose yes.");
                                }
                                String system = selectedItem.toString();
                                Cursor crs = db.getSystemRow(db, system, null);
                                crs.moveToFirst();
                                int id = crs.getInt(DatabaseInfo.TABLE_ID_COL);
                                boolean success = db.deleteSystem(db, id);
                                if (success) {
                                    String msg = selectedItem.toString() + " is successfully deleted from database.";
                                    Toast.makeText(getBaseContext(), msg, Toast.LENGTH_SHORT).show();
                                } else {
                                    String msg = "Could not delete: " + selectedItem.toString() + " from database.";
                                    Toast.makeText(getBaseContext(), msg, Toast.LENGTH_SHORT).show();
                                }
                                finish();
                            }
                        });
                        sysBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //dialog.cancel();
                                if (debugMode) {
                                    Log.d("USERACTION", "User chose no.");
                                }
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