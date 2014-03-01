package Client;

import java.net.*;
import java.io.*;
import javax.net.ssl.*;
import javax.security.cert.X509Certificate;
import java.security.KeyStore;
import java.security.cert.*;

import java.util.ArrayList;

import server.journalEntry;
import server.Patient;

/*
 * This example shows how to set up a key manager to perform client
 * authentication.
 *
 * This program assumes that the client is not inside a firewall.
 * The application can be modified to connect to a server outside
 * the firewall by following SSLSocketClientWithTunneling.java.
 */
public class client {
/*
    private SSLSocket socket;
    private BufferedReader reader;
    private BufferedWriter writer;


    private InputStream inputStream;
    private InputStreamReader inputReader;

    private OutputStream outputStream;
    private OutputStreamWriter outputWriter;

    private String host;
    private int port;

    private static final int PATIENT= 0;
    private static final int NURSE= 1;
    private static final int DOCTOR= 2;
    private static final int GOVERNMENT= 3;

    public Client(String host, int port, String keystore, String password) {
        this.host   = host;
        this.port   = port;
        try {
            System.setProperty("javax.net.ssl.keyStore", keystore);
            System.setProperty("javax.net.ssl.keyStorePassword", password);
            System.setProperty("javax.net.ssl.trustStore", "certificates/truststore.jks");
            System.setProperty("javax.net.ssl.trustStorePassword", "eit060");


            SSLSocketFactory factory = (SSLSocketFactory)SSLSocketFactory.getDefault();

            System.out.println(":>client making contact with " + host + ":" + port);
            socket = (SSLSocket)factory.createSocket(host, port);
            socket.setUseClientMode(true);
            socket.startHandshake();
            System.out.println(":>client handshake is done.");

            inputStream = socket.getInputStream();
            inputReader = new InputStreamReader(inputStream);
            reader = new BufferedReader(inputReader);

            outputStream = socket.getOutputStream();
            outputWriter = new OutputStreamWriter(outputStream);
            writer = new BufferedWriter(outputWriter);
        } catch (Exception e) {
            System.out.println(e);
        }
    }*/

    public ArrayList<JournalEntry> getAllEntries(String id){
        if(sendString"getAllEntries:"+id){
            String s = waitForString(); // hämtar (nurseId,doctorId,unit)
            String[] j = parseCNField(s);

            if(!s.equals("null")){
                ArrayList<JournalEntry> journals = new ArrayList<JournalEntry>();
                for(int i = 0; i < j.length; i+=4){
                    String doctorId = j[i];
                    String nurseId = j[i+1];
                    String division = j[i+2];
                    String dScript = j[i+3];
                    JournalEntry je= new JournalEntry(nurseId, doctorId, division)
                    je.addNotes(dScript);
                    journals.add(je);
                }
                return journals;
            }
        }
        return null;
    }
    public boolean createEntry(String id, String nurseId, String doctorId, String division){
        if (sendString("createEntry:"+id+":"+ nurseId +":"+ nurseId+":"+ division){
            String s = waitForString();
            if(s.equals("true")){
                return true;
            }
        }
        return false;
    }

    public boolean deleteEntries(String id){
        if(sendString("deleteEntries:"+ id)){
            String s = waitForString();
            if(s.equals("true")){
                return true;
            }
        }
        return false;
    }

    public boolean addNote(String id, int entryNo, String note ){
        if(sendString("addNote:"+id +":"+ entryNo+":"+ note)){
            String s = waitForString();
            if(s.equals("true")){
                return true;
            }
        }
        return false;
    }

    public String[] parseCNField(){
        return s.split(":");
    }

    public String waitForString(){
        while(!socket.isClosed()){
            try{

                String s = null;
                if((s = reader.readLine()) != null){
                    System.out.prinln(":>clienten tar emot " + s);
                    return s;
                }
            }catch(IOException e){
                e.printStackTrace();
            }
        } 
        return null;
    }

    public boolean sendString(String s){
        try{
            writer.write(s+"\n");
            writer.flush();
            outputWriter.flush();
            outputStream.flush();
            System.out.println(":> Client skickar"+ s);
            return true;
        }catch(IOException e){
            return false;
        }
    }
    public void close(){
        try{
            reader.close();
            inputReader.close()
            inputStream.close();

            writer.close();
            outputWriter.close();
            outputStream.close();

            socket.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        String host = null;
        int port = -1;
        for (int i = 0; i < args.length; i++) {
            System.out.println("args[" + i + "] = " + args[i]);
        }
        if (args.length < 2) {
            System.out.println("USAGE: java client host port");
            System.exit(-1);
        }
        try { /* get input parameters */
            host = args[0];
            port = Integer.parseInt(args[1]);
        } catch (IllegalArgumentException e) {
            System.out.println("USAGE: java client host port");
            System.exit(-1);
        }

        try { /* set up a key manager for client authentication */
            SSLSocketFactory factory = null;
            try {
                char[] password = "password".toCharArray();
                KeyStore ks = KeyStore.getInstance("JKS");
                KeyStore ts = KeyStore.getInstance("JKS");
                KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
                TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
                SSLContext ctx = SSLContext.getInstance("TLS");
                ks.load(new FileInputStream("clientkeystore"), password);  // keystore password (storepass)
				ts.load(new FileInputStream("clienttruststore"), password); // truststore password (storepass);
				kmf.init(ks, password); // user password (keypass)
				tmf.init(ts); // keystore can be used as truststore here
				ctx.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);
                factory = ctx.getSocketFactory();
            } catch (Exception e) {
                throw new IOException(e.getMessage());
            }
            SSLSocket socket = (SSLSocket)factory.createSocket(host, port);
            System.out.println("\nsocket before handshake:\n" + socket + "\n");

            /*
             * send http request
             *
             * See SSLSocketClient.java for more information about why
             * there is a forced handshake here when using PrintWriters.
             */
            socket.startHandshake();

            SSLSession session = socket.getSession();
            X509Certificate cert = (X509Certificate)session.getPeerCertificateChain()[0];
            String subject = cert.getSubjectDN().getName();
            System.out.println("certificate name (subject DN field) on ce2rtificate received from server:\n" + subject + "\n");
            System.out.println("socket after handshake:\n" + socket + "\n");
            System.out.println("secure connection established\n\n");

            BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // String msg;
			// for (;;) {
   //              System.out.print(">");
   //              msg = read.readLine();
   //              if (msg.equalsIgnoreCase("quit")) {
			// 	    break;
			// 	}
   //              System.out.print("sending '" + msg + "' to server...");
   //              out.println(msg);
   //              out.flush();
   //              System.out.println("done");

   //              System.out.println("received '" + in.readLine() + "' from server\n");
   //          }
            in.close();
			out.close();
			read.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
