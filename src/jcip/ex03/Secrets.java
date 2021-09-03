package jcip.ex03;

import java.util.HashSet;
import java.util.Set;

/**
 * <h6>CodeList 3-5 Secrets</h6> <i>Publishing an object</i>
 * <p>
 * 发布: 使对象能够在当前作用域之外的代码中使用
 * 
 * @author Brian Goetz and Tim Peierls
 */
class Secrets {

	public static Set<Secret> knownSecrets;

	/**
	 * 一个HashSet对象赋值给knownSecrets，使得对象发布<br>
	 * 发布某个对象时，可能会间接地发布其他对象。
	 * <p>
	 * 在knownSecrets中添加Secret对象时，Secret对象也等同被发布。
	 */
	public void initialize() {
		knownSecrets = new HashSet<Secret>();
	}
}

class Secret {
}
