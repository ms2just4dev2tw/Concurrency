/**
 * <h2>JUC 的锁</h2>
 * 
 * <h3>Lock 锁接口</h3>
 * Lock 接口提供关键字 synchronized 所不具有的主要特性
 * <ul>
 * 		<li>lockInterruptibly() 在获取锁的过程中，如果</li>
 * 		<li>tryLock() 尝试非阻塞地获取锁</li>
 * 		<li>tryLock(long time, TimeUnit unit) </li>
 * 		<li>newCondition() 获取等待通知组件</li>
 * </ul>
 * {@link org.self.juc.lock.LockUseCase 使用锁的例子}
 * 
 * <h3>AbstractQueuedSynchronizer 队列同步器</h3>
 * 队列同步器是用来构建锁和其他同步组件的基础框架，它使用一个 int 成员变量表示同步状态，通过
 * 内置的 FIFO 队列来完成资源获取线程的排队工作。
 * <p>
 * 同步器简化了锁的实现方式，屏蔽了同步状态管理、线程的排队、等待与唤醒等底层操作。
 * <p>
 * 同步器的设计是基于模板方法模式，实现者可以根据需要重写模板方法。
 * <p>
 * 
 * 1，重写同步器指定的方法时，需要使用同步器提供的 3 个方法访问或修改同步状态
 * <ul>
 * 		<li>getState()，获取当前同步状态</li>
 * 		<li>setState()，设置当前同步状态</li>
 * 		<li>compareAndSetState(int expect, int update)，使用 CAS 保证状态设置的原子性</li>
 * </ul>
 * 
 * 2，同步器可以重写的方法
 * <ul>
 * 		<li>tryAcquire(int arg) 独占式获取同步状态。<p>
 * 		实现该方法需要查询当前状态并判断同步状态是否符合预期，然后进行 CAS 设置同步状态</li>
 * 		<li>tryRelease(int arg) 独占式释放同步状态</li>
 * 		<li>tryAcquireShared(int arg) 共享式获取同步状态，返回大于 0 的值，表示成功，反之失败</li>
 * 		<li>tryReleaseShared(int arg) 共享式释放同步状态</li>
 * 		<li>isHeldExclusively() 如果同步仅针对当前调用线程保持，则返回true。<p>
 * 		每次调用 AbstractQueuedSynchronizer.ConditionObject 方法时都会调用此方法</li>
 * </ul>
 * 
 * 3，同步器提供的模板方法基本上分为 3 类
 * <ul>
 * 		<li>独占式获取与释放同步状态</li>
 * 		<li>共享式获取与释放同步状态</li>
 * 		<li>查询同步队列中的等待线程情况</li>
 * </ul>
 * 同步器提供的模板方法
 * <ul>
 * 		<li>acquire(int arg) 独占式获取同步状态，当前线程获取到同步状态就返回，否则进入同步队列。<p>
 * 		这个方法会调用重写的 tryAcquire(int arg) 方法</li>
 * 		<li>acquireInterruptibly(int arg) 大体上与 acquire(int arg) 相同，但是这个方法可以响应中断</li>
 * 		<li>tryAcquireNanos(int arg, long nanos) 在 acquireInterruptibly(int arg) 的基础上加上了超时限制</li>
 * 		<li>acquireShared(int arg) 共享式地获取同步状态，允许同一时刻可以有多个线程获取同步状态 <p>
 * 		这个方法会调用重写的 tryAcquireShared(int arg) 方法</li>
 * 		<li>acquireSharedInterruptibly(int arg) 在 acquireShared(int arg) 的基础上添加了中断机制</li>
 * 		<li>tryAcquireSharedNanos(int arg, long nanos) 在 acquireSharedInterruptibly(int arg)  的
 * 		基础上添加了超时机制</li>
 * 		<li>release(int arg) 独占式释放同步状态，该方法会将同步队列的第一个节点包含的线程唤醒</li>
 * 		<li>releaseShared(int arg) 共享式释放同步状态</li>
 * 		<li>getQueuedThreads() 获取在同步队列上的线程集合</li>
 * </ul>
 * 
 * 4，队列同步器的实现
 * <p>
 * 4.1，同步队列
 * 
 * 4.2，独占式同步状态的获取与实现
 * 
 * 4.3，共享式同步状态的获取与释放
 * 
 * 4.4，获取同步状态的超时机制
 * 
 * <h3>ReentrantLock 重入锁</h3>
 * 重入锁表示支持对一个资源的重复加锁，除此之外还支持获取锁时的公平和非公平选择。
 * <p>
 * 对于 n 层递归方法有 n 次加锁和 n 次的解锁操作，
 * <p>
 * 
 * 公平锁
 * <p>
 * 锁的获取顺序应该符合请求的绝对时间顺序，ReentrantLock 的公平锁方式实现的 
 * tryAcquire(int acquires) 方法与公平锁只有一处不同。<p>有个 hasQueuedPredecessors() 方法
 * 检测同步队列中当前节点是否有前驱节点，如果返回 true，说明有线程更早请求获取锁，需要等待
 * 前驱线程获取并释放锁之后才能获取锁。
 * <p>
 * 
 * 非公平锁
 * <p>
 * 由于刚释放锁的线程再次获取到同步状态的概率会非常大，使用非公平锁可以明显地减少切换线程的开销
 * 
 * <h3>ReadWriteLock 读写锁</h3>
 * ReadWriteLock 的特性
 * <ul>
 * 		<li>支持非公平(默认)和公平的锁获取方式，在吞吐量方面非公平优于公平</li>
 * 		<li>支持锁的重进入，读线程获取读锁后能再次获取读锁，写线程获取写锁后能获取写锁或读锁</li>
 * 		<li>写锁能降级为读锁，遵循获取写锁、再获取读锁最后释放写锁的次序</li>
 * </ul>
 * 
 * 1，读写状态的设计
 * <p>
 * 读写锁的自定义同步器需要在同步状态(整型变量)上维护多个读线程和写线程的状态。读写锁的整型变量
 * 将高 16 位表示为读，低 16 位表示为写。
 * <p>
 * 
 * 2，写锁的获取与释放
 * <p>
 * 写锁除了重进入条件还得确保不存在读锁，锁得释放与 ReentrantLock 的释放一致
 * 
 * 3，读锁的获取与释放
 * <p>
 * 读锁的状态是所有的读线程获取读锁次数的总和，通过 getReadHoldCount() 返回当前读线程获取锁的次数。
 * <p>
 * 每个读线程将获取读锁的次数保存在 ThreadLocal 变量中。
 * <p>
 * 
 * 4，锁降级(写锁降级为读锁)
 * <p>
 * 锁降级是指在持有写锁的前提下，再获取读锁，随后释放持有的写锁的过程。
 * <p>
 * 不支持锁升级，多个读线程持有读锁的情况下任意一个读线程持有写锁的情况下，则
 * 获取写锁的读线程的更新对于其他线程是不可见的。
 * <p>
 * 
 * <h3>LockSupport 工具</h3>
 * LockSupport 提供了一组的公共静态方法，这些方法提供了最基本的线程阻塞和唤醒功能。
 * <p>
 * 当需要阻塞和唤醒一个线程时，使用 LockSupport 工具成为构建同步组件的基础工具。
 * <ul>
 * 		<li></li>
 * 		<li></li>
 * 		<li></li>
 * </ul>
 * 由于 Java 5 推出的 Lock 等并发工具在线程 dump 时无法提供阻塞对象的信息，因此在 Java 6 中
 * 提供以下 3 个包含阻塞对象的 park 方法
 * <ul>
 * 		<li></li>
 * 		<li></li>
 * 		<li></li>
 * </ul>
 * 
 * <h3>Condition 通知组件接口</h3>
 * 任何 Object 对象都有一组监视器方法，这些方法与 Synchronized 关键字结合可以实现等待/通知机制。
 * <p>
 * Condition 接口也提供了类似的方法，与 Lock 配合可以实现等待/通知模式。
 * <p>
 * 
 * 1，Object 的监视器方法与 Condition 接口的对比
 * <ul>
 * 		<li>前置条件 <p>
 * 		Object 获取对象的锁 <p>
 * 		Condition 先调用 Lock.lock() 获取锁，再调用 Lock.newCondition() 获取 Condition 对象</li>
 * 		<li>线程等待队列个数 <p>
 * 		Object 一个 <p>
 * 		Condition 多个 </li>
 * 		<li>当前线释放锁并进入等待状态 <p>
 * 		Object wait() <p>
 * 		Condition await() <p>
 * 		能从等待方法返回说明当前线程获取到了锁 </li>
 * 		<li>当前线释放锁并进入超时等待状态 <p>
 * 		Object wait(long timeoutMillis, int nanos) <p>
 * 		Condition <p>
 * 		Long awaitNanos(long nanosTimeout) 返回 nanosTimeout 减去从此方法返回所花费的时间<p>
 * 		boolean await(long time, TimeUnit unit) 超时返回 false </li>
 * 		<li>当前线释放锁并进入等待状态，在等待状态不响应中断 <p>
 * 		Object 不支持 <p>
 * 		Condition awaitUninterruptibly() </li>
 * 		<li>当前线释放锁并进入等待状态直到未来的某一时间点 <p>
 * 		Object 不支持 <p>
 * 		Condition awaitUntil(Date deadline) </li>
 * 		<li>唤醒等待队列中的一个线程 <p>
 * 		Object notify() <p>
 * 		Condition signal() </li>
 * 		<li>唤醒等待队列中的所有线程 <p>
 * 		Object notifyAll() <p>
 * 		Condition signalAll() </li>
 * </ul>
 * 
 * 2，Condition 的实现
 * <p>
 * 2.1，等待队列
 * <p>
 * 等待队列是一个 FIFO 队列，队列中的每个节点都包含一个线程引用，该线程是在 Condition 对象上
 * 等待的线程。<p>队列节点实际上是复用了同步器的节点 AbstractQueuedSynchronizer.Node。
 * <p>
 * 
 * 2.2，线程等待
 * <p>
 * 
 * 
 * 2.3，唤醒线程
 * <p>
 * 
 */
package org.self.juc.lock;
