/**
 * <h2>JUC 的并发容器</h2>
 * 
 * <h3>并发集合</h3>
 * 并发散列表
 * {@link java.util.concurrent.ConcurrentHashMap} 对标 HashMap
 * 
 * {@link java.util.concurrent.ConcurrentSkipListMap} 对标 TreeMap 
 * {@link java.util.concurrent.ConcurrentSkipListSet} 对标 TreeSet
 * 
 * 并发队列
 * {@link java.util.concurrent.ConcurrentLinkedQueue}
 * {@link java.util.concurrent.ConcurrentLinkedDeque}
 * 
 * 
 * {@link java.util.concurrent.CopyOnWriteArrayList} 对标 ArrayList
 * {@link java.util.concurrent.CopyOnWriteArraySet}
 * 
 * <p>
 * 
 * <h3>阻塞队列</h3>
 * Java 的{@link java.util.concurrent.BlockingQueue 阻塞队列}支持两种附加操作
 * <ul>
 * 		<li>1，支持阻塞的插入方法：当队列满时，队列会阻塞插入的线程，直到队列不满</li>
 * 		<li>2，支持阻塞的移除方法：当队空时，获得队列元素的线程会阻塞到队列非空</li>
 * </ul>
 * 阻塞队列常用于生产者和消费者的场景，另外阻塞队列为这两个操作提供了 4 种处理方式
 * <ul>
 * 		<li>插入方法：add(e)[抛出异常] offer(e)[返回特殊值] put(e)[一直阻塞] offer(e, time, unit)[超时退出]</li>
 * 		<li>移除方法：remove()[抛出异常] poll()[返回特殊值] take()[一直阻塞] poll(time, unit)[超时退出]</li>
 * 		<li>检查方法：element()[抛出异常] peek()[返回特殊值]</li>
 * </ul>
 * 处理方式详解
 * <ul>
 * 		<li>抛出异常：当队满时，如果线程再插入元素，会抛出 IllegalStateException 异常 <p>
 * 		当队空时，如果线程再获取元素，会抛出 NoSuchElementException 异常</li>
 * 		<li>返回特殊值：插入元素时，如果插入成功，返回 true，否则返回 false <p>
 * 		移除元素时，如果获取元素成功，返回获取到的元素，否则返回 null</li>
 * 		<li>一直阻塞：插入元素或获取元素时，如果条件不符合，就一直阻塞到条件满足</li>
 * 		<li>超时退出：在一直阻塞的基础上加上了超时条件</li>
 * </ul>
 * Java 的阻塞队列
 * <ul>
 * 		<li>{@link java.util.concurrent.ArrayBlockingQueue 数组构成的有界阻塞队列}</li>
 * 		<li>{@link java.util.concurrent.LinkedBlockingQueue 链表构成的有界阻塞队列}</li>
 * 		<li>{@link java.util.concurrent.LinkedTransferQueue 链表组成的无界阻塞队列}</li>
 * 		<li>{@link java.util.concurrent.LinkedBlockingDeque 链表组成的双向阻塞队列}</li>
 * 		<li>{@link java.util.concurrent.PriorityBlockingQueue 支持优先级排序的无界阻塞队列}</li>
 * 		<li>{@link java.util.concurrent.DelayQueue 使用优先级队列实现的无界阻塞队列}</li>
 * 		<li>{@link java.util.concurrent.SynchronousQueue 不存储元素的阻塞队列}</li>
 * </ul>
 * 
 * <h3>Fork/Join 框架</h3>
 * 这个框架是 Java 7 提供的一个并行执行任务的框架。具体的是将一个大任务分解成若干个小任务，
 * 这若干个子任务可以交由多处理器并行执行任务而不会相互影响，最终合并所有子任务的结果。
 * <p>
 * 
 * 1，work-stealing (工作窃取算法)
 * <p>
 * 将一个非常大的任务分解成若干个互相不依赖的子任务。每个子任务都会有一个执行线程，并且子任务
 * 要执行的任务都会放在一个双端队列中。<p>
 * 这写子任务线程会从队列首部拿取任务，先完成自己任务的子线程可以从其他线程的队列尾部拿取任务。
 * <p>
 * 
 * 2，框架设计
 * <p>
 * {@link java.util.concurrent.ForkJoinTask ForkJoin 任务}
 * 提供在任务中执行 fork 和 join 的操作机制，它有两个子类便于继承
 * <ul>
 * 		<li>{@link java.util.concurrent.RecursiveAction 没有返回结果的任务}</li>
 * 		<li>{@link java.util.concurrent.RecursiveTask 有返回结果的任务}</li>
 * </ul>
 * {@link java.util.concurrent.ForkJoinPool ForkJoin 任务需要通过 ForkJoinPool 来执行}
 * <p>
 * 
 */
package org.self.juc.collection;
