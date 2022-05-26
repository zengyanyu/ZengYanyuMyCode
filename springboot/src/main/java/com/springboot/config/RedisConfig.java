package com.springboot.config;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

/**
 * redis配置
 * @author ZengYanyu
 * @Description
 * @Date 2020年11月24日 下午6:17:14
 * @see com.springboot.config.RedisConfig.java
 */
@Configuration
public class RedisConfig {

	@Value("${spring.redis.cluster.nodes}")
	private String redisNotes;

	@Bean
	public JedisCluster getJdeisCluster() {
		String[] redisnotes = redisNotes.split(",");
		Set<HostAndPort> notes = new HashSet<>();
		for (String node : redisnotes) {
			String[] arr = node.split(":");
			//设置IP地址和端口
			HostAndPort hostAndPort = new HostAndPort(arr[0], Integer.parseInt(arr[1]));
			notes.add(hostAndPort);
		}
		JedisCluster jedisCluster = new JedisCluster(notes);
		return jedisCluster;
	}

}
