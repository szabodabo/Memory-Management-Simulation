import java.util.Scanner;

public class Main {
	public static void main( String[] args ) {
		if (args.length != 2) {
			Externals.invalidUsageExit();
		}
		
		String simName = args[1].trim();
		MemorySimulatorBase sim = null;
		Scanner scanner = new Scanner(System.in);
		String userInput = "";
		
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
		
		while (sim.processesRemaining() > 0) {
			userInput = "";
			while (!(userInput.equals("c") || userInput.equals("q"))) {
				System.out.print("memsim> ");
				userInput = scanner.next();
				if (userInput.equals("c")) {
					//Continue the simulation
					break;
				} else if (userInput.equals("q")) {
					System.exit(0);
				} else {
					System.out.println("Enter 'c' to continue or 'q' to quit.");
				}
			}
			
			sim.timeStep();
			sim.printMemory();
		}
		
		System.out.println("No more events to process... exiting!");
		
	}
}
