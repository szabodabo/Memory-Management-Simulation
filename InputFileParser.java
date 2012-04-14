import java.io.*;

public class InputFileParser {
	private static final boolean INPUT_DEBUG = true;

	public static void parseInputFile(String fileName) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(fileName));	
			String line = br.readLine(); //First line is number of processes... throw it out
			while ( (line = br.readLine()) != null) {
				parseInputLine(line);
			}
		} catch (IOException ioe) {
			System.err.println("Couldn't read input file '" + fileName + "'");
			System.exit(1);
		}
	}

	private static void parseInputLine(String line) {
		debugPrint("Got line " + line);
		String[] lineParts = line.split("\\s");
		debugPrintln(" (" + lineParts.length + ")");
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
