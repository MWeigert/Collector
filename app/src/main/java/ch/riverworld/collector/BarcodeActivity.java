package ch.riverworld.collector;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

public class BarcodeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode);

        BarcodeDetector codeDetector = new BarcodeDetector.Builder(getApplicationContext()).setBarcodeFormats(Barcode
                .EAN_13).build();
    }
}
