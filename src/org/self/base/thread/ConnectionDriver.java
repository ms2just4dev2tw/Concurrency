package org.self.base.thread;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.util.concurrent.TimeUnit;

/**
 * 通过动态代理构造 Connection
 * 
 * @author TungWang
 */
public class ConnectionDriver {

	// 创建一个Connection的代理
	public static final Connection createConnection() {
		return (Connection) Proxy.newProxyInstance(ConnectionDriver.class.getClassLoader(),
				new Class<?>[] { Connection.class }, new ConnectionHandler());
	}

	/**
	 * 代理类
	 * 
	 * @author TungWang
	 */
	static class ConnectionHandler implements InvocationHandler {
		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			// 如果是 commit 操作，就休眠 100 毫秒
			if (method.getName().equals("commit"))
				TimeUnit.MILLISECONDS.sleep(100);
			// 操作返回结果为 null
			return null;
		}
	}

}
