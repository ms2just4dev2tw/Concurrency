package jcip.ex02;

import java.math.BigInteger;

import javax.servlet.GenericServlet;
import javax.servlet.Servlet;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * <h6>CodeList 2-6 SynchronizedFactorizer</h6> <i>Servlet that caches last
 * result, but with unnacceptably poor concurrency</i>
 * <p>
 * 内置锁(Synchronized)包括两部分: 作为锁的对象引用和锁保护的代码块
 * <li>同步代码块[synchronized(lock){}]</li>
 * <li>同步方法[锁是Class对象)]</li>
 * 
 * @see chapter_02.UnsafeCachingFactorizer 线程不安全的示例
 * @author Brian Goetz and Tim Peierls
 */
@ThreadSafe
public class SynchronizedFactorizer extends GenericServlet implements Servlet {

	/**
	 * @Field serialVersionUID: TODO()
	 */
	private static final long serialVersionUID = 1L;

	@GuardedBy("this")
	private BigInteger lastNumber;
	@GuardedBy("this")
	private BigInteger[] lastFactors;

	/**
	 * 存在性能问题, 只支持一个线程访问该方法, 太过于极端 性能改进的方案 {@link CachedFactorizer}
	 */
	public synchronized void service(ServletRequest req, ServletResponse resp) {
		BigInteger i = extractFromRequest(req);
		if (i.equals(lastNumber))
			encodeIntoResponse(resp, lastFactors);
		else {
			BigInteger[] factors = factor(i);
			lastNumber = i;
			lastFactors = factors;
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
