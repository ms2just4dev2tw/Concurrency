package taojcp.ex07;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 
 * @author tengfei.fangtf
 * @version $Id: AtomicReferenceTest.java, v 0.1 2015-8-1 上午12:05:14
 *          tengfei.fangtf Exp $
 */
public class AtomicReferenceTest {

	public static AtomicReference<User> atomicUserRef = new AtomicReference<User>();

	public static void main(String[] args) {
		User user = new User("conan", 15);
		atomicUserRef.set(user);
		User updateUser = new User("Shinichi", 17);
		atomicUserRef.compareAndSet(user, updateUser);
		System.out.println(atomicUserRef.get().getName());
		System.out.println(atomicUserRef.get().getOld());
	}

	public static class User {
		private String name;
		private int old;

		public User(String name, int old) {
			this.name = name;
			this.old = old;
		}

		public String getName() {
			return name;
		}

		public int getOld() {
			return old;
		}
	}
}
