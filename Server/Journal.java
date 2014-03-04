package Server;
import java.util.ArrayList;


public class Journal{
	private ArrayList<JournalEntry> entries;

	public Journal(){
		entries = new ArrayList<JournalEntry>();	
	}

	public void addEntry(JournalEntry je){
		entries.add(je);
	}
//
//	public boolean findDoctor(String id){
//		for(JournalEntry e: entries){
//			if(e.getDoctorID().equals(id)){
//				return true;
//			}
//		}
//		return false;
//	}
//	public boolean findNurse(String id){
//		for(JournalEntry e: entries){
//			if(e.getNurseID().equals(id)){
//				return true;
//			}
//		}
//		return false;
//	}
//
//	public boolean findDivision(String division){
//		for(JournalEntry e: entries){
//			if(e.getDivision().equals(division)){
//				return true;
//			}
//		}
//		return false;
//	}

	public String getJournal(){
		StringBuilder sb = new StringBuilder();
		System.out.println(entries.size());
		for(int i = 0; i < entries.size(); i++){
			sb.append(entries.get(i).getStr());
		}
		String str = sb.toString();
		return str;
	}

	public String getAllowedJournalEntries(Nurse nurse){
		String nurseID = nurse.getNurseID();
		String division = nurse.getDivision();
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < entries.size(); i++){
			JournalEntry entry = entries.get(i);
			if(entry.getNurseID().equals(nurseID) || entry.getDivision().equals(division)){
				sb.append(entry.getStr());
			}
		}
		return sb.toString();
	}

	public String getAllowedJournalEntries(Doctor doctor){
		String doctorID = doctor.getDoctorID();
		String division = doctor.getDivision();
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < entries.size(); i++){
			JournalEntry entry = entries.get(i);
			if(entry.getDoctorID().equals(doctorID) || entry.getDivision().equals(division)){
				sb.append(entry.getStr());
			}
		}
		return sb.toString();
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
		return false;
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
		return false;
	}
}