package ch.riverworld.collector;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void buttonOnClick(View v) {
        switch (v.getId()) {
            case R.id.btn_newItem:
                //button Neues Item gedrueckt
                break;
            case R.id.btn_collection:
                //button Sammlung gedrueckt
                final Intent filterIntent=new Intent(this,FilterActivity.class);
                startActivity(filterIntent);
                break;
            case R.id.btn_rental:
                //button Leihwesen gedrueckt
                break;
            case R.id.btn_info:
                //button Info gedrueckt
                final Intent infoIntent=new Intent(this,InfoActivity.class);
                startActivity(infoIntent);
                break;
        }
    }
}
