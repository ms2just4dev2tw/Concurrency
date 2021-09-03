package org.self.juc.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 自定义同步组件 - 独占锁
 * 
 * @author TungWang
 */
public class Mutex implements Lock {

	/**
	 *  静态内部类，自定义同步器
	 *  
	 * @author TungWang
	 */
	private static class Sync extends AbstractQueuedSynchronizer {
		private static final long serialVersionUID = -4387327721959839431L;

		// 是否处于占用状态
		@Override
		protected boolean isHeldExclusively() {
			return getState() == 1;
		}

		// 当状态为0的时候获取锁
		@Override
		public boolean tryAcquire(int arg) {
			// 只使用 arg 为 1 的情况
			assert arg == 1;
			if (compareAndSetState(0, 1)) {
				setExclusiveOwnerThread(Thread.currentThread());
				return true;
			}
			return false;
		}

		// 释放锁，将状态设置为0
		@Override
		protected boolean tryRelease(int arg) {
			// 只使用 arg 为 1 的情况
			assert arg == 1;
			if (getState() == 0)
				throw new IllegalMonitorStateException();
			setExclusiveOwnerThread(null);
			setState(0);
			return true;
		}

		// 返回一个Condition，每个condition都包含了一个condition队列
		Condition newCondition() {
			return new ConditionObject();
		}
	}

	// 仅需要将操作代理到Sync上
	private final Sync sync = new Sync();

	@Override
	public void lock() {
		sync.acquire(1);
	}

	@Override
	public boolean tryLock() {
		return sync.tryAcquire(1);
	}

	@Override
	public void unlock() {
		sync.release(1);
	}

	@Override
	public Condition newCondition() {
		return sync.newCondition();
	}

	public boolean isLocked() {
		return sync.isHeldExclusively();
	}

	public boolean hasQueuedThreads() {
		return sync.hasQueuedThreads();
	}

	@Override
	public void lockInterruptibly() throws InterruptedException {
		sync.acquireInterruptibly(1);
	}

	@Override
	public boolean tryLock(long timeout, TimeUnit unit) throws InterruptedException {
		return sync.tryAcquireNanos(1, unit.toNanos(timeout));
	}
}
