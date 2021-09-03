package org.self.base.thread;

import java.util.concurrent.TimeUnit;

/**
 * 中断是线程的一种标识符属性
 * <p>
 * {@link Thread#interrupted() 该方法会对当前线程的中断标识符进行复位}
 * <p>
 * {@link InterruptedException 许多声明抛出中断异常的方法，虚拟机会清除线程的中断标识符}
 * 
 * @author TungWang
 */
public class Interrupted {

	public static void main(String[] args) throws Exception {
		// sleepThread不停的尝试睡眠
		Thread sleepThread = new Thread(new SleepRunner(), "SleepThread");
		sleepThread.setDaemon(true);
		// busyThread不停的运行
		Thread busyThread = new Thread(new BusyRunner(), "BusyThread");
		busyThread.setDaemon(true);
		sleepThread.start();
		busyThread.start();
		// 休眠5秒，让sleepThread和busyThread充分运行
		TimeUnit.SECONDS.sleep(5);
		sleepThread.interrupt();
		busyThread.interrupt();
		System.out.println("SleepThread interrupted is " + sleepThread.isInterrupted());
		System.out.println("BusyThread interrupted is " + busyThread.isInterrupted());
		// 防止sleepThread和busyThread立刻退出
		TimeUnit.SECONDS.sleep(2);
	}

	static class SleepRunner implements Runnable {
		@Override
		public void run() {
			while (true) {
				try {
					TimeUnit.SECONDS.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	static class BusyRunner implements Runnable {
		@Override
		public void run() {
			while (true) {
			}
		}
	}

}
