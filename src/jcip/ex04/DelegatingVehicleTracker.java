package jcip.ex04;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import net.jcip.annotations.ThreadSafe;

/**
 * <h6>CodeList 4-7|4-8 DelegatingVehicleTracker</h6> <i>Delegating thread
 * safety to a ConcurrentHashMap</i>
 * <p>
 * 
 * 程序中没有使用任何显式的同步, 将线程安全委托给ConcurrentHashMap， 由于unmodifiableMap中所有的键和值是不可变的
 * 
 * @author Brian Goetz and Tim Peierls
 */
@ThreadSafe
public class DelegatingVehicleTracker {

	private final ConcurrentMap<String, Point> locations;

	private final Map<String, Point> unmodifiableMap;

	public DelegatingVehicleTracker(Map<String, Point> points) {
		locations = new ConcurrentHashMap<String, Point>(points);
		unmodifiableMap = Collections.unmodifiableMap(locations);
	}

	/**
	 * 返回一个不可修改但实时的车辆位置视图，这样做的好处是数据的更新。<br>
	 * 这里是浅拷贝(Shallow Copy)，同样也存在不一致的车辆视图的缺点。
	 */
	public Map<String, Point> getLocations() {
		return unmodifiableMap;
	}

	public Point getLocation(String id) {
		return locations.get(id);
	}

	public void setLocation(String id, int x, int y) {
		if (locations.replace(id, new Point(x, y)) == null)
			throw new IllegalArgumentException("invalid vehicle name: " + id);
	}

	/**
	 * <h6>CodeList 4-8</h6> <i>Alternate version of getLocations (Listing 4.8)</i>
	 * <p>
	 * locations的拷贝构造，由于Point的所有状态是不可变的。
	 * 
	 * @author Brian Goetz and Tim Peierls
	 * @see MonitorVehicleTracker#deepCopy() locations深度复制
	 */
	public Map<String, Point> getLocationsAsStatic() {
		return Collections.unmodifiableMap(new HashMap<String, Point>(locations));
	}
}
