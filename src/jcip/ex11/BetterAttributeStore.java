package jcip.ex11;

import java.util.*;
import java.util.regex.*;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * <h6>CodeList 11-5 BetterAttributeStore</h6>
 * <i>Reducing lock duration</i>
 * <p>
 * 
 * @author Brian Goetz and Tim Peierls
 */
@ThreadSafe
public class BetterAttributeStore {

	@GuardedBy("this")
	private final Map<String, String> attributes = new HashMap<String, String>();

	public boolean userLocationMatches(String name, String regexp) {
		String key = "users." + name + ".location";
		String location;
		synchronized (this) {
			location = attributes.get(key);
		}
		if (location == null)
			return false;
		else
			return Pattern.matches(regexp, location);
	}
}
