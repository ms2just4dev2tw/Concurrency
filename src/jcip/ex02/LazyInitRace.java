package jcip.ex02;

import net.jcip.annotations.NotThreadSafe;

/**
 * <h6>CodeList 2-3 LazyInitRace</h6> <i>Race condition in lazy
 * initialization</i>
 * <p>
 * 单例模式中的延迟初始化竞态条件
 * 
 * @author Brian Goetz and Tim Peierls
 */
@NotThreadSafe
public class LazyInitRace {

	private ExpensiveObject instance = null;

	public ExpensiveObject getInstance() {
		if (instance == null)
			instance = new ExpensiveObject();
		return instance;
	}
}

class ExpensiveObject {
}
