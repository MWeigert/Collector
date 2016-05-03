// ********************************************************************************************
// *                                                                                          *
// *                                        SEMESTERARBEIT                                    *
// *                                             ZHAW                                         *
// *                                                                                          *
// * Programmed by: Mathias Weigert                                                           *
// *       Version: 0.1                                                                       *
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class CollectionActivity extends AppCompatActivity {

    private Boolean debugMode;
    private ListView list;
    private Object selectedItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);

        //Getting information regarding debug mode
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            debugMode = extras.getBoolean("debugMode");
        }

        list = (ListView) findViewById(R.id.lst_collection);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                selectedItem = list.getItemAtPosition(position);
                Intent detailsIntent = new Intent(CollectionActivity.this, DetailsActivity.class);
                detailsIntent.putExtra("debugMode", debugMode);
                detailsIntent.putExtra("itemTitle", selectedItem.toString());
                startActivity(detailsIntent);
            }
        });

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
            Log.d("COLAC", "DATABASE: " + anz.toString() + " items in table.");
        }

        if (anz > 0) {
            titleCrs.moveToFirst();
            indexTitle = titleCrs.getColumnIndex(DatabaseInfo.ITEMS_TITLE_COL);

            do {
                title = titleCrs.getString(indexTitle);
                if (debugMode) {
                    Log.d("COLAC", "DATABASE: Get " + title);
                }
                titles.add(title);
                if (debugMode) {
                    Log.d("COLAC", "Size of titles = " + titles.size());
                }
            } while (titleCrs.moveToNext());

            if (debugMode) {
                Log.d("COLAC", "Putting data in Adapter");
            }
            final ArrayAdapter adapter = new ArrayAdapter(this,
                    android.R.layout.simple_list_item_1, titles);

            list.setAdapter(adapter);
        }
    }
}
