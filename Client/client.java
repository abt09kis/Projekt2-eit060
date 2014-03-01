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

private X509Certificate cert;
private static final int PATIENT = 0;
private static final int NURSE = 1;
private static final int DOCTOR = 2;
private static final int GOVERNMENT = 3;

/*
 * This example shows how to set up a key manager to perform client
 * authentication.
 *
 * This program assumes that the client is not inside a firewall.
 * The application can be modified to connect to a server outside
 * the firewall by following SSLSocketClientWithTunneling.java.
 */
public class client {

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

            SSLSession session = socket.getSession();
            cert = (X509Certificate)session.getPeerCertificateChain()[0]; //gör så vi kan göra anropet få SubjectDn name sen i getType metoden

            inputStream = socket.getInputStream();
            inputReader = new InputStreamReader(inputStream);
            reader = new BufferedReader(inputReader);

            outputStream = socket.getOutputStream();
            outputWriter = new OutputStreamWriter(outputStream);
            writer = new BufferedWriter(outputWriter);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

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
 
    public String[] parseCNField(String s){
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

    public static void main(String[] args) {
        /*
         * Logga in som Alfred, doktor.
         */
        String id = "d01";
        Client client = new Client("localhost", 10000, "certificates/" + id + ".jks", "eit060");

        System.out.println(":>client User authenticated as " + id + ".");

        // Hämta alla mina journalentries
        System.out.println("Hämtar alla Alfreds entries!");
        ArrayList<JournalEntry> journals = client.getAllEntries(id);
        if (journals != null) {
            for (int i = 0; i < journals.size(); i++) {
                JournalEntry entry = journals.get(i);
                System.out.println(i + ". " + entry);
            }
        }
        client.close();
    }

    public int getType(){
        String subject = cert.getSubjectDN().getName();
        String typeLetter = Character.toString(text.charAt(3));
        if(typeLetter.equals("P")){
            return PATIENT;
        } else if(typeLetter.equals("N")) {
            return NURSE;
        } else if(typeLetter.equals("D")){
            return DOCTOR;    
        } else{
            return GOVERNMENT;
        }
    }
}

    
