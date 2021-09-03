/**
 * <h2>JUC 的 Executor 框架</h2>
 * Java 的线程即是工作单元，也是执行单元。从 JDK 5 开始，把工作单元与执行机制分离。
 * <ul>
 * 		<li>工作单元，Runnable 和 Callable</li>
 * 		<li>执行机制由 Executor 框架提供</li>
 * </ul>
 * 
 * <h3>Executor 框架简介</h3>
 * 1，Executor 的两级调度模型
 * 
 * 2，Executor 框架的架构
 * <ul>
 * 		<li>任务，执行任务需要实现 Runnable 和 Callable 接口</li>
 * 		<li>任务的执行，执行机制的核心接口 Executor，以及继承 Executor 的接口 ExecutorService <p>
 * 		ExecutorService 有两个关键实现类 ThreadPoolExecutor 和 ScheduledThreadPoolExecutor</li>
 * 		<li>异步计算的结果，Future 接口和实现 Future 的 FutureTask</li>
 * </ul>
 * 
 * <h3></h3>
 * 
 * <h3>ThreadPoolExecutor 详解</h3>
 * 通过工具类 Executors 可以创建 3 种类型的 ThreadPoolExecutor
 * 
 * 1，FixedThreadPool
 * 
 * 2，SingleThreadPool
 * 
 * 3，CachedThreadPool
 * 
 * <h3>ScheduledThreadPoolExecutor 详解</h3>
 * 它的功能类似于 {@link java.util.Timer}，但是 ScheduledThreadPoolExecutor 功能更强，更灵活
 * 
 * <h3>Future 接口和 FutureTask类代表异步计算的结果</h3>
 * 
 */
package org.self.juc.executor;
