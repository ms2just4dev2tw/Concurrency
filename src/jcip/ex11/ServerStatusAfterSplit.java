package jcip.ex11;

import java.util.*;

import net.jcip.annotations.*;

/**
 * <h6>CodeList 11-7 ServerStatusAfterSplit</h6>
 * <i>ServerStatus refactored to use split locks</i>
 * <p>
 * 
 * @author Brian Goetz and Tim Peierls
 */
@ThreadSafe
public class ServerStatusAfterSplit {

	@GuardedBy("users")
	public final Set<String> users;
	@GuardedBy("queries")
	public final Set<String> queries;

	public ServerStatusAfterSplit() {
		users = new HashSet<String>();
		queries = new HashSet<String>();
	}

	public void addUser(String u) {
		synchronized (users) {
			users.add(u);
		}
	}

	public void addQuery(String q) {
		synchronized (queries) {
			queries.add(q);
		}
	}

	public void removeUser(String u) {
		synchronized (users) {
			users.remove(u);
		}
	}

	public void removeQuery(String q) {
		synchronized (users) {
			queries.remove(q);
		}
	}
}
