package Server;
		public class Logger{

		StringBuilder logString;

		public logger(){
			logString = new StringBuilder();
		}

		public void append(String s){
			logString.append(s + "\n");
		}

		public String returnString(){
			return logString.ToString();
		}

		public void writeToFile(String filename) {

			PrintWriter writer = new PrintWriter("Logg.txt", "UTF-8");
			writer.print(logString); //Ska det inte vara "writer.write(logstring.toString())"
			writer.close();

		public void readFromFile(String fileName) { 

			FileInputStream inputStream = new FileInputStream("Logg.txt");
    			try {
       				 String everything = IOUtils.toString(inputStream);
   			 } finally {
       		 inputStream.close();
   		 }


		}