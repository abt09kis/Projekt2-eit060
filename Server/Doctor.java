package Server;

public class Doctor{
	private String division;
	private String name;
	private String doctorID;

	public Doctor(String division, String name, String doctorID){
		this.division=division;
		this.name=name;
		this.doctorID=doctorID;
	}

	public String getDivision(){
		return division;
	}

	public String getDoctorID(){
		return doctorID;
	}
}
