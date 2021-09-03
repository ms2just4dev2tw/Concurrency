package jcip.ex04;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import net.jcip.annotations.ThreadSafe;

/**
 * <h6>CodeList 4-12 PublishingVehicleTracker</h6> <i>Vehicle tracker that
 * safely publishes underlying state</i>
 * <p>
 * 将线程安全性委托给底层的ConcurrentHashMap 只是Map中的元素是线程安全并可变的SafePoint @{link SafePoint}
 * 
 * @author Brian Goetz and Tim Peierls
 */
@ThreadSafe
public class PublishingVehicleTracker {

	private final Map<String, SafePoint> locations;
	private final Map<String, SafePoint> unmodifiableMap;

	public PublishingVehicleTracker(Map<String, SafePoint> locations) {
		this.locations = new ConcurrentHashMap<String, SafePoint>(locations);
		this.unmodifiableMap = Collections.unmodifiableMap(this.locations);
	}

	/**
	 * unmodifiableMap实时带来的是好处还是坏处，取决于实际需求。
	 */
	public Map<String, SafePoint> getLocations() {
		return unmodifiableMap;
	}

	public SafePoint getLocation(String id) {
		return locations.get(id);
	}

	public void setLocation(String id, int x, int y) {
		if (!locations.containsKey(id))
			throw new IllegalArgumentException("invalid vehicle name: " + id);
		locations.get(id).set(x, y);
	}
}
