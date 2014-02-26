Public class Patient(IdNbr, IdName){
	String idNbr, idName, div;

	Public Patient(idNbr, idName, div){		//ger en patient persnummer, namn, och avdelning på sjukhuset
	this.idName=idName;
	this.idNbr=idNbr;
	this.div=div;
	journal journal = new journal();
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
}