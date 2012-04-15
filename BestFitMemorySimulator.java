
public class BestFitMemorySimulator extends MemorySimulatorBase {
	public BestFitMemorySimulator(String filename) {
		super(filename);
	}

	public int getNextSlot(int slotsize) {
		int best_size 	= -1;
		int best_start 	= -1;	
		int block_size 	= 0;

		for (int i = 0; i < main_memory.length-slotSize; i++) {
			if(main_memory[i] == '.')
				block_size++;
			else
			{
				if(block_size >= slotsize && block_size - slotsize < best_size - slotsize || best_size == -1)
				{
					best_start 	= i - block_size;
					best_size 	= block_size;
				}
				block_size = 0;
			}
		}

		System.out.println("Best size is: " + best_size);
		return -1;
	}
}
