package jcip.ex04;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.jcip.annotations.NotThreadSafe;
import net.jcip.annotations.ThreadSafe;

/**
 * </h6>CodeList 4-14|4-15 ListHelder</h6> <i>Examples of thread-safe and
 * non-thread-safe implementations of put-if-absent helper methods for List</i>
 * 
 * @author Brian Goetz and Tim Peierls
 */
@NotThreadSafe
class BadListHelper<E> {

	public List<E> list = Collections.synchronizedList(new ArrayList<E>());

	/**
	 * 此处存在着同步假象，synchronized方法的锁是BadListHelper对象，
	 * 而不是synchronizedList方法返回的同步对象，持有不同的锁，会破坏同步策略
	 */
	public synchronized boolean putIfAbsent(E x) {
		boolean absent = !list.contains(x);
		if (absent)
			list.add(x);
		return absent;
	}
}

@ThreadSafe
class GoodListHelper<E> {

	public List<E> list = Collections.synchronizedList(new ArrayList<E>());

	/**
	 * list对象引用作为锁与synchronizedList方法返回的同步对象保持一致， 不会破坏同步策略。
	 */
	public boolean putIfAbsent(E x) {
		synchronized (list) {
			boolean absent = !list.contains(x);
			if (absent)
				list.add(x);
			return absent;
		}
	}
}
