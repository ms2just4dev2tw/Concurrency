/**
 * <h2>同步的经典问题</h2>
 * 
 * <h3>生产者-消费者问题（The proceducer-consumer problem）</h3>
 * 在生产者和消费者之间设置一个缓冲区，生产者向缓冲区放入产品，消费者从缓冲区取出产品
 * <p>
 * 生产者和消费者都是以异步方式运行，但他们之间必须保持同步
 * <p>
 * 不允许生产者向<b>满的缓冲区放入产品</b>，也不允许从<b>空的缓冲区取出产品</b>
 * <p>
 * 解决方案
 * <ul>
 * 		<li>1，记录型信号变量</li>
 * 		<li>2，AND型信号变量</li>
 * 		<li>3，管程</li>
 * </ul>
 * 
 * <h3>Dijkstra 哲学家就餐问题</h3>
 * 一个圆桌上有五个哲学家和五根筷子，哲学家的任务就是思考和吃饭，哲学家左右两边都有一根筷子
 * <p>
 * 哲学家吃饭时需要<b>左右两边的两根筷子</b>
 * <p>
 * 解决方案
 * <ul>
 * 		<li>1，记录型信号变量</li>
 * 		<li>2，AND型信号变量</li>
 * </ul>
 * 
 * <h3>读者-写者问题</h3>
 * 一个数据文件可被多个进程共享，只要求读文件的进程称为读进程，其他进程为写进程
 * <p>
 * 允许<b>多个读进程访问文件</b>，但不允许<b>写进程与其他读或写进程同时访问文件</b>
 * <p>
 * 解决方案
 * <ul>
 * 		<li>1，记录型信号变量</li>
 * 		<li>2，信号量集机制</li>
 * </ul>
 */
package org.self;
