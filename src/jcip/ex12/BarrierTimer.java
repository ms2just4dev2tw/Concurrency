package jcip.ex12;

/**
 * <h6>CodeList 12-11 BarrierTimer</h6>
 * <i>Barrier-based timer</i>
 * <p>
 * 
 * @author Brian Goetz and Tim Peierls
 */
public class BarrierTimer implements Runnable {
	
	private boolean started;
	private long startTime, endTime;

	public synchronized void run() {
		long t = System.nanoTime();
		if (!started) {
			started = true;
			startTime = t;
		} else
			endTime = t;
	}

	public synchronized void clear() {
		started = false;
	}

	public synchronized long getTime() {
		return endTime - startTime;
	}
}
