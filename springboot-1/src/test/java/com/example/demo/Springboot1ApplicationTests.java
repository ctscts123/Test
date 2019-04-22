package com.example.demo;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.pojo.User;
import com.example.demo.pojo.Redis;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Springboot1ApplicationTests {
	
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private Redis redis;
	

	@Test
	public void findAll() {
		
		List<User> userList=userMapper.findAll();
		System.out.println(userList);	
	}
	
	@Test
	public void testFind2() {
		QueryWrapper<User> querywrappper=new QueryWrapper<>();
		querywrappper.gt("age",0).lt("age",150);
		List<User> userList=userMapper.selectList(querywrappper);
		System.out.println(userList);
		
	}
	
	@Test
	public void testInster() {
		User user=new User();
		user.setAge(1);
		user.setName("流浪地球");
		user.setSex("男");
		
		int rows=userMapper.insert(user);
		System.out.println("入库成功！影响了"+rows+"行");
	}
	
	@Test
	public void testDel() {
		QueryWrapper<User> queryWrapper=new QueryWrapper<>();
		queryWrapper.eq("name","流浪地球");
		userMapper.delete(queryWrapper);
		System.out.println("删除数据成功！！！");
	}
	
	@Test
	public void testUpdate() {
		User user=new User();
		user.setAge(20);
		user.setSex("女");
		user.setName("搵嘢做4561231");
		UpdateWrapper<User> updateWrapper=new UpdateWrapper<>();
		updateWrapper.eq("id", 1);
		userMapper.update(user, updateWrapper);
		System.out.println("更新数据成功！！！");
	}

	@Test
	public void testString() {
		
		Jedis jedis=new Jedis(redis.getHost(),7000);
		jedis.set("1810", "1810班");
		String value =jedis.get("1810");
		System.out.println("从redis中获取数据："+value);
		jedis.close();
	}
	
	@Test
	public void testHash() {
		Jedis jedis =new Jedis("192.168.0.128",6379);
		jedis.hset("dog","id", "1000");
		jedis.hset("dog","name","旺财");
		
		System.out.println(jedis.hgetAll("dog"));
		
	}
	
	@Test
	public void testList() {
		Jedis jedis=new Jedis("192.168.0.128",6379);
		jedis.lpush("list","1","2","3");
		for(int i=0;i<3;i++) {
			String value=jedis.lpop("list");
			System.out.println(value);
		}
		
	}
	
	//测试redis集群
	@Test
	public void testsCluster() {
		Set<HostAndPort> nodes=new HashSet<>();
		nodes.add(new HostAndPort("192.168.0.128",7000));
		nodes.add(new HostAndPort("192.168.0.128",7001));
		nodes.add(new HostAndPort("192.168.0.128",7002));
		nodes.add(new HostAndPort("192.168.0.128",7003));
		nodes.add(new HostAndPort("192.168.0.128",7004));
		nodes.add(new HostAndPort("192.168.0.128",7005));
		nodes.add(new HostAndPort("192.168.0.128",7006));
		nodes.add(new HostAndPort("192.168.0.128",7007));
		nodes.add(new HostAndPort("192.168.0.128",7008));
		
		JedisCluster jedisCluster=new JedisCluster(nodes);
		jedisCluster.set("redis","学习集群使用");
		System.out.println("获取数据:  "+jedisCluster.get("redis"));
		try {
			jedisCluster.close();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}
	
	
}














