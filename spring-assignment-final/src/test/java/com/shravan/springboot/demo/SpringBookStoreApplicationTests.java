package com.shravan.springboot.demo;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringBookStoreApplicationTests {

	@Test
	void contextLoads() {
		Assert.assertEquals(2,3-1);
	}

}
