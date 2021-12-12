import java.util.Scanner;

/**
 * Main class sets up and runs the simulation,
 * including managing user input and timestep control.
 */
public class Main {
	public static void main( String[] args ) {
		
		MemorySimulatorBase sim = null;
		System.out.println("=====================================================");
		System.out.println("Choose (1, 2, 3, 4, or 5) To Pick A Memory Simulator:");
		System.out.println("=====================================================");
		System.out.println("1: First Fit" +
		"\n2: Best Fit" +
		"\n3: Next Fit" +
		"\n4: Worst Fit" +
		"\n5: Non Contiguous");
		Scanner scanner = new Scanner(System.in);
		int userInput = scanner.nextInt();
		
		if ( userInput == 1 ) {
			sim = new FirstFitMemorySimulator( "input.txt" );
		} else if ( userInput == 2 ) {
			sim = new BestFitMemorySimulator( args[0] );
		} else if ( userInput == 3 ) {
			sim = new NextFitMemorySimulator( args[0] );
		} else if ( userInput == 4 ) {
			sim = new WorstFitMemorySimulator( args[0] );
		} else if ( userInput == 5 ) {
			sim = new NonContiguousMemorySimulator( args[0] );
		} else if (userInput == 6){
			sim = new BuddySortSimulator("input2.txt");
		} else {
			System.out.println("Invalid option, exiting...");
		}
		
		if(userInput != 6){

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
		else{
			sim.buddyWalk();
			System.out.println("Done");
		}
	}
}