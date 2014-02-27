package Server;

public class Doctor{

	private boolean isDoctor;
	private String unit;
	private String id:
	private String name;

	public Doctor(String unit, String id, String name, boolean isDoctor){
		this.unit=unit;
		this.id=id;
		this.name=name;
		this.isDoctor=isDoctor;

	}
	public boolean isDoctor(){
		return isDoctor;
	}

	public String getId(){
		return id;
	}

	public String getUnit(){
		return unit;
	}
}