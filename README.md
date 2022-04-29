# QRCodeScanner
This is a fork of [Blikoons QRCode Scanner](https://github.com/blikoon/QRCodeScanner) adapted to work for my needings. Possibly also works for newer Android Versions
QR Scanning library based on zxing for android devices API 15 and up

![In action](https://github.com/blikoon/QRCodeScanner/blob/master/showOff.gif)

# Features
 * Scan QR Code
 * Load images containing QR Code and scan them
 * Easy to use
 * Flash light

# How to use
* In your root gradle file do the following :
```java
   allprojects {
     repositories {
	...
	maven { url 'https://jitpack.io' }
	}
   }
```
* In your app module gradle file just add the dependency
```java
   dependencies {
    compile 'com.github.drblaui:QRCodeScanner:0.1.6'
   }
```
Be sure to check the latest version [here](https://github.com/drblaui/QRCodeScanner/releases) 
* In your activity, Declare the Request code for QR Code scan
```java
private static final int REQUEST_CODE_QR_SCAN = 101;
```
* Register activity for Result and catch it
```java
private static ActivityResultLauncher<Intent> mGetConten;

@Override
protected void onCreate(Bundle savedInstanceState) {
    ...
    mGetContent = registerForActivityResult(
        new ActivityResultContracts.StartActivityForResult(),
        result -> {
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
        }
    )
}
```
```
* Start the QR Code scan activity, FOR RESULT,
```java
@Override
public void onClick(View v) {
   Intent i = new Intent(MainActivity.this,QrCodeActivity.class);
   mGetContent.launch(i);
}
```
* You're good to go!

# Example app
https://github.com/drblaui/QRCodeScanner/tree/master/app

# Video Tutorial
https://www.youtube.com/watch?v=R9JxDpKpkAk

# Licence
GPLv3

# Things changed in this distribution
* Some unused functions have been removed
* where possible, lambda expressions have been used

# Found a bug?
Submit a [github issue](https://github.com/drblaui/QRCodeScanner/issues/new)
