
import java.util.Scanner;

/**
 * Main class sets up and runs the simulation,
 * including managing user input and timestep control.
 */
public class Main {
	public static void main( String[] args ) {
		if (args.length != 2) {
			Externals.invalidUsageExit();
		}
		
		String simName = args[1].trim();
		MemorySimulatorBase sim = null;
		Scanner scanner = new Scanner(System.in);
		int userInput;
		
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
		
		sim.timeStepUntil(0);
		sim.printMemory();
	
		while (sim.processesRemaining() > 0) {
			userInput = 0;
			System.out.print("memsim> ");
			userInput = scanner.nextInt();
			if (userInput == 0) {
				System.exit(0);
			}		
			sim.timeStepUntil(userInput);
			sim.printMemory();
		}
		
		System.out.println("No more events to process... exiting!");
	}
}
