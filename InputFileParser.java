
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Contains functionality for reading and interpreting input files
 */
public class InputFileParser {
	private static final boolean INPUT_DEBUG = false;

	/**
	 * Parse an input file into a list of processes contained therein
	 * @param fileName The file to parse
	 * @return A list of processes contained therein
	 */
	public static ArrayList<Process> parseInputFile(String fileName) {
		ArrayList<Process> processList = new ArrayList<Process>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(fileName));	
			String line = br.readLine(); //First line is number of processes... throw it out
			while ( (line = br.readLine()) != null) {
				ArrayList<Process> processesInLine = parseInputLine(line);
				for (Process p : processesInLine) {
					processList.add( p );
				}
			}
		} catch (IOException ioe) {
			System.err.println("Couldn't read input file '" + fileName + "'");
			System.exit(1);
		}
		
		Collections.sort(processList);
		return processList;
	}

	/**
	 * Parse an input line into a sublist of Processes
	 * @param line The line to parse
	 * @return A list of Process objects that can be parsed from the line
	 */
	private static ArrayList<Process> parseInputLine(String line) {
		ArrayList<Process> processesToReturn = new ArrayList<Process>();
		debugPrint("Got line " + line);
		String[] lineParts = line.split("\\s");
		debugPrintln(" (" + lineParts.length + ")");

		//Need at least 4 items: pid, mem, and an entry/exit pair
		//Also, must be an even number of items (pairs of times only)
		if (lineParts.length < 4  || lineParts.length % 2 != 0) {
			System.err.println("Malformed input file with line '" + line + "'");
			System.exit(1);
		}
		
		char pid = lineParts[0].charAt(0);
		int memSize = Integer.parseInt(lineParts[1]);

		for (int i = 2; i < lineParts.length; i += 2) {
			int start = Integer.parseInt( lineParts[i] );
			int end = Integer.parseInt( lineParts[i+1] );

			//Also, times must be strictly increasing
			if ( start > end ) {
				//Time wasn't strictly increasing
				System.err.println("Times not strictly increasing on line '" + line + "'");
				System.exit(1);
			}
			processesToReturn.add( new Process(pid, memSize, start, end) );
		}

		return processesToReturn;
	}

	/**
	 * Print a string if a debug flag is set.
	 * Do not include a newline.
	 * @param toPrint The string to print
	 */
	private static void debugPrint(String toPrint) {
		if (INPUT_DEBUG == true) {
			System.out.print(toPrint);
		}
	}

	/**
	 * Print a string if a debug flag is set.
	 * Include a newline.
	 * @param toPrint The string to print
	 */
	private static void debugPrintln(String toPrint) {
		if (INPUT_DEBUG == true) {
			System.out.println(toPrint);
		}
	}
}
