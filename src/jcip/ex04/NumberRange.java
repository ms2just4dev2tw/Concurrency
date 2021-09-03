package jcip.ex04;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * <h6>CodeList 4-10 NumberRange</h6> <i>Number range class that does not
 * sufficiently protect its invariants</i>
 * <p>
 * 这里的setLower和setUpper都是先检查后执行，这里明显的不安全。<br>
 * 如果一个线程调用setLower(9)，同时另一个线程调用setUpper(4)，结果(9,4)。
 * <p>
 * 可以通过加锁来保证复合操作是原子操作。
 * 
 * @author Brian Goetz and Tim Peierls
 */
public class NumberRange {

	// 不变性条件(INVARIANT): lower <= upper
	private final AtomicInteger lower = new AtomicInteger(0);
	private final AtomicInteger upper = new AtomicInteger(0);

	public void setLower(int i) {
		// Warning -- unsafe check-then-act 不安全的先检查后执行
		// 没有足够的加锁机制来保证操作的原子性
		if (i > upper.get())
			throw new IllegalArgumentException("can't set lower to " + i + " > upper");
		lower.set(i);
	}

	public void setUpper(int i) {
		// Warning -- unsafe check-then-act 不安全的先检查后执行
		// 在一些错误的执行时序中, 会破坏不变性条件
		if (i < lower.get())
			throw new IllegalArgumentException("can't set upper to " + i + " < lower");
		upper.set(i);
	}

	public boolean isInRange(int i) {
		return (i >= lower.get() && i <= upper.get());
	}
}
