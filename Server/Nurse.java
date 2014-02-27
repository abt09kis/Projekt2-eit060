package server;

public class Nurse{
	private String division;
	private String name;
	private String id;
	private boolean isNurse;

	public Nurse(String division, String name, String id, boolean isNurse){
		this.unit=unit;
		this.name=name;
		this.id=id;
		this.isNurse=isNurse;
	}

	public boolean isNurse(){
		return isNurse;
	}

	public String getDivision(){
		return division;
	}

	public String getId(){
		return id;
	}
}
