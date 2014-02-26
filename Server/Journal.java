package server;
import java.util.ArrayList;


public class journal{
	private Patient p;
	private ArrayList<JournalEntry> entries;

	public journal(Patient p){
		this.p=p;
		entries = new ArrayList<JournalEntry>();	
	}

	public void addEntry(JournalEntry je){
		entries.add(je);
	}

	public ArrayList<JournalEntry> getEntries(){
		return entries;
	}

	public void printJournal(){
		for(int i=0; i < entries.size(); i++){
			System.out.println(entries.get(i));
		}
	}

	public boolean findDoctor(String id){
		for(JournalEntry e: entries){
			if(e.getDoctorId().equals(id)){
				return true;
			}
		}
		return false;
	}
	public boolean findNurse(String id){
		for(JournalEntry e: entries){
			if(e.getNurseId().equals(id)){
				return true;
			}
		}
		return false;
	}



}