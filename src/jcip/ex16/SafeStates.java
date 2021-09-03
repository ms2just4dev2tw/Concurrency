package jcip.ex16;

import java.util.*;

import net.jcip.annotations.ThreadSafe;

/**
 * <h6>CodeList 16-8 SafeStates</h6>
 * <i>Initialization safety for immutable objects</i>
 * <p>
 * 
 * @author Brian Goetz and Tim Peierls
 */
@ThreadSafe
public class SafeStates {

	private final Map<String, String> states;

	public SafeStates() {
		states = new HashMap<String, String>();
		states.put("alaska", "AK");
		states.put("alabama", "AL");
		/* ... */
		states.put("wyoming", "WY");
	}

	public String getAbbreviation(String s) {
		return states.get(s);
	}
}
