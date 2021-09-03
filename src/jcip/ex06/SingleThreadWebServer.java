package jcip.ex06;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * <h6>CodeList 6-1 SingleThreadWebServer</h6>
 * <i>Sequential web server</i>
 * <p>
 * 
 * @author Brian Goetz and Tim Peierls
 */
public class SingleThreadWebServer {
	
	public static void main(String[] args) throws IOException {
		ServerSocket socket = new ServerSocket(80);
		while (true) {
			Socket connection = socket.accept();
			handleRequest(connection);
		}
	}

	private static void handleRequest(Socket connection) {
		// request-handling logic here
	}
}
