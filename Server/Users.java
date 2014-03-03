package Server;

import java.util.HashMap;

public class Users{
	private HashMap<String, Doctor> doctorMap;
	private HashMap<String, Nurse> nurseMap;
	private HashMap<String, Journal> journalMap;

	public Users(){
		doctorMap = new HashMap<String, Doctor>();
		nurseMap = new HashMap<String, Nurse>();
		journalMap = new HashMap<String, Journal>();
	}

	public Doctor findDoctor(String doctorID){
		return doctorMap.get(doctorID);
	}

	public Nurse findNurse(String nurseID){
		return nurseMap.get(nurseID);
	}

	public Journal findJournal(String patientID){
		return journalMap.get(patientID);
	}

	public boolean deletePatient(String patientID){
		if(journalMap.containsKey(patientID)){
			journalMap.remove(patientID);
			return true;
		}
		return false;
	}

	public void addPatient(String patientID, Journal journal){
		journalMap.put(patientID, journal);
	}

	

	public void fillTestUsers(){
		//Skapar en patient Johan
		Journal johanJournal = new Journal();
		johanJournal.addEntry(new JournalEntry("n01", "d01", "Hals", "igår"));
		johanJournal.addEntry(new JournalEntry("n02", "d01", "Ortopeden", "idag"));
		journalMap.put("p01", johanJournal);

		//Skapar en patient Filippa
		Journal filippaJournal = new Journal();
		johanJournal.addEntry( new JournalEntry("n02", "d02", "Hals", "igår"));
		journalMap.put("p02", filippaJournal);

		//Skapar en läkare Alfred.
		Doctor alfred = new Doctor("Hals","d01","Alfred Pennyworth");
		doctorMap.put("d01", alfred);

		//Skapar en läkare Julian.
		Doctor julian = new Doctor("Ortopeden", "d02","Julian Grey");
		doctorMap.put("d02", julian);

		//Skapar en Sjuksköterska Mia.
		Nurse mia= new Nurse("Hals","Mia Olofsson", "n01");
		nurseMap.put("n01", mia);

		//Skapar en Sjuksköterska Ann-britt.
		Nurse annBritt = new Nurse("Ortopeden","Ann-britt Gunnarsson", "n02");
		nurseMap.put("n02", annBritt);
	}
}