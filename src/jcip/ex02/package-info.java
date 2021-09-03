/**
 * <h3>第2章 线程安全性</h3>
 * 
 * <h4>2.1 线程安全</h4> 线程安全的定义中，最为核心的是正确性，某个类的行为与其规范一致。
 * <p>
 * {@link StatelessFactorizer 无状态的Servlet}
 * 
 * <h4>2.2 原子性</h4> 当我们在无状态对象中增加一个状态 {@link UnsafeCountingFactorizer 非同步计数器}
 * <h5>2.2.1 竞态条件</h5> 最常见的竞态条件是（Check-Then-Act）先检查或执行。
 * <h5>2.2.2 延迟初始化中的竞态条件</h5> 先检查或执行的一种常见情况是{@link LazyInitRace 延迟初始化}
 * <h5>2.2.3 复合操作</h5> {@link CountingFactorizer 原子类型}包含了复合操作.
 * 
 * <h4>2.3 加锁机制</h4> Servlet中一个状态变量可以通过线程安全的对象来管理，
 * 如果有个{@link UnsafeCachingFactorizer 多个状态变量实例}
 * <h5>2.3.1 内置锁</h5> 内置锁机制来支持原子性 {@link SynchronizedFactorizer 同步实例}
 * <h5>2.3.2 重入</h5> 内置锁是可重入的。<br>
 * 重入获取锁的粒度是{@link LoggingWidget 线程}而不是像(POSIX线程)的调用。
 * 
 * <h4>2.4 用锁来保护状态</h4>
 * <li>对于可能被多个线程同时访问的可变状态变量，在访问它时都需要持有同一锁</li>
 * <li>每个共享的和可变的变量都应该只有一个锁来保护</li>
 * <li>对于涉及到多个变量的不变性条件，都应该由同一个锁保护</li>
 * 
 * <h4>2.5 活跃性与性能</h4>
 * <li>同步策略需平衡各种需求(安全性，简单性，性能)，安全性是必须满足的。</li>
 * <li>不要盲目地为了性能牺牲简单性，这会破坏安全性</li>
 * <li>当执行较长的计算或无法快速完成的操作(网络I/O或控制台I/O)，不要持有锁</li> {@link CachedFactorizer
 * 改进后的同步实例}
 * 
 */
package jcip.ex02;
