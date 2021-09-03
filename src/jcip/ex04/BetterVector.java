package jcip.ex04;

import java.util.Vector;

import net.jcip.annotations.ThreadSafe;

/**
 * <h6>CodeList 4-13 BetterVector</h6> <i>Extending Vector to have a
 * put-if-absent method</i>
 * 
 * @author Brian Goetz and Tim Peierls
 */
@ThreadSafe
public class BetterVector<E> extends Vector<E> {

	// When extending a serializable class, you should redefine serialVersionUID
	static final long serialVersionUID = -3963416950630760754L;

	/**
	 * 如果底层类改变同步策略并使用不同的锁来保护它的状态变量，那么子类会被破坏。 <br>
	 * 由于Vector定义了它的同步策略，所以不存在这个问题。
	 */
	public synchronized boolean putIfAbsent(E x) {
		boolean absent = !contains(x);
		if (absent)
			add(x);
		return absent;
	}
}
