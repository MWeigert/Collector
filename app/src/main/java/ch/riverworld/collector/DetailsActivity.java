package ch.riverworld.collector;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
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
