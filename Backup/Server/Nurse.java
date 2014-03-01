package Server;

public class Nurse{
	private String division;
	private String name;
	private String nurseID;

	public Nurse(String division, String name, String nurseId){
		this.division = division;
		this.name = name;
		this.nurseID = nurseID;
	}

	public String getDivision(){
		return division;
	}

	public String getNurseID(){
		return nurseID;
	}
}
