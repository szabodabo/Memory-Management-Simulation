import java.util.ArrayList;

public abstract class MemorySimulatorBase {
	public static final char FREE_MEMORY = '.';
	public static final char RESERVED_MEMORY = '#';
	public int CURRENT_TIME = -1;
	
	protected char[] main_memory;
	protected ArrayList<Process> processes;
	
	public static final boolean MEMSIM_DEBUG = true;
	
	public MemorySimulatorBase(String fileName) {
		main_memory = new char[ Externals.MAIN_MEMORY_SIZE ];
		processes = InputFileParser.parseInputFile( fileName );
		initializeMainMemory();
		for (Process p : processes) {
			debugPrintln("Process " + p.getPid() + " (size " + p.getSize() + ")");
			debugPrintln("  Start Time: " + p.getStartTime());
			debugPrintln("  End Time: " + p.getEndTime());
		}
	}
	
	protected abstract int getNextSlot(int slotSize);
	
	public void timeStep() {
		CURRENT_TIME++;
		while (!eventOccursAt(CURRENT_TIME)) {
			debugPrintln("Fast-forwarding past boring time " + CURRENT_TIME);
			CURRENT_TIME++;
		}
		
		debugPrintln("=========== TIME IS NOW " + CURRENT_TIME + " ============");
		
		//Processes exit the system
		ArrayList<Process> toRemove = new ArrayList<Process>();
		for (Process p : processes) {
			if (p.getEndTime() == CURRENT_TIME) {
				debugPrintln("Removing process " + p.getPid());
				removeFromMemory(p);
				toRemove.add(p);
			}
		} 
		for (Process p : toRemove) {
			processes.remove(p);
		}
		
		//Processes enter the system
		for (Process p : processes) {
			if (p.getStartTime() == CURRENT_TIME) {
				debugPrintln("Adding process " + p.getPid());
				putInMemory(p);
			}
		}
	}
	
	private boolean eventOccursAt(int time) {
		for (Process p : processes) {
			if (p.getStartTime() == time || p.getEndTime() == time) {
				return true;
			}
		}
		return false;
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
		debugPrintln("Got a target slot of " + targetSlot + " for pid " + p.getPid());
		//If we get here, we know that there's an open chunk
		for (int i = 0; i < p.getSize(); i++) {
			main_memory[i+targetSlot] = p.getPid();
		}
	}
	
	protected void removeFromMemory(Process p) {
		for (int i = 0; i < main_memory.length; i++) {
			if (main_memory[i] == p.getPid()) {
				main_memory[i] = FREE_MEMORY;
			}
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
				debugPrintln("");
			}
			debugPrint( main_memory[i] + "" );
		}
		debugPrintln("");
	}
	
	private void defragment() {
		debugPrintln("Defrag started!");
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
	
	private static void debugPrint(String toPrint) {
		if (MEMSIM_DEBUG == true) {
			System.out.print(toPrint);
		}
	}

	private static void debugPrintln(String toPrint) {
		if (MEMSIM_DEBUG == true) {
			System.out.println(toPrint);
		}
	}
	
	public int processesRemaining() {
		return processes.size();
	}
	
}
