Public class Patient(IdNbr, IdName){
	String IdNbr, IdName;

	Public Patient(IdNbr, IdName){		//konstruktor
	this.IdName=IdName;
	this.IdNbr=IdNbr;
	}

	public journal getJournal() {
	//hitta och returnera journalen för personnummret
	}

	}
	public boolean deleteJournal(){
		//Ta bort journalen.
		//True om man hade rätighet till det och det gick vägen
	}

	public String getIdNbr(){
		return IdNbr;
	}
	public String getIdName(){
		return IdName;
	}
}