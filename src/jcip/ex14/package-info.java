/**
 * <h3>第14章</h3>
 * 
 * <h4>14.1 状态依赖性的管理</h4>
 * <h5>14.1.1 示例: 将前提条件的失败传递调用者</h5>
 * <h5>14.1.2 示例: 通过轮训与休眠来实现简单的阻塞</h5>
 * <h5>14.1.3 条件队列</h5>
 * 
 * <h4>14.2 使用条件队列</h4>
 * <h5>14.2.1 条件谓词</h5>
 * <h5>14.2.2 过早唤醒</h5>
 * <h5>14.2.3 丢失的信号</h5>
 * <h5>14.2.4 通知</h5>
 * <h5>14.2.5 示例: 阀门类</h5>
 * <h5>14.2.6 子类的安全问题</h5>
 * <h5>14.2.7 封装条件队列</h5>
 * <h5>14.2.8 入口协议与出口协议</h5>
 * 
 * <h4>14.3 显式的Condition对象</h4>
 * 
 * <h4>14.4 Synchronizer剖析</h4>
 * 
 * <h4>14.5 AbstractQueuedSynchronizer</h4>
 * 
 * <h4>14.6 java.util.concurrent同步器类中的AQS</h4>
 * <h5>14.6.1 ReentrantLock</h5>
 * <h5>14.6.2 Semaphore与CountDownLatch</h5>
 * <h5>14.6.3 FutureTask</h5>
 * <h5>14.6.4 ReentrantReadWriteLock</h5>
 */
package jcip.ex14;
