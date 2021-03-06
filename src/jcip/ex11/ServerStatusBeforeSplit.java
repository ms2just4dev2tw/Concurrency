package jcip.ex11;

import java.util.*;

import net.jcip.annotations.*;

/**
 * <h6>CodeList 11-6 ServerStatusBeforeSplit</h6>
 * <i>Candidate for lock splitting</i>
 * <p>
 * 
 * @author Brian Goetz and Tim Peierls
 */
@ThreadSafe
public class ServerStatusBeforeSplit {

	@GuardedBy("this")
	public final Set<String> users;
	@GuardedBy("this")
	public final Set<String> queries;

	public ServerStatusBeforeSplit() {
		users = new HashSet<String>();
		queries = new HashSet<String>();
	}

	public synchronized void addUser(String u) {
		users.add(u);
	}

	public synchronized void addQuery(String q) {
		queries.add(q);
	}

	public synchronized void removeUser(String u) {
		users.remove(u);
	}

	public synchronized void removeQuery(String q) {
		queries.remove(q);
	}
}
