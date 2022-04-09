package com.abc.mobiletopc_filetransfer;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

public class setNetworkValues extends AppCompatActivity {

    /*
    EditText mobileip,pcip;
    Button b1,b2;
    TextView t1;
    static int hotspot_started=0;
    static String mobile_ip_address="not found";
    static String pc_ip_address="not found";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_network_values);
        mobileip=(EditText)findViewById(R.id.mobileip);
        pcip=(EditText)findViewById(R.id.pcip);
        t1=(TextView)findViewById(R.id.textView3) ;
        b1=(Button)findViewById(R.id.b1);
        b2=(Button)findViewById(R.id.b2);

        mobileip.setEnabled(false);



         checkandstarthotspot();




         b1.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
             boolean hotspot_status=isApOn(getApplicationContext());
             if(!hotspot_status)
             {
               checkandstarthotspot();
             }

             else {
                 hotspot_started=1;
                 getMobileIP();
                //show message to connect PC to wifi hotspot and install client software in PC
                 AlertDialog alertDialog = new AlertDialog.Builder(setNetworkValues.this).create();
                 alertDialog.setTitle("Instructions");
                 alertDialog.setMessage("1.Make sure your PC is connected to wifihotspot \n 2. Install cilent software from fileshare.blogspot.com \n When above instructions are done press OK");
                 alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                         new DialogInterface.OnClickListener() {
                             public void onClick(DialogInterface dialog, int which) {
                                 dialog.dismiss();
                             }
                         });
                 alertDialog.show();
                 t1.setText(" Enter PC IP  above and click connect to PC");
                 b2.setVisibility(View.VISIBLE);





             }


             }
         });

         b2.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {

                 CheckConnectionTOPCAsync checkConnectionTOPCAsync=new CheckConnectionTOPCAsync(setNetworkValues.this,pcip.getText().toString(),t1);
                 checkConnectionTOPCAsync.execute();






             }
         });

    }

public void startsharing()
{

    Intent intent=new Intent(setNetworkValues.this,transferfiles_activity.class);
    intent.putExtra("mobileip",mobile_ip_address);
    intent.putExtra("pcip",pc_ip_address);
    startActivity(intent);
}




    public void getMobileIP()
    {
        try {
            Enumeration nis = NetworkInterface.getNetworkInterfaces();
            while(nis.hasMoreElements())
            {
                NetworkInterface ni = (NetworkInterface) nis.nextElement();
                Enumeration ias = ni.getInetAddresses();
                while (ias.hasMoreElements() )
                {
                    InetAddress ia = (InetAddress) ias.nextElement();
                    System.out.println(ia.getHostAddress());

                    String s1=String.valueOf(ia.getHostAddress());

                    // some mobile devices , ip ends with .1 if(s1.startsWith("192.168.") && ! s1.endsWith(".1"))
                    if(s1.startsWith("192.168.") )
                    {

                      mobileip.setText(s1);
                      mobile_ip_address=s1;


                    }

                }

            }




        }

        catch(Exception e)
        {
            Toast.makeText(this, e.getMessage(),Toast.LENGTH_LONG).show();

        }

    }

    public boolean checkandstarthotspot()
    {

        boolean hotspot_status= isApOn(getApplicationContext());
        if(hotspot_status==false)
        {


            Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "Turn on hotspot and connect it to PC", Snackbar.LENGTH_LONG).setAction("Settings", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent tetherSettings = new Intent();
                    tetherSettings.setClassName("com.android.settings", "com.android.settings.TetherSettings");
                    startActivity(tetherSettings);
                }
            });
            View snackbarView = snackbar.getView();
            TextView textView = (TextView) snackbarView.findViewById(R.id.snackbar_text);
            textView.setMaxLines(5);
            snackbar.show();



        }

        return hotspot_status;

    }

    public static boolean isApOn(Context context) {
        WifiManager wifimanager = (WifiManager) context.getSystemService(context.WIFI_SERVICE);
        try {
            Method method = wifimanager.getClass().getDeclaredMethod("isWifiApEnabled");
            method.setAccessible(true);
            return (Boolean) method.invoke(wifimanager);
        }
        catch (Throwable ignored) {}
        return false;
    }


    class CheckConnectionTOPCAsync extends AsyncTask<Void, Void, String> {
        ProgressDialog mProgressDialog;
        Context context;
        String ip;
        TextView t1;
        int check=0;



        public  CheckConnectionTOPCAsync(Context context,String ip,TextView t1)
        {
            this.context=context;
            this.ip=ip;
            this.t1=t1;

        }


        protected void onPreExecute() {
            mProgressDialog = ProgressDialog.show(context, "",
                    "Please wait,while connecting to PC");
        }

        @Override
        protected String doInBackground(Void... voids) {
            // do network operations here
            try {
                InetAddress inet=InetAddress.getByName(ip);
                if(inet.isReachable(5000))
                {
                    check=1;
                }

            }
            catch (Exception e)
            {
                Toast.makeText(this.context,e.getMessage(),Toast.LENGTH_LONG).show();

            }
            return "Complete";
        }

        protected void onPostExecute(String result){
            if(check==1)
            {
                t1.setText("Connected to PC");
                pc_ip_address=ip;
                startsharing();


            }
            if(check==0)
            {
                t1.setText("could nt connect to PC, please enter valid IP");
            }

            if (result.equals("Complete")) {
                mProgressDialog.dismiss();

            }

        }

    }


     */

}








