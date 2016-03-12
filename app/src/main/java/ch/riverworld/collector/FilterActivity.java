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
import android.widget.TabHost;

public class FilterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        TabHost host = (TabHost)findViewById(R.id.tabHost);
        host.setup();

        //First tab with filter selections for all types of collection items.
        TabHost.TabSpec spec = host.newTabSpec("Allg.");
        spec.setContent(R.id.tab_common);
        spec.setIndicator("Common");
        host.addTab(spec);

        //Second tab with filter selections for books and games.
        spec = host.newTabSpec("Buch & Spiel");
        spec.setContent(R.id.tab_bg);
        spec.setIndicator("Book & Game");
        host.addTab(spec);

        //Third tab with filter selections for movies.
        spec = host.newTabSpec("Film");
        spec.setContent(R.id.tab_movie);
        spec.setIndicator("Movie");
        host.addTab(spec);

    }
    // Button listener for the FilterActivity
    public void buttonOnClick(View v) {
        switch (v.getId()) {
            case R.id.btn_filter_tab1:
                //Pressed button on first tab to filter collection
                final Intent resultIntent = new Intent(this,FilterResult.class);
                startActivity(resultIntent);
                break;
            case R.id.btn_filter_tab2:
                //Pressed button on second tab to filter collection
                final Intent resultIntent2 = new Intent(this,FilterResult.class);
                startActivity(resultIntent2);
                break;
            case R.id.btn_filter_tab3:
                //Pressed button on third tab to filter collection
                final Intent resultIntent3 = new Intent(this,FilterResult.class);
                startActivity(resultIntent3);
                break;
        }
    }
}
