package jcip.ex01;

import net.jcip.annotations.NotThreadSafe;

/**
 * CodeList 1-1
 * <p>
 * UnsafeSequence (非线程安全的顺序序列)
 * 
 * @see chapter_01.Sequence (线程安全的顺序序列)
 * 
 * @author Brian Goetz and Tim Peierls
 */
@NotThreadSafe
public class UnsafeSequence {

	private int value;

	public int getNext() {
		return value++;// Returns a unique value.
	}
}
