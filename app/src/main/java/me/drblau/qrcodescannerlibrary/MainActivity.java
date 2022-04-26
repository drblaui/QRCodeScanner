package me.drblau.qrcodescannerlibrary;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import me.drblau.qrcodescanner.QrCodeActivity;


public class MainActivity extends AppCompatActivity {
    private Button button;
    private final String LOGTAG = "QRCScanner-MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityResultLauncher<Intent> mGetContent = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    //TODO
                    if (result == null || result.getData() == null) return;
                    if(result.getResultCode() != Activity.RESULT_OK) {
                        String res = result.getData().getStringExtra("me.drblau.qrcodescanner.error_decoding_image");
                        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                        alertDialog.setTitle("Scan Error");
                        alertDialog.setMessage("QR Code could not be scanned");
                        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                (dialog, which) -> dialog.dismiss());
                        alertDialog.show();
                        return;
                    }
                    String res = result.getData().getStringExtra("me.drblau.qrcodescanner.got_qr_scan_result");
                    System.out.println(res);
                });
        button = (Button) findViewById(R.id.button_start_scan);
        button.setOnClickListener(v -> {
            //Start the qr scan activity
            Intent i = new Intent(MainActivity.this, QrCodeActivity.class);
            mGetContent.launch(i);
        });
    }
}
