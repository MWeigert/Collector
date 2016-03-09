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

    public void buttonOnClick(View v) {
        switch (v.getId()) {
            case R.id.btn_yes:
                //button Ja gedrueckt
                break;
            case R.id.btn_no:
                //button nein gedrueckt
                final Intent detailsIntent=new Intent(this,DetailsActivity.class);
                startActivity(detailsIntent);
                break;
        }
    }

}
