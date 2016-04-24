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
            Toast.makeText(getBaseContext(),msg,Toast.LENGTH_LONG).show();
        }

        String item;

        switch (v.getId()) {
            case R.id.btn_add_item:
                switch (table.toUpperCase()) {
                    case "GENRE":
                        if (debugMode) {
                            msg = "User choosed add item to genre table.";
                            Log.d("USER", msg);
                            Toast.makeText(getBaseContext(),msg,Toast.LENGTH_LONG).show();
                        }

                        //Pressed button to add new genre to the database.
                        item = INPUT.getText().toString();
                        if (item.length()<=0) {
                            msg ="Please enter genre.";
                            Toast.makeText(getBaseContext(),msg,Toast.LENGTH_LONG).show();
                        } else {
                            db.addGenre(db,item);

                            if (debugMode) {
                                msg = "Added: " + item;
                                Toast.makeText(getBaseContext(),msg,Toast.LENGTH_LONG).show();
                            }
                        }
                        finish();
                        break;
                    case "LANGUAGE":
                        if (debugMode) {
                            msg = "User choosed add item to languages table.";
                            Log.d("USER", msg);
                            Toast.makeText(getBaseContext(),msg,Toast.LENGTH_LONG).show();
                        }

                        //Pressed button to add new language to the database.
                        item = INPUT.getText().toString();
                        if (item.length()<=0) {
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
                    case "SYSTEM":
                        if (debugMode) {
                            msg = "User choosed add item to systems table.";
                            Log.d("USER", msg);
                            Toast.makeText(getBaseContext(),msg,Toast.LENGTH_LONG).show();
                        }

                        //Pressed button to add new system to the database.
                        item = INPUT.getText().toString();
                        if (item.length()<=0) {
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
                    case "GENRE":
                        if (debugMode) {
                            msg = "User choosed remove item from genres table.";
                            Log.d("USER", msg);
                            Toast.makeText(getBaseContext(),msg,Toast.LENGTH_LONG).show();
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
                            Toast.makeText(getBaseContext(),msg,Toast.LENGTH_LONG).show();
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
                    case "SYSTEM":
                        if (debugMode) {
                            msg = "User choosed remove item from systems table.";
                            Log.d("USER", msg);
                            Toast.makeText(getBaseContext(),msg,Toast.LENGTH_LONG).show();
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