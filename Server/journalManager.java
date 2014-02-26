	public class journalManager{
		private static final int PATIENT = 0;
		private static final int NURSE = 1;
		private static final int DOCTOR = 2;
		private static final int GOVERNMENT = 3;
		
		//klassen som innehåller alla Journaler

		public Hashmap<IdNbr, Journal> journals = new Hashmap();


	public void addJournal(int idNbr, Journal journal){
		//Lägg till en person i registret av journaler genom att lägga in dens personnummer och journal i mappen
		journals.put(idNbr, journal);
	}

	//Tar bort en person ur journalregistret med angivet personnummer
	public boolean deletePatient(String idNbr){
			deletePatient(idNbr);
			return journals.remove(idNbr);
		}

	//Tar bort en person ur journalregistret med angivet personnummer	
	}
	public journal readJournal(String idNbr, String staffID, int type){
		if(type == PATIENT || type GOVERNMENT){
			return journals.get(idNbr);
		} else if(type == NURSE) {
			Nurse nurse = nurseMap.get(staffID);
			if(nurse.getDivision().equals("Division of pati") || nurse.getPatients().equals("Nurse of patient")){
				return journals.get(idNbr);
			} else {
				Doctor doctor = doctorMap.get(staffID);
				if(doctor.getDivision().equals("Division of patient") || doctor.getPatients().equals("Doctor of patient"))){
					return journals.get(idNbr);
				}
			}
	}
	public boolean writeTo(int idNbr, String text, String nurseiId, String docId, String div, String date){


		Journal jour = journals.get(idNbr);	//hätar ut journalen för personen
		JournalEntry journalEntry = new JournalEntry(nurseiId, docId, div, date);
		journalEntry.addNote(text);
		jour.add(journalEntry);

	}
	}


