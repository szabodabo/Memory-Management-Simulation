
public class FirstFitMemorySimulator extends MemorySimulatorBase {

	public FirstFitMemorySimulator(String fileName) {
		super(fileName);
	}

	@Override
	public int getNextSlot(int slotSize) {
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
		return -1;
	}

}
