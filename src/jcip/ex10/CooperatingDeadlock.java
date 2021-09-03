package jcip.ex10;

import java.util.*;

import jcip.ex04.Point;
import net.jcip.annotations.GuardedBy;

/**
 * <h6>CodeList 10-5 CooperatingDeadlock</h6>
 * <i>Lock-ordering deadlock between cooperating objects</i>
 * <p>
 * 
 * @author Brian Goetz and Tim Peierls
 */
public class CooperatingDeadlock {
	
	// Warning: deadlock-prone!
	class Taxi {
		@GuardedBy("this")
		private Point location, destination;
		private final Dispatcher dispatcher;

		public Taxi(Dispatcher dispatcher) {
			this.dispatcher = dispatcher;
		}

		public synchronized Point getLocation() {
			return location;
		}

		public synchronized void setLocation(Point location) {
			this.location = location;
			if (location.equals(destination))
				dispatcher.notifyAvailable(this);
		}

		public synchronized Point getDestination() {
			return destination;
		}

		public synchronized void setDestination(Point destination) {
			this.destination = destination;
		}
	}

	class Dispatcher {
		@GuardedBy("this")
		private final Set<Taxi> taxis;
		@GuardedBy("this")
		private final Set<Taxi> availableTaxis;

		public Dispatcher() {
			taxis = new HashSet<Taxi>();
			availableTaxis = new HashSet<Taxi>();
		}

		public synchronized void notifyAvailable(Taxi taxi) {
			availableTaxis.add(taxi);
		}

		public synchronized Image getImage() {
			Image image = new Image();
			for (Taxi t : taxis)
				image.drawMarker(t.getLocation());
			return image;
		}
	}

	class Image {
		public void drawMarker(Point p) {
		}
	}
}
