package jcip.ex04;

import net.jcip.annotations.GuardedBy;

/**
 * <h6>CodeList 4-3 PrivateLock</h6> <i>Guarding state with a private lock</i>
 * 
 * 本例中使用一个私有锁保护对象
 * 
 * @author Brian Goetz and Tim Peierls
 */
public class PrivateLock {

	@GuardedBy("myLock")
	Widget widget;

	/**
	 * myLock是私有且不可变的锁
	 */
	private final Object myLock = new Object();

	void someMethod() {
		// 私有锁可以在公有方法中访问
		synchronized (myLock) {
			// Access or modify the state of widget
		}
	}
}

class Widget {
	public synchronized void doSomething() {
	}
}
