	package Server;

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

	//Prints the patient Journal 
	public void readJournal(String idNbr, String staffID, int type){
		Journal patientJournal = journals.get(IdNbr);
		Patient patient = patientMap.get(idNbr);
		ArrayList<JournalEntry> temp = patientJournal.getEntries();
		if(type == PATIENT || type GOVERNMENT){
			patientJournal.printJournal();
		} else if(type == NURSE){
			Nurse nurse = nurseMap.get(staffID);
			//Checks if the nurse is the assigned nurse to the patient or on the same division
			for(int i=0;i<temp.size();i++){
				if(temp.get(i).getNurseId().equals(nurse.getId()) || temp.get(i).getDivision().equals(nurse.getDivision())){
					temp.get(i).printStr();				
				}
			}
		} else {
			Doctor doctor = doctorMap.get(staffID);
			//Checks if the doctor is the assigned doctor to the patient or on the same division
			for(int k=0;k<temp.size();k++){
				if(temp.get(k).getDoctorId().equals(doctor.getId()) || temp.get(k).getDivision().equals(doctor.getDivision())){
					temp.get(k).printStr();				
				}			
			}
		}
		return null;
	}

	public boolean writeTo(int idNbr, String text, String nurseId, String docId, String div, String date){
		Journal jour = journals.get(idNbr);	//hämtar ut journalen för personen
		JournalEntry journalEntry = new JournalEntry(nurseId, docId, div, date);
		journalEntry.addNote(text);
		jour.add(journalEntry);
	}
	}


