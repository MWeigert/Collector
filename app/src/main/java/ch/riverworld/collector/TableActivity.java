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

                Context ctx = this;
                db = new DatabaseOperations(ctx, debugMode);
                Cursor crs = db.getLanguages(db);

                Integer anz = crs.getCount();
                if (debugMode) {
                    String msg = "DATABASE: " + anz.toString() + " languages in table.";
                    Log.d("DATABASE", msg);
                    Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                }

                if (anz > 0) {
                    crs.moveToFirst();
                    indexLanguage = crs.getColumnIndex(DatabaseInfo.LANGUAGE_LANGUAGE_COL);

                    do {
                        language = crs.getString(indexLanguage);
                        if (debugMode) {
                            String msg = "DATABASE: Get " + language;
                            Log.d("DATABASE", msg);
                        }
                        languages.add(language);
                        if (debugMode) {
                            String msg = "Size of languages = " + languages.size();
                            Log.d("CODE", msg);
                        }
                    } while (crs.moveToNext());

                    if (debugMode) {
                        String msg = "Putting data in Adapter";
                        Log.d("ACTIVITY", msg);
                    }
                    final ArrayAdapter adapter = new ArrayAdapter(this,
                            android.R.layout.simple_list_item_1, languages);

                    LIST.setAdapter(adapter);
                }
                break;
        }
    }

    // Button Listener for the table activity
    public void buttonOnClick(View v) {
        if (debugMode) {
            String msg = "Entered button listener.";
            Log.d("CODE", msg);
            Toast.makeText(getBaseContext(),msg,Toast.LENGTH_LONG).show();
        }
        switch (v.getId()) {
            case R.id.btn_add_item:
                switch (table.toUpperCase()) {
                    case "LANGUAGE":
                        if (debugMode) {
                            String msg = "User choosed add item to languages table.";
                            Log.d("USER", msg);
                            Toast.makeText(getBaseContext(),msg,Toast.LENGTH_LONG).show();
                        }

                        //Pressed button to add new friend to the database.
                        String item = INPUT.getText().toString();
                        if (item.length()<=0) {
                            String msg = "Please enter a language.";
                            Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                        } else {
                            db.addLanguage(db, item);

                            if (debugMode) {
                                String msg = "Added: " + item;
                                Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                            }
                        }
                        finish();

                        break;
                }
                break;
            case R.id.btn_remove_item:
                switch (table.toUpperCase()) {
                    case "LANGUAGE":
                        if (debugMode) {
                            String msg = "User choosed remove item from languages table.";
                            Log.d("USER", msg);
                            Toast.makeText(getBaseContext(),msg,Toast.LENGTH_LONG).show();
                        }

                        //Pressed button to delete language from database.

                        AlertDialog.Builder langBuilder = new AlertDialog.Builder(TableActivity.this);
                        String msg = "Do you really want to delete " + selectedItem.toString();
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
                }
                break;
        }
    }
}