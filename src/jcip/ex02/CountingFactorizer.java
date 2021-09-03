package jcip.ex02;

import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicLong;

import javax.servlet.GenericServlet;
import javax.servlet.Servlet;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import net.jcip.annotations.ThreadSafe;

/**
 * <h6>CodeList 2-4 CountingFactorizer</h6> <i>Servlet that counts requests
 * using AtomicLong</i>
 * 
 * @see UnsafeCountingFactorizer 线程不安全的示例
 * @author Brian Goetz and Tim Peierls
 */
@ThreadSafe
public class CountingFactorizer extends GenericServlet implements Servlet {

	/**
	 * @Field serialVersionUID: TODO()
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 原子变量类，这里修饰为final是为了
	 * <ol>
	 * <li>count上的更新操作对于所有的线程的可见性</li>
	 * <li>确保count上不存在竞态条件</li>
	 * </ol>
	 */
	private final AtomicLong count = new AtomicLong(0);

	public long getCount() {
		return count.get();
	}

	public void service(ServletRequest req, ServletResponse resp) {
		BigInteger i = extractFromRequest(req);
		BigInteger[] factors = factor(i);
		// 对象的复合操作
		count.incrementAndGet();
		encodeIntoResponse(resp, factors);
	}

	void encodeIntoResponse(ServletResponse res, BigInteger[] factors) {
	}

	BigInteger extractFromRequest(ServletRequest req) {
		return null;
	}

	BigInteger[] factor(BigInteger i) {
		return null;
	}
}
