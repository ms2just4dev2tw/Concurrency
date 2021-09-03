package jcip.ex03;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import net.jcip.annotations.Immutable;

/**
 * <h6>CodeList 3-11 ThreeStooges</h6> <i>Immutable class built out of mutable
 * underlying objects, demonstration of candidate for lock elision</i>
 * 
 * 当满足以下条件时，对象才是不可变。
 * <ul>
 * <li>对象创建以后状态不能改变</li>
 * <li>对象的所有域都是final类型</li>
 * <li>对象是正常创建的(在对象创建期间，this引用没有逸出)</li>
 * </ul>
 * 
 * @author Brian Goetz and Tim Peierls
 */
@Immutable
public final class ThreeStooges {

	// 不可变的对象[引用]
	private final Set<String> stooges = new HashSet<String>();

	public ThreeStooges() {
		stooges.add("Moe");
		stooges.add("Larry");
		stooges.add("Curly");
	}

	public boolean isStooge(String name) {
		return stooges.contains(name);
	}

	public String getStoogeNames() {
		List<String> stooges = new Vector<String>();
		stooges.add("Moe");
		stooges.add("Larry");
		stooges.add("Curly");
		return stooges.toString();
	}
}
