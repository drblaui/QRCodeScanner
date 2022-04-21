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
    compile 'com.github.drblaui:QRCodeScanner:0.1.3'
   }
```
Be sure to check the latest version [here](https://github.com/drblaui/QRCodeScanner/releases) 
* In your activity, Declare the Request code for QR Code scan
```java
private static final int REQUEST_CODE_QR_SCAN = 101;
```
* Start the QR Code scan activity, FOR RESULT,
```java
@Override
public void onClick(View v) {
   Intent i = new Intent(MainActivity.this,QrCodeActivity.class);
   startActivityForResult( i,REQUEST_CODE_QR_SCAN);
}
```
* And catch the scan result:
```java
protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(resultCode != Activity.RESULT_OK)
        {
            Log.d(LOGTAG,"COULD NOT GET A GOOD RESULT.");
            if(data==null)
                return;
            //Getting the passed result
            String result = data.getStringExtra("com.blikoon.qrcodescanner.error_decoding_image");
            if( result!=null)
            {
                AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                alertDialog.setTitle("Scan Error");
                alertDialog.setMessage("QR Code could not be scanned");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
            }
            return;

        }
        if(requestCode == REQUEST_CODE_QR_SCAN)
        {
            if(data==null)
                return;
            //Getting the passed result
            String result = data.getStringExtra("com.blikoon.qrcodescanner.got_qr_scan_relult");
            Log.d(LOGTAG,"Have scan result in your app activity :"+ result);
            AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
            alertDialog.setTitle("Scan result");
            alertDialog.setMessage(result);
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();

        }
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
Submit a [github issue](https://github.com/blikoon/QRCodeScanner/issues/new)