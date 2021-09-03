package jcip.ex16;

import net.jcip.annotations.ThreadSafe;

/**
 * <h6>CodeList 16-6 ResourceFactory</h6>
 * <i>Lazy initialization holder class idiom</i>
 * <p>
 * 
 * @author Brian Goetz and Tim Peierls
 */
@ThreadSafe
public class ResourceFactory {
	
	private static class ResourceHolder {
		public static Resource resource = new Resource();
	}

	public static Resource getResource() {
		return ResourceFactory.ResourceHolder.resource;
	}

	static class Resource {
	}
}
