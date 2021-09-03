package jcip.ex15;

import java.util.concurrent.atomic.*;

import net.jcip.annotations.ThreadSafe;

/**
 * <h6>CodeList 15-5 AtomicPseudoRandom</h6>
 * <i>Random number generator using AtomicInteger</i>
 * <p>
 * 
 * @author Brian Goetz and Tim Peierls
 */
@ThreadSafe
public class AtomicPseudoRandom extends PseudoRandom {
	
	private AtomicInteger seed;

	AtomicPseudoRandom(int seed) {
		this.seed = new AtomicInteger(seed);
	}

	public int nextInt(int n) {
		while (true) {
			int s = seed.get();
			int nextSeed = calculateNext(s);
			if (seed.compareAndSet(s, nextSeed)) {
				int remainder = s % n;
				return remainder > 0 ? remainder : remainder + n;
			}
		}
	}
}
