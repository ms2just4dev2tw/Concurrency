package jcip.ex03;

import java.math.BigInteger;

import javax.servlet.GenericServlet;
import javax.servlet.Servlet;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import net.jcip.annotations.ThreadSafe;

/**
 * <h6>CodeList 3-13 VolatileCachedFactorizer</h6> <i>Caching the last result
 * using a volatile reference to an immutable holder object</i>
 * <p>
 * 使用volatile发布不可变容器
 * 
 * @author Brian Goetz and Tim Peierls
 */
@ThreadSafe
public class VolatileCachedFactorizer extends GenericServlet implements Servlet {

	private static final long serialVersionUID = 1L;

	/**
	 * 当某个线程将cache设置为引用一个新的OneValueCache时，<br>
	 * 其他持有对象引用的线程能马上看到缓存数据。
	 */
	private volatile OneValueCache cache = new OneValueCache(null, null);

	public void service(ServletRequest req, ServletResponse resp) {
		BigInteger i = extractFromRequest(req);
		BigInteger[] factors = cache.getFactors(i);
		if (factors == null) {
			factors = factor(i);
			cache = new OneValueCache(i, factors);
		}
		encodeIntoResponse(resp, factors);
	}

	void encodeIntoResponse(ServletResponse resp, BigInteger[] factors) {
	}

	BigInteger extractFromRequest(ServletRequest req) {
		return new BigInteger("7");
	}

	BigInteger[] factor(BigInteger i) {
		// Doesn't really factor
		return new BigInteger[] { i };
	}
}
