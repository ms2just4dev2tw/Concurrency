package taojcp.ex04;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 
 * @author TungWang
 */
public class ConnectionPoolTest {

	static ConnectionPool pool = new ConnectionPool(10);
	// 保证所有ConnectionRunner能够同时开始
	static CountDownLatch start = new CountDownLatch(1);
	// main线程将会等待所有ConnectionRunner结束后才能继续执行
	static CountDownLatch end;

	public static void main(String[] args) throws Exception {
		// 线程数量，可以线程数量进行观察
		int threadCount = 50;
		end = new CountDownLatch(threadCount);
		// 每个线程的任务数量
		int taskCount = 20;
		// 所有线程成功获得线程的计数和获取线程失败的计数
		AtomicInteger got = new AtomicInteger(), notGot = new AtomicInteger();

		for (int i = 0; i < threadCount; i++) {
			Thread thread = new Thread(new ConnetionRunner(taskCount, got, notGot), "ConnectionRunnerThread");
			thread.start();
		}
		// 启动所有的线程
		start.countDown();
		// 等待所有的线程都完成工作
		end.await();
		System.out.println("total invoke: " + (threadCount * taskCount));
		System.out.println("got connection:  " + got);
		System.out.println("not got connection " + notGot);
	}

	static class ConnetionRunner implements Runnable {
		int count;
		AtomicInteger got, notGot;

		public ConnetionRunner(int count, AtomicInteger got, AtomicInteger notGot) {
			this.count = count;
			this.got = got;
			this.notGot = notGot;
		}

		@Override
		public void run() {
			try {
				// 线程挂起，等待 start 计数归零
				start.await();
				// 分别统计连接获取的数量got和未获取到的数量notGot
				while (count > 0) {
					Connection connection = pool.fetchConnection(1000);
					if (connection != null) {
						try {
							connection.createStatement();
							connection.commit();
						} catch (SQLException e) {
							e.printStackTrace();
						} finally {
							// 对于成功获取链接的要进行计数
							pool.releaseConnection(connection);
							got.incrementAndGet();
						}
					} else
						notGot.incrementAndGet();
				}
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			} finally {
				count--;
			}
			end.countDown();
		}
	}

}
