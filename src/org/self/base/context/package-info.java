/**
 * <h2>并发编程的背景</h2>
 * 
 * <h3>线程上下文切换的开销</h3>
 * 
 * <h3>死锁</h3>
 * 
 * <h3>资源的限制</h3>
 * 
 * <h3>CPU 的三级缓存</h3>
 * CPU 的 L1，L2 缓存都是位于 CPU 核中，但是 L3 级缓存在 CPU 核心外部
 * 
 * <h3>CPU 的缓存行</h3>
 * 缓存行一般来说是 64 字节，缓存行位于 L3 级缓存中
 * 
 * <h3>CPU 的超线程</h3>
 * 单核单线程是指 CPU 由一套运算单元，寄存器和 PC 计数器组成，而单核双线程有额外的寄存器 PC 计数器
 * 
 * <h3>CPU 的总线锁和缓存锁</h3>
 * 总线锁锁住总线后，其他处理器的请求会被阻塞。
 * 而缓存锁会锁住共享的缓存行，并通过缓存一致性协议，保证与其他处理器缓存的一致性
 * 
 * <h3>CPU 的缓存一致性协议</h3>
 * 缓存一致性协议有 MSI，MESI，MOSI；比较广泛的是 MESI 协议。
 * <p>
 * MESI 在每个缓存行前通过 2 bit 表示 4 种状态：
 * <ul>
 * 		<li>Modified (修改) <p>
 * 		处于该状态的 CPU 时刻监听其他 CPU 的读取操作，一旦监听到，将本 CPU 的缓存行写回
 * 		内存，并标记为 Shared</li>
 * 		<li>Exclusive (独占) <p>
 * 		处于该状态的 CPU 时刻监听其他 CPU 的读取操作，一旦监听到，将本 CPU 的
 * 		缓存行标记为 Shared</li>
 * 		<li>Shared (共享) <p>
 * 		处于该状态的 CPU 时刻监听其他 CPU 的使缓存行失效的指令，即其他 CPU 的写入操作，
 * 		一旦监听到，将本 CPU 的缓存行标记为 Invalid，其他 CPU 进入 Modified </li>
 * 		<li>Invalid (失效) <p>
 * 		当缓存行 处于 Invalid，从内存读取数据</li>
 * </ul>
 * 
 * <h3>CPU 的伪共享问题 false sharing </h3>
 * 
 */
package org.self.base.context;
