package jcip.ex06;

import java.util.concurrent.*;

/**
 * <h6>CodeList 6-5 ThreadPerTaskExecutor</h6>
 * <i>Executor that starts a new thread for each task</i>
 * <p>
 * 
 * @author Brian Goetz and Tim Peierls
 */
public class ThreadPerTaskExecutor implements Executor {
	
	public void execute(Runnable r) {
		new Thread(r).start();
	};
}
