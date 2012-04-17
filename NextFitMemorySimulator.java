
public class NextFitMemorySimulator extends MemorySimulatorBase {

	public NextFitMemorySimulator(String fileName) {
		super(fileName);
	}

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
