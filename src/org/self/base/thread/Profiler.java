package org.self.base.thread;

import java.util.concurrent.TimeUnit;

/**
 * <h3>ThreadLocal 的设计思路</h3>
 * 1，首先对于每个{@link Thread 线程}内都有一个 ThreadLocalMap 类型的变量
 * <p>
 * 2，ThreadLocalMap 维护当前线程所有的 ThreadLocal 类型的 key 和 它指向的 value
 * <p>
 * 3，get 和 set 操作先通过当前线程获得 ThreadLocalMap，再通过 ThreadLocal 获得或设置 value
 * 
 * @author TungWang
 */
public class Profiler {

	// 第一次get()方法调用时会进行初始化 (如果set方法没有调用)，每个线程会调用一次 initialValue
	private static final ThreadLocal<Long> TIME_THREADLOCAL = new ThreadLocal<Long>() {
		@Override
		protected Long initialValue() {
			return System.currentTimeMillis();
		}
	};

	/**
	 * 为调用线程设置调用 begin 方法的开始
	 */
	public static final void begin() {
		TIME_THREADLOCAL.set(System.currentTimeMillis());
	}

	/**
	 * @return 返回从 begin 方法调用开始到 end 方法被调用的时间差
	 */
	public static final long end() {
		return System.currentTimeMillis() - TIME_THREADLOCAL.get();
	}

	public static void main(String[] args) throws Exception {
		Profiler.begin();
		TimeUnit.SECONDS.sleep(1);
		System.out.println("Cost: " + Profiler.end() + " mills");
	}

}
