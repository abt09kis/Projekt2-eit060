Public class Patient(IdNbr, IdName){
	String idNbr, idName, div;

	Public Patient(idNbr, idName, div){		//ger en patient persnummer, namn, och avdelning på sjukhuset
	this.idName=idName;
	this.idNbr=idNbr;
	this.div=div;
	}

	public journal getJournal() {
	//hitta och returnera journalen för personen
	}

	public String getidNbr(){
		return idNbr;
	}
	public String getidName(){
		return idName;
	}
}