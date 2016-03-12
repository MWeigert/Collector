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

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // Button listener for the MainActivity
    public void buttonOnClick(View v) {
        switch (v.getId()) {
            case R.id.btn_newItem:
                //Pressed button to add new item to the collection.
                break;
            case R.id.btn_collection:
                //Pressed button do show total or filtered collection.
                final Intent filterIntent=new Intent(this,FilterActivity.class);
                startActivity(filterIntent);
                break;
            case R.id.btn_rental:
                //Pressed button to enter lend administration.
                break;
            case R.id.btn_info:
                //Pressed button to display informations regarding this app.
                final Intent infoIntent=new Intent(this,InfoActivity.class);
                startActivity(infoIntent);
                break;
        }
    }
}
