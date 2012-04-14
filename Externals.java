public abstract class Externals {
	public static final int MAIN_MEMORY_SIZE = 2400;

	public static void invalidUsageExit() {
		System.err.println("USAGE: java MemorySimulator <input-file> { first | best | next | worst }");
		System.exit(1);
	}
}
