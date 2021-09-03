package org.self.juc.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Lock 接口提供 synchronized 关键字不具备的主要特性
 * <ul>
 * 		<li>1，非阻塞的尝试获取锁</li>
 * 		<li>2，能中断地获取锁 <p>
 * 		获取锁的线程能够响应中断。当获取锁的线程被中断时，抛出中断异常，并且释放锁</li>
 * 		<li>3，超时获取锁 <p>
 * 		在指定的截止时间之前获取锁</li>
 * </ul>
 * 
 * @author TungWang
 * @see Lock#lock() 调用该方法当前线程会获取锁，获得锁后，从该方法返回
 * @see Lock#lockInterruptibly() 可中断地获取锁
 * @see Lock#tryLock() 尝试非阻塞地获取锁
 * @see Lock#tryLock(long, java.util.concurrent.TimeUnit) 加上超时时间的非阻塞获取锁
 * @see Lock#unlock() 释放锁
 * @see Lock#newCondition() 获取通知组件，在获取锁后才能调用该组件的wait方法，调用后释放锁
 */
public class LockUseCase {

	Lock lock = new ReentrantLock();

	public void doSomething() {
		// 获取锁
		lock.lock();
		try {
			// 做些任务
		} finally {
			// 一定要释放锁
			lock.unlock();
		}
	}

}
