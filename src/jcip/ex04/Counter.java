package jcip.ex04;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * <h6>CodeList 4-1 Counter</h6> <i>Simple thread-safe counter using the Java
 * monitor pattern</i>
 * <p>
 * 
 * 消费者(线程)使用这个房间，首先他必须到一个大厅(EntrySet)等待，<br>
 * 调度程序 将基于某些标准(FIFO)从大厅中选择一个消费者(线程)进入特殊房间(SpecialRoom)，<br>
 * 如果这个线程因为某些原因被“挂起”，它将被调度程序安排到等待房间(WaitRoom)，<br>
 * 并且一段时间之后会被重新分配到特殊房间。
 * <p>
 * 监视器用来监视线程进入这个特别房间， 他确保同一时间只能有一个线程可以访问特殊房间中的数据和代码。
 * 
 * @see http://ifeve.com/monitors-java-synchronization-mechanism/ Java监视器模式
 * @author Brian Goetz and Tim Peierls
 */
@ThreadSafe
public final class Counter {

	@GuardedBy("this")
	private long value = 0;

	public synchronized long getValue() {
		return value;
	}

	public synchronized long increment() {
		if (value == Long.MAX_VALUE)
			throw new IllegalStateException("counter overflow");
		return ++value;
	}
}
