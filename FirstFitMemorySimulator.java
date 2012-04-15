
public class FirstFitMemorySimulator extends MemorySimulatorBase {

	public FirstFitMemorySimulator(String fileName) {
		super(fileName);
	}

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
