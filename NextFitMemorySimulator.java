

/**
 * Memory strategy that puts a process in memory at the next fitting location
 * in memory.
 */
public class NextFitMemorySimulator extends MemorySimulatorBase {

	/**
	 * Default constructor that initializes the sim using an input file
	 * @param fileName The input file
	 */
	public NextFitMemorySimulator(String fileName) {
		super(fileName);
	}

	/**
	 * Return the index of the first position of the next available slot
	 * in memory
	 * @param slotSize The size of the requested slot
	 * @return The index of the first position of an available requested block
	 */
	@Override
	protected int getNextSlot(int slotSize) {
		int spaceAvailable = 0;
		for (int i = main_memory.length-1; i >=0; i--) {
			if (main_memory[i] == FREE_MEMORY) {
				spaceAvailable++;
			} else {
				if (spaceAvailable < slotSize) {
					return -1;
				} else {
					return i+1;
				}
			}
		}
		return -1;
	}

}
