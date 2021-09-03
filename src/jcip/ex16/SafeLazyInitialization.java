package jcip.ex16;

import net.jcip.annotations.ThreadSafe;

/**
 * <h6>CodeList 16-4 SafeLazyInitialization</h6>
 * <i>Thread-safe lazy initialization</i>
 * <p>
 * 
 * @author Brian Goetz and Tim Peierls
 */
@ThreadSafe
public class SafeLazyInitialization {
	
	private static Resource resource;

	public synchronized static Resource getInstance() {
		if (resource == null)
			resource = new Resource();
		return resource;
	}

	static class Resource {
	}
}
