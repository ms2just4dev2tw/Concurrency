package jcip.ex03;

/**
 * <h6>CodeList 3-7 ThisEscape</h6> <i>Implicitly allowing the this reference to
 * escape</i>
 * 
 * @see SafeListener 使用工厂方法防止this引用在构造过程中逸出
 * @author Brian Goetz and Tim Peierls
 */
public class ThisEscape {

	/**
	 * 当ThisEscape发布EventListener对象时，也隐含地发不了ThisEscape实例本身.<br>
	 * 因为在内部类的实例中也包含了ThisEscape的隐含引用。
	 */
	public ThisEscape(EventSource source) {
		source.registerListener(new EventListener() {
			public void onEvent(Event e) {
				doSomething(e);
			}
		});
	}

	void doSomething(Event e) {
	}

	interface EventSource {
		void registerListener(EventListener e);
	}

	interface EventListener {
		void onEvent(Event e);
	}

	interface Event {
	}
}
