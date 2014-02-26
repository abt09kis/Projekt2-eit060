package server;

public class Nurse{
	private String unit;
	private String name;
	private String id;
	private boolean isNurse;

	public Nurse(String unit, String name, String id, boolean isNurse){
		this.unit=unit;
		this.name=name;
		this.id=id;
		this.isNurse=isNurse;
	}

	public boolean isNurse(){
		return isNurse;
	}

	public String getUnit(){
		return unit;
	}

	public String getId(){
		return id;
	}
}
