package jcip.ex02;

import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicReference;

import javax.servlet.GenericServlet;
import javax.servlet.Servlet;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import net.jcip.annotations.NotThreadSafe;

/**
 * CodeList 2-5
 * <p>
 * UnsafeCachingFactorizer <i>Servlet that attempts to cache its last result
 * without adequate atomicity</i>
 * 
 * @see chapter_02.SynchronizedFactorizer 加锁机制 Synchronized
 * @author Brian Goetz and Tim Peierls
 */
@NotThreadSafe
public class UnsafeCachingFactorizer extends GenericServlet implements Servlet {

	/**
	 * @Field serialVersionUID: TODO()
	 */
	private static final long serialVersionUID = 1L;

	private final AtomicReference<BigInteger> lastNumber = new AtomicReference<BigInteger>();
	private final AtomicReference<BigInteger[]> lastFactors = new AtomicReference<BigInteger[]>();

	public void service(ServletRequest req, ServletResponse resp) {
		BigInteger i = extractFromRequest(req);
		if (i.equals(lastNumber.get()))
			encodeIntoResponse(resp, lastFactors.get());
		else {
			BigInteger[] factors = factor(i);
			/**
			 * lastNumber与lastFactors缓存的数值应该一样 在不同的线程中不能保证不变性, 尽管这是原子变量类
			 */
			lastNumber.set(i);
			lastFactors.set(factors);
			encodeIntoResponse(resp, factors);
		}
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
