package Client;

	private static final int PATIENT = 0;
	private static final int NURSE = 1;
	private static final int DOCTOR = 2;
	private static final int GOVERNMENT = 3;
	private journalManager jm;
	
	public static void main(String[] args) {


		boolean close = false;
		scanner = new Scanner(System.in);

		//Ska vi göra ungeför så här??
		System.out.println("Write your certificate name: ");
		String certName = scan.next();
		System.out.print("Enter Password: ");
		String password = scanner.next();
		Client client = new client("localhost", 10000, "certificates/" + certName + ".jks", password);
		int type = client.getType(); //Hämtar typen från certifikatet
		jm = new journalManager();


	if (type == PATIENT) {
			System.out.println("Welcome patient, here is your journal:");
			jm.readJournal("", "", type);
		} else if (type == DOCTOR) {
			String subjectDN = getSubjectDN(); //Metod från klassen client. Returnerar en sträng med subjectDN
			String doctorID = substring(10, 12);
				while(!close){
				System.out.println("Welcome doctor, what would you like to do?");
				System.out.println("1. Read a journal");
				System.out.println("2. Write to a journal");
				System.out.println("3. Add a new Patient and a journal for him/her";
				System.out.println("4. exit");

				int choice = scanner.nextInt();
				if(choice!=4){
						doDoctorCommand(choice, doctorID);
				}else{
						close = true;	
				}
			}
		} else if (type == NURSE) {
				String subjectDN = getSubjectDN(); //Metod från klassen client. Returnerar en sträng med subjectDN
				String nurseID = substring(9, 11);
				System.out.println("Welcome nurse, what would you like to do?");
				System.out.println("1. Read a journal");
				System.out.println("2. Write to a journal");
				System.out.println("3. exit");

				int choice = scanner.nextInt();
				if(choice!=3){
					doNurseCommand(choice, nurseID);
				}
			} else if (type == GOVERNMENT) {
				System.out.println("Welcome goverment, what would you like to do?");
				System.out.println("1. Read a journal");
				System.out.println("2. Delete a journal");
				System.out.println("3. exit");
				int choice = scanner.nextInt();
				if(choice != 3){
					government(choice);	
				}
			}
		}

		private void doDoctorCommand(int choice, String doctorID){				
			//case-satser utifrån vad läkaren har valt att han vill göra!!
				switch(choice){	
					case 1: //Läs en journalEntry
						System.out.Println("Enter PatientID: ");						
						String patientID = scanner.next();
						jm.readJournal(patientID, doctorID, DOCTOR);
					case 2:	//Lägg till text i en existerande journal entry					
						System.out.Println("Enter patientID: ");
						String patientID = scanner.next();
						System.out.Println("Enter nurseId: ");
						String nurseID = scanner.next();
						System.out.Println("Enter division: ");
						String division = scanner.next();
						System.out.Println("Enter date: ");
						String date = scanner.next();
						System.out.Println("Enter text: ");
						String text = scanner.next();

						JournalEntry je = new JournalEntry(nurseID, doctorID, division, date);
						je.addNote(text);

						jm.writeToJournal(DOCTOR, patientID, nurseID, doctorID, je);
					
					case 3: //Ny patient
						System.out.println("Enter Patient ID: ")
						String patientID = scanner.next();
						jm.addJournal(patientID);
				}
			}

		}
		// private void patient(idNbr){

		// 	//hitta patienten i listan av Users och anropa en metod som skriver ut alla journalentries är den patienten
		// 		Patient temp = findPatient(String id);
		// 		temp.getJournal.printJournal;

		// }
		private void government(int choice){
		switch(choice){
			case 1:
					System.out.Println("Enter PatientID: ")	
					String patientId = scanner.next();
					jm.readJournal(patientID, "", GOVERNMENT);

			case 2:
					System.out.Println("Enter PatientID for removal: ");	
					String patientID = scanner.next();
					boolean checkIfDeleted = jm.deletePatient(patientID);
					if(checkIfDeleted){
						System.out.println("Patienten har blivit borttagen");
					} else {
						System.out.println("Ingen patient ID: " + patientID + " kunde hittas");
					}					
		}
	}
		private void doNurseCommand(int choice, String nurseID){

					case 1: //Läs en journalEntry
					
						System.out.Println("Enter PatientID: ");						
						String patientID = scanner.next();
						jm.readJournal(patientID, nurseID, NURSE);

					case 2:	//Lägg till text i en existerande journal entry					
						System.out.Println("Enter patientID: ");
						String patientID = scanner.next();
						System.out.Println("Enter doctorID: ");
						String doctorID = scanner.next();
						System.out.Println("Enter division: ");
						String division = scanner.next();
						System.out.Println("Enter date: ");
						String date = scanner.next();
						System.out.Println("Enter text: ");
						String text = scanner.next();

						JournalEntry je = new JournalEntry(nurseID, doctorID, division, date);
						je.addNote(text);
						jm.writeToJournal(NURSE, patientID, nurseID, doctorID, je);
					
		}




