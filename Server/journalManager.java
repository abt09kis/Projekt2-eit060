	package Server;

	public class JournalManager{
		private static final int PATIENT_TYPE = 0;
		private static final int NURSE_TYPE = 1;
		private static final int DOCTOR_TYPE = 2;
		private static final int GOVERNMENT_TYPE = 3;
		private Logger logg;
		private Users users; 	

		//Skapar alla användare och patientjournaler som journalManager tar hand om
		public JournalManager(){
			users = new Users();
			users.fillTestUsers();
		}
		
		public Users getUsers(){
			return users;
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
	public String readJournal(String patientID, String staffID, int type){
		String str = "";
		Journal patientJournal = users.findJournal(patientID);  //Hittar journalen som tillhör patienten med patientID
		if(type == PATIENT_TYPE || type == GOVERNMENT_TYPE){ //Om patienten själv eller myndigheten gör req så printas hela journalen
			if(patientID.equals(staffID) || type == GOVERNMENT_TYPE){
				str = patientJournal.getJournal();				
			}
		} else if(type == NURSE_TYPE){
			Nurse nurse = users.findNurse(staffID);
			str = patientJournal.getAllowedJournalEntries(nurse);	//Det nurse har access till printas
		} else {
			Doctor doctor = users.findDoctor(staffID);
			str = patientJournal.getAllowedJournalEntries(doctor);	////Det doctor har access till printas
		}
		return str;
	}


	//Om nurse eller doctor har skriv rättigheter till journaler läggs JournalEntry je till
	public boolean writeToJournal(int type, String patientID, String nurseID, String doctorID, String division, String text){
		Journal patientJournal = users.findJournal(patientID);	//hämtar ut journalen för personen
		JournalEntry je = new JournalEntry(nurseID, doctorID, division, "idag");
		je.addNote(text);
		if(type == NURSE_TYPE){
			Nurse nurse = users.findNurse(nurseID);
			if(patientJournal.hasWriteRights(nurse)){
				patientJournal.addEntry(je);
				users.addPatient(patientID, patientJournal);  //Ersätter den gamla journalen med den uppdaterade
				return true;
			}
		} else {
			Doctor doctor = users.findDoctor(doctorID);
			if(patientJournal.hasWriteRights(doctor)){
				patientJournal.addEntry(je);
				users.addPatient(patientID, patientJournal);  //Ersätter den gamla journalen med den uppdaterade
				return true;
			}
		}
		return false;
	}
	}


