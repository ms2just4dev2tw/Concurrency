package jcip.ex03;

/**
 * <h6>CodeList 3-6 UnsafeStates</h6> <i>Allowing internal mutable state to
 * escape</i>
 * <p>
 * 逸出对象的非私有域的对象也会随之发布, 同时误用引用的风险始终存在。<br>
 * 这正是使用封装的主要原因:
 * <ol>
 * <li>使对程序的正确性分析变得可能</li>
 * <li>无意破坏设计约束条件变得困难</li>
 * </ol>
 * 
 * @author Brian Goetz and Tim Peierls
 */
class UnsafeStates {

	private String[] states = new String[] { "AK", "AL" /* ... */ };

	/**
	 * 外部代码可以随意修改states的内容
	 */
	public String[] getStates() {
		return states;
	}
}
