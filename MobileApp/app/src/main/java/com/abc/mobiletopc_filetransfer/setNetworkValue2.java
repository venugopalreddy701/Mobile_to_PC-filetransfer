package com.abc.mobiletopc_filetransfer;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
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

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Enumeration;

public class setNetworkValue2 extends AppCompatActivity {
    static String mobile_ip_address="not found";
    static String pc_ip_address="not found";
    Button next_button;
    EditText showmobileip;
    TextView error_textview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_network_value2);
        next_button=(Button)findViewById(R.id.next_button);
        showmobileip=(EditText)findViewById(R.id.showmobileip);
        error_textview=(TextView)findViewById(R.id.error_textview);


        //check hotspot and start hotspot
        boolean result=checkandstarthotspot();

        //show MobileIP in the beginning only if hostpot is active
        if(result) {
            getWifiApIpAddress();
            asyncwaitforpc();

        }

// idle screen
        next_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //1.check hotspot status
                //1.5 get mobile ip address
                //2.start async and wait for PC to connect

                boolean hotspot_status=isApOn(getApplicationContext());
                if(!hotspot_status)
                {
                    checkandstarthotspot();
                }
                else
                {
                    //hotspot started , now get mobile IP
                    getWifiApIpAddress();
                    asyncwaitforpc();
                }


            }
        });



    }


    public void asyncwaitforpc()
    {

        //setNetworkValues.CheckConnectionTOPCAsync checkConnectionTOPCAsync= new setNetworkValues.CheckConnectionTOPCAsync(setNetworkValue2.this,mobile_ip_address,null);
      //  checkConnectionTOPCAsync.execute();

        CheckConnectionTOPCAsync checkConnectionTOPCAsync=new CheckConnectionTOPCAsync(setNetworkValue2.this,error_textview);
        checkConnectionTOPCAsync.execute();

    }

    class CheckConnectionTOPCAsync extends AsyncTask<Void, Void, String> {
        ProgressDialog mProgressDialog;
        Context context;
        String ip;
        TextView t1;




        public  CheckConnectionTOPCAsync(Context context,TextView t1)
        {
            this.context=context;
            this.ip=ip;
            this.t1=t1;

        }


        protected void onPreExecute() {
            mProgressDialog = ProgressDialog.show(context, "",
                    "Please enter this IP in you PC screen: "+mobile_ip_address);
        }

        @Override
        protected String doInBackground(Void... voids) {
            // do network operations here


            ServerSocket serverSocket=null;
            Socket client=null;
            InputStream inputStream=null;
            OutputStream outputStream=null;
            //ObjectInputStream objectInputStream=null;
            ObjectOutputStream objectOutputStream=null;

            try {
                serverSocket=new ServerSocket(7777);
                client=serverSocket.accept();
                Log.d("IP","Connection accepted");
                outputStream=client.getOutputStream();
               // inputStream=client.getInputStream();
               // objectInputStream=new ObjectInputStream(inputStream);
                objectOutputStream=new ObjectOutputStream(outputStream);
                InetSocketAddress socketAddress=(InetSocketAddress )client.getRemoteSocketAddress();
              String connectedPCIP=socketAddress.getAddress().getHostAddress();

               Log.d("IP","PC IP address:"+connectedPCIP);
                objectOutputStream.writeUTF(connectedPCIP);
                objectOutputStream.flush();
                Log.d("IP","Setting PC IP..");
                pc_ip_address=connectedPCIP;


            }
            catch (Exception e)
            {
                Toast.makeText(this.context,e.getMessage(),Toast.LENGTH_LONG).show();
                t1.setText(e.getMessage());

            }
            finally {
                try {
                    serverSocket.close();
                    client.close();
                }
                catch (Exception e)
                {
                    Toast.makeText(this.context,e.getMessage(),Toast.LENGTH_LONG).show();
                    t1.setText(e.getMessage());
                }
            }
            return "Complete";
        }

        protected void onPostExecute(String result){
            // do the work here before closing progress bar



            if (result.equals("Complete")) {

                startsharing();
                mProgressDialog.dismiss();

            }

        }

    }







    //move to next screen

    public void startsharing()
    {

        Intent intent=new Intent(setNetworkValue2.this,transferfiles_activity.class);
        intent.putExtra("mobileip",mobile_ip_address);
        intent.putExtra("pcip",pc_ip_address);
        startActivity(intent);
    }

    public String getWifiApIpAddress() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en
                    .hasMoreElements();) {
                NetworkInterface intf = en.nextElement();
                if (intf.getName().contains("wlan")) {
                    for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr
                            .hasMoreElements();) {
                        InetAddress inetAddress = enumIpAddr.nextElement();
                        if (!inetAddress.isLoopbackAddress()
                                && (inetAddress.getAddress().length == 4)) {
                            mobile_ip_address=inetAddress.getHostAddress();
                            showmobileip.setText(mobile_ip_address);
                            Log.d("IP", inetAddress.getHostAddress());
                            return inetAddress.getHostAddress();
                        }
                    }
                }
            }
        } catch (SocketException ex) {
            Log.e("IP", ex.toString());
        }
        return null;
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


    //disable back button

    @Override
    public void onBackPressed() {

    }

}