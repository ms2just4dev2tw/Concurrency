package org.self.base.thread;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;

/**
 * 管道输入/输出流
 * <p>
 * 管道流主要用于线程之间的数据传输，而传输的媒介为内存
 * <p>
 * 管道流必须绑定，不然会抛出 IO 异常
 * 
 * @author TungWang
 */
public class Piped {

	public static void main(String[] args) throws Exception {
		PipedWriter out = new PipedWriter();
		PipedReader in = new PipedReader();
		// 将输出流和输入流进行连接，否则在使用时会抛出IOException
		out.connect(in);
		// 通过 PipedReader 读取数据并打印出来
		Thread printThread = new Thread(new Print(in), "PrintThread");
		printThread.start();
		// 读取 main 线程的输入，通过 PipedWriter 写入数据
		int receive = 0;
		try {
			// 读取系统的输入，再通过 PipedWriter 将内容写到 PipedReader
			while ((receive = System.in.read()) != -1)
				out.write(receive);
		} finally {
			out.close();
		}
	}

	static class Print implements Runnable {
		private PipedReader in;

		public Print(PipedReader in) {
			this.in = in;
		}

		@Override
		public void run() {
			int receive = 0;
			try {
				// 从 PipedReader 读取到来自于 PipedWriter 的内容
				while ((receive = in.read()) != -1)
					System.out.print((char) receive);
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

}
