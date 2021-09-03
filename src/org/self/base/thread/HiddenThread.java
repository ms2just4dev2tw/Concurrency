package org.self.base.thread;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * 普通的 Java 程序会使用那些线程
 * <p>
 * main 用户程序入口
 * <p>
 * Reference Handler 清楚 Reference 的线程
 * <p>
 * Finalizer 调用对象的 finalizer 方法的线程
 * <p>
 * Signal Dispatcher 分发处理发送给 JVM 信号的线程
 * 
 * @author TungWang
 */
public class HiddenThread {

	public void showDetail() {
		// 获取 Java 线程管理MXBean
		ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
		// 不需要获取同步的 monitor 和 synchronizer 信息，仅获取线程和线程堆栈信息
		ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);
		// 遍历线程信息，仅打印线程ID和线程名称信息
		for (ThreadInfo threadInfo : threadInfos)
			System.out.println("[" + threadInfo.getThreadId() + "] " + threadInfo.getThreadName());
	}

}
