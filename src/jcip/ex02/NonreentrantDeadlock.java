package jcip.ex02;

/**
 * <h6>CodeList 2-7 NonreentrantDeadlock</h6> <i>Code that would deadlock if
 * intrinsic locks were not reentrant</i>
 * <p>
 * 重入锁: 获取锁的粒度是线程不是调用
 * 
 * @author Brian Goetz and Tim Peierls
 */
class Widget {

	/**
	 * 定义一个使用内置锁的接口
	 */
	public synchronized void doSomething() {
	}
}

class LoggingWidget extends Widget {

	/**
	 * 调用子类的doSomething()，会首先获取Widget上的锁。 <br>
	 * 当super.doSomething()时，如果内置锁是不可重入锁， <br>
	 * 线程执行到此处时会无法获得Widget上的锁，形成死锁。
	 */
	public synchronized void doSomething() {
		System.out.println(toString() + ": calling doSomething");
		super.doSomething();
	}
}
