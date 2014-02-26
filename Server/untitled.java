	public class logger{

	StringBuilder logString;

	public logger(){
		logString = new StringBuilder();
	}

	public void append(String s){
		logString.append(s);
	}

	public String returnString(){
		return logString.ToString();
	}

	public void writeToFile(String filename) {
		Scanner scan = logString.scanNext();
		PrintWriter out = null;
		try {
			out = new PrintWriter(new File(filename));
		} catch (FileNotFoundException e) {
			System.err.println("Filen kunde inte öppnas");
			System.exit(1);
		}
		while (scan.hasNext()){
			out.print(scan.hasNext);
		}
		// ... utskrifter med out.print hamnar nu på filen
		out.close();
	}


	}