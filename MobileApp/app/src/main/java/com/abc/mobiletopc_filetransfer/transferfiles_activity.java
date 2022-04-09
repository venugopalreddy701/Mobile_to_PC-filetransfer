package com.abc.mobiletopc_filetransfer;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class transferfiles_activity extends Activity {

    String mobile_ip_address = null;
    String pc_ip_address = null;

    Button send_button;
    TextView show_mobileip,show_pcip;
    ServerSocket serverSocket=null;
    Socket sendsocket=null;
    Socket receivesocket=null;
    int REQUESTCODE_PICK_FILE_TO_SEND = 1008;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transferfiles_activity);
        send_button=(Button)findViewById(R.id.send_button);
        show_mobileip=(TextView)findViewById(R.id.show_mobileip);
        show_pcip=(TextView)findViewById(R.id.show_pcip);

        Intent intent = getIntent();
        mobile_ip_address = intent.getStringExtra("mobileip");
        pc_ip_address = intent.getStringExtra("pcip");
        show_mobileip.setText("Mobile IP:"+mobile_ip_address);
        show_pcip.setText("PC IP:"+pc_ip_address);

        Log.v("IP ", mobile_ip_address);
        Log.v("IP", pc_ip_address);

        //start receiver thread
         ReceiveThread receiveThread=new ReceiveThread();
         Thread receiver=new Thread(receiveThread);
         receiver.start();


        send_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mediaIntent = new Intent(Intent.ACTION_GET_CONTENT);
                mediaIntent.setType("*/*");
                mediaIntent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                mediaIntent.addCategory(Intent.CATEGORY_OPENABLE);

                startActivityForResult(Intent.createChooser(mediaIntent, "choose files"), REQUESTCODE_PICK_FILE_TO_SEND);


            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUESTCODE_PICK_FILE_TO_SEND
                && resultCode == MainActivity.RESULT_OK) {




            if (data != null) {
                if (null != data.getClipData()) {
                    for (int i = 0; i < data.getClipData().getItemCount(); i++) {
                        Uri uri = data.getClipData().getItemAt(i).getUri();



                        File f1 = new File(FileUtils.getPath(this,uri));
                       String path=f1.getAbsolutePath();
                       // Toast.makeText(this,path,Toast.LENGTH_LONG).show();
                        //show_mobileip.setText(path);
                        SendFileAsync sendFileAsync =new SendFileAsync(transferfiles_activity.this,f1);
                        sendFileAsync.execute();


                    }
                }
                //single file selection
                else {


                    Uri uri = data.getData();

                    File f1 = new File(FileUtils.getPath(this,uri));


                    String path=f1.getAbsolutePath();
                   // Toast.makeText(this,path,Toast.LENGTH_LONG).show();
                    //show_mobileip.setText(path);
                    SendFileAsync sendFileAsync =new SendFileAsync(transferfiles_activity.this,f1);
                    sendFileAsync.execute();




                }



            }
        }



    }

    class SendFileAsync extends AsyncTask<Void, Void, String> {
        ProgressDialog mProgressDialog;
        Context context;
        File file;





        public  SendFileAsync(Context context,File file)
        {
            this.context=context;
            this.file=file;


        }


        protected void onPreExecute() {
            mProgressDialog = ProgressDialog.show(context, "",
                    "Sending file:"+file.getName());
        }

        @Override
        protected String doInBackground(Void... voids) {
            // do network operations here



            InputStream inputStream=null;
            OutputStream outputStream=null;

            ObjectOutputStream objectOutputStream=null;

            Socket socket = new Socket();

            try {


                socket.bind(null);
                socket.connect(new InetSocketAddress(pc_ip_address, 8888));
                 outputStream = socket.getOutputStream();
                 objectOutputStream = new ObjectOutputStream(outputStream);
                long filesize= file.length();


                objectOutputStream.writeLong(filesize);
                objectOutputStream.flush();
                objectOutputStream.writeUTF(file.getName());
                objectOutputStream.flush();

                int len = 0;
                byte buf[] = new byte[8192];


               inputStream =new FileInputStream(file);

                while ((len = inputStream.read(buf)) != -1) {
                    objectOutputStream.write(buf, 0, len);
                    objectOutputStream.flush();
                }

                inputStream.close();
                objectOutputStream.close();
                socket.close();
                System.out.println("files sent."+file.getName());


            }
            catch (Exception e)
            {
                Toast.makeText(this.context,e.getMessage(),Toast.LENGTH_LONG).show();


            }
            finally {
                try {
                    socket.close();
                }
                catch (Exception e)
                {
                    Toast.makeText(this.context,e.getMessage(),Toast.LENGTH_LONG).show();

                }
            }
            return "Complete";
        }

        protected void onPostExecute(String result){
            // do the work here before closing progress bar



            if (result.equals("Complete")) {


                mProgressDialog.dismiss();

            }
            else
            {

                mProgressDialog.dismiss();
                Toast.makeText(this.context,"Error while sending",Toast.LENGTH_LONG).show();
            }

        }

    }




    public class ReceiveThread implements Runnable
    {
        @Override
        public void run() {
            Log.v("IP","Started receiver thread");
            try {
                serverSocket = new ServerSocket(9999);
            } catch (IOException e) {
                e.printStackTrace();
            }

            while(true) {
                try {

                    receivesocket = serverSocket.accept();
                    Log.v("IP","Accepted connection");
                    byte buf[] = new byte[8192];
                    int len = 0;
                    InputStream inputStream = null;
                    ObjectInputStream objectInputStream = null;
                    inputStream = receivesocket.getInputStream();
                    objectInputStream = new ObjectInputStream(inputStream);
                    long filesize = objectInputStream.readLong();
                    String filename = objectInputStream.readUTF();


                    File myDirectory = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "MobileToPC-filetransfer");

                    if (!myDirectory.exists()) {
                        myDirectory.mkdirs();
                    }
                    File file = new File(myDirectory, filename);
                    OutputStream outputStream = new FileOutputStream(file);

                    Log.d("IP", file.getAbsolutePath());
                    while (filesize > 0 &&
                            (len = objectInputStream.read(buf, 0, (int) Math.min(buf.length, filesize))) != -1) {

                        outputStream.write(buf, 0, len);
                        outputStream.flush();

                        filesize = filesize - len;


                    }
                    outputStream.flush();
                    outputStream.close();
                    objectInputStream.close();


                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();

                } finally {
                    try {
                        receivesocket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }


            }

        }
    }


    //disabling back button
    @Override
    public void onBackPressed() {

        
    }





}
