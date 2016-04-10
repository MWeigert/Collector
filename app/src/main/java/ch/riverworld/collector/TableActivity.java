package ch.riverworld.collector;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class TableActivity extends AppCompatActivity {

    private boolean debugMode = false;
    private String table;
    private ListView LIST;
    private DatabaseOperations db;

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

        switch (table.toUpperCase()) {
            case "LANGUAGE":
                if(debugMode) {
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
}
