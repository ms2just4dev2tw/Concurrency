package jcip.ex03;

/**
 * <h6>CodeList 3-4 CountingSheep</h6>
 * 
 * <b>调试提示</b>启动JVM时指定<br>
 * -server命令行选项(部署环境)，<br>
 * server模式的JVM会比client(开发环境)模式的JVM更多的优化。<br>
 * 本例中asleep如果忘记声明为volatile, <br>
 * server模式中会将asleep提升到循环体外部, 导致无限循环
 * <p>
 * 
 * @author Brian Goetz and Tim Peierls
 */
public class CountingSheep {

	volatile boolean asleep;

	void tryToSleep() {
		while (!asleep)
			countSomeSheep();
	}

	void countSomeSheep() {
		// One, two, three...
	}
}
