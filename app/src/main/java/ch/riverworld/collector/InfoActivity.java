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

public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
    }

    // Button listener for the InfoActivity
    public void buttonOnClick(View v) {
        switch (v.getId()) {
            case R.id.btn_settings:
                //Button pressed to enter configuration mode of the app.
                final Intent settingIntent=new Intent(this,SettingActivity.class);
                startActivity(settingIntent);
                break;
        }
    }
}
