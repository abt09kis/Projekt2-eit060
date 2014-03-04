package Server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import javax.security.cert.X509Certificate;
import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

public class Server extends Thread {

	private int port = 19000;

	private SSLSocket socket;
	private BufferedReader reader;
	private BufferedWriter writer;

	private InputStream inputStream;
	private InputStreamReader inputReader;
	private OutputStream outputStream;
	private OutputStreamWriter outputWriter;

	private String userID;
	private boolean loggedIn;
	private Users users;
	private JournalManager jm;
	private Doctor doctor;
	private Nurse nurse;
	private int type;
	private String ID;
	private Logger audit;

	private static final int TYPE_PATIENT = 0;
	private static final int TYPE_NURSE = 1;
	private static final int TYPE_DOCTOR = 2;
	private static final int TYPE_GOVERNMENT = 3;

	public Server() {
		audit = new Logger("audit.log");
		jm = new JournalManager();

		System.setProperty("javax.net.ssl.keyStore", "C:/Users/Philip/Desktop/Server/Server/src/Server/certificates/serverkeystore");
		System.setProperty("javax.net.ssl.keyStorePassword", "eit060");
		System.setProperty("javax.net.ssl.trustStore", "C:/Users/Philip/Desktop/Server/Server/src/Server/certificates/servertruststore");
		System.setProperty("javax.net.ssl.trustStorePassword", "eit060");

		try {
			SSLServerSocketFactory factory = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
			SSLServerSocket server = (SSLServerSocket) factory.createServerSocket(port);
			server.setNeedClientAuth(true);

			System.out.println("Väntar på anslutning från klient...");
			socket = (SSLSocket) server.accept();

			SSLSession session = socket.getSession();
			X509Certificate cert = (X509Certificate) session.getPeerCertificateChain()[0];
			System.out.println("Servern tog emot certifikat: " + cert.getSubjectDN().getName());
			System.out.println("Servern anslöt sig mot: " + socket.getInetAddress().getHostName());


			userID = parseID(cert.getSubjectDN().getName());
			String userType = userID.substring(0, 1);
			users = jm.getUsers();
			ID = getPersonalID(cert.getSubjectDN().getName());
			if(userType.equals("p")){
				type = TYPE_PATIENT;
			}else if(userType.equals("N")){
				type = TYPE_NURSE;
				nurse = users.findNurse(ID);
			}else if(userType.equals("D")){
				type = TYPE_DOCTOR;
				doctor = users.findDoctor(ID);
			}else if(userType.equals("G")){
				type = TYPE_GOVERNMENT;
			}
			audit.log(ID, null, "Användaren" +ID+ " har loggat. ");
			System.out.println("ID på personen som gör req: " +ID);
			System.out.println("Typen på användaren är: " + type);

			inputStream = socket.getInputStream();
			inputReader = new InputStreamReader(inputStream);
			reader = new BufferedReader(inputReader);

			outputStream = socket.getOutputStream();
			outputWriter = new OutputStreamWriter(outputStream);
			writer = new BufferedWriter(outputWriter);
		} catch (Exception e) {
			e.printStackTrace();
			loggedIn = false;
		}

		loggedIn = true;
	}

	private String parseID(String name) {
		String[] s = name.split(",");
		return s[0].split("=")[1];
	}

	private String getPersonalID(String name) {
		String[] s = name.split(",");
		String ID = s[0].split(" ")[1];
		return ID.substring(0, 3);
	}

	public void run() {
		try {

			while (!socket.isClosed()) {
				if (loggedIn) { // logged in
					System.out.println("Väntar på kommando.. ");
					String str = readStringFromClient();

					String[] clientCommand = parseCommand(str);

					if (clientCommand[0].equals("readJournal")) {
						System.out.println(clientCommand[1]);
						Journal patientJournal = users.findJournal(clientCommand[1]);
						String s = "";
						if (patientJournal != null) {
							if(doctor != null){
								s = jm.readJournal(clientCommand[1], doctor.getDoctorID(), type);
							}else if(nurse != null){
								s = jm.readJournal(clientCommand[1], nurse.getNurseID(), type);
							}else {
								s = jm.readJournal(clientCommand[1], ID, type);
							}
						}
						if(!s.isEmpty()){
							audit.log(ID, clientCommand[1], "Access godkänt till " + ID + " för att läsa journal" + clientCommand[1]);
							sendStringToClient(s);
						} else {
							audit.log(ID, clientCommand[1], "Access nekad till " + ID + " för att läsa journal" + clientCommand[1]);
							sendStringToClient("Ej access att läsa journal");
						} 
					}else if (clientCommand[0].equals("newPatient")) { 
						if(type == TYPE_DOCTOR){
							jm.newPatient(clientCommand[1]);
							audit.log(ID, clientCommand[1], "Doctor med id: " + ID + "skapade en ny patient med ID: " + clientCommand[1]);
							sendStringToClient("Ny patient skapad. ");
						} else {
							audit.log(ID, clientCommand[1], "ID: " + ID + "försökte skapa en ny patient med ID: " + clientCommand[1] + " men nekades");
							sendStringToClient("Ej access att lägga till patient. ");
						}
					} else if (clientCommand[0].equals("deletePatient")) {
						String patientID = clientCommand[1];
						if(type == TYPE_GOVERNMENT){
							audit.log(ID, clientCommand[1], "Gov tog bort patient med ID: " + clientCommand[1]);
							sendStringToClient("Patienten är borttagen. ");
						} else {
							audit.log(ID, patientID, "ID: " + ID + "försökte ta bort en patient med ID: " + clientCommand[1] + " men nekades");
							sendStringToClient("Ej access med att ta bort patient. ");
						}
					}else if(clientCommand[0].equals("writeToJournal")){
						String patientID = clientCommand[1];
						String nurseID = clientCommand[2];
						String doctorID = clientCommand[3];
						String text = clientCommand[4];						
						if(type == TYPE_NURSE){
							boolean hasRights = jm.writeToJournal(type, patientID, ID, doctorID, nurse.getDivision(), text);
							if(hasRights){
								audit.log(ID, patientID, "ID: " + ID + "skrev ett entry till patient med ID: " + clientCommand[1]);
								sendStringToClient("Texten är tillagd.");
							} else {
								audit.log(ID, patientID, "ID: " + ID + "skrev ett entry till patient med ID: " + clientCommand[1]+ " men nekades");
								sendStringToClient("Nekades.");
							}
						} else if(type == TYPE_DOCTOR){
							boolean hasRights = jm.writeToJournal(type, patientID, nurseID, ID, doctor.getDivision(), text);
							if(hasRights){
								audit.log(ID, patientID, "ID: " + ID + "skrev ett entry till patient med ID: " + clientCommand[1]);
								sendStringToClient("Texten är tillagd.");
							} else {
								audit.log(ID, patientID, "ID: " + ID + "skrev ett entry till patient med ID: " + clientCommand[1]+ " men nekades");
								sendStringToClient("Nekades.");
							}
						} else {
							audit.log(ID, clientCommand[1], "ID: " + ID + "skrev ett entry till patient med ID: " + clientCommand[1]+ " men nekades");
							sendStringToClient("Nekades.");
						}
					}
				}
			}

			socket.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public String[] parseCommand(String s) {
		return s.split(":");
	}

	public void sendStringToClient(String s) {
		try {
			writer.write(s +"\n");
			writer.flush();
			outputWriter.flush();
			outputStream.flush();
			System.out.println(":>server sending " + s);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String readStringFromClient() {
		while (!socket.isClosed()) {
			try {
				String s = null;
				if ((s = reader.readLine()) != null) {
					s = s.replace("\n", "");
					System.out.println(":>server recieveing " + s);
					return s;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public static void main(String[] args){
		Server a = new Server();
		a.start();
	}
}