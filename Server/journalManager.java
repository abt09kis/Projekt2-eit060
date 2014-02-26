	public class journalManager{
		
		//klassen som innehåller alla Journaler

		public Hashmap<IdNbr, Journal> journals = new Hashmap();


	public void addJournal(int idNbr, Journal journal){
		//Lägg till en person i registret av journaler genom att lägga in dens personnummer och journal i mappen
		journals.put(idNbr, journal);
	}
	public boolean deletePatient(int idNbr){
		return journals.remove(idNbr);
	//Tar bort en person ur journalregistret med angivet personnummer	
	}
	public journal getJournal(int idNbr){
		//returnerar hela journalen för en viss person
		journals.get(idNbra);
	}
	public boolean writeTo(int idNbr, String text, String nurseiId, String docId, String div, String date){

		
		Journal jour = journals.get(idNbr);	//hätar ut journalen för personen
		JournalEntry journalEntry = new JournalEntry(nurseiId, docId, div, date);
		journalEntry.addNote(text);
		jour.add(journalEntry);

	}
	}


