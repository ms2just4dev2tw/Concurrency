/**
 * <h2>多线程并发基础</h2>
 * 
 * <h3>1，隐藏的线程</h3>
 * {@link org.self.base.thread.HiddenThread 一个普通的 Java 程序隐藏了那些线程}
 * 
 * <h3>2，线程的优先级</h3>
 * {@link org.self.base.thread.ThreadPriority OS 会无视 Java 对线程优先级的设置}
 * 
 * <h3>3，线程的状态</h3>
 * {@link org.self.base.thread.ThreadState 线程在生命周期内拥有的状态}
 * 
 * <h3>4，Daemon线程</h3>
 * {@link org.self.base.thread.DaemonThread 支持线程}
 * 
 * <h3>5，线程的过期方法</h3>
 * {@link org.self.base.thread.DeprecatedMethod 过期且危险的方法} 
 * 
 * <h3>6，Synchronized</h3>
 * Synchronized 关键字用于修饰方法和同步块，本质是对一个对象的监视器进行获取。<p>
 * 这个获取过程是排他的，只能有一个线程获取到 Synchronized 所保护对象的监视器，
 * 其他线程会进入同步队列，线程状态变为 BLOCKED。<p>
 * 当获取监视器的线程进行释放操作后会唤醒阻塞在同步队列的线程，使它们重新竞争。<p>
 * 
 * <h3>6，Java 的等待/通知机制</h3>
 * 问题:：生产者消费者的思考 <p>
 * 一个线程修改了线程的值，另一个线程需要感知到了变化，然后进行相应的动作。<p>
 * 在这个过程中存在的矛盾性
 * <ul>
 * 		<li>1，确保及时性 <p>
 * 		生产者产出商品的瞬间消费者需要立马感应到变化</li>
 * 		<li>2，降低 CPU 消耗 <p>
 * 		如果消费者不停地访问临界条件，会造成 CPU 资源的浪费</li>
 * </ul>
 * 我们可以通过 Java 内置的{@link org.self.base.thread.WaitNotify 等待/通知机制}解决这个矛盾
 * 
 * <h3>6，线程间的数据传输</h3>
 * {@link org.self.base.thread.Piped 管道流}
 * 
 * <h3>7，线程的本地变量 ThreadLocal</h3>
 * {@link }
 * 
 * <h3>8，线程池技术</h3>
 * {@link org.self.base.thread.ConnectionPool 数据库连接池}
 * {@link org.self.base.thread.DefaultThreadPool 服务器的任务线程池}
 * {@link org.self.base.thread.SimpleHttpServer 一个简单的 Http 服务器}
 */
package org.self.base.thread;
