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
    private Item selectedItem;
    private String mode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);

        //Getting information regarding debug mode
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            debugMode = extras.getBoolean("debugMode");
            mode=extras.getString("Mode");
        }

        list = (ListView) findViewById(R.id.lst_collection);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                selectedItem = (Item) list.getItemAtPosition(position);
                Intent detailsIntent = new Intent(CollectionActivity.this, DetailsActivity.class);
                detailsIntent.putExtra("debugMode", debugMode);
                detailsIntent.putExtra("itemID", selectedItem.getId());
                startActivity(detailsIntent);
            }
        });

        // Fill items from database to list item
        final ArrayList<Item> items = new ArrayList<Item>();

        Context ctx = this;
        DatabaseOperations db = new DatabaseOperations(ctx, debugMode);
        Integer anz;

        Cursor crs = db.getItems(db);
        crs.moveToFirst();
        anz = crs.getCount();

        if (debugMode) {
            Log.d("COLAC", "DATABASE: " + anz.toString() + " items in table.");
        }

        if (anz > 0) {
            int indexItemId = crs.getColumnIndex(DatabaseInfo.ITEMS_ID_COL);
            do {
                int id = crs.getInt(indexItemId);
                if (debugMode) {
                    Log.d("COLAC", "Adding item (ID: " + id + ") to ArrayList.");
                }
                Item item = new Item(ctx, id);
                items.add(item);
            } while (crs.moveToNext());

            if (debugMode) {
                Log.d("COLAC", "Putting data in Adapter");
            }
            final ArrayAdapter adapter = new ArrayAdapter(this,
                    android.R.layout.simple_list_item_1, items);

            list.setAdapter(adapter);
        }
    }
}
