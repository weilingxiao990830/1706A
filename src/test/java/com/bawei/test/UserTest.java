package com.bawei.test;

import java.util.Random;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bawei.cms.bean.User;
import com.bawei.cms.util.RandomUtil;
@SuppressWarnings("all")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring-context.xml")
public class UserTest {
	@Resource
	private RedisTemplate redisTemplate;
	@Test
	public void userTest(){
		//获取当前时间
		long time1 = System.currentTimeMillis();
		//String 类型
		ValueOperations ops = redisTemplate.opsForValue();
		User user = new User();
		for (int i = 1; i <= 10; i++) {
				//设置id
			  user.setId(i);
			  //设置名字
			  user.setName(RandomUtil.getChineseName());
			  //设置性别
			  user.setSex(RandomUtil.getSex());
			  //设置手机号
			  user.setPhone(RandomUtil.RandomPhone());
			  //设置邮箱
			  user.setEmail(RandomUtil.getEmail());
			  //生日区间
			  user.setBirthday(RandomUtil.randomDate("1949-01-01 00:00:00", "2001-01-01 00:00:00"));
			  //System.out.println(user); 
			  //存入redis
			  ops.set(i+"", user);
		}
		long time2 = System.currentTimeMillis();
		long time=time2-time1;
		System.out.println("序列化方式 jdk，耗时："+time);
	}
	
	
	@Test
	public void userTestJson(){
		//获取当前时间
		long time1 = System.currentTimeMillis();
		//String 类型
		ValueOperations ops = redisTemplate.opsForValue();
		User user = new User();
		for (int i = 1; i <= 100000; i++) {
				//设置id
			  user.setId(i);
			  //设置名字
			  user.setName(RandomUtil.getChineseName());
			  //设置性别
			  user.setSex(RandomUtil.getSex());
			  //设置手机号
			  user.setPhone(RandomUtil.RandomPhone());
			  //设置邮箱
			  user.setEmail(RandomUtil.getEmail());
			  //生日区间
			  user.setBirthday(RandomUtil.randomDate("1949-01-01 00:00:00", "2001-01-01 00:00:00"));
			  //System.out.println(user); 
			  //存入redis
			  ops.set(i+"", user);
		}
		long time2 = System.currentTimeMillis();
		long time=time2-time1;
		System.out.println("序列化方式 json，耗时："+time);
	}
	
	
	@Test
	public void userTestHash(){
		//获取当前时间
		long time1 = System.currentTimeMillis();
		//String 类型
		//ValueOperations ops = redisTemplate.opsForValue();
		
		//hash类型
		BoundHashOperations hashOps = redisTemplate.boundHashOps("user_hash");
		
		
		User user = new User();
		for (int i = 1; i <= 10; i++) {
				//设置id
			  user.setId(i);
			  //设置名字
			  user.setName(RandomUtil.getChineseName());
			  //设置性别
			  user.setSex(RandomUtil.getSex());
			  //设置手机号
			  user.setPhone(RandomUtil.RandomPhone());
			  //设置邮箱
			  user.setEmail(RandomUtil.getEmail());
			  //生日区间
			  user.setBirthday(RandomUtil.randomDate("1949-01-01 00:00:00", "2001-01-01 00:00:00"));
			  //System.out.println(user); 
			  //存入redis
			  hashOps.put(i+"", user.toString());
			
		}
	
		
		long time2 = System.currentTimeMillis();
		long time=time2-time1;
		System.out.println("序列化方式 hash，耗时："+time);
	}
	
	
	
	
}
