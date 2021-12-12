
import java.io.*;
import java.util.ArrayList;
//Collections is like an array database mix type thing
import java.util.Collections;

/**
 * Contains functionality for reading and interpreting input files
 */

public class InputFileParser {

	/**
	 * Parse an input file into a list of processes contained therein
	 * @param fileName The file to parse
	 *	//j tells us what a comment will do
	 * @return A list of processes contained therein
	 */

	public static ArrayList<Process> parseInputFile(String fileName) {
		//a list of processes 
		ArrayList<Process> processList = new ArrayList<Process>();
		//try to read the file 
		try {
			BufferedReader br = new BufferedReader(new FileReader(fileName));	
			String line = br.readLine(); //First line is number of processes... throw it out
			//keep going to the next line 
			while ( (line = br.readLine()) != null) {
				ArrayList<Process> processesInLine = parseInputLine(line);
				//this is like a forEach 
				//add all the processes into the processList which is defined above
				for (Process p : processesInLine) {
					processList.add( p );
				}
			}
			//if wrong file name do this... 
		} catch (IOException ioe) {
			System.err.println("Couldn't read input file '" + fileName + "'");
			System.exit(1);
		}
		//java library 
		Collections.sort(processList); 
		return processList;
	}

	/**
	 * Parse an input line into a sublist of Processes
	 * @param line The line to parse
	 * @return A list of Process objects that can be parsed from the line
	 */
	 //this one will break down what each part of the line means 
	private static ArrayList<Process> parseInputLine(String line) {
		//what will be returned later... being added upon 
		ArrayList<Process> processesToReturn = new ArrayList<Process>();
		//\\s means display \s
		String[] lineParts = line.split("\\s");

		//Need at least 4 items: pid, mem, and an entry/exit pair
		//Also, must be an even number of items (pairs of times only)
		//lineparts is the amount of pieces in an individual line 
		if (lineParts.length < 4  || lineParts.length % 2 != 0) { 
			//error correction 
			System.err.println("Malformed input file with line '" + line + "'");
			System.exit(1);
		}
		
		//the letters are the pid 
		char pid = lineParts[0].charAt(0);
		// the first numbeer
		//you have to parseint bc the default type is string 
		int memSize = Integer.parseInt(lineParts[1]);

		//starts i at the 3rd item in the array
		for (int i = 2; i < lineParts.length; i += 2) {
			//need to parse the int out
			//these are pairs so it increments by 2 
			//start and end are side by side 
			int start = Integer.parseInt( lineParts[i] );
			int end = Integer.parseInt( lineParts[i+1] );

			//Also, times must be strictly increasing
			//error validation happening 
			if ( start > end ) {
				//Time wasn't strictly increasing
				System.err.println("Times not strictly increasing on line '" + line + "'");
				System.exit(1);
			}
			//adds a new process to the list 
			processesToReturn.add( new Process(pid, memSize, start, end) );
		}
		//return the list made at the top 
		return processesToReturn;
	}
}
