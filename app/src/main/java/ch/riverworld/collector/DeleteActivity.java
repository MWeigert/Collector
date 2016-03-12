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

public class DeleteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
    }

    // Button listener for the DeleteActivity
    public void buttonOnClick(View v) {
        switch (v.getId()) {
            case R.id.btn_yes:
                //Button to confirm delete of item pressed.
                break;
            case R.id.btn_no:
                //Button to abort deletion of item pressed.
                final Intent detailsIntent=new Intent(this,DetailsActivity.class);
                startActivity(detailsIntent);
                break;
        }
    }

}