package com.redis;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.logger.NssLogger;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @Project: ThinkpageAPI
 * @Author: hectorhua
 * @Date: 2014
 */

public class RedisManager {
    private static RedisManager instance = null;

    JedisPool pool = null;
    JedisPoolConfig poolconfig = null;
    String ip;
    int port;
    int timeout;

    private RedisManager() {
    }

    public static RedisManager getInstance() {
        synchronized (RedisManager.class) {
            if (instance == null) {
                instance = new RedisManager();
            }
        }
        return instance;
    }

    public void init(String ip, int port, int max_active, int max_idle, int timeout) {
        this.ip = ip;
        this.port = port;
        this.timeout = timeout;
        this.poolconfig = new JedisPoolConfig();
        //this.poolconfig.setMaxActive(max_active);
        this.poolconfig.setMaxIdle(max_idle);

        init_pool(this.poolconfig, this.ip, this.port, this.timeout);
    }

    public void init_pool(JedisPoolConfig poolconfig, String ip, int port, int timeout) {
        if (this.pool == null)
            this.pool = new JedisPool(poolconfig, ip, port, timeout);
    }

    public Jedis get_jedis() {
        Jedis jedis = null;
        try {
            if (this.pool == null)
                init_pool(this.poolconfig, this.ip, this.port, this.timeout);
            jedis = this.pool.getResource();
        } catch (redis.clients.jedis.exceptions.JedisConnectionException e) {
            throw e;
        }
        return jedis;
    }

    public JedisPool get_jedisPool() {
        try {
            if (this.pool == null)
                init_pool(this.poolconfig, this.ip, this.port, this.timeout);
        } catch (redis.clients.jedis.exceptions.JedisConnectionException e) {
            throw e;
        }
        return this.pool;
    }
    
    public Set<String> get_keys(String key){
        Set<String> value = null;
        Jedis jedis = null;
        try {
            jedis = get_jedis();
            value = jedis.keys(key);
        } catch (redis.clients.jedis.exceptions.JedisConnectionException e) {
            try {
                jedis.disconnect();
                this.pool.returnBrokenResource(jedis);
                jedis = get_jedis();
                value = jedis.keys(key);
            } catch (java.lang.ClassCastException exp) {
                jedis.disconnect();
                this.pool.returnBrokenResource(jedis);
                jedis = get_jedis();
                value = jedis.keys(key);
            } catch (Exception exp) {
                NssLogger.error(exp, exp);
            }
        } catch (Exception exp) {
            NssLogger.error(exp, exp);
        }
        this.pool.returnResource(jedis);
        return value;
    }

    public String get_string(String key) {
        String value = "";
        Jedis jedis = null;
        try {
            jedis = get_jedis();
            value = jedis.get(key);
        } catch (redis.clients.jedis.exceptions.JedisConnectionException e) {
            try {
                jedis.disconnect();
                this.pool.returnBrokenResource(jedis);
                jedis = get_jedis();
                value = jedis.get(key);
            } catch (java.lang.ClassCastException exp) {
                jedis.disconnect();
                this.pool.returnBrokenResource(jedis);
                jedis = get_jedis();
                value = jedis.get(key);
            } catch (Exception exp) {
                NssLogger.error(exp, exp);
            }
        } catch (Exception exp) {
            NssLogger.error(exp, exp);
        }
        this.pool.returnResource(jedis);

        return value;
    }

    public Map<String, String> get_hashmap(String key) {

        Map<String, String> value = null;

        Jedis jedis = null;
        try {
            jedis = get_jedis();
            value = jedis.hgetAll(key);
        } catch (redis.clients.jedis.exceptions.JedisConnectionException e) {
            try {
                jedis.disconnect();
                this.pool.returnBrokenResource(jedis);
                jedis = get_jedis();
                value = jedis.hgetAll(key);
            } catch (java.lang.ClassCastException exp) {
                jedis.disconnect();
                this.pool.returnBrokenResource(jedis);
                jedis = get_jedis();
                value = jedis.hgetAll(key);
            } catch (Exception exp) {
                NssLogger.error(exp, exp);
            }
        } catch (Exception exp) {
            NssLogger.error(exp, exp);
        }
        this.pool.returnResource(jedis);

        return value;
    }

    public String get_hashmap(String key, String field) {
        String value = "";
        Jedis jedis = null;
        try {
            jedis = get_jedis();
            value = jedis.hget(key, field);
        } catch (redis.clients.jedis.exceptions.JedisConnectionException e) {
            try {
                jedis.disconnect();
                this.pool.returnBrokenResource(jedis);
                jedis = get_jedis();
                value = jedis.hget(key, field);
            } catch (java.lang.ClassCastException exp) {
                jedis.disconnect();
                this.pool.returnBrokenResource(jedis);
                jedis = get_jedis();
                value = jedis.hget(key, field);
            } catch (Exception exp) {
                NssLogger.error(exp, exp);
            }
        } catch (Exception exp) {
            NssLogger.error(exp, exp);
        }
        this.pool.returnResource(jedis);

        return value;
    }

    public String set_hashmap(String key, Map<String, String> value) {
        String res = "";
        Jedis jedis = null;
        try {
            jedis = get_jedis();
            res = jedis.hmset(key, value);
        } catch (redis.clients.jedis.exceptions.JedisConnectionException e) {
            try {
                jedis.disconnect();
                this.pool.returnBrokenResource(jedis);
                jedis = get_jedis();
                res = jedis.hmset(key, value);
            } catch (java.lang.ClassCastException exp) {
                jedis.disconnect();
                this.pool.returnBrokenResource(jedis);
                jedis = get_jedis();
                res = jedis.hmset(key, value);
            } catch (Exception exp) {
                NssLogger.error(exp, exp);
            }
        } catch (Exception exp) {
            NssLogger.error(exp, exp);
        }
        this.pool.returnResource(jedis);

        return res;
    }

    public String set_hashmap_byte(byte[] key, Map<byte[], byte[]> value) {
        String res = "";
        Jedis jedis = null;
        try {
            jedis = get_jedis();
            res = jedis.hmset(key, value);
        } catch (redis.clients.jedis.exceptions.JedisConnectionException e) {
            try {
                jedis.disconnect();
                this.pool.returnBrokenResource(jedis);
                jedis = get_jedis();
                res = jedis.hmset(key, value);
            } catch (java.lang.ClassCastException exp) {
                jedis.disconnect();
                this.pool.returnBrokenResource(jedis);
                jedis = get_jedis();
                res = jedis.hmset(key, value);
            } catch (Exception exp) {
                NssLogger.error(exp, exp);
            }
        } catch (Exception exp) {
            NssLogger.error(exp, exp);
        }
        this.pool.returnResource(jedis);

        return res;
    }
    
    public long set_hashmap(String key, String field, String value) {

        long res = 0;
        Jedis jedis = null;
        try {
            jedis = get_jedis();
            res = jedis.hset(key, field, value);

        } catch (redis.clients.jedis.exceptions.JedisConnectionException e) {

            try {
                jedis.disconnect();
                this.pool.returnBrokenResource(jedis);

                jedis = get_jedis();
                res = jedis.hset(key, field, value);
            } catch (java.lang.ClassCastException exp) {
                jedis.disconnect();
                this.pool.returnBrokenResource(jedis);

                jedis = get_jedis();
                res = jedis.hset(key, field, value);
            } catch (Exception exp) {
                NssLogger.error(exp, exp);
                res = -1;
            }
        } catch (Exception exp) {
            NssLogger.error(exp, exp);
            res = -1;
        }
        this.pool.returnResource(jedis);

        return res;
    }
    
    public long del_hashmap(String key, String field, String value) {

        long res = 0;
        Jedis jedis = null;
        try {
            jedis = get_jedis();
            res = jedis.hdel(key, field, value);

        } catch (redis.clients.jedis.exceptions.JedisConnectionException e) {

            try {
                jedis.disconnect();
                this.pool.returnBrokenResource(jedis);

                jedis = get_jedis();
                res = jedis.hdel(key, field, value);
            } catch (java.lang.ClassCastException exp) {
                jedis.disconnect();
                this.pool.returnBrokenResource(jedis);

                jedis = get_jedis();
                res = jedis.hdel(key, field, value);
            } catch (Exception exp) {
                NssLogger.error(exp, exp);
                res = -1;
            }
        } catch (Exception exp) {
            NssLogger.error(exp, exp);
            res = -1;
        }
        this.pool.returnResource(jedis);

        return res;
    }
    
    public long del_hashmap(String key) {

        long res = 0;
        Jedis jedis = null;
        try {
            jedis = get_jedis();
            res = jedis.del(key);

        } catch (redis.clients.jedis.exceptions.JedisConnectionException e) {

            try {
                jedis.disconnect();
                this.pool.returnBrokenResource(jedis);

                jedis = get_jedis();
                res = jedis.del(key);
            } catch (java.lang.ClassCastException exp) {
                jedis.disconnect();
                this.pool.returnBrokenResource(jedis);

                jedis = get_jedis();
                res = jedis.del(key);
            } catch (Exception exp) {
                NssLogger.error(exp, exp);
                res = -1;
            }
        } catch (Exception exp) {
            NssLogger.error(exp, exp);
            res = -1;
        }
        this.pool.returnResource(jedis);

        return res;
    }

    public void release() {
        if (this.pool != null)
            pool.destroy();
    }

    /**
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        // TODO Auto-generated method stub
    	/**
        for(int i = 0; i < 10; i ++){
        RedisManager.getInstance().init("172.27.18.82", 6379, 4, 0, 0);
        Set<String> keyset = RedisManager.getInstance()
                .get_keys("*_" + "33634156" + "_*");

        for (Iterator<String> it = keyset.iterator(); it.hasNext();) {
            String rediskey = it.next();
            System.out.println(rediskey);

            System.out.println(RedisManager.getInstance().get_hashmap(rediskey).get("effective_date"));

            System.out.println(RedisManager.getInstance().get_hashmap(rediskey).get("end_date"));
            System.out.println(RedisManager.getInstance().get_hashmap(rediskey).get("frequency"));
        }
        }
        */
    }
}