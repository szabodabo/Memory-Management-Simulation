
public class Process {
	private String pid;
	private int size;
	private int[] start_times;
	private int[] end_times;

	public Process(String pid, int size, int[] start_times, int[] end_times) {
		this.pid 			= pid;
		this.size 			= size;
		this.start_times 	= start_times;
		this.end_times 		= end_times;
	}

	public String getPid() {
		return pid;
	}

	public int getSize() {
		return size;
	}

	public int getStartTime(int processNum) {
		return start_times[processNum];
	}
	
	public int getEndTime(int processNum) {
		return end_times[processNum];
	}
}
