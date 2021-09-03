package jcip.ex03;

import net.jcip.annotations.NotThreadSafe;

/**
 * <h6>CodeList 3-2 MutableInteger 失效数据</h6> <i>Non-thread-safe mutable integer
 * holder</i>
 * 
 * value的get/set操作没有同步
 * 
 * @see SynchronizedInteger get/set操作同步
 * 
 * @author Brian Goetz and Tim Peierls
 */
@NotThreadSafe
public class MutableInteger {

	private int value;

	public int get() {
		return value;
	}

	public void set(int value) {
		this.value = value;
	}
}
