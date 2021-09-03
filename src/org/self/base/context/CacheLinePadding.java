package org.self.base.context;

import java.util.concurrent.CountDownLatch;

public class CacheLinePadding {

	private static final int COUNT = 1000_000_000;

	/**
	 *  在 1.80_231 中不用带有 volatile 关键字
	 *  <p>
	 *  在 11 中需要 volatile 关键字才能有区别
	 */
	private static volatile CacheObject[] objArr = new CacheObject[2];
	static {
		objArr[0] = new CacheObject();
		objArr[1] = new CacheObject();
	}

	public static void main(String[] args) throws InterruptedException {
		CountDownLatch latch = new CountDownLatch(2);
		//
		Thread t1 = new Thread(() -> {
			// CacheObject obj = new CacheObject();
			for (int index = 0; index < COUNT; index++) {
				objArr[0].number = index;
				// objArr[1].number = -index;
			}

			latch.countDown();
		});

		Thread t2 = new Thread(() -> {
			// CacheObject obj = new CacheObject();
			for (int index = 0; index < COUNT; index++) {
				objArr[1].number = index;
				// objArr[0].number = -index;
			}

			latch.countDown();
		});

		// currentTimeMillis 毫秒，
		final long startTime = System.nanoTime();
		System.out.println(startTime);
		t1.start();
		t2.start();
		latch.await();
		final long endTime = System.nanoTime();
		System.out.println(endTime);
		System.out.println("耗时: " + (endTime - startTime) / 1000_000 + " mills");
	}

	/**
	 * 前方填充 7 个 long 型数据，中间的有效 long 型数据，后方填充 8 个 long 型数据
	 * 
	 * @author TungWang
	 */
	public static class CacheObject {
		//
		private long p1, p2, p3p4, p5, p6, p7;
		public long number;
		private long p9, p10, p11, p12, p13, p14, p15;

		//
		// private static long s1;

		//
		// private final long f1 = 1L, f2 = 2L, f3 = 3L, f4 = 4L, f5 = 5L;
	}

}
