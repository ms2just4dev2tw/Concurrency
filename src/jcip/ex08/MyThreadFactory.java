package jcip.ex08;

import java.util.concurrent.*;

/**
 * <h6>CodeList 8-6 MyThreadFactory</h6>
 * <i>Custom thread factory</i>
 * <p>
 * 
 * @author Brian Goetz and Tim Peierls
 */
public class MyThreadFactory implements ThreadFactory {

	private final String poolName;

	public MyThreadFactory(String poolName) {
		this.poolName = poolName;
	}

	public Thread newThread(Runnable runnable) {
		return new MyAppThread(runnable, poolName);
	}
}
