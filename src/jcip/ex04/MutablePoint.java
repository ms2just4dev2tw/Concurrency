package jcip.ex04;

import net.jcip.annotations.NotThreadSafe;

/**
 * <h6>CodeList 4-5 MutablePoint</h6> <i>Mutable Point class similar to
 * java.awt.Point</i>
 * <p>
 * 车辆位置
 * 
 * @see MonitorVehicleTracker 基于监视器模式的车辆追踪器
 * @author Brian Goetz and Tim Peierls
 */
@NotThreadSafe
public class MutablePoint {

	public int x, y;

	public MutablePoint() {
		x = 0;
		y = 0;
	}

	public MutablePoint(MutablePoint p) {
		this.x = p.x;
		this.y = p.y;
	}
}
