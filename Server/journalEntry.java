package Server;

public class JournalEntry{
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

	public String getStr(){
		dScript.append("Doktor: " + doctorID + "\t" + "Nurse: "+ nurseID + "\t"+ "Avdelning: "+ division+ "\t"+ "Date: " + date + "\n");
		return dScript.toString();
	}

	public void addNote(String text){
		dScript.append(text + "\t");
	}
}