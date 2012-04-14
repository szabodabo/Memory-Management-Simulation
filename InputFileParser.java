import java.io.*;
import java.util.ArrayList;

public class InputFileParser {
	private static final boolean INPUT_DEBUG = true;

	public static ArrayList<Process> parseInputFile(String fileName) {
		ArrayList<Process> processList = new ArrayList<Process>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(fileName));	
			String line = br.readLine(); //First line is number of processes... throw it out
			while ( (line = br.readLine()) != null) {
				processList.add( parseInputLine(line) );
			}
		} catch (IOException ioe) {
			System.err.println("Couldn't read input file '" + fileName + "'");
			System.exit(1);
		}
		return processList;
	}

	private static Process parseInputLine(String line) {
		debugPrint("Got line " + line);
		String[] lineParts = line.split("\\s");
		debugPrintln(" (" + lineParts.length + ")");

		//Need at least 4 items: pid, mem, and an entry/exit pair
		//Also, must be an even number of items (pairs of times only)
		if (lineParts.length < 4  || lineParts.length % 2 != 0) {
			System.err.println("Malformed input file with line '" + line + "'");
			System.exit(1);
		}
		
		String pid = lineParts[0];
		String mem = lineParts[1];

		int[] processTimes = new int[ lineParts.length-2 ];
		for (int i = 2; i < lineParts.length; i++) {
			processTimes[i-2] = Integer.parseInt( lineParts[i] );

			//Also, times must be strictly increasing
			if (i-2 > 0 && processTimes[i-3] > processTimes[i-2]) {
				//Time wasn't strictly increasing
				System.err.println("Times not strictly increasing on line '" + line + "'");
				System.exit(1);
			}
		}

		Process newProcess = new Process( pid, mem, processTimes );
		return newProcess;
	}

	private static void debugPrint(String toPrint) {
		if (INPUT_DEBUG == true) {
			System.out.print(toPrint);
		}
	}

	private static void debugPrintln(String toPrint) {
		if (INPUT_DEBUG == true) {
			System.out.println(toPrint);
		}
	}
}
