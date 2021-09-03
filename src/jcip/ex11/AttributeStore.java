package jcip.ex11;

import java.util.*;
import java.util.regex.*;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * <h6>CodeList 11-4 AttributeStore</h6>
 * <i>Holding a lock longer than necessary</i>
 * <p>
 * 
 * @author Brian Goetz and Tim Peierls
 */
@ThreadSafe
public class AttributeStore {

	@GuardedBy("this")
	private final Map<String, String> attributes = new HashMap<String, String>();

	public synchronized boolean userLocationMatches(String name, String regexp) {
		String key = "users." + name + ".location";
		String location = attributes.get(key);
		if (location == null)
			return false;
		else
			return Pattern.matches(regexp, location);
	}
}
