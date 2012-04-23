

/**
 * Represent a process that uses memory
 */
public class Process implements Comparable<Process> {
	private char pid;
	private int size;
	private int start_time;
	private int end_time;

	/**
	 * Default constructor
	 * @param pid The ASCII identifier of the new process
	 * @param size The amount of memory the process uses
	 * @param start_time The virtual time the process enters memory
	 * @param end_time The virtual time the process leaves memory
	 */
	public Process(char pid, int size, int start_time, int end_time) {
		this.pid 			= pid;
		this.size 			= size;
		this.start_time 	= start_time;
		this.end_time 		= end_time;
	}

	/**
	 * Get the PID of the process
	 * @return The PID of the process
	 */
	public char getPid() {
		return pid;
	}

	/**
	 * Get the amount of memory the process needs
	 * @return The amount of memory the process takes up
	 */
	public int getSize() {
		return size;
	}

	/**
	 * Get the virtual time at which the process enters memory
	 * @return The virtual time at which the process enters memory
	 */
	public int getStartTime() {
		return start_time;
	}
	
	/**
	 * Get the virtual time at which the process leaves memory
	 * @return The virtual time at which the process leaves memory
	 */
	public int getEndTime() {
		return end_time;
	}

	/**
	 * Compare this process with another process by start time
	 * @param o The other process
	 */
	@Override
	public int compareTo(Process o) {
		if (this.start_time < o.start_time) {
			return -1;
		} else if (this.start_time == o.start_time) {
			return 0;
		} else {
			return 1;
		}		
	}
}
