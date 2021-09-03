package org.self.base.thread;

import taojcp.ex04.SleepUtils;

/**
 * Daemon 线程，主要被用作程序中后台调度以及支持性工作
 * <p>
 * 当一个 Java 虚拟机中不存在非 Daemon 的线程时，虚拟机将会退出。
 * <p>
 * 在虚拟机退出时，Daemon 线程中的 finally 块不一定会执行
 * 
 * @author TungWang
 */
public class DaemonThread {

	public static void main(String[] args) {
		Thread thread = new Thread(new DaemonRunner());
		thread.setDaemon(true);
		thread.start();
	}

	static class DaemonRunner implements Runnable {
		@Override
		public void run() {
			try {
				SleepUtils.second(100);
			} finally {
				System.out.println("DaemonThread finally run.");
			}
		}
	}

}
