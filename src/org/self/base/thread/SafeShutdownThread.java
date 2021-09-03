package org.self.base.thread;

import java.util.concurrent.TimeUnit;

/**
 * 安全地终止线程
 * <p>
 * 通过中断操作的方式能够使线程有机会清理资源，比起武断地停止线程更加安全
 * 
 * @author TungWang
 * @see Interrupted 线程中断的理解
 */
public class SafeShutdownThread {

	public static void main(String[] args) throws Exception {
		Runner one = new Runner();
		Thread countThread = new Thread(one, "CountThread");
		countThread.start();
		// 睡眠1秒，main线程对CountThread进行中断，使CountThread能够感知中断而结束
		TimeUnit.SECONDS.sleep(1);
		countThread.interrupt();
		Runner two = new Runner();
		countThread = new Thread(two, "CountThread");
		countThread.start();
		// 睡眠1秒，main线程对Runner two进行取消，使CountThread能够感知on为false而结束
		TimeUnit.SECONDS.sleep(1);
		two.cancel();
	}

	private static class Runner implements Runnable {
		private long i;

		private volatile boolean on = true;

		@Override
		public void run() {
			// 通过一个变量和中断标识符来停止任务
			while (on && !Thread.currentThread().isInterrupted())
				i++;
			System.out.println("Count i = " + i);
		}

		public void cancel() {
			on = false;
		}
	}

}
