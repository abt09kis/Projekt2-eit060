package server;

public class Patient(IdNbr, IdName){
	private Journal journal;
	String idNbr, idName, div;

	public Patient(String idNbr, String idName, String div){		//ger en patient persnummer, namn, och avdelning på sjukhuset
	this.idName=idName;
	this.idNbr=idNbr;
	journal = new journal(this);
	this.div = div;
	}

	public journal getJournal() {
	return journal;	
	}

	public String getidNbr(){
		return idNbr;
	}
	public String getidName(){
		return idName;
	}

	public String getDivison(){
		return div;
	}

	public void addJournalEntry(String nurseId, String doctorId, String division){
		journal.addEntry(new journalEntry(nurseId, doctorId, division));
	}
	public void wipeJournals(){
		journal= new Journal(this);
	}

	public String toString(){
		return id +":"+ " "+ name;
	}

}