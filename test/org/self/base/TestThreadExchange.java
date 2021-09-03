package org.self.base;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.self.base.context.ThreadExchange;

class TestThreadExchange {

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

	@RepeatedTest(value = 1)
	void test() throws InterruptedException {
		long countList[] = { 10_000, 100_000, 1_000_000, 10_000_000, 100_000_000 };
		for (long count : countList) {
			System.out.println("count=" + count);
			ThreadExchange te = new ThreadExchange(count);
			te.concurrency();
			System.out.println();
		}
	}

}
