package jcip.ex04;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * <h6>CodeList 4-9 VisualComponent</h6> <i>Delegating thread safety to multiple
 * underlying state variables</i>
 * <p>
 * 可以将线程安全性委托给多个状态变量, 只要它们之间彼此独立
 * 
 * @author Brian Goetz and Tim Peierls
 */
public class VisualComponent {

	/**
	 * CopyOnWriteArrayList是写时拷贝策略的List，<br>
	 * keyListeners是键盘监听器列表和mouseListeners是鼠标监听器列表
	 * <p>
	 * 两个状态变量之间不存在耦合关系
	 */
	private final List<KeyListener> keyListeners = new CopyOnWriteArrayList<KeyListener>();
	private final List<MouseListener> mouseListeners = new CopyOnWriteArrayList<MouseListener>();

	public void addKeyListener(KeyListener listener) {
		keyListeners.add(listener);
	}

	public void addMouseListener(MouseListener listener) {
		mouseListeners.add(listener);
	}

	public void removeKeyListener(KeyListener listener) {
		keyListeners.remove(listener);
	}

	public void removeMouseListener(MouseListener listener) {
		mouseListeners.remove(listener);
	}
}
