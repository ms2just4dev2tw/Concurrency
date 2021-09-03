package jcip.ex10;

/**
 * <h6>CodeList 10-1 LeftRightDeadlock</h6>
 * <i>Simple lock-ordering deadlock</i>
 * <p>
 * 
 * @author Brian Goetz and Tim Peierls
 */
public class LeftRightDeadlock {

	private final Object left = new Object();
	private final Object right = new Object();

	public void leftRight() {
		synchronized (left) {
			synchronized (right) {
				doSomething();
			}
		}
	}

	public void rightLeft() {
		synchronized (right) {
			synchronized (left) {
				doSomethingElse();
			}
		}
	}

	void doSomething() {
	}

	void doSomethingElse() {
	}
}
