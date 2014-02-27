	public class journalManager{
		private static final int PATIENT = 0;
		private static final int NURSE = 1;
		private static final int DOCTOR = 2;
		private static final int GOVERNMENT = 3;
		private Hashmap<IdNbr, Journal> journals = new Hashmap();	
	
	//klassen som innehåller alla Journaler
	public void addJournal(int idNbr, Journal journal, Patient patient){
		//Lägg till en person i registret av journaler genom att lägga in dens personnummer och journal i mappen
		addPatient(idNbr, Patient);
		journals.put(idNbr, journal);
	}

	//Tar bort en person ur journalregistret med angivet personnummer
	public boolean deletePatient(String idNbr){
		deletePatient(idNbr);
		return journals.remove(idNbr);	
	}

	//Returns the patient journal 
	public journal readJournal(String idNbr, String staffID, int type){
		Journal patientJournal = journals.get(IdNbr);
		Patient patient = patientMap.get(idNbr);
		if(type == PATIENT || type GOVERNMENT){
			return journals.get(idNbr);
		} else if(type == NURSE){
			Nurse nurse = nurseMap.get(staffID);
			//Checks if the nurse is the assigned nurse to the patient or on the same division
			if(nurse.getDivision().equals(patient.getDivision()) || patientJournal.findNurse(nurse.getId())){
				return journals.get(idNbr);
			}
		} else {
			Doctor doctor = doctorMap.get(staffID);
			//Checks if the doctor is the assigned doctor to the patient or on the same division
			if(doctor.getDivision().equals(patient.getDivision()) || patientJournal.findDoctor(doctor.getId()){
				return journals.get(idNbr);
			}
		}
		return null;
	}

	public boolean writeTo(int idNbr, String text, String nurseiId, String docId, String div, String date){
		Journal jour = journals.get(idNbr);	//hämtar ut journalen för personen
		JournalEntry journalEntry = new JournalEntry(nurseiId, docId, div, date);
		journalEntry.addNote(text);
		jour.add(journalEntry);

	}
	}


