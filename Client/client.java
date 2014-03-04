package client;

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

	public Client(String host, int port, String clientkeystore, String password) {
		try {
			System.setProperty("javax.net.ssl.keyStore", "C:/Users/Philip/Desktop/Server/Server/src/Server/certificates/" + clientkeystore);
			System.setProperty("javax.net.ssl.keyStorePassword", password);
			System.setProperty("javax.net.ssl.trustStore", "C:/Users/Philip/Desktop/Server/Server/src/Server/certificates/clienttruststore");
			System.setProperty("javax.net.ssl.trustStorePassword", "eit060");

			SSLSocketFactory socketFactory = (SSLSocketFactory)SSLSocketFactory.getDefault();
			
			System.out.println("The client is connecting to the server through " + host + ":" + port);
		    socket = (SSLSocket)socketFactory.createSocket(host, port);
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
		if(sendStringToServer("readJournal:"+patientID)){
			s = readStringFromServer();
		} 
		return s;
	}

	public String newPatient(String patientID){
		String s = "";
		if(sendStringToServer("newPatient:"+patientID)){
			s = readStringFromServer();
		}
		return s;
	}
	
	public String writeToJournal(String patientID, String nurseID, String doctorID, String text){
		String s = "";
		if(sendStringToServer("writeToJournal:" +patientID+ ":" +nurseID+ ":" +doctorID+ ":" +text)){
			s = readStringFromServer();
		}
		return s;
	}
	
	public String deletePatient(String patientID){
		String s = "";
		if(sendStringToServer("deletePatient:" +patientID)){
			s = readStringFromServer();
		}
		return s;
	}

	public String readStringFromServer() {
		while (!socket.isClosed()) {
			try {
				String s = null;
				if ((s = reader.readLine()) != null) {
					System.out.println("The client received: " + s);
					return s;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public boolean sendStringToServer(String s) {
		try {
			writer.write(s +"\n");
			writer.flush();
			outputWriter.flush();
			outputStream.flush();
			System.out.println("The client is sending " + s);
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