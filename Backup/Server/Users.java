package Server;
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
		return journalMap.remove(patientID);
	}

	public void addPatient(String patientID, Journal journal){
		journalMap.put(patientID, journal);
	}

	

	public void fillTestUsers(){
		//Skapar en patient Johan
		Journal johanJournal = new Journal();
		johanJournal.addJournalEntry( new addJournalEntry("n01", "d01", "Hals", "igår"));
		johanJournal.addJournalEntry(new addJournalEntry("n02", "d01", "Ortopeden", "idag"));
		journalMap.put(p01, johanJournal);

		//Skapar en patient Filippa
		Journal filippaJournal = new Journal();
		johanJournal.addJournalEntry( new addJournalEntry("n02", "d02", "Hals", "igår"));
		journalMap.put(p02, filippaJournal);

		//Skapar en läkare Alfred.
		Doctor alfred = new Doctor("Hals","d01","Alfred Pennyworth");
		doctorMap.put(alfred.getId(), alfred);

		//Skapar en läkare Julian.
		Doctor julian = new Doctor("Ortopeden", "d02","Julian Grey");
		doctorMap.put(julian.getId(), julian);

		//Skapar en Sjuksköterska Mia.
		Nurse mia= new Nurse("Hals","Mia Olofsson", "n01", true);
		nurseMap.put(mia.getId(), mia);

		//Skapar en Sjuksköterska Ann-britt.
		Nurse annBritt = new Nurse("Ortopeden","Ann-britt Gunnarsson", "n02", true);
		nurseMap.put(annBritt.getId(), annBritt);
	}
}