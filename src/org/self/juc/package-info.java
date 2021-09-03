/**
 * <h2>JUC 的分层结构</h2>
 * 
 * <h3>第1层、线程间的通信</h3>
 * 这两个部分可以实现线程间的通信
 * <ul>
 * 		<li>volatile 变量</li>
 * 		<li>CAS(compareAndSet 方法) 构成的内存语义</li>
 * </ul>
 * 
 * <h3>第2层、基础类</h3>
 * 这三个部分组成基础结构
 * <ul>
 * 		<li>AQS(AbstractQueuedSynchronizer) </li>
 * 		<li>非阻塞数据结构</li>
 * 		<li>原子变量类</li>
 * </ul>
 * 
 * <h3>第3层、高层类</h3>
 * 这五个部分构成高层框架
 * <ul>
 * 		<li>Lock 锁</li>
 * 		<li>同步器</li>
 * 		<li>阻塞队列</li>
 * 		<li>Executor</li>
 * 		<li>并发容器</li>
 * </ul>
 * 
 */
package org.self.juc;
