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
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class DetailsActivity extends AppCompatActivity {

    private boolean debugMode = false;
    private String itemTitle;


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
        int id = db.getItemID(db, itemTitle);
        Item selectedItem = new Item(ctx);
        Cursor crs = db.getItemRow(db, id);
        crs.moveToFirst();
        selectedItem.putItem(crs);

        Switch swLent = (Switch) findViewById(R.id.sw_lent);
        ImageView iView = (ImageView) findViewById(R.id.imageView);
        TextView eanTXT = (TextView) findViewById(R.id.txt_ean);
        TextView titleTXT = (TextView) findViewById(R.id.txt_title);
        TextView genreTXT = (TextView) findViewById(R.id.txt_genre);
        TextView yearTXT = (TextView) findViewById(R.id.txt_year);
        TextView languageTXT = (TextView) findViewById(R.id.txt_language);
        RadioButton bookRB = (RadioButton) findViewById(R.id.rb_detail_book);
        RadioButton movieRB = (RadioButton) findViewById(R.id.rb_detail_movie);
        RadioButton gameRB = (RadioButton) findViewById(R.id.rb_detail_game);
        TextView publisherTXT = (TextView) findViewById(R.id.txt_publisher);
        TextView authorTXT = (TextView) findViewById(R.id.txt_author);
        CheckBox dvdCB = (CheckBox) findViewById(R.id.cb_details_dvd);
        CheckBox blurayCB = (CheckBox) findViewById(R.id.cb_details_blueray);
        TextView directorTXT = (TextView) findViewById(R.id.txt_director);
        TextView studioTXT = (TextView) findViewById(R.id.txt_studio);
        TextView systemTXT = (TextView) findViewById(R.id.txt_system);
        CheckBox fsk0CB = (CheckBox) findViewById(R.id.cb_details_0);
        CheckBox fsk6CB = (CheckBox) findViewById(R.id.cb_details_6);
        CheckBox fsk12CB = (CheckBox) findViewById(R.id.cb_details_12);
        CheckBox fsk16CB = (CheckBox) findViewById(R.id.cb_details_16);
        CheckBox fsk18CB = (CheckBox) findViewById(R.id.cb_details_18);

        // Set functionality and listener to lent switch
        if (swLent != null) {
            if (selectedItem.isLent()) {
                swLent.setChecked(true);
            } else swLent.setChecked(false);
            final Intent lentIntent = new Intent(this, LentActivity.class);
            lentIntent.putExtra("debugMode", debugMode);
            lentIntent.putExtra("isLent", selectedItem.isLent());
            lentIntent.putExtra("itemID", selectedItem.getId());

            swLent.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        // Changed status of lent switch to true (item is lent)
                        if (debugMode) {
                            Log.d("USERACTION", "Item status was changed to lent.");
                        }
                    } else {
                        // Changed status of lent switch to false (item is back)
                        if (debugMode) {
                            Log.d("USERACTION", "Item status was changed to lent.");
                        }
                    }
                    startActivity(lentIntent);
                }
            });
        }

        // Getting data of chosen item from database and displaying these.
        if (debugMode) {
            String msg = "Item ID: " + db.getItemID(db, itemTitle);
            Log.d("DATABASE", msg);
        }

        // Need to save picture ID in database table items and refactor this switch
        id = db.getItemID(db, itemTitle);
        switch (id) {
            case 1:
                assert iView != null;
                iView.setBackgroundResource(R.drawable.p1);
                break;
            case 2:
                assert iView != null;
                iView.setBackgroundResource(R.drawable.p2);
                break;
            case 3:
                assert iView != null;
                iView.setBackgroundResource(R.drawable.p3);
                break;
        }

        //Cursor crs = db.getItem(db, itemTitle);
        //crs.moveToFirst();

        if (debugMode) {
            String msg = "";
            String[] columns = crs.getColumnNames();
            for (String column : columns) {
                msg = msg + column + ";";
            }
            Log.d("DETAC", msg);
        }
        //selectedItem.putItem(crs);

        String value = String.valueOf(selectedItem.getEAN());
        assert eanTXT != null;
        eanTXT.setText(String.format("EAN: %s", value));
        titleTXT.setText("Title: " + selectedItem.getTitle());
        genreTXT.setText("Genre: " + selectedItem.getGenre());
        yearTXT.setText("Year: " + String.valueOf(selectedItem.getYear()));
        languageTXT.setText("Language: " + selectedItem.getLanguage());
        if (debugMode) {
            String msg = "Book: " + selectedItem.isBook() + " Movie: " + selectedItem.isMovie() + " Game: " +
                    selectedItem.isGame() + " MediaType: " + selectedItem.getMediaType();
            Log.d("DATABASE", msg);
        }
        if (selectedItem.isBook()) {
            bookRB.setChecked(true);
        } else bookRB.setVisibility(View.INVISIBLE);
        if (selectedItem.isMovie()) {
            movieRB.setChecked(true);
        } else movieRB.setVisibility(View.INVISIBLE);
        if (selectedItem.isGame()) {
            gameRB.setChecked(true);
        } else gameRB.setVisibility(View.INVISIBLE);
        publisherTXT.setText("Publisher: " + selectedItem.getPublisher());
        authorTXT.setText("Author: " + selectedItem.getAuthor());
        if (selectedItem.isDvd()) {
            dvdCB.setChecked(true);
        } else dvdCB.setVisibility(View.INVISIBLE);
        if (selectedItem.isBluRay()) {
            blurayCB.setChecked(true);
        } else blurayCB.setVisibility(View.INVISIBLE);
        directorTXT.setText("Director: " + selectedItem.getDirector());
        studioTXT.setText("Studio: " + selectedItem.getStudio());
        systemTXT.setText("System: " + selectedItem.getSystem());
        switch (selectedItem.getFsk()) {
            case 0:
                fsk0CB.setChecked(true);
                break;
            case 6:
                fsk6CB.setChecked(true);
                break;
            case 12:
                fsk12CB.setChecked(true);
                break;
            case 16:
                fsk16CB.setChecked(true);
                break;
            case 18:
                fsk18CB.setChecked(true);
                break;
            default:
                fsk0CB.setVisibility(View.INVISIBLE);
                fsk6CB.setVisibility(View.INVISIBLE);
                fsk12CB.setVisibility(View.INVISIBLE);
                fsk16CB.setVisibility(View.INVISIBLE);
                fsk18CB.setVisibility(View.INVISIBLE);
                break;
        }
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
