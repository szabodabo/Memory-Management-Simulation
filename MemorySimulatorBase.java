
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Starter code for a memory simulator.
 * Simulator strategies extend this abstract class.
 */
public abstract class MemorySimulatorBase {
	//visual output of free and reserved memory
	protected static final char FREE_MEMORY = '.';
	protected static final char RESERVED_MEMORY = '#';
	//-1 is the current start time... i think 
	protected int CURRENT_TIME = -1;
	
	//an array of .'s and #'s
	protected char[] main_memory;

	//an object instance of a type. thats it 
	protected ArrayList<Process> processes;
	
	//used later to tell program to print output
	protected static final boolean MEMSIM_DEBUG = false;

	//Array to track buddy system nodes
	ArrayList<Block> blocks[];
	
	/**
	 * Default constructor that takes an input file
	 * @param fileName
	 */

	public MemorySimulatorBase(String fileName) {
		//the array of characters . and #
		//Externals just stores data for us 
		//...it includes errors for invalid usage and out of memory
		//...also has main memory size 
		//so main_memory is j a char array of size 2400 
		main_memory = new char[ 640 ];
		
		//deals with the file we put 
		//inputfileparser returns all the stuff in the input file in 
		//a format that this program uses, aka proccesses 
		processes = InputFileParser.parseInputFile( fileName );
		initializeMainMemory();
	}
	
	/**
	 * Return the index of the first position of the next available slot
	 * in memory
	 * 
	 * Different memory strategy classes must override this abstract method.
	 * @param slotSize The size of the requested slot
	 * @return The index of the first position of an available requested block
	 */
	protected abstract int getNextSlot(int slotSize);
	
	/**
	 * Move the simulator one virtual time step into the future,
	 * handling processes leaving and entering the system.
	 * NOTE: Not used now that the project specifications have changed.
	 */
	public void timeStep() {
		CURRENT_TIME++;
		while (!eventOccursAt(CURRENT_TIME)) {
			CURRENT_TIME++;
		}
		
		//Processes exit the system
		ArrayList<Process> toRemove = new ArrayList<Process>();
		for (Process p : processes) {
			if (p.getEndTime() == CURRENT_TIME) {
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
				putInMemory(p);
			}
		}
	}

	public void buddyWalk(){
		initializeBuddySystem();
		for (Process P: processes){
			putInMemoryBuddy(P);
			printMemory();
		}
	}


	//class to create objects for buddy algorithm
	class Block {
		int begin;
        int end;

        Block(int b, int e){
            begin = b;
            end = e;
        }
	}

	

	/**
	 * Move the simulator into the future
	 * @param t The time to which to move the simulator
	 */
	public void timeStepUntil(int t) {
		while (CURRENT_TIME < t) {
			CURRENT_TIME++;
			while (!eventOccursAt(CURRENT_TIME) && CURRENT_TIME < t) {
				CURRENT_TIME++;
			} 
			
			//Processes exit the system
			ArrayList<Process> toRemove = new ArrayList<Process>();
			for (Process p : processes) {
				if (p.getEndTime() == CURRENT_TIME) {
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
					putInMemory(p);
				}
			}
		}
	}
	
	/**
	 * Find whether an event occurs at a specific time
	 * Useful for ascertaining if we can skip a time in the simulator
	 * @param time The time we should check to see if an event occurs
	 * @return True if an event occurs at time, else false
	 */
	private boolean eventOccursAt(int time) {
		for (Process p : processes) {
			if (p.getStartTime() == time || p.getEndTime() == time) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Put a process into memory
	 * @param p The process to put into memory
	 */
	protected void putInMemory(Process p) {
		int targetSlot = getNextSlot(p.getSize());
		if (targetSlot == -1) {
			defragment();
			targetSlot = getNextSlot(p.getSize());
			if (targetSlot == -1) {
				System.out.println("No more memory");
			}
		}
		
		for (int i = 0; i < p.getSize(); i++) {
			main_memory[i+targetSlot] = p.getPid();
		}
	}

	public void putInMemoryBuddy(Process p){
		int x = blocking(p.getSize());
		int mark;
		Block temp = null;

		if(blocks[x].size() > 0){
			temp = (Block)blocks[x].remove(0);
			for(int i = temp.begin; i < temp.end; i++){
				main_memory[i] = p.getPid();
			}
			return;
		}

		for (mark = x; mark < blocks.length; mark++){
			if(blocks[mark].size() == 0){
				continue;
			}
			break;
		}

		if(mark == blocks.length){
			System.out.println("No available memory, process exiting");
			return;
		}

		temp = (Block)blocks[mark].remove(0);
		mark = mark - 1;

		while (mark >= x){
			
			Block a = new Block(temp.begin, temp.begin + (temp.end - temp.begin) / 2);
			Block b = new Block(temp.begin + (temp.end - temp.begin + 1) / 2, temp.end);

			blocks[mark].add(a);
			blocks[mark].add(b);

			temp = (Block)blocks[mark].remove(0);
			mark--;
		}

		for(int i = temp.begin; i < temp.end; i++){
			main_memory[i] = p.getPid();
		}
		System.out.println("Memory successfully allocated");
	}
	
	/**
	 * Take a process out of memory
	 * @param p The process to remove from memory
	 */
	protected void removeFromMemory(Process p) {
		for (int i = 0; i < main_memory.length; i++) {
			if (main_memory[i] == p.getPid()) {
				main_memory[i] = FREE_MEMORY;
			}
		}
	}

	/**
	 * Initialize our main memory with the predetermined amount of reserved and
	 * free memory 
	 */
	private void initializeMainMemory() {
		//main memory from externals file... 2400
		for (int i = 0; i < 80 && i < main_memory.length; i++) {
			//reserved memory is j the # 
			main_memory[i] = RESERVED_MEMORY;
		}
		//by default the first 80 spaces of the memory are j reserved 
		for (int i = 80; i < main_memory.length; i++) {
			//free memory is the .
			main_memory[i] = FREE_MEMORY;
		}
	}

	//begin tracking for buddy algorithm steps
	//calculates number of blocks needed
	//fills array list with empty slots for those blocks
	private void initializeBuddySystem(){
		int x = blocking(main_memory.length);
		blocks = new ArrayList[x + 1];
		for(int i = 0; i <= x; i++){
			blocks[i] = new ArrayList<>();
		}
		blocks[x].add(new Block(80, 639)); 
	}

	/**
	 * Print the current contents of memory
	 */
	//print out how all the memory is at a current state of time 
	//prints out all the memory at a given point
	public void printMemory() {
		//prompts the user for the time they want to display 
		System.out.print("Memory at time " + CURRENT_TIME + ":");
		//just prints the memory 
		for (int i = 0; i < main_memory.length; i++) {
			//doesnt display more than 80 characters per line 
			if (i % 80 == 0) {
				System.out.println("");
			}
			System.out.print( main_memory[i] + "" );
		}
		System.out.println("");
	}
	
	/**
	 * Attempt to defragment main memory
	 */
	 //defragmenting cleans up empty spaces 
	private void defragment() { 
		//hashmap is a key-value pair 
		//this hashmap is keeping track of where the data moves to 
		HashMap<Character, Integer> processesMoved = new HashMap<Character, Integer>();
		//formats a number 2 digits and 2 variables 
		DecimalFormat f = new DecimalFormat("##.00");
		
		System.out.println("Performing defragmentation...");
		
		//80 is where the free memory starts 
		//EXPAND THE RESERVED MEMORY????????
		int destination = 80;

		//this is basically a sorting algorithm 
		for (int i = 0; i < main_memory.length; i++) {
			//free memory is the . 
			if (main_memory[i] != FREE_MEMORY 
					// not a #
					&& main_memory[i] != RESERVED_MEMORY
					//not the destination 80
					&& i != destination ) {
						//destination incremembents 
				main_memory[destination] = main_memory[i];
				main_memory[i] = FREE_MEMORY; 
				destination++;
				//put inserts into the hashmap 
				//
				processesMoved.put(main_memory[i], null);
			}
		}
		int numMoved = processesMoved.size();
		int freeBlockSize = main_memory.length - destination;
		double percentage = (double)freeBlockSize / (double)main_memory.length;
	
		System.out.println("Defragmentation completed.");
		System.out.println("Relocated " + numMoved + " processes " +
				"to create a free memory block of " + freeBlockSize + " units " +
				"(" + f.format(percentage * 100) + "% of total memory).");
	}
	
	/**
	 * Get the number of processes with events remaining in the simulator
	 * @return The number of processes with events remaining in the simulator
	 */
	public int processesRemaining() {
		return processes.size();
	}

	public int blocking(int x){
		return (int)Math.ceil(Math.log(x)/Math.log(2));
	}
	
}
