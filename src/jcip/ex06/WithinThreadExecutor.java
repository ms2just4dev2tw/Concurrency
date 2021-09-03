package jcip.ex06;

import java.util.concurrent.*;

/**
 * <h6>CodeList 6-6 WithinThreadExecutor</h6>
 * <i>Executor that executes tasks synchronously in the calling thread</i>
 * <p>
 * 
 * @author Brian Goetz and Tim Peierls
 */
public class WithinThreadExecutor implements Executor {
	
	public void execute(Runnable r) {
		r.run();
	};
}
