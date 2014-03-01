package Server;
public class Users{
	private HashMap<String, Doctor> doctorMap;
	private HashMap<String, Nurse> nurseMap;
	private HashMap<String, Patient> patientMap;
	private HashMap<String,Government> govMap;


	public Users(){
		doctorMap = new HashMap<String, Doctor>();
		nurseMap = new HashMap<String, Nurse>();
		patientMap = new HashMap<String, Patient>();
		govMap = new HashMap<String, Government>();
	}

	public Doctor findDoctor(String id){
		return doctorMap.get(id);
	}


	public Nurse findNurse(String id){
		return NurseMap.get(id);
	}

	public Patient findPatient(String id){
		return patientMapMap.get(id);
	}

	Public ArrayList<Patient> getPatients(){
		return new ArrayList<Patient>(patientMap.values());

	}

	public void deletePatient(String id){
		patientMap.remove(id);
	}

	public void addPatient(String id, Patient patient){
		patientMap.add(id, patient);
	}

	public void fillTestUsers(){
		Government gov = new Government("socialstyrelsen","Socialstyrelsen");
		govMap.put("g01", gov);


		//Skapar en patient Johan.
		Patient johan = new Patient("p01", "Johan");
		johan.addJournalEntry("d01", "n01", "Hals");
		johan.addJournalEntry("d02", "n01", "Ortopeden");
		patients.put(johan.getId(), johan);

		//Skapar en patient Filippa.
		Patient filippa = new Patient("p02", "Filippa");
		filippa.addJournalEntry("d02","n02","Ortopeden");
		patients.put(filippa.getId(),filippa);

		//Skapar en läkare Alfred.
		Doctor alfred = new Doctor("Hals","d01","Alfred Pennyworth", true);
		doctorMap.put(alfred.getId(), alfred);

		//Skapar en läkare Julian.
		Doctor julian = new Doctor("Ortopeden", "d02","Julian Grey", true);
		doctorMap.put(julian.getId(), julian);

		//Skapar en Sjuksköterska Mia.
		Nurse mia= new Nurse("Hals","Mia Olofsson", "n01", true);
		nurseMap.put(mia.getId(), mia);

		//Skapar en Sjuksköterska Ann-britt.
		Nurse annBritt = new Nurse("Ortopeden","Ann-britt Gunnarsson", "n02", true);
		nurseMap.put(annBritt.getId(), annBritt);





	}
}