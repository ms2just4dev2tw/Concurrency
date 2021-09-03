package jcip.ex03;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * <h6>CodeList 3-3 SynchronizedInteger 非原子的64位操作</h6> <i>Thread-safe mutable
 * integer holder</i>
 * <p>
 * 仅对set方法进行同步时不够的，get方法仍会看见失效数据。 {@link examples.ex03.CountingSheep}
 * 
 * @see MutableInteger get/set操作没有同步
 * @author Brian Goetz and Tim Peierls
 */
@ThreadSafe
public class SynchronizedInteger {

	@GuardedBy("this")
	private int value;

	public synchronized int get() {
		return value;
	}

	public synchronized void set(int value) {
		this.value = value;
	}
}
