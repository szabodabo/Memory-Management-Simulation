
public class NonContiguousMemorySimulator extends MemorySimulatorBase {

	public NonContiguousMemorySimulator(String fileName) {
		super(fileName);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected int getNextSlot(int slotSize) {
		throw new IllegalArgumentException("This method isn't relevant " +
				"for this memory allocation strategy");
	}
	
	@Override
	protected void putInMemory(Process p) {
		
	}

}
