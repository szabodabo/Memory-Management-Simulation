

/**
 * Memory strategy that puts a process in memory at the
 * first location available in memory
 */
public class FirstFitMemorySimulator extends MemorySimulatorBase {

	/**
	 * Default constructor that initializes the sim using an input file
	 * @param fileName The input file
	 */
	public FirstFitMemorySimulator(String fileName) {
		super(fileName);
	}

	/**
	 * Return the index of the first position of the next available slot
	 * in memory
	 * @param slotSize The size of the requested slot
	 * @return The index of the first position of an available requested block
	 */
	@Override
	public int getNextSlot(int slotSize) {
		int blocksize = 0;
		
		int i;
		for(i = 0; i < main_memory.length - slotSize; i++)
		{
			if(main_memory[i] == FREE_MEMORY && blocksize < slotSize)
				blocksize++;
			else
			{
				if(blocksize >= slotSize)
					return i - blocksize;
				blocksize = 0;
			}
		}
		
		return -1;
		
		/*
		System.out.println("Finding open slot of size " + slotSize);
		for (int i = 0; i < main_memory.length-slotSize; i++) {
			//TODO: Are the bounds correct here? ^^^^^^^
			boolean blockIsFree = true;
			for (int j = 0; j < slotSize; j++) {
				if (main_memory[i+j] != '.') {
					blockIsFree = false;
					break;
				}
			}
			if (blockIsFree) {
				return i;
			}
		}
		return -1;*/
	}

}
