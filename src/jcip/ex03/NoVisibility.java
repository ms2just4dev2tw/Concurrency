package jcip.ex03;

/**
 * <h6>CodeList 3-1 NoVisibility</h6> <i>Sharing variables without
 * synchronization</i>
 * <p>
 * 共享变量的可见性: 在没有同步的情况下共享变量是线程不安全的
 * 
 * @author Brian Goetz and Tim Peierls
 */
public class NoVisibility {

	private static int number;
	private static boolean ready;

	private static class ReaderThread extends Thread {
		public void run() {
			while (!ready)
				Thread.yield();
			System.out.println(number);
		}
	}

	/**
	 * <b>System.out.println(number)</b>可能存在三种情况
	 * <ol>
	 * <li>[42] 我们希望的情况</li>
	 * <li>[0] 重排序，<b>ready</b>发生在<b>number</b>之前，而且number值没同步</li>
	 * <li>读线程看不到主线程的修改变化, 一直循环</li>
	 * </ol>
	 * 
	 */
	public static void main(String[] args) {
		new ReaderThread().start();
		number = 42;
		ready = true;
	}
}
