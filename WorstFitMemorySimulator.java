
/**
 * Memory strategy that puts a process in memory at the worst-fitting location
 */
public class WorstFitMemorySimulator extends MemorySimulatorBase {

	/**
	 * Default constructor that initializes the sim using an input file
	 * @param fileName The input file
	 */
	public WorstFitMemorySimulator(String fileName) {
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
		// TODO Auto-generated method stub
		//TODO: Noah
		return 0;
	}

}
