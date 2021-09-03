package jcip.ex05;

import java.util.*;

import net.jcip.annotations.GuardedBy;

/**
 * <h6>CodeList 5-6 HiddenIterator</h6>
 * <i>Iteration hidden within string concatenation</i>
 * <p>
 * 
 * @author Brian Goetz and Tim Peierls
 */
public class HiddenIterator {

	@GuardedBy("this")
	private final Set<Integer> set = new HashSet<Integer>();

	public synchronized void add(Integer i) {
		set.add(i);
	}

	public synchronized void remove(Integer i) {
		set.remove(i);
	}

	public void addTenThings() {
		Random r = new Random();
		for (int i = 0; i < 10; i++)
			add(r.nextInt());
		System.out.println("DEBUG: added ten elements to " + set);
	}
}
