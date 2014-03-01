package Server;

public class JournalEntry(){

	private String nurseID;
	private String doctorID;
	private String division;
	private String date;
	private StringBuilder dScript;

	public JournalEntry(String nurseID, String doctorID, String division, String date){
		this.nurseID=nurseID;
		this.doctorID=doctorID;
		this.division=division;
		this.date = date;
		dScript = new StringBuilder();
	}

	public String getNurseID(){
		return nurseID;
	}

	public String getDoctorID(){
		return doctorID;
	}

	public String getDivision(){
		return division;
	}

	pulic String toString(){
		String n= getNotes();
		return doctorID+ ":" + nurseID+ ":" + division+ ":" + date ":" +(n.isEmpty()? "null" : n);
		} fs
	public void printStr(){
		System.out.println("Doktor: " + doctorID + "\n" +
			"Nurse: "+ nurseID + "\n"+
			"Avdelning: "+ division+ "\n"+
			"Date: " + date "\n" +
			"Anteckningar: "+ getNotes());
	}

	public void addNote(String text){
		dScript.append(text + "\n");
	}

	public String getNotes(){
			return dScript.toString();
	}
 
	
}