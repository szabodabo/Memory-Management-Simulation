

/**
 * Memory strategy that puts a process in memory at a location which best
 * fits the amount of memory requested.
 */
public class BestFitMemorySimulator extends MemorySimulatorBase {
	
	/**
	 * Default constructor that initializes the sim using an input file
	 * @param fileName The input file
	 */
	public BestFitMemorySimulator(String fileName) {
		super(fileName);
	}

	/**
	 * Return the index of the first position of the next available slot
	 * in memory
	 * @param slotSize The size of the requested slot
	 * @return The index of the first position of an available requested block
	 */
	public int getNextSlot(int slotSize) {
		
		int best_start = -1;
		int best_size = Integer.MAX_VALUE;
		int current_start = -1;
		int current_size = 0;
		
		for (int i = 0; i < main_memory.length; i++) {
			if (main_memory[i] == FREE_MEMORY) {
				if (current_size == 0) {
					current_start = i;
				}
				current_size++;
			} else {
				//We just hit in-use memory
				
				//Only change placement info if we're coming off of an empty block
				if (current_size > 0) {
					if (current_size < best_size && current_size >= slotSize) {
						best_start = current_start;
						best_size = current_size;
					}
					current_size = 0;
				}
			}
		}
		
		//If the last spot is free, take that block into account
		if (current_size < best_size && current_size >= slotSize) {
			best_start = current_start;
			best_size = current_size;
			current_size = 0;
		}
		
		return best_start;
	}
}
