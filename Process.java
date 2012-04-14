
public class Process {
	private String pid;
	private String memory;
	private int[] times;

	public Process(String new_pid, String new_mem, int[] new_times) {
		pid = new_pid;
		memory = new_mem;
		times	= new_times;
	}

	public String getPid() {
		return pid;
	}

	public String getMemory() {
		return memory;
	}
}
