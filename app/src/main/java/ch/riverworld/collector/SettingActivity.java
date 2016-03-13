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
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class SettingActivity extends AppCompatActivity {

    private boolean debugMode = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        //Getting information regarding debug mode
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            debugMode = extras.getBoolean("debugMode");
        }
    }

    // Button listener for the SettingActivity
    public void buttonOnClick(View v) {
        switch (v.getId()) {
            case R.id.btn_friends:
                //Button for friends administration pressed.
                if (debugMode) {
                    String msg = "Entering friends administration.";
                    Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                    Log.d("USERACTION", msg);
                }

                final Intent friendsIntent=new Intent(this,FriendsActivity.class);
                friendsIntent.putExtra("debugMode", debugMode);
                startActivity(friendsIntent);
                break;
        }
    }
}
