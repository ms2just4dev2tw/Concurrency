package org.self.base.context;

/**
 * 死锁例子
 * 
 * @author tengfei.fangtf
 * @version $Id: DeadLockDemo.java, v 0.1 2015-7-18 下午10:08:28
 */
public class DeadLock {

	/** A锁 */
	private static String A = "A";
	/** B锁 */
	private static String B = "B";

	public void deadLock() {
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				synchronized (A) {
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					synchronized (B) {
						System.out.println("1");
					}
				}
			}
		});

		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				synchronized (B) {
					synchronized (A) {
						System.out.println("2");
					}
				}
			}
		});
		t2.start();
		t1.start();
	}

}
