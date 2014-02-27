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


		//Skapar en patient.
		Patient patient1 = new Patient("p01", "Johan");
		patient1.addJournalEntry("d01", "n01", "Hals");
		patient1.addJournalEntry("d02", "n01", "Ortopeden");
		patients.put(patient1.getId(), Patient1);

		//Likadant för att skapa en till.



	}
}