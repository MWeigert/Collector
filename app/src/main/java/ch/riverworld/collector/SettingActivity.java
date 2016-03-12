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

public class SettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
    }

    // Button listener for the SettingActivity
    public void buttonOnClick(View v) {
        switch (v.getId()) {
            case R.id.btn_friends:
                //Button for friends administration pressed.
                final Intent friendsIntent=new Intent(this,FriendsActivity.class);
                startActivity(friendsIntent);
                break;
        }
    }
}
