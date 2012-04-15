
public class Process {
	private String pid;
	private int size;
	private int start_time;
	private int end_time;

	public Process(String pid, int size, int start_time, int end_time) {
		this.pid 			= pid;
		this.size 			= size;
		this.start_time 	= start_time;
		this.end_time 		= end_time;
	}

	public String getPid() {
		return pid;
	}

	public int getSize() {
		return size;
	}

	public int getStartTime() {
		return start_time;
	}
	
	public int getEndTime() {
		return end_time;
	}
}
