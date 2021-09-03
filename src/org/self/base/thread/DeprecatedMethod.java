package org.self.base.thread;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 线程的运作看作是音乐播放器播放音乐
 * <p>
 * {@link Thread#suspend() 暂停播放音乐}
 * {@link Thread#resume() 恢复播放音乐}
 * {@link Thread#stop() 关闭音乐播放器}
 * <p>
 * suspend 方法会占用资源进入睡眠状态，这样容易引发死锁问题 <p>
 * stop 方法在终结线程时不能保证线程的资源能正常释放
 * <p>
 * 对于线程的暂停和恢复操作可以使用等待和通知机制来替代
 * 
 * @author TungWang
 * @see SafeShutdownThread 安全地终止线程
 * @see WaitNotify 等待通知机制
 */
public class DeprecatedMethod {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws Exception {
		DateFormat format = new SimpleDateFormat("HH:mm:ss");
		// 打印线程信息的线程
		Thread printThread = new Thread(new Runner(), "PrintThread");
		printThread.setDaemon(true);
		printThread.start();
		TimeUnit.SECONDS.sleep(3);
		// 将PrintThread进行暂停，输出内容工作停止
		printThread.suspend();
		System.out.println("main suspend PrintThread at " + format.format(new Date()));
		TimeUnit.SECONDS.sleep(20);
		// 将PrintThread进行恢复，输出内容继续
		printThread.resume();
		System.out.println("main resume PrintThread at " + format.format(new Date()));
		TimeUnit.SECONDS.sleep(3);
		// 将PrintThread进行终止，输出内容停止
		printThread.stop();
		System.out.println("main stop PrintThread at " + format.format(new Date()));
		TimeUnit.SECONDS.sleep(3);
	}

	static class Runner implements Runnable {
		@Override
		public void run() {
			DateFormat format = new SimpleDateFormat("HH:mm:ss");
			while (true) {
				String threadName = Thread.currentThread().getName();
				System.out.println(threadName + " Run at " + format.format(new Date()));
				try {
					TimeUnit.SECONDS.sleep(3);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
