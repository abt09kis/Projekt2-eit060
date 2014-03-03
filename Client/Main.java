package Client;
import java.util.Scanner;

public class Main{
	private Scanner scanner;
	Client client;
	
	public Main(){
	scanner = new Scanner(System.in);
	System.out.println("Write your certificate name: ");
	String certName = scanner.next();
	System.out.println("Enter Password: ");
	String password = scanner.next();
	client = new Client("localhost", 10000, "certificates/" + certName + ".jks", password);
	}
	
	public static void main(String[] args){
		Main m = new Main();
		m.doCommands();
	}

	public void doCommands(){
		boolean closeProgram = false;
		while(!closeProgram){
			System.out.println("Welcome, what would you like to do?");
			System.out.println("1. Read a journal");
			System.out.println("2. Write to a journal");
			System.out.println("3. Add a new patient");
			System.out.println("4. Deleta a patient");
			System.out.println("5. Exit program");
			int chosenOption = scanner.nextInt();
			String s;
			switch(chosenOption){
			case 1: 
				System.out.println("Write patientID");
				s = client.readJournal(scanner.next());
				System.out.println(s);
				break;
			case 2:
				System.out.println("Write PatientID");
				String patientID = scanner.next();
				System.out.println("Write nurseID");
				String nurseID = scanner.next();
				System.out.println("Write doctorID");
				String doctorID = scanner.next();
				System.out.println("Write text");
				String text = scanner.next();
				s = client.writeToJournal(patientID, nurseID, doctorID, text);
				System.out.println(s);
				break;
			case 3:
				System.out.println("Write patientID");
				s = client.newPatient(scanner.next());
				System.out.println(s);
				break;
			case 4:
				System.out.println("Write patientID");
				s = client.deletePatient(scanner.next());
				System.out.println(s);
				break;
			case 5: 
				closeProgram = true;
				break;
			default: 
				System.out.println("Ogiltigt kommando");
				break;
			}
		}
	}
}




