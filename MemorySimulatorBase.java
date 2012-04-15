import java.util.ArrayList;

public abstract class MemorySimulatorBase {
	public static final char FREE_MEMORY = '.';
	public static final char RESERVED_MEMORY = '#';
	
	protected char[] main_memory;
	protected ArrayList<Process> processes;
	
	public MemorySimulatorBase(String fileName) {
		main_memory = new char[ Externals.MAIN_MEMORY_SIZE ];
		processes = InputFileParser.parseInputFile( fileName );
		initializeMainMemory();
		System.out.println("The next available slot of size 5 is at location " + getNextSlot(5));
	}
	
	protected abstract int getNextSlot(int slotSize);
	
	protected void putInMemory(Process p) {
		int targetSlot = getNextSlot(p.getSize());
		if (targetSlot == -1) {
			defragment();
			targetSlot = getNextSlot(p.getSize());
			if (targetSlot == -1) {
				Externals.outOfMemoryExit();
			}
		}
		//If we get here, we know that there's an open chunk
		for (int i = targetSlot; i < p.getSize(); i++) {
			main_memory[i] = p.getPid();
		}
	}

	private void initializeMainMemory() {
		for (int i = 0; i < 80 && i < main_memory.length; i++) {
			main_memory[i] = RESERVED_MEMORY;
		}
		for (int i = 80; i < main_memory.length; i++) {
			main_memory[i] = FREE_MEMORY;
		}
	}

	public void printMemory() {
		for (int i = 0; i < main_memory.length; i++) {
			if (i % 80 == 0) {
				System.out.println();
			}
			System.out.print( main_memory[i] );
		}
		System.out.println();
	}
	
	private void defragment() {
		int destination = 0;
		for (int i = 0; i < main_memory.length; i++) {
			if (main_memory[i] != FREE_MEMORY 
					&& main_memory[i] != RESERVED_MEMORY
					&& i != destination ) {
				main_memory[destination] = main_memory[i];
				destination++;
			}
		}
	}
}
