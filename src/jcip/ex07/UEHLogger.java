package jcip.ex07;

import java.util.logging.*;

/**
 * <h6>CodeList 7-25 UEHLogger</h6>
 * <i>UncaughtExceptionHandler that logs the exception</i>
 * <p>
 * 
 * @author Brian Goetz and Tim Peierls
 */
public class UEHLogger implements Thread.UncaughtExceptionHandler {

	public void uncaughtException(Thread t, Throwable e) {
		Logger logger = Logger.getAnonymousLogger();
		logger.log(Level.SEVERE,
				"Thread terminated with exception: " + t.getName(), e);
	}
}
