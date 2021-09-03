/**
 * <h2>并发编程的底层实现</h2>
 * Java 中使用的并发机制是依赖于 JVM 的实现和 CPU 提供的指令
 * 
 * <h3>1，volatile 的实现原理与应用</h3>
 * volatile 是轻量级的 synchronized，保证对共享变量的可见性。
 * <p>
 * 可见性是指当一个线程修改了共享变量后，其他线程能读到这个修改的值。
 * <p>
 * volatile 只具有可见性，在并发中对于共享变量的修改应该通过排他锁单独获取。
 * 
 * <h3>2，synchronized 的原理与应用</h3>
 * synchronized 在 Java 1.6 之后引入了偏向锁和轻量级锁
 * <ul>
 * 		<li>1，偏向锁</li>
 * 		<li>2，轻量级锁</li>
 * 		<li>2，重量级锁</li>
 * </ul>
 * 
 * <h3>3，原子操作的原理与应用</h3>
 * 处理器实现原子操作的方式
 * <ul>
 * 		<li>1，使用总线锁</li>
 * 		<li>2，使用缓存锁</li>
 * </ul>
 * JVM 实现原子操作的方式
 * <ul>
 * 		<li>1，使用循环CAS</li>
 * 		<li>2，使用锁机制</li>
 * </ul>
 */
package org.self.base.facilities;
