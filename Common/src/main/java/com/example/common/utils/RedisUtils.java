//package com.example.common.utils;
//
//
//import jakarta.annotation.Resource;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.stereotype.Component;
//import org.springframework.util.CollectionUtils;
//
//import java.util.Collection;
//import java.util.Set;
//import java.util.concurrent.TimeUnit;
//
///**
// * @author fangyaohui
// * @version 0.0.3
// * @description redisUtils
// * @since 2024/3/19 16:15
// */
//
//@Component
//public class RedisUtils {
//
//    @Resource
//    private RedisTemplate<String,Object> redisTemplate;
//
//
//    public Set<String> keys(String keys){
//        try{
//            return redisTemplate.keys(keys);
//        }catch (Exception e){
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    /**
//     * 指定缓存失效时间
//     * @param key 键
//     * @param time 时间(秒)
//     * @return
//     */
//    public boolean expire(String key, long time) {
//        try {
//            if (time > 0) {
//                redisTemplate.expire(key, time, TimeUnit.SECONDS);
//            }
//            return true;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//
//    /**
//     * 根据key 获取过期时间
//     * @param key 键 不能为null
//     * @return 时间(秒) 返回0代表为永久有效
//     */
//    public long getExpire(String key) {
//        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
//    }
//    /**
//     * 判断key是否存在
//     * @param key 键
//     * @return true 存在 false不存在
//     */
//    public boolean hasKey(String key) {
//        try {
//            return redisTemplate.hasKey(key);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//
//    /**
//     * 删除缓存
//     * @param key 可以传一个值 或多个
//     */
//    @SuppressWarnings("unchecked")
//    public void del(String... key) {
//        if (key != null && key.length > 0) {
//            if (key.length == 1) {
//                redisTemplate.delete(key[0]);
//            } else {
//                redisTemplate.delete((Collection<String>) CollectionUtils.arrayToList(key));
//            }
//        }
//    }
//    /**
//     * 普通缓存获取
//     * @param key 键
//     * @return 值
//     */
//    public Object get(String key) {
//        return key == null ? null : redisTemplate.opsForValue().get(key);
//    }
//    /**
//     * 普通缓存放入
//     * @param key 键
//     * @param value 值
//     * @return true成功 false失败
//     */
//    public boolean set(String key, Object value) {
//        try {
//            redisTemplate.opsForValue().set(key, value);
//            return true;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//
//}