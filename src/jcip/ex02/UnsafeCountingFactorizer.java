package jcip.ex02;

import java.math.BigInteger;

import javax.servlet.GenericServlet;
import javax.servlet.Servlet;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import net.jcip.annotations.NotThreadSafe;

/**
 * <h6>CodeList 2-2 UnsafeCountingFactorizers</h6> <i>Servlet that counts
 * requests without the necessary synchronization</i>
 * <p>
 * 竞态条件: 不恰当的执行时序导致不正确的结果
 * 
 * @see jcip.ex02.CountingFactorizer 复合操作
 * @author Brian Goetz and Tim Peierls
 */
@NotThreadSafe
public class UnsafeCountingFactorizer extends GenericServlet implements Servlet {

	/**
	 * @Field serialVersionUID: TODO()
	 */
	private static final long serialVersionUID = 1L;

	private long count = 0;

	public long getCount() {
		return count;
	}

	public void service(ServletRequest req, ServletResponse resp) {
		BigInteger i = extractFromRequest(req);
		BigInteger[] factors = factor(i);
		/**
		 * 原子性问题 ++count; 该操作涉及三个操作：读取-修改-写入 不是原子操作，是非线程安全
		 */
		++count;
		encodeIntoResponse(resp, factors);
	}

	void encodeIntoResponse(ServletResponse res, BigInteger[] factors) {
	}

	BigInteger extractFromRequest(ServletRequest req) {
		return new BigInteger("7");
	}

	BigInteger[] factor(BigInteger i) {
		// Doesn't really factor
		return new BigInteger[] { i };
	}
}
