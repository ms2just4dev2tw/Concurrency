package jcip.ex16;

import net.jcip.annotations.ThreadSafe;

/**
 * <h6>CodeList 16-5 EagerInitialization</h6>
 * <i>Eager initialization</i>
 * <p>
 * 
 * @author Brian Goetz and Tim Peierls
 */
@ThreadSafe
public class EagerInitialization {
	
	private static Resource resource = new Resource();

	public static Resource getResource() {
		return resource;
	}

	static class Resource {
	}
}
