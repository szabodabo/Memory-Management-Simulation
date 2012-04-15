
public class Main {
	public static void main( String[] args ) {
		if (args.length != 2) {
			Externals.invalidUsageExit();
		}

		MemorySimulatorBase sim = new FirstFitMemorySimulator( args[0] );
		System.out.println("Very goode");
		sim.printMemory();
	}
}
