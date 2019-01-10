package com.yunchao.hsh.utils.redis;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

/**
 * redis工具类
 */
public class RedisUtil {
    private static RedisUtil instance;

    private RedisTemplate redisTemplate ;

    RedisUtil(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:spring-redis.xml");
        redisTemplate = (RedisTemplate) ctx.getBean("redisTemplate");
    }

    public synchronized static RedisUtil getInstance(){
        if(instance == null){
            instance = new RedisUtil();
        }
        return instance;
    }

    /**
     * 将数据放入redis
     * @param key
     * @param value
     */
    public void putInRedis(String key,Serializable value){
        // 存入Redis
        redisTemplate.opsForValue().set(key,value);
}

    /**
     * 将数据放入的Redis中， 在指定的时间后失效
     * @param key
     * @param value
     * @param expireSeconds  秒
     */
    public  void  putInRedis(String key,Serializable value, Integer expireSeconds){
        redisTemplate.opsForValue().set(key, value, expireSeconds, TimeUnit.SECONDS);
    }

    /**
     * 获取数据
     * @param key
     * @return
     */
    public Object getFromRedis(String key){
        Object obj=null;
        try {
            obj = redisTemplate.opsForValue().get(key);
        }catch (Exception e){
            e.printStackTrace();
            obj=null;
        }
        return obj;
    }

    /**
     * 清除数据
     * @param key
     */
    public void deleteByKey(String key){
        redisTemplate.delete(key);
    }

}
