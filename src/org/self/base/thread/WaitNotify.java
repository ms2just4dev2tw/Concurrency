/**
 * 
 */
package org.self.base.thread;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * <h3>线程的暂停/恢复操作可以使用等待/通知机制来替代</h3>
 * <h3>生产者消费者问题</h3>
 * 等待方 - 消费者
 * <ul>
 * 		<li>1，获取对象的锁</li>
 * 		<li>2，条件不满足就等待</li>
 * 		<li>3，条件满足就执行逻辑</li>
 * </ul> <p>
 * 等待方 - 消费者
 * <ul>
 * 		<li>1，获取对象的锁</li>
 * 		<li>2，改变条件</li>
 * 		<li>3，通知其他消费者</li>
 * </ul> <p>
 * 整个流程的要求
 * <ul>
 * 		<li>1，确保及时性。线程能够迅速地感应到变量发生的变化</li>
 * 		<li>2，降低开销。不断的自检操作会占有大量的CPU资源</li>
 * </ul>
 * <h3>等待/通知机制</h3>
 * 1，调用 wait(), notify(), notifyAll() 方法时一般需要对调用对象进行加锁
 * <p>
 * 2，调用 wait() 方法后，当前线程状态由 RUNNING 变为 WAITING，并将当前线程放置到对象的等待队列
 * <p>
 * 3，调用 notify(), notifyAll() 方法后，等待线程依旧不会从 wait() 方法返回，需要等到调用唤醒方法的线程释放锁之后
 * <p>
 * 4，唤醒方法是将等待队列中的线程移到同步队列中，并且线程状态由 WAITING 变为 BLOCKED
 * <p>
 * 5，从 wait() 方法返回的前提是等待线程能获得到锁
 * 
 * @author TungWang
 * @see Thread#suspend() 过期的暂停操作
 * @see Thread#resume() 过期的恢复操作
 */
public class WaitNotify {

	static boolean flag = true;
	static Object lock = new Object();

	public static void main(String[] args) throws Exception {
		Thread waitThread = new Thread(new Wait(), "WaitThread");
		waitThread.start();
		TimeUnit.SECONDS.sleep(1);

		Thread notifyThread = new Thread(new Notify(), "NotifyThread");
		notifyThread.start();
	}

	static class Wait implements Runnable {
		@Override
		public void run() {
			// 加锁，尝试拥有 lock 对象的 Monitor
			synchronized (lock) {
				// 当条件不满足时
				while (flag) {
					try {
						System.out.println(Thread.currentThread() + " flag is true. wait @ "
								+ new SimpleDateFormat("HH:mm:ss").format(new Date()));
						// 调用 wait 方法的线程进入等待状态，同时释放了lock的锁
						lock.wait();
					} catch (InterruptedException e) {
					}
				}
				// 条件满足时，完成工作
				System.out.println(Thread.currentThread() + " flag is false. running @ "
						+ new SimpleDateFormat("HH:mm:ss").format(new Date()));
			}
		}
	}

	static class Notify implements Runnable {
		@Override
		public void run() {
			// 加锁，尝试拥有 lock 对象的 Monitor
			synchronized (lock) {
				System.out.println(Thread.currentThread() + " hold lock. notify @ "
						+ new SimpleDateFormat("HH:mm:ss").format(new Date()));
				// 唤醒所有在 lock 对象上等待的线程，通知时不会释放 lock 的锁
				// 被唤醒的 WaitThread 由于 lock 还处于锁定的状态，进入 lock 的同步队列
				// 直到当前线程释放了lock后，WaitThread才能从wait方法中返回
				lock.notifyAll();
				flag = false;
				second(5);
			}
			// 再次加锁，尝试拥有 lock 对象的 Monitor
			synchronized (lock) {
				System.out.println(Thread.currentThread() + " hold lock again. sleep @ "
						+ new SimpleDateFormat("HH:mm:ss").format(new Date()));
				second(5);
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
