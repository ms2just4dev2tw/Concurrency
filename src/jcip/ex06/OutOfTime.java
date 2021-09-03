package jcip.ex06;

import java.util.*;
import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * <h6>CodeList 6-9 OutOfTime</h6>
 * <i>Class illustrating confusing Timer behavior</i>
 * <p>
 * 
 * @author Brian Goetz and Tim Peierls
 */
public class OutOfTime {
	
	public static void main(String[] args) throws Exception {
		Timer timer = new Timer();
		timer.schedule(new ThrowTask(), 1);
		SECONDS.sleep(1);
		timer.schedule(new ThrowTask(), 1);
		SECONDS.sleep(5);
	}

	static class ThrowTask extends TimerTask {
		public void run() {
			throw new RuntimeException();
		}
	}
}
