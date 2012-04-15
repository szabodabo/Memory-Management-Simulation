import java.util.ArrayList;

public abstract class MemorySimulatorBase {
	public static final char FREE_MEMORY = '.';
	public static final char RESERVED_MEMORY = '#';
	public int CURRENT_TIME = 0;
	
	protected char[] main_memory;
	protected ArrayList<Process> processes;
	
	public MemorySimulatorBase(String fileName) {
		main_memory = new char[ Externals.MAIN_MEMORY_SIZE ];
		processes = InputFileParser.parseInputFile( fileName );
		initializeMainMemory();
		for (Process p : processes) {
			System.out.println("Process " + p.getPid() + " (size " + p.getSize() + ")");
			System.out.println("  Start Time: " + p.getStartTime());
			System.out.println("  End Time: " + p.getEndTime());
		}
	}
	
	protected abstract int getNextSlot(int slotSize);
	
	public void timeStep() {
		for (Process p : processes) {
			if (p.getStartTime() == CURRENT_TIME) {
				System.out.println("Adding process " + p.getPid());
				putInMemory(p);
			}
		}
	}
	
	protected void putInMemory(Process p) {
		int targetSlot = getNextSlot(p.getSize());
		if (targetSlot == -1) {
			defragment();
			targetSlot = getNextSlot(p.getSize());
			if (targetSlot == -1) {
				Externals.outOfMemoryExit();
			}
		}
		System.out.println("Got a target slot of " + targetSlot + " for pid " + p.getPid());
		//If we get here, we know that there's an open chunk
		for (int i = 0; i < p.getSize(); i++) {
			main_memory[i+targetSlot] = p.getPid();
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
		System.out.println("Defrag started!");
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
