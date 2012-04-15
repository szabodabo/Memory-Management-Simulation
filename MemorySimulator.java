import java.io.*;
import java.util.ArrayList;

public class MemorySimulator extends Externals {
	private char[] main_memory;
	private ArrayList<Process> processes;
	
	public MemorySimulator(String fileName) {
		main_memory = new char[ MAIN_MEMORY_SIZE ];
		processes = InputFileParser.parseInputFile( fileName );
		initializeMainMemory();
	}

	public static void main( String[] args ) {
		if (args.length != 2) {
			invalidUsageExit();
		}

		MemorySimulator sim = new MemorySimulator( args[0] );
		System.out.println("Very goode");
		sim.printMemory();
	}

	public void runSimFunction(String simName) {
		if ( simName.equals("first") ) {
			//TODO
		} else if ( simName.equals("best") ) {
			//TODO
		} else if ( simName.equals("next") ) {
			//TODO
		} else if ( simName.equals("worst") ) {
			//TODO
		} else {
			invalidUsageExit();
		}
	}

	private void initializeMainMemory() {
		for (int i = 0; i < 80 && i < MAIN_MEMORY_SIZE; i++) {
			main_memory[i] = '#';
		}
		for (int i = 80; i < MAIN_MEMORY_SIZE; i++) {
			main_memory[i] = '.';
		}
	}

	public void printMemory() {
		for (int i = 0; i < MAIN_MEMORY_SIZE; i++) {
			if (i % 80 == 0) {
				System.out.println();
			}
			System.out.print( main_memory[i] );
		}
		System.out.println();
	}
}
