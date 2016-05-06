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
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class DetailsActivity extends AppCompatActivity {

    private boolean debugMode = false;
    private int itemId;
    private Item selectedItem;
    private Context ctx;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        //Getting information regarding debug mode
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            debugMode = extras.getBoolean("debugMode");
            this.itemId = extras.getInt("itemID");
        }

        ctx = this;
        DatabaseOperations db = new DatabaseOperations(ctx, debugMode);

        this.selectedItem = new Item(ctx, itemId);

        Switch swLent = (Switch) findViewById(R.id.sw_lent);
        ImageView iView = (ImageView) findViewById(R.id.imageView);
        TextView eanTXT = (TextView) findViewById(R.id.txt_ean);
        TextView titleTXT = (TextView) findViewById(R.id.txt_title);
        RatingBar ratingBar =(RatingBar) findViewById(R.id.ratingBar);
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
        // Need to save picture ID in database table items and refactor this switch
        switch (itemId) {
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

        String value = String.valueOf(selectedItem.getEAN());
        assert eanTXT != null;
        eanTXT.setText(String.format("EAN: %s", value));
        titleTXT.setText("Title: " + selectedItem.getTitle());
        ratingBar.setRating(selectedItem.getRating());
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
                String msg = "Do you really want to delete " + selectedItem.toString();
                itemBuilder.setMessage(msg);


                itemBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //dialog.cancel();
                        if (debugMode) {
                            Log.d("USERACTION", "User choosed yes.");
                        }
                        // Put code to delete item here
                        DatabaseOperations db = new DatabaseOperations(ctx, debugMode);
                        boolean success = db.deleteItem(db, itemId);
                        if (success) {
                            Toast.makeText(getBaseContext(), selectedItem.toString() + " was successfully deleted.",
                                    Toast.LENGTH_SHORT).show();
                            finish();
                        } else
                            Toast.makeText(getBaseContext(), "Troubles while deleting " + selectedItem.toString(), Toast
                                    .LENGTH_SHORT).show();
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
                final Intent editIntent = new Intent(this, ItemActivity.class);
                editIntent.putExtra("debugMode", debugMode);
                editIntent.putExtra("Mode", "EditItem");
                editIntent.putExtra("ID", itemId);
                startActivity(editIntent);
                break;
        }
    }
}
