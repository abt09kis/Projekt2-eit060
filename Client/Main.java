package Client;

	boolean close = false;
	scanner = new Scanner(System.in);
	
	public static void main(String[] args) {

		

		System.out.print("Enter ID: ");
		id = scanner.next();
		System.out.print("Enter Password: ");
		pass = scanner.next();

		//CERTIFIKAT??()


	if (id.equals(patient) ) {
			System.out.println("Welcome patient, here is your journal:");
			patient(id);
		} else if (id.equals(doctor)) {
				while(!close){
				System.out.println("Welcome doctor, what would you like to do?");
				System.out.println("1. Read a journal");
				System.out.println("2. Write to a journal");
				System.out.println("3. Add a new Patient and a journal for him/her";
				System.out.println("4. exit");

				int choice = scanner.nextInt();
					if(choice!=4){

					doctor(choice, id);
					}else{

					close = true;	
					}
		}

		} else if (id.equals(nurse) {
				System.out.println("Welcome nurse, what would you like to do?");
				System.out.println("1. Read a journal");
				System.out.println("2. Write to a journal");
				System.out.println("4. exit");

				int choice = scanner.nextInt();
				if(choice!=4){

				nurse(choice, id);

				}
				}
			}
		} else {
			System.out.println("Welcome goverment, what would you like to do?");
				System.out.println("1. Read a journal");
				System.out.println("2. Delete a journal");
				System.out.println("4. exit");

				int choice = scanner.nextInt();

				if(choice!=4){
				government(choice, id);	
				}
			}
		}

		private void doctor(id, choice){
				

			//case-satser utifrån vad läkaren har valt att han vill göra!!
				switch(choice){
			

					case 1: //Läs en journalEntry
						Sysytem.out.Println("Enter PatientID:")
						
					String patId = scanner.next();
						readJournal(patId, id, 2);

					case 2:	//Lägg till text i en existerande journal entry
					
						System.out.Println("Enter nurseId");
						String nurseId = scanner.next();
						System.out.Println("Enter docId");
						String docId scanner.next();
						System.out.Println("Enter div");
						String div = scanner.next();
						System.out.Println("Enter date");
						String date = scanner.next();
						System.out.Println("Enter text");
						String text = scanner.next();

						writeTo(id, text, nurseId, docId, div, date);
					
					case 3: //skapa ny journal

						System.out.println("Enter Patient id:")
						String newPid = scanner.next();
						System.out.println("Enter Patient name:")
						String newPname = scanner.next();

						Patient newPatient =new Patient (newPid, newPname);
						Journal newJournal = new Journal (newPatient);

						public void addJournal(id, newJournal, newPatient);


				}
			}

		}
		private void patient(idNbr){

			//hitta patienten i listan av Users och anropa en metod som skriver ut alla journalentries är den patienten
				Patient temp = findPatient(String id);
				temp.getJournal.printJournal;

		}
		private void government(name, choice){

		switch(choice){
			case 1:
			readJournal
			
						Sysytem.out.Println("Enter PatientID:")
						
					String patId = scanner.next();
						readJournal(patId, id, 3);

			case 2:

			Sysytem.out.Println("Enter PatientID for removal:")
			
			String patId = scanner.next();

			deletePatient(patId);

			


		}
	}
	}
		private void nurse (id, choice){

					case 1: //Läs en journalEntry
					
					Sysytem.out.Println("Enter PatientID:")
						
					String patId = scanner.next();
						readJournal(patId, id, 1);

					case 2:	//Lägg till text i en existerande journal entry
					
						System.out.Println("Enter nurseId");
						String nurseId = scanner.next();
						System.out.Println("Enter docId");
						String docId scanner.next();
						System.out.Println("Enter div");
						String div = scanner.next();
						System.out.Println("Enter date");
						String date = scanner.next();
						System.out.Println("Enter text");
						String text = scanner.next();

						writeTo(id, text, nurseId, docId, div, date);
					
		}






