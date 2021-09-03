package jcip.ex04;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * <h6>CodeList 4-11 SafePoint</h6>
 * <p>
 * 发布底层状态变量
 * 
 * @author Brian Goetz and Tim Peierls
 */
@ThreadSafe
public class SafePoint {

	@GuardedBy("this")
	private int x, y;

	private SafePoint(int[] a) {
		this(a[0], a[1]);
	}

	/**
	 * 此处使用this(p.x,p.y)存在竞态条件。<br>
	 * 如果在p.x和p.y之间有另一个线程修改p的 状态，那么和出现无效的位置。
	 */
	public SafePoint(SafePoint p) {
		this(p.get());
	}

	public SafePoint(int x, int y) {
		this.set(x, y);
	}

	public synchronized int[] get() {
		return new int[] { x, y };
	}

	public synchronized void set(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
