package Client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public class Client {

	private SSLSocket socket;
	private BufferedReader reader;
	private BufferedWriter writer;

	private InputStream inputStream;
	private InputStreamReader inputReader;
	private OutputStream outputStream;
	private OutputStreamWriter outputWriter;

	public Client(String host, int port, String keystore, String password) {
		try {
			System.setProperty("javax.net.ssl.keyStore", keystore);
			System.setProperty("javax.net.ssl.keyStorePassword", password);
			System.setProperty("javax.net.ssl.trustStore", "/Users/Kevin/Desktop/Projekt2-eit060/Certificates/clienttruststore");
			System.setProperty("javax.net.ssl.trustStorePassword", "eit060");

			SSLSocketFactory factory = (SSLSocketFactory)SSLSocketFactory.getDefault();
			
			System.out.println("The client is connecting to the server through " + host + ":" + port);
		    socket = (SSLSocket)factory.createSocket(host, port);
			socket.setUseClientMode(true);
			socket.startHandshake();
			System.out.println("Authentification has been made");

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
	
	public String readJournal(String patientID){
		String s = "";
		if(sendString("readJournal:"+patientID)){
			s = waitForString();
		} 
		return s;
	}

	public String newPatient(String patientID){
		String s = "";
		if(sendString("newPatient:"+patientID)){
			s = waitForString();
		}
		return s;
	}
	
	public String writeToJournal(String patientID, String nurseID, String doctorID, String text){
		String s = "";
		if(sendString("writeToJournal:" +patientID+ ":" +nurseID+ ":" +doctorID+ ":" +text)){
			s = waitForString();
		}
		return s;
	}
	
	public String deletePatient(String patientID){
		String s = "";
		if(sendString("deletePatient:" +patientID)){
			s = waitForString();
		}
		return s;
	}

	public String waitForString() {
		while (!socket.isClosed()) {
			try {
				String s = null;
				if ((s = reader.readLine()) != null) {
					System.out.println(":>client recieveing " + s);
					return s;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public boolean sendString(String s) {
		try {
			writer.write(s +"\n");
			writer.flush();
			outputWriter.flush();
			outputStream.flush();
			System.out.println(":>client sending " + s);
			return true;
		} catch (IOException e) {
			return false;
		}
	}

	public void close() {
		try {
			reader.close();
			inputReader.close();
			inputStream.close();

			writer.close();
			outputWriter.close();
			outputStream.close();

			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}