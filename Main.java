
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
			//TODO
		} else if ( simName.equals("next") ) {
			//TODO
		} else if ( simName.equals("worst") ) {
			//TODO
		} else {
			Externals.invalidUsageExit();
		}

		
		System.out.println("Very goode");
		sim.printMemory();
	}
}
