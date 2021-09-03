package org.self.deadlock;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.self.base.context.BankerAlgorithm;

class TestBankerAlgorithm {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	/**
	 * 				 MAX		Allocation		Need		Available 	<p>
	 *      		 A  B  C  	A  B  C  		A  B  C		A  B  C	   	<p>
	 * Pid[0]     7  5  3  	0  1  0  			7  4  3 		2  3  0		<p>
	 * Pid[1]     3  2  2  	2  0  0  			1  2  2 						<p>
	 * Pid[2]     9  0  2  	3  0  2  			6  0  0 						<p>
	 * Pid[3]     2  2  2  	2  1  1  			0  1  1 						<p>
	 * Pid[4]     4  3  3  	0  0  2  			4  3  1 
	 */
	void testBankerAlgorithm() {
		// 系统开始时有三种资源 A(10)，B(5)，C(7)
		int available[] = { 10, 5, 7 };
		// 五个进程的最大需求矩阵
		int max[][] = { { 7, 5, 3 }, { 3, 2, 2 }, { 9, 0, 2 }, { 2, 2, 2 }, { 4, 3, 3 } };
		// 系统为这五个进程分配的资源
		int allocation[][] = { { 0, 1, 0 }, { 2, 0, 0 }, { 3, 0, 2 }, { 2, 1, 1 }, { 0, 0, 2 } };

		BankerAlgorithm banker = new BankerAlgorithm(available, max, allocation);
		// 进程 1 的资源请求
		banker.displaySystem();
		int request1[] = { 1, 0, 2 };
		System.out.println(banker.requestSource(1, request1));
		// 进程 4 的资源请求
		banker.displaySystem();
		int request2[] = { 3, 3, 0 };
		System.out.println(banker.requestSource(4, request2));
		// 进程 0 的资源请求
		banker.displaySystem();
		int request3[] = { 0, 2, 0 };
		System.out.println(banker.requestSource(0, request3));
	}

}
