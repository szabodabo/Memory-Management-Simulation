public class BuddySortSimulator extends MemorySimulatorBase {


    public BuddySortSimulator(String filename){
        super(filename);
    }

    public int getNextSlot(int slotSize){
        return -1;
    }
}

// import java.io.*;
// import java.util.*;

// class Buddy {
	
// 	// Inner class to store lower
// 	// and upper bounds of the allocated memory
// 	class Pair
// 	{
// 		int lb, ub;
// 		Pair(int a, int b)
// 		{
// 			lb = a;
// 			ub = b;
// 		}
// 	}
	
// 	// Size of main memory
// 	int size;
	
// 	// Array to track all
// 	// the free nodes of various sizes
// 	ArrayList<Pair> arr[];
	
// 	// Else compiler will give warning
// 	// about generic array creation
// 	@SuppressWarnings("unchecked")
	
// 	Buddy(int s)
// 	{
// 		size = s;
		
// 		// Gives us all possible powers of 2
// 		int x = (int)Math.ceil(Math.log(s) / Math.log(2));
		
// 		// One extra element is added
// 		// to simplify arithmetic calculations
// 		arr = new ArrayList[x + 1];
		
// 		for (int i = 0; i <= x; i++)
// 			arr[i] = new ArrayList<>();
			
// 		// Initially, only the largest block is free
// 		// and hence is on the free list
// 		arr[x].add(new Pair(0, size - 1));
// 	}
	
// 	void allocate(int s)
// 	{
		
// 		// Calculate which free list to search to get the
// 		// smallest block large enough to fit the request
// 		int x = (int)Math.ceil(Math.log(s) / Math.log(2));
		
// 		int i;
// 		Pair temp = null;
		
// 		// We already have such a block
// 		if (arr[x].size() > 0)
// 		{
			
// 			// Remove from free list
// 			// as it will be allocated now
// 			temp = (Pair)arr[x].remove(0);
// 			System.out.println("Memory from " + temp.lb
// 							+ " to " + temp.ub + " allocated");
// 			return;
// 		}
		
// 		// If not, search for a larger block
// 		for (i = x + 1; i < arr.length; i++) {
			
// 			if (arr[i].size() == 0)
// 				continue;
				
// 			// Found a larger block, so break
// 			break;
// 		}
		
// 		// This would be true if no such block was found
// 		// and array was exhausted
// 		if (i == arr.length)
// 		{
// 			System.out.println("Sorry, failed to allocate memory");
// 			return;
// 		}
		
// 		// Remove the first block
// 		temp = (Pair)arr[i].remove(0);
		
// 		i--;
		
// 		// Traverse down the list
// 		for (; i >= x; i--) {
			
// 			// Divide the block in two halves
// 			// lower index to half-1
// 			Pair newPair = new Pair(temp.lb, temp.lb
// 									+ (temp.ub - temp.lb) / 2);
			
// 			// half to upper index
// 			Pair newPair2 = new Pair(temp.lb
// 								+ (temp.ub - temp.lb + 1) / 2, temp.ub);
			
// 			// Add them to next list
// 			// which is tracking blocks of smaller size
// 			arr[i].add(newPair);
// 			arr[i].add(newPair2);
			
// 			// Remove a block to continue the downward pass
// 			temp = (Pair)arr[i].remove(0);
// 		}
		
// 		// Finally inform the user
// 		// of the allocated location in memory
// 		System.out.println("Memory from " + temp.lb
// 							+ " to " + temp.ub + " allocated");
// 	}
	
// 	public static void main(String args[]) throws IOException
// 	{
// 		int initialMemory = 0, val = 0;
		
		
// 		// Uncomment the below section for interactive I/O
// 		/*Scanner sc=new Scanner(System.in);
// 		initialMemory = sc.nextInt();
// 		Buddy obj = new Buddy(initialMemory);
// 		while(true)
// 		{
// 			val = sc.nextInt();// Accept the request
// 			if(val <= 0)
// 				break;
// 			obj.allocate(val);// Proceed to allocate
// 		}*/
		
		
// 		initialMemory = 128;
		
// 		// Initialize the object with main memory size
// 		Buddy obj = new Buddy(initialMemory);
// 		obj.allocate(32);
// 		obj.allocate(7);
// 		obj.allocate(64);
// 		obj.allocate(56);
// 	}
// }

// class Buddy_New{

//     class Block{
//         int begin;
//         int end;

//         Block(int b, int e){
//             begin = b;
//             end = e;
//         }
//     }

//     int mm = 2400;

//     ArrayList<Block> blocks[];

//     Buddy_New(){
//         int x = blocking(mm);
//         blocks = new ArrayList[x+1];

//         //this for loop iterates through all powers of 2 possible
//         //...results in all of the possible blocks of memory to be
//         //...manipulated
//         for(int i = 0; i <= x; i++)
//             blocks[i] = new ArrayList<>();

//         blocks[x].add(new Block(0, mm - 1));
//     }

//     private void allocate_new(int size){
//         int x = blocking(size);
//         Block temp = new Block();

//         if(blocks[x].size() > 0){
//             temp = 
//         }
//     }

//     private int blocking(int num){
//         return (int)Math.ceil(Math.log(num)/Math.log(2));
//     }

// }
