
public class Main {
	public static void main( String[] args ) {
		if (args.length != 2) {
			Externals.invalidUsageExit();
		}
		
		String simName = args[1];
		MemorySimulatorBase sim = null;
		
		if ( simName.equals("first") ) {
			sim = new FirstFitMemorySimulator( args[0] );
		} else if ( simName.equals("best") ) {
			sim = new BestFitMemorySimulator( args[0] );
		} else if ( simName.equals("next") ) {
			sim = new NextFitMemorySimulator( args[0] );
		} else if ( simName.equals("worst") ) {
			sim = new WorstFitMemorySimulator( args[0] );
		} else if ( simName.equals("noncontig") ) {
			sim = new NonContiguousMemorySimulator( args[0] );
		} else {
			Externals.invalidUsageExit();
		}

		
		System.out.println("Very goode");
		sim.printMemory();
	}
}
