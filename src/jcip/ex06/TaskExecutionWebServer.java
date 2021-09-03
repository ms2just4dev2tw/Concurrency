package jcip.ex06;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

/**
 * <h6>CodeList 6-4 TaskExecutionWebServer</h6>
 * <i>Web server using a thread pool</i>
 * <p>
 * 
 * @author Brian Goetz and Tim Peierls
 */
public class TaskExecutionWebServer {
	
	private static final int NTHREADS = 100;
	private static final Executor exec = Executors.newFixedThreadPool(NTHREADS);

	public static void main(String[] args) throws IOException {
		ServerSocket socket = new ServerSocket(80);
		while (true) {
			final Socket connection = socket.accept();
			Runnable task = new Runnable() {
				public void run() {
					handleRequest(connection);
				}
			};
			exec.execute(task);
		}
	}

	private static void handleRequest(Socket connection) {
		// request-handling logic here
	}
}
