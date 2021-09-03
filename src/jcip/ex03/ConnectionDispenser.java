package jcip.ex03;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * <h6>CodeList 3-10 ConnectionDispenser</h6> </i>ConnectionDispenser Using
 * ThreadLocal to ensure thread confinement</i>
 * <p>
 * 线程封闭-ThreadLocal:
 *
 * @author Brian Goetz and Tim Peierls
 */
public class ConnectionDispenser {

	static String DB_URL = "jdbc:mysql://localhost/mydatabase";

	private ThreadLocal<Connection> connectionHolder = new ThreadLocal<Connection>() {
		public Connection initialValue() {
			try {
				return DriverManager.getConnection(DB_URL);
			} catch (SQLException e) {
				throw new RuntimeException("Unable to acquire Connection, e");
			}
		};
	};

	public Connection getConnection() {
		return connectionHolder.get();
	}
}
