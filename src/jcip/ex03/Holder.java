package jcip.ex03;

/**
 * <h6>CodeList 3-15 Holder</h6> <i>Holder Class at risk of failure if not
 * properly published</i>
 * 
 * @author Brian Goetz and Tim Peierls
 */
public class Holder {

	// 可以添加final来提供一个初始化安全性
	private int n;

	/**
	 * 由于没有使用同步来确保Holder对象对其他线程可见，<br>
	 * 那么在未被正确发布的对象中存在两个问题: <br>
	 * <ol>
	 * <li>除发布对象的线程外，其他线程可能看见一个失效值(空引用或之前的旧值)</li>
	 * <li>某个线程看见的Holder引用的值是最新的，但Holder状态的值是失效的。<br>
	 * 尽管在构造函数中设置的域值似乎是第一次写入，但其实在Object的构造函数会在 子类的构造函数运行之前将默认值写入所有的域中。</li>
	 * </ol>
	 * <li>情况更加莫名的是，某个线程第一次读取域值是失效值，第二次读取是最新值， 这也是为什么assertSanity方法抛出异常的原因。</li>
	 */
	public Holder(int n) {
		this.n = n;
	}

	public void assertSanity() {
		if (n != n) {
			throw new AssertionError("This statement is false.");
		}
	}
}
