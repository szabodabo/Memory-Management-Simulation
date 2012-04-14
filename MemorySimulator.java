import java.io.*;

public class MemorySimulator {
	private char[] main_memory;
	private static final int MAIN_MEMORY_SIZE = 2400;
	private static final boolean INPUT_DEBUG = true;
	
	public MemorySimulator(String fileName) {
		main_memory = new char[MAIN_MEMORY_SIZE];
		InputFileParser.parseInputFile(fileName);
	}

	public static void invalidUsageExit() {
		System.err.println("USAGE: java MemorySimulator <input-file> { first | best | next | worst }");
		System.exit(1);
	}

	public static void main( String[] args ) {
		if (args.length != 2) {
			invalidUsageExit();
		}

		MemorySimulator sim = new MemorySimulator(args[0]);
		System.out.println("Very goode");
	}

	public void runSimFunction(String simName) {
		if (simName.equals("first")) {
			//TODO
		} else if (simName.equals("best")) {
			//TODO
		} else if (simName.equals("next")) {
			//TODO
		} else if (simName.equals("worst")) {
			//TODO
		} else {
			invalidUsageExit();
		}
	}
}
