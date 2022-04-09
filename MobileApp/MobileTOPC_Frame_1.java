/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mobiletopc.filetransfer;


import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;


/**
 *
 * @author VENUGOPAL-ACER
 */
public class MobileTOPC_Frame_1 extends javax.swing.JFrame {
static String pcIP="Couldn't get";
 static String mobileIp="couldn't get";
 static int connected_to_mobile=0;
 
    /**
     * Creates new form MobileTOPC_Frame_1
     */
    public MobileTOPC_Frame_1() throws SocketException {
        initComponents();
         setMyPCIP2();
        jTextField1.setText(pcIP);
         send_button.setVisible(false);
        receive_button.setVisible(false);
        
        
        
       
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        mobileip = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        send_button = new javax.swing.JButton();
        receive_button = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setText("Connect to hotspot and Click refresh");

        jLabel1.setText("PC IP");

        jTextField1.setEditable(false);

        jButton1.setText("Refresh");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel2.setText("Mobile IP");

        mobileip.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                mobileipCaretUpdate(evt);
            }
        });

        jButton2.setText("check connection");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(mobileip, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
                            .addComponent(jTextField1))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(mobileip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        send_button.setText("Send files");
        send_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                send_buttonActionPerformed(evt);
            }
        });

        receive_button.setText("Receive files");
        receive_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                receive_buttonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(receive_button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(send_button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(send_button, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(receive_button, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 110, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        setMyPCIP2();
        jTextField1.setText(pcIP);
        jLabel3.setText("enter your ip from the mobile screen");
        
       
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void mobileipCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_mobileipCaretUpdate
    
        
    }//GEN-LAST:event_mobileipCaretUpdate

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        
        
            // TODO add your handling code here:
        String ip=mobileip.getText();
        InetAddress inet=null;
        Color myGreen=new Color(74, 145, 93);
        try {
        inet = InetAddress.getByName(ip);
			System.out.println("Sending Ping Request to " + ip);
			if (inet.isReachable(2000)) {
				System.out.println(ip + " is reachable.");
                                jLabel3.setText("your are connected to Mobile");
                                jLabel3.setForeground(myGreen);
                                connected_to_mobile=1;
                                 send_button.setVisible(true);
                                receive_button.setVisible(true);
                                mobileIp=ip;
                                
                                 JOptionPane.showMessageDialog(this,"Connected to mobile device, you can now send or receive files");
        
			} else {
				System.out.println(ip + " NOT reachable.");
                                 jLabel3.setText("No device connected");
                                 jLabel3.setForeground(Color.red);
                                 connected_to_mobile=0;
			}
                   
		}
    catch (Exception e) {
			System.out.println("Exception:" + e.getMessage());
                        jLabel3.setText("Exception:" + e.getMessage());
                        jLabel3.setForeground(Color.blue);
                        connected_to_mobile=0;
		}
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void receive_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_receive_buttonActionPerformed
        // TODO add your handling code here:
        
      int result = JOptionPane.showConfirmDialog(this,"Did you choose a file to send in the mobile", "Message",
               JOptionPane.YES_NO_OPTION,
               JOptionPane.QUESTION_MESSAGE);
            if(result == JOptionPane.YES_OPTION){
              receivefiles();
                
            }else if (result == JOptionPane.NO_OPTION){
               JOptionPane.showMessageDialog(this,"Click this button only after you have choose the file to send from the mobile");
            }
        
    }//GEN-LAST:event_receive_buttonActionPerformed

    private void send_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_send_buttonActionPerformed
        // TODO add your handling code here:
    JOptionPane.showMessageDialog(this, "Press receive on your phone");
           int nooffiles = 0; 
   
     Socket socket = new Socket();
        try{
            
            JFileChooser ch = new JFileChooser();

            JFileChooser chooser=new JFileChooser();

            chooser.setMultiSelectionEnabled(true);
            chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            chooser.showOpenDialog(null);

           // String serverAddress="192.168.43.1";
            
            String serverAddress=mobileIp;
            System.out.println("d 0");
            socket.bind(null);
            System.out.println("d 1");
            socket.connect(new InetSocketAddress(serverAddress, 9999));
            System.out.println("d 2");
            OutputStream outputStream = socket.getOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

            File[] files = chooser.getSelectedFiles();
            File f=null;
           nooffiles=files.length;

            if(nooffiles==1)
            {
                f = files[0];
                //file choose
            }
            else
            {
               f=new File(zipmyfiles(files));
            }


            long filesize= f.length();

            System.out.println(filesize+" "+f.getName());
            objectOutputStream.writeLong(filesize);
            objectOutputStream.flush();
            objectOutputStream.writeUTF(f.getName());
            objectOutputStream.flush();


            int len = 0;
            byte buf[] = new byte[8192];


            InputStream inputStream =new FileInputStream(f);

            while ((len = inputStream.read(buf)) != -1) {
                objectOutputStream.write(buf, 0, len);
                objectOutputStream.flush();
            }
            inputStream.close();




            objectOutputStream.close();
            socket.close();
           
            System.out.println("files sent.");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (socket.isConnected()) {
                try {
                    socket.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            // delete the zip file for multiple send
            if(nooffiles>1)
            {
               File filetodelete=new File("archive.zip");
               filetodelete.delete();
            }
            
        }
        
        
        
        
        
            
 
        
        
        
    }//GEN-LAST:event_send_buttonActionPerformed

    
    public static String zipmyfiles(File files[])
    {
        String zipfilepath=null;
        String zipFile = "archive.zip";

        File makezip=new File(zipFile);
        
        if(makezip.exists())
        {
            makezip.delete();
            makezip=new File(zipFile);
            
        }
        

      

        try {

            // create byte buffer
            byte[] buffer = new byte[1024];

            FileOutputStream fos = new FileOutputStream(zipFile);

            ZipOutputStream zos = new ZipOutputStream(fos);

            for (int i=0; i < files.length; i++) {

                File srcFile = new File(files[i].getAbsolutePath());

                FileInputStream fis = new FileInputStream(srcFile);

                // begin writing a new ZIP entry, positions the stream to the start of the entry data
                zos.putNextEntry(new ZipEntry(srcFile.getName()));

                int length;

                while ((length = fis.read(buffer)) > 0) {
                    zos.write(buffer, 0, length);
                }

                zos.closeEntry();

                // close the InputStream
                fis.close();

            }

            // close the ZipOutputStream
            zos.close();

        }
        catch (IOException ioe) {
            System.out.println("Error creating zip file: " + ioe);
        }


        zipfilepath=zipFile;
        return zipfilepath;

    }
    
    
    public void receivefiles()
    {
          Socket client=null;

        ServerSocket serverSocket= null;

        try {
            serverSocket = new ServerSocket(9999);
            

        } catch (IOException e) {
            e.printStackTrace();
        }


        byte buf[] = new byte[8192];
        int len = 0;





        try {
            System.out.println("waiting for sender");
            client = serverSocket.accept();
            System.out.println("accepted sender connection");
        } catch (IOException e) {
            e.printStackTrace();
        }
        InputStream inputStream=null;
        ObjectInputStream objectInputStream=null;
        try {

            inputStream = client.getInputStream();
            objectInputStream = new ObjectInputStream(inputStream);
            long filesize=objectInputStream.readLong();
            System.out.println("filesize:"+String.valueOf(filesize));
            String filename=objectInputStream.readUTF();
            System.out.println("filename:"+filename);
            // Toast.makeText(getApplicationContext(),filename,Toast.LENGTH_LONG).show();;

            File myDirectory = new File("D://megashare");

            if(!myDirectory.exists()) {
                myDirectory.mkdirs();
            }

            //NOTE : Send filename parameter in next after testing
            File file=new File(myDirectory+ "/"+ filename);
            OutputStream outputStream = new FileOutputStream(file);

            System.out.println("fileshare"+file.getAbsolutePath());



            while (filesize > 0 &&
                    (len = objectInputStream.read(buf, 0, (int) Math.min(buf.length, filesize))) != -1) {

                outputStream.write(buf, 0, len);
                outputStream.flush();

                filesize = filesize-len;


            }
            outputStream.flush();
            outputStream.close();
            objectInputStream.close();

            // Toast.makeText(getApplicationContext(),"sent",Toast.LENGTH_LONG).show();
            System.out.println("fileshare finish");

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally {
            try {
                serverSocket.close();
            } catch (IOException ex) {
                System.out.println(ex);
                
            }
        }



    }
    
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws SocketException {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MobileTOPC_Frame_1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MobileTOPC_Frame_1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MobileTOPC_Frame_1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MobileTOPC_Frame_1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            
            
            public void run() {
                try {
                    new MobileTOPC_Frame_1().setVisible(true);
                } catch (SocketException ex) {
                    Logger.getLogger(MobileTOPC_Frame_1.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        /// get and set PC IP
        
       
        
       
    }
    public static void setMyPCIP2()
    {
        pcIP="Couldn't get";
        ArrayList<String> iplist=new ArrayList<String>();
        int check=0;
        
try {
        Enumeration nis = NetworkInterface.getNetworkInterfaces();
        while(nis.hasMoreElements())
        {
            NetworkInterface ni = (NetworkInterface) nis.nextElement();
            Enumeration ias = ni.getInetAddresses();
            while (ias.hasMoreElements())
            {
                InetAddress ia = (InetAddress) ias.nextElement();
                System.out.println(ia.getHostAddress());
                iplist.add(String.valueOf(ia.getHostAddress()));
                String s1=String.valueOf(ia.getHostAddress());
                if(s1.startsWith("192.168.") && ! s1.endsWith(".1"))
                {
                  pcIP=s1;
                  
               
                }
                
            }

        }
      
           
        }
     
    
    catch(Exception e)
    {
        JOptionPane.showMessageDialog(null,e.getMessage());
    }
    }
    
    
    
    
    
   
   
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField mobileip;
    private javax.swing.JButton receive_button;
    private javax.swing.JButton send_button;
    // End of variables declaration//GEN-END:variables
}
