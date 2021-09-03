package jcip.ex04;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * <h6>CodeList 4-4 MonitorVehicleTracker</h6> <i>Monitor-based vehicle tracker
 * implementation</i>
 * <p>
 * 基于监视器模式的车辆追踪器
 * 
 * @see MutablePoint 车辆位置
 * @author Brian Goetz and Tim Peierls
 */
@ThreadSafe
public class MonitorVehicleTracker {

	/**
	 * 封装车辆的标识和位置
	 */
	@GuardedBy("this")
	private final Map<String, MutablePoint> locations;

	public MonitorVehicleTracker(Map<String, MutablePoint> locations) {
		this.locations = deepCopy(locations);
	}

	// ----------get/set需要封装到同一个锁中，这里是内置锁。
	public synchronized Map<String, MutablePoint> getLocations() {
		return deepCopy(locations);
	}

	public synchronized MutablePoint getLocation(String id) {
		MutablePoint loc = locations.get(id);
		return loc == null ? null : new MutablePoint(loc);
	}

	public synchronized void setLocation(String id, int x, int y) {
		MutablePoint loc = locations.get(id);
		if (loc == null)
			throw new IllegalArgumentException("No such ID: " + id);
		loc.x = x;
		loc.y = y;
	}

	/**
	 * deepCopy方法是私有的。<br>
	 * 访问该方法的路径不是封装到synchronized方法中 就是在构造方法中，且没有this用用逸出。可以不必添加synchronized关键字。
	 * <p>
	 * 
	 * @see DelegatingVehicleTracker#getLocationsAsStatic() result是拷贝构造函数
	 */
	private static Map<String, MutablePoint> deepCopy(Map<String, MutablePoint> m) {
		Map<String, MutablePoint> result = new HashMap<String, MutablePoint>();

		for (String id : m.keySet())
			result.put(id, new MutablePoint(m.get(id)));

		/**
		 * 返回指定Map的一个不可修改的视图，这里只保证容器对象不可修改， 并不能保证容器内的对象不可修改。
		 */
		return Collections.unmodifiableMap(result);
	}
}
