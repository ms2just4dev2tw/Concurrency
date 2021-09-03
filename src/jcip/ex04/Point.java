package jcip.ex04;

import net.jcip.annotations.Immutable;

/**
 * <h6>CodeList 4-6 Point</h6> <i>Immutable Point class used by
 * {@link DelegatingVehicleTracker}</i>
 * 
 * @see MutablePoint 可变的Point
 * @author Brian Goetz and Tim Peierls
 */
@Immutable
public class Point {

	/**
	 * 不可变的值可自由地共享与发布
	 */
	public final int x, y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
