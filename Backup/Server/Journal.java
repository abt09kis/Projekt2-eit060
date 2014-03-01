package Server;
import java.util.ArrayList;


public class journal{
	private ArrayList<JournalEntry> entries;

	public journal()){
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

	public boolean findDivision(String division){
		for(JournalEntry e: entries){
			if(e.getDivision().equals(division)){
				return true;
			}
		}
		return false;
	}

	public void printJournal(){
		for(int i = 0; i < entries.size(); i++){
			entries.get(i).printStr();
		}
	}

	public void printAllowedJournalEntries(Nurse nurse){
		String nurseID = nurse.getNurseID();
		String division = nurse.getDivision();
		for(int i = 0; i < entries.size(); i++){
			JournalEntry entry = entries.get(i);
			if(entry.getNurseID().equals(nurseID) || entry.getDivision().equals(division)){
				entry.printStr();
			}
		}
	}

	public void printAllowedJournalEntries(Doctor doctor){
		String doctorID = doctor.getDoctorID();
		String division = doctor.getDivision();
		for(int i = 0; i < entries.size(); i++){
			JournalEntry entry = entries.get(i);
			if(entry.getDoctorID().equals(doctorID) || entry.getDivision().equals(division)){
				entry.printStr();
			}
		}
	}

	public boolean hasWriteRights(Nurse nurse){
		String nurseID = nurse.getNurseID();
		String division = nurse.getDivision();
		for(int i = 0; i < entries.size(); i++){
			JournalEntry entry = entries.get(i);
			if(entry.getNurseID().equals(nurseID) || entry.getDivision().equals(division)){
				return true;
			}
		}

	}

	public boolean hasWriteRights(Doctor doctor){
		String doctorID = doctor.getDoctorID();
		String division = doctor.getDivision();
		for(int i = 0; i < entries.size(); i++){
			JournalEntry entry = entries.get(i);
			if(entry.getDoctorID().equals(doctorID) || entry.getDivision().equals(division)){
				return true;
			}
		}
	}
}