import java.util.ArrayList;

public abstract class MemorySimulatorBase {
	private char[] main_memory;
	private ArrayList<Process> processes;
	
	public MemorySimulatorBase(String fileName) {
		main_memory = new char[ Externals.MAIN_MEMORY_SIZE ];
		processes = InputFileParser.parseInputFile( fileName );
		initializeMainMemory();
	}
	
	public abstract int getAvailableSlot(int slotSize);

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
			Externals.invalidUsageExit();
		}
	}

	private void initializeMainMemory() {
		for (int i = 0; i < 80 && i < Externals.MAIN_MEMORY_SIZE; i++) {
			main_memory[i] = '#';
		}
		for (int i = 80; i < Externals.MAIN_MEMORY_SIZE; i++) {
			main_memory[i] = '.';
		}
	}

	public void printMemory() {
		for (int i = 0; i < Externals.MAIN_MEMORY_SIZE; i++) {
			if (i % 80 == 0) {
				System.out.println();
			}
			System.out.print( main_memory[i] );
		}
		System.out.println();
	}
}
