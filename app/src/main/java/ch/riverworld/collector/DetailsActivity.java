package ch.riverworld.collector;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

public class DetailsActivity extends AppCompatActivity {
    private  Switch swRent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        swRent = (Switch) findViewById(R.id.sw_rent);
        final Intent rentalIntent=new Intent(this,RentalActivity.class);

        swRent.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        // The toggle is enabled
                        startActivity(rentalIntent);
                    }
                }
            });
    }

    public void buttonOnClick(View v) {
        switch (v.getId()) {
            case R.id.btn_back:
                //button Zurueck gedrueckt
                final Intent mainIntent=new Intent(this,MainActivity.class);
                startActivity(mainIntent);
                break;
            case R.id.btn_delete:
                //button Loeschen gedrueckt
                final Intent deleteIntent=new Intent(this,DeleteActivity.class);
                startActivity(deleteIntent);
                break;
            case R.id.btn_edit:
                //button Bearbeiten gedrueckt
                break;
        }
    }
}
