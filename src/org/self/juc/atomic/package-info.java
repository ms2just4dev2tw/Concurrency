/**
 * <h2>JUC 的原子操作类</h2>
 * 
 * <h3>1，原子更新基本类型</h3>
 * <ul>
 * 		<li>{@link java.util.concurrent.atomic.AtomicBoolean 更新布尔类}</li>
 * 		<li>{@link java.util.concurrent.atomic.AtomicInteger 更新整型}</li>
 * 		<li>{@link java.util.concurrent.atomic.AtomicLong 更新长整型}</li>
 * </ul>
 * 
 * <h3>2，原子更新数组</h3>
 * <ul>
 * 		<li>{@link java.util.concurrent.atomic.AtomicIntegerArray 更新整型数组的元素}</li>
 * 		<li>{@link java.util.concurrent.atomic.AtomicLongArray 更新长整型数组的元素}</li>
 * 		<li>{@link java.util.concurrent.atomic.AtomicReferenceArray 更新引用数组的元素}</li>
 * </ul>
 * 
 * <h3>3，原子更新引用类型</h3>
 * <ul>
 * 		<li>{@link java.util.concurrent.atomic.AtomicReference 更新引用类型}</li>
 * 		<li>{@link java.util.concurrent.atomic.AtomicLongFieldUpdater 更新引用类型里的字段}</li>
 * 		<li>{@link java.util.concurrent.atomic.AtomicStampedReference 更新带标记位的引用}</li>
 * </ul>
 * 
 * <h3>4，原子更新字段类</h3>
 * <ul>
 * 		<li>{@link java.util.concurrent.atomic.AtomicIntegerFieldUpdater 更新整型字段}</li>
 * 		<li>{@link java.util.concurrent.atomic.AtomicLongFieldUpdater 更新长整型字段}</li>
 * 		<li>{@link java.util.concurrent.atomic.AtomicStampedReference 更新带版本号的引用}</li>
 * </ul>
 */
package org.self.juc.atomic;
