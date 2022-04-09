package com.abc.mobiletopc_filetransfer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class MainActivity extends AppCompatActivity {
    int REQUEST_EXTERNAL_STORAGE=11;
    Button permissionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        permissionButton=(Button)findViewById(R.id.b3);
        ///ask storage permissions intially
        askStoragePermissions();


        //if permissions are already granted , then move to next activity
        if(checkRequiredPermissions())
        {
            //Toast.makeText(getApplicationContext(),"Storage permissios granted for this App,start new activity",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(MainActivity.this,
                    setNetworkValue2.class);
            startActivity(intent);
            finish();
        }
        else
        {


            requestPermissions(new String[] {READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE},REQUEST_EXTERNAL_STORAGE);

        }



        //b3
        permissionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!checkRequiredPermissions())
                {
                    Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "You must approve this permission in \"Permissions\" in the app settings on your device.", Snackbar.LENGTH_LONG).setAction("Settings", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            startActivity(new Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse("package:" + BuildConfig.APPLICATION_ID)));

                        }
                    });
                    View snackbarView = snackbar.getView();
                    TextView textView = (TextView) snackbarView.findViewById(R.id.snackbar_text);
                    textView.setMaxLines(5);
                    snackbar.show();


                }

                if(checkRequiredPermissions())
                {
                  //  Toast.makeText(getApplicationContext(),"Storage permissios grantedfor this App,start new activty",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(MainActivity.this,
                            setNetworkValue2.class);
                    startActivity(intent);
                    finish();
                }



            }
        });
        //end of b3






    }







    public boolean checkRequiredPermissions()
    {
        int result1 = ContextCompat.checkSelfPermission(this, WRITE_EXTERNAL_STORAGE);
        int result2=ContextCompat.checkSelfPermission(this, READ_EXTERNAL_STORAGE);
        return result1 == PackageManager.PERMISSION_GRANTED && result2==PackageManager.PERMISSION_GRANTED ;
    }


    //1//
    private void askStoragePermissions()
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if(checkSelfPermission(READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED)
            {
                requestPermissions(new String[] {READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE},REQUEST_EXTERNAL_STORAGE);
            }
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if( requestCode == REQUEST_EXTERNAL_STORAGE)
        {
            if ( grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                //Toast.makeText(this,"Storage permissions granted for App",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainActivity.this,
                        setNetworkValue2.class);
                startActivity(intent);
                finish();
            }else
            {
                Toast.makeText(this,"Without these permissions, you won't be able to send or receive files with App",Toast.LENGTH_LONG).show();
            }
        }
    }







}