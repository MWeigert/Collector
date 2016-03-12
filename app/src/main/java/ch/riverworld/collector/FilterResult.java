//*****************************************************************
//*                                                               *
//* Programmed by: Mathias Weigert                                *
//*       Version: 0.01                                           *
//*                                                               *
//*****************************************************************

package ch.riverworld.collector;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class FilterResult extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_result);
    }

    // Button listener for the FilterResultActivity
    public void buttonOnClick(View v) {
        switch (v.getId()) {
            case R.id.btn_details:
                //Button pressed to show details of the selected item.
                final Intent detailsIntent = new Intent(this, DetailsActivity.class);
                startActivity(detailsIntent);
                break;
        }
    }
}
