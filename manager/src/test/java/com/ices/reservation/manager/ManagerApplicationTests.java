package com.ices.reservation.manager;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class ManagerApplicationTests {

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Test
	public void save(){

		stringRedisTemplate.opsForValue().set("zzp","big z");
		Assert.assertEquals("big z",stringRedisTemplate.opsForValue().get("zzp"));
	}
}
