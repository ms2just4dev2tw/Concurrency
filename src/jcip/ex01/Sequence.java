package jcip.ex01;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * CodeList 1-2
 * <p>
 * Sequence (线程安全的顺序序列)
 * 
 * @see chapter_01.UnsafeSequence (线程不安全的顺序序列)
 * 
 * @author Brian Goetz and Tim Peierls
 */
@ThreadSafe
public class Sequence {

	// 与 nextValue相关联的锁
	@GuardedBy("this")
	private int nextValue;

	// 锁住方法体
	public synchronized int getNext() {
		return nextValue++;
	}

}
