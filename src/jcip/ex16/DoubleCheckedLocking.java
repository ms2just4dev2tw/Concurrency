package jcip.ex16;

import net.jcip.annotations.NotThreadSafe;

/**
 * <h6>CodeList 16-7 DoubleCheckedLocking</h6>
 * <i>Double-checked-locking antipattern</i>
 * <p>
 * 
 * @author Brian Goetz and Tim Peierls
 */
@NotThreadSafe
public class DoubleCheckedLocking {

	private static Resource resource;

	public static Resource getInstance() {
		if (resource == null) {
			synchronized (DoubleCheckedLocking.class) {
				if (resource == null)
					resource = new Resource();
			}
		}
		return resource;
	}

	static class Resource {

	}
}
