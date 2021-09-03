package jcip.ex06;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * <h6>CodeList 6-2 ThreadPerTaskWebServer</h6>
 * <i>Web server that starts a new thread for each request</i>
 * <p>
 * 
 * @author Brian Goetz and Tim Peierls
 */
public class ThreadPerTaskWebServer {
	
	public static void main(String[] args) throws IOException {
		ServerSocket socket = new ServerSocket(80);
		while (true) {
			final Socket connection = socket.accept();
			Runnable task = new Runnable() {
				public void run() {
					handleRequest(connection);
				}
			};
			new Thread(task).start();
		}
	}

	private static void handleRequest(Socket connection) {
		// request-handling logic here
	}
}
