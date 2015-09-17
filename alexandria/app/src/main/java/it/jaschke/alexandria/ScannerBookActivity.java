package it.jaschke.alexandria;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ScannerBookActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    private ZXingScannerView mScannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_scanner_book);
        mScannerView = new ZXingScannerView(this);
        mScannerView.setAutoFocus(true);
        setContentView(mScannerView);
    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();
    }

    @Override
    public void handleResult(Result result) {
        if (result.getBarcodeFormat() == BarcodeFormat.EAN_13 && (result.getText().length() == 13 || result.getText().length() == 10)) {
            Intent intent = new Intent();
            intent.putExtra("result", result.getText());
            setResult(RESULT_OK, intent);
            mScannerView.stopCamera();
            finish();
        } else {
            Toast.makeText(this, R.string.err_barcode_lecture, Toast.LENGTH_SHORT).show();
            mScannerView.startCamera();
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("result", "");
        setResult(RESULT_CANCELED, intent);
        mScannerView.stopCamera();
        super.onBackPressed();
    }
}
