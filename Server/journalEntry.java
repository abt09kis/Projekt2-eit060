package server;

public class JournalEntry(){

	private String nurseId;
	private String doctorId;
	private String division;
	private StringBuilder dScript;

	public JournalEntry(String nurseId, String doctorId, String division){
		this.nurseId=nurseId;
		this.doctorId=doctorId;
		this.division=division;
		dScript=new StringBuilder();
	}

	public String getNurseId(){
		return nurseId;
	}

	public String getDoctorId(){
		return doctorId;
	}

	public String getDivision(){
		return division;
	}

	pulic String toString(){
		String n= getNotes();
		return doctorId+ ":" + nurseId+ ":" + division+ ":" +(n.isEmpty()? "null" : n);
		}
	public void printStr(){
		System.out.println("Doktor: " + doctorId + "\n" +
			"Nurse: "+ nurseId + "\n"+
			"Avdelning: "+ division+ "\n"+
			"Anteckningar: "+ getNotes());
	}

	public void addNote(String string){
		dScript.append(string + "\n");
	}

	public String getNotes(){
			return dScript.toString();
	}
 
	
}