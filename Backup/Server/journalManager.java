	package Server;

	public class journalManager{
		private static final int PATIENT = 0;
		private static final int NURSE = 1;
		private static final int DOCTOR = 2;
		private static final int GOVERNMENT = 3;
		private Logger logg;
		private Users users; 	

		//Skapar alla användare och patientjournaler som journalManager tar hand om
		public journalManager(){
			users = new Users();
			users.fillTestUsers();
		}

	//Skapar en ny patient med String patientID och en tom jfournal
	public void newPatient(String patientID){
		Journal journal = null;
		users.addPatient(patientID, journal);
	}

	//Tar bort en person ur journalregistret med angivet patientID
	public boolean deletePatient(String patientID){
		return users.deletePatient(patientID);	
	}

	//Prints the patient Journal. Nurses and doctors has access to relevant journalentries 
	public void readJournal(String patientID, String staffID, int type){
		Journal patientJournal = users.findJournal(patientID);  //Hittar journalen som tillhör patienten med patientID
		if(type == PATIENT || type GOVERNMENT){ //Om patienten själv eller myndigheten gör req så printas hela journalen
			patientJournal.printJournal();
		} else if(type == NURSE){
			Nurse nurse = users.findNurse();
			patientJournal.printAllowedJournalEntries(nurse);	//Det nurse har access till printas
		} else {
			Doctor doctor = users.findDoctor();
			patientJournal.printAllowedJournalEntries(doctor);	////Det doctor har access till printas
		}
	}


	//Om nurse eller doctor har skriv rättigheter till journaler läggs JournalEntry je till
	public boolean writeToJournal(int type, String patientID, String nurseID, String doctorID, JournalEntry je){
		Journal patientJournal = users.finJournal(patientID);	//hämtar ut journalen för personen
		if(type == NURSE){
			Nurse nurse = users.findNurse(nurseID);
			if(patientJournal.hasWriteRights(nurse)){
				patientJournal.add(je);
				users.addPatient(patientID, patientJournal);  //Ersätter den gamla journalen med den uppdaterade
			}
		} else {
			Doctor doctor = users.findDoctor(doctor);
			if(patientJournal.hasWriteRights(doctor)){
				patientJournal.add(je);
				users.addPatient(patientID, patientJournal);  //Ersätter den gamla journalen med den uppdaterade
			}
		}
	}
	}


