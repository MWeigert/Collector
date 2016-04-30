package ch.riverworld.collector;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class CollectionActivity extends AppCompatActivity {

    private Boolean debugMode;
    private ListView list;
    private Object selectedItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);

        list = (ListView) findViewById(R.id.lst_collection);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                selectedItem = list.getItemAtPosition(position);
                if (debugMode) {
                    String msg = selectedItem.toString() + " ausgewählt.";
                    Log.d("USERACTION", msg);
                    Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                }
            }
        });

        //Getting information regarding debug mode
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            debugMode = extras.getBoolean("debugMode");
        }

        // Fill items from database to list item
        String title;
        Integer indexTitle;

        final ArrayList<String> titles = new ArrayList<String>();

        Context ctx = this;
        DatabaseOperations db = new DatabaseOperations(ctx, debugMode);
        Integer anz;

        Cursor titleCrs = db.getItemTitles(db);
        anz = titleCrs.getCount();

        if (debugMode) {
            String msg = "DATABASE: " + anz.toString() + " items in table.";
            Log.d("DATABASE", msg);
            //Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
        }

        if (anz > 0) {
            titleCrs.moveToFirst();
            indexTitle = titleCrs.getColumnIndex(DatabaseInfo.ITEMS_TITLE_COL);

            do {
                title = titleCrs.getString(indexTitle);
                if (debugMode) {
                    String msg = "DATABASE: Get " + title;
                    Log.d("DATABASE", msg);
                }
                titles.add(title);
                if (debugMode) {
                    String msg = "Size of titles = " + titles.size();
                    Log.d("CODE", msg);
                }
            } while (titleCrs.moveToNext());

            if (debugMode) {
                String msg = "Putting data in Adapter";
                Log.d("ACTIVITY", msg);
            }
            final ArrayAdapter adapter = new ArrayAdapter(this,
                    android.R.layout.simple_list_item_1, titles);

            list.setAdapter(adapter);
        }
    }

    // Button listener for the CollectionActivity
    public void buttonOnClick(View v) {
        switch (v.getId()) {
            case R.id.btn_select:
                //Pressed button do confirm selected item.
                final Intent detailsIntent = new Intent(this, DetailsActivity.class);
                detailsIntent.putExtra("debugMode", debugMode);
                detailsIntent.putExtra("itemTitle",selectedItem.toString());
                startActivity(detailsIntent);
                break;
        }
    }
}
