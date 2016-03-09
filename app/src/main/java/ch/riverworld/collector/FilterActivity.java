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

        //Tab 1
        TabHost.TabSpec spec = host.newTabSpec("Allg.");
        spec.setContent(R.id.tab_common);
        spec.setIndicator("Allg.");
        host.addTab(spec);

        //Tab 2
        spec = host.newTabSpec("Buch & Spiel");
        spec.setContent(R.id.tab_bg);
        spec.setIndicator("Buch & Spiel");
        host.addTab(spec);

        //Tab 3
        spec = host.newTabSpec("Film");
        spec.setContent(R.id.tab_movie);
        spec.setIndicator("Film");
        host.addTab(spec);

    }

    public void buttonOnClick(View v) {
        switch (v.getId()) {
            case R.id.btn_back:
                //button Zurueck gedrueckt
                final Intent mainIntent=new Intent(this,MainActivity.class);
                startActivity(mainIntent);
                break;
            case R.id.btn_back2:
                //button Zurueck2 gedrueckt
                final Intent mainIntent2=new Intent(this,MainActivity.class);
                startActivity(mainIntent2);
                break;
            case R.id.btn_back3:
                //button Zurueck3 gedrueckt
                final Intent mainIntent3=new Intent(this,MainActivity.class);
                startActivity(mainIntent3);
                break;
            case R.id.btn_update:
                //button Anzeigen gedrueckt
                final Intent resultIntent = new Intent(this,FilterResult.class);
                startActivity(resultIntent);
                break;
            case R.id.btn_update2:
                //button Anzeigen gedrueckt
                final Intent resultIntent2 = new Intent(this,FilterResult.class);
                startActivity(resultIntent2);
                break;
            case R.id.btn_update3:
                //button Anzeigen gedrueckt
                final Intent resultIntent3 = new Intent(this,FilterResult.class);
                startActivity(resultIntent3);
                break;
        }
    }
}
