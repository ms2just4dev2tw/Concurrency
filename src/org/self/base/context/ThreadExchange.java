package org.self.base.context;

/**
 * 线程的上下文切换的开销
 * 
 * @author tengfei.fangtf
 * @version $Id: ConcurrencyTest.java, v 0.1 2014-7-18 下午10:03:31
 */
public class ThreadExchange {

	// 执行次数
	private long count;

	public ThreadExchange() {
		this(10000L);
	}

	public ThreadExchange(long count) {
		this.count = count;
	}

	// 并发计算
	public void concurrency() throws InterruptedException {
		long start = System.currentTimeMillis();
		// 创建线程
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				int a = 0;
				for (long i = 0; i < count; i++)
					a += 5;
				System.out.println(a);
			}
		});
		// 开启线程
		thread.start();
		int b = 0;
		for (long i = 0; i < count; i++)
			b--;
		// 在 b 计算完毕后，等待 a 计算完毕
		thread.join();
		// 打印耗时
		long time = System.currentTimeMillis() - start;
		System.out.println("concurrency :" + time + "ms,b=" + b);
	}

	// 串行计算
	public void serial() {
		long start = System.currentTimeMillis();
		// 计算的任务
		int a = 0;
		for (long i = 0; i < count; i++)
			a += 5;
		int b = 0;
		for (long i = 0; i < count; i++)
			b--;
		// 打印耗时
		long time = System.currentTimeMillis() - start;
		System.out.println("serial:" + time + "ms,b=" + b + ",a=" + a);
	}

}
