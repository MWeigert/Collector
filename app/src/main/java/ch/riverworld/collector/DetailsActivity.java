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

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class DetailsActivity extends AppCompatActivity {

    private boolean debugMode = false;
    private String itemTitle;
    private Item selectedItem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        //Getting information regarding debug mode
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            debugMode = extras.getBoolean("debugMode");
            this.itemTitle = extras.getString("itemTitle");
        }

        Context ctx = this;
        DatabaseOperations db = new DatabaseOperations(ctx, debugMode);
        selectedItem = new Item();

        Switch swLent = (Switch) findViewById(R.id.sw_lent);
        ImageView iView = (ImageView) findViewById(R.id.imageView);
        TextView eanTXT = (TextView) findViewById(R.id.txt_ean);
        TextView titleTXT = (TextView) findViewById(R.id.txt_title);
        TextView genreTXT = (TextView) findViewById(R.id.txt_genre);
        TextView yearTXT = (TextView) findViewById(R.id.txt_year);

        // Set functionality and listener to lent switch
        final Intent lentIntent = new Intent(this, LentActivity.class);
        lentIntent.putExtra("debugMode", debugMode);

        swLent.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // Changed status of lent switch to true (item is lent)
                    if (debugMode) {
                        String msg = "Item status was changed to lent.";
                        Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                        Log.d("USERACTION", msg);
                    }
                    startActivity(lentIntent);
                }
            }
        });

        // Getting data of choosed item from database and displaying these.
        if (debugMode) {
            String msg = "Item ID: " + db.getItemID(db, itemTitle);
            Log.d("DATABASE", msg);
        }

        // Need to save picture ID in database table items and refactor this switch
        int id = db.getItemID(db, itemTitle);
        switch (id) {
            case 1:
                iView.setBackgroundResource(R.drawable.p1);
                break;
            case 2:
                iView.setBackgroundResource(R.drawable.p2);
                break;
            case 3:
                iView.setBackgroundResource(R.drawable.p3);
                break;
        }

        Cursor crs = db.getItem(db, itemTitle);
        crs.moveToFirst();
        if (debugMode) {
            String msg = "";
            String[] columns = crs.getColumnNames();
            for (String column : columns) {
                msg = msg + column + ";";
            }
            Log.d("DATABASE", msg);
        }
        selectedItem.putItem(crs);

        crs = db.getGenre(db, selectedItem.getGenre_id());
        if (crs != null) {
            crs.moveToFirst();
            int index = crs.getColumnIndex(DatabaseInfo.GENRES_GENRE_COL);
            selectedItem.setGenre(crs.getString(index));
        }

        String value = String.valueOf(selectedItem.getEAN());
        eanTXT.setText("EAN: " + value);
        titleTXT.setText("Title: " + selectedItem.getTitle());
        genreTXT.setText("Genre: " + selectedItem.getGenre());
        yearTXT.setText("Year: " + String.valueOf(selectedItem.getYear()));
    }

    // Button listener for the DetailsActivity
    public void buttonOnClick(View v) {
        switch (v.getId()) {
            case R.id.btn_delete:
                //Button to delete item pressed.

                final AlertDialog.Builder itemBuilder = new AlertDialog.Builder(DetailsActivity.this);
                String msg = "Do you really want to delete " + itemTitle.toString();
                itemBuilder.setMessage(msg);


                itemBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //dialog.cancel();
                        if (debugMode) {
                            String msg = "User choosed yes.";
                            Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                            Log.d("USERACTION", msg);
                        }
                        // Put code to delete item here
                        finish();
                    }
                });

                itemBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //dialog.cancel();
                        if (debugMode) {
                            // Nothing happens here
                        }
                    }
                });

                AlertDialog itemAlert = itemBuilder.create();
                itemAlert.show();
                break;
            case R.id.btn_edit:
                //Button to edit item pressed.
                if (debugMode)

                {
                    msg = "User want to edit the information of this item.";
                    Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                    Log.d("USERACTION", msg);
                }

                break;
        }
    }
}
