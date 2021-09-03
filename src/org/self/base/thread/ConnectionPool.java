package org.self.base.thread;

import java.sql.Connection;
import java.util.LinkedList;
import java.util.Objects;

/**
 * 
 * @author TungWang
 */
public class ConnectionPool {

	// 双向队列线程池
	private LinkedList<Connection> pool = new LinkedList<Connection>();

	public ConnectionPool(int initialSize) {
		if (initialSize > 0)
			for (int i = 0; i < initialSize; i++)
				pool.addLast(ConnectionDriver.createConnection());
	}

	public void releaseConnection(Connection connection) {
		Objects.requireNonNull(connection);
		synchronized (pool) {
			pool.addLast(connection);
			// 添加后需要进行通知，这样其他消费者能够感知到链接池中已经归还了一个链接
			pool.notifyAll();
		}
	}

	// 在mills内无法获取到连接，将会返回null
	public Connection fetchConnection(long mills) throws InterruptedException {
		synchronized (pool) {
			// 完全超时
			if (mills <= 0) {
				while (pool.isEmpty())
					pool.wait();
				return pool.removeFirst();
			} else {
				// 等待持续时间
				long remaining = mills;
				// 超时时间
				long future = System.currentTimeMillis() + mills;
				// 如果线程池没有线程，等待剩余时间 > 0
				while (pool.isEmpty() && remaining > 0) {
					// 继续等待 remaining 时间
					pool.wait(remaining);
					// 更新等待持续时间 remaining
					remaining = future - System.currentTimeMillis();
				}
				return pool.isEmpty() ? null : pool.removeFirst();
			}
		}
	}

}
