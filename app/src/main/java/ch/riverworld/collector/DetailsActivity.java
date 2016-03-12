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
import android.widget.CompoundButton;
import android.widget.Switch;

public class DetailsActivity extends AppCompatActivity {
    private  Switch swLent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        swLent = (Switch) findViewById(R.id.sw_lent);
        final Intent lentIntent=new Intent(this,LentActivity.class);

        swLent.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // Changed status of lent switch to true (item is lent)
                    startActivity(lentIntent);
                }
            }
        });
    }

    // Button listener for the DetailsActivity
    public void buttonOnClick(View v) {
        switch (v.getId()) {
            case R.id.btn_delete:
                //Button to delete item pressed.
                final Intent deleteIntent=new Intent(this,DeleteActivity.class);
                startActivity(deleteIntent);
                break;
            case R.id.btn_edit:
                //Button to edit item pressed.
                break;
        }
    }
}
