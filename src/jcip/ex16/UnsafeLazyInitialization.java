package jcip.ex16;

import net.jcip.annotations.NotThreadSafe;

/**
 * <h6>CodeList 16-3 UnsafeLazyInitialization</h6>
 * <i>Unsafe lazy initialization</i>
 * <p>
 *
 * @author Brian Goetz and Tim Peierls
 */
@NotThreadSafe
public class UnsafeLazyInitialization {
	
    private static Resource resource;

    public static Resource getInstance() {
        if (resource == null)
            resource = new Resource(); // unsafe publication
        return resource;
    }

    static class Resource {
    }
}
