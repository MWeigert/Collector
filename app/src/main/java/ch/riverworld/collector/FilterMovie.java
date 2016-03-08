package ch.riverworld.collector;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class FilterMovie extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_movie);
    }

    public void buttonOnClick(View v) {
        switch (v.getId()) {
            case R.id.btn_back:
                //button Zurueck gedrueckt
                final Intent mainIntent=new Intent(this,MainActivity.class);
                startActivity(mainIntent);
                break;
        }
    }
}
