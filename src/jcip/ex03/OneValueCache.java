package jcip.ex03;

import java.math.BigInteger;
import java.util.Arrays;

import net.jcip.annotations.Immutable;

/**
 * <h6>CodeList 3-12 OneValueCache</h6> <i>Immutable holder for caching a number
 * and its factors</i>
 * <p>
 * 对结果进行缓存的不可变容器类
 * 
 * @author Brian Goetz and Tim Peierls
 */
@Immutable
public class OneValueCache {

	private final BigInteger lastNumber;
	private final BigInteger[] lastFactors;

	public OneValueCache(BigInteger i, BigInteger[] factors) {
		lastNumber = i;
		lastFactors = Arrays.copyOf(factors, factors.length);
	}

	public BigInteger[] getFactors(BigInteger i) {
		if (lastNumber == null || !lastNumber.equals(i))
			return null;
		else
			return Arrays.copyOf(lastFactors, lastFactors.length);
	}
}
