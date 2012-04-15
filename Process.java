
public class Process {
	private String pid;
	private String memory;
	private int[] start_times;
	private int[] end_times;

	public Process(String pid, String memory, int[] start_times, int[] end_times) {
		this.pid 		= pid;
		this.memory 		= memory;
		this.start_times 	= start_times;
		this.end_times 		= end_times;
	}

	public String getPid() {
		return pid;
	}

	public String getMemory() {
		return memory;
	}

	public int getStartTime(int processNum) {
		return start_times[processNum]
	}
	
	public int getEndTime(int processNum) {
		return end_times[processNum]
	}
}
