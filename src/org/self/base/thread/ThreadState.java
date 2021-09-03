package org.self.base.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程在运行的生命周期中可能处于6种不同的状态
 * <ul>
 * 		<li>1，NEW，初始状态。创建后的线程还没有调用 start 方法</li>
 * 		<li>2，RUNNABLE，运行状态。Java 将 OS 中就绪和运行笼统称为运行中</li>
 * 		<li>2，BLOCKED，阻塞状态。线程阻塞于锁</li>
 * 		<li>2，WAITING，等待状态。进入该状态的线程需要其他线程通知或中断该线程</li>
 * 		<li>2，TIME_WAITING，超时等待状态。达到超时时间后，会返回到运行状态</li>
 * 		<li>2，TERMINATED，终止状态。线程执行完毕</li>
 * </ul>
 * 
 * @author TungWang
 */
public class ThreadState {

	public static void main(String[] args) {
		new Thread(new TimeWaiting(), "TimeWaitingThread").start();
		new Thread(new Waiting(), "WaitingThread").start();
		// 使用两个Blocked线程，一个获取锁成功，另一个被阻塞
		new Thread(new Blocked(), "BlockedThread-1").start();
		new Thread(new Blocked(), "BlockedThread-2").start();
		new Thread(new Sync(), "SyncThread-1").start();
		new Thread(new Sync(), "SyncThread-2").start();
	}

	/**
	 * 该线程不断的进行睡眠
	 * 
	 * @author TungWang
	 */
	static class TimeWaiting implements Runnable {
		@Override
		public void run() {
			while (true) {
				second(100);
			}
		}
	}

	/**
	 * 该线程在Waiting.class实例上等待
	 *  
	 * @author TungWang
	 */
	static class Waiting implements Runnable {
		@Override
		public void run() {
			while (true) {
				synchronized (Waiting.class) {
					try {
						// 调用类对象的等待方法，会使调用线程进入等待状态
						Waiting.class.wait();
						;
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	/**
	 * 该线程在Blocked.class实例上加锁后，不会释放该锁
	 * 
	 * @author TungWang
	 */
	static class Blocked implements Runnable {
		@Override
		public void run() {
			synchronized (Blocked.class) {
				while (true) {
					second(100);
				}
			}
		}
	}

	// 重入锁
	private static Lock lock = new ReentrantLock();

	/**
	 * 使用锁机制的同步
	 * 
	 * @author TungWang
	 */
	static class Sync implements Runnable {
		@Override
		public void run() {
			lock.lock();
			try {
				second(100);
			} finally {
				lock.unlock();
			}
		}
	}

	private static final void second(long seconds) {
		try {
			TimeUnit.SECONDS.sleep(seconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
