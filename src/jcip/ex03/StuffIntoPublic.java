package jcip.ex03;

/**
 * <h6>CodeList 3-14 StuffIntoPublic</h6> <i>Unsafe publication</i>
 *
 * @author Brian Goetz and Tim Peierls
 */
public class StuffIntoPublic {

	/**
	 * 由于可见性的缘故，其他线程看到的Holder对象会处于不一致的情况。<br>
	 * 即使在构造函数中正确地构建了不变性条件，<br>
	 * 这种不正确的发布使得其他线程看见为创建的对象
	 * <p>
	 */
	public Holder holder;

	public void initialize() {
		holder = new Holder(42);
	}
}
