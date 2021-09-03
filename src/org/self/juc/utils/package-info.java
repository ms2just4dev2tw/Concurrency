/**
 * <h2>JUC 的并发工具类</h2>
 * 
 * <h3>等待多线程完成的 CountDownLatch</h3>
 * CountDownLatch 允许一个或多个线程等待其他线程完成操作
 * <p>
 * CountDownLatch 的构造器会接受一个 int 类型的参数作为计数器
 * <p>
 * 调用 countDown() 方法时，计数器减一；await() 方法会阻塞当前线程，直到计数器变为零。
 * <p>
 * CountDownLatch 可以用于替代 Java 原生的等待/通知机制
 * 
 * <h3>CyclicBarrier 同步屏障 </h3>
 * CyclicBarrier(int parties)，每个线程调用 await 方法时会被阻塞，并告知屏障一个线程已经到达，
 * 直到最后一个线程到达屏障，所有被拦截的线程才能继续运行
 * <p>
 * CyclicBarrier(int parties, Runnable barrierAction)，允许在屏障打开时优先执行 barrierAction 方法
 * <p>
 * 
 * 同步屏障与多线程等待的不同之处在于同步屏障的能调用 reset 方法重置计数，多线程等待只能计数归零
 * 
 * <h3>Semaphore 信号量</h3>
 * 用于控制临界资源的并发线程数量
 * 
 * <h3>Exchanger 交换者</h3>
 * 1，这是一个用于线程间进行数据交换的协作工具类。
 * <p>
 * 它会提供一个同步点，如果一个线程先执行 exchange 方法，他会等到第二个线程执行 exchange 方法。
 * <p>
 * 当两个线程到达到同步点时，这两个线程就可以交换自己本线程的数据给对方
 * <p>
 * 2，使用场景
 * <p>
 * 
 */
package org.self.juc.utils;
