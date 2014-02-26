public class Users{
	private HashMap<String, Doctor> doctorMap;
	private HashMap<String, Nurse> nurseMap;
	private HashMap<String, Patient> patientMap;


	public Users(){
		doctorMap = new HashMap<String, Doctor>();
		nurseMap = new HashMap<String, Nurse>();
		patientMap = new HashMap<String, Patient>();
	}

	public Doctor findDoctor(String id){
		return doctorMap.get(id);
	}


	public Nurse findDoctor(String id){
		return NurseMap.get(id);
	}

	public Patient findDoctor(String id){
		return PatientMap.get(id);
	}


	
}