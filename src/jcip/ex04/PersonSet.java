package jcip.ex04;

import java.util.HashSet;
import java.util.Set;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * <h6>CodeList 4-2 PersonSet</h6> <i>Using confinement to ensure thread
 * safety</i>
 * <p>
 * 
 * @author Brian Goetz and Tim Peierls
 */
@ThreadSafe
public class PersonSet {

	/**
	 * PersonSet的状态由HashSet对象保存，mySet对象引用是私有的，<br>
	 * mySet的访问由addPerson和containsPerson控制，但是方法被加上同步锁。
	 */
	@GuardedBy("this")
	private final Set<Person> mySet = new HashSet<Person>();

	public synchronized void addPerson(Person p) {
		mySet.add(p);
	}

	public synchronized boolean containsPerson(Person p) {
		return mySet.contains(p);
	}

	interface Person {
	}
}
