package tianmao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Map;
import java.util.Set;

/**
 * Created by roach on 2017/6/8.
 */
@Service
public class RedisServiceImpl implements RedisService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public long del(final String... keys) {
        return (long) redisTemplate.execute((RedisCallback<Long>) connection -> {
            long result = 0;
            for (int i = 0; i < keys.length; i++) {
                result = connection.del(keys[i].getBytes());
            }
            return result;
        });
    }

    @Override
    public void delLick(final String key) {
        redisTemplate.execute((RedisCallback<Long>) connection -> {
            Set<byte[]> keys = connection.keys(key.getBytes());
            for (byte[] kes : keys) {
                connection.del(kes);
            }
            return 0L;
        });
    }

    @Override
    public <T> long set(final String key, final T t, final long expire) {
        return (long) redisTemplate.execute((RedisCallback) connection -> {
            connection.set(key.getBytes(), rawValue(t));
            if (expire > 0) {
                connection.expire(key.getBytes(), expire);
            }
            return 1L;
        });
    }

    @Override
    public <T> long set(final String key, final T t) {
        return set(key, t, 0);
    }

    private long set(final byte[] key, final byte[] value, final long expire) {
        return (long) redisTemplate.execute((RedisCallback<Long>) connection -> {
            connection.set(key, value);
            if (expire > 0) {
                connection.expire(key, expire);
            }
            return 1L;
        });
    }

    @Override
    public <T> long hset(final String key, final String field, final T t, final long expire) {
        return hset(key.getBytes(), field.getBytes(), rawValue(t), expire);
    }

    @Override
    public <T> long hset(final String key, final String field, final T t) {
        return hset(key.getBytes(), field.getBytes(), rawValue(t), 0);
    }

    private long hset(final byte[] key, final byte[] field, final byte[] value, final long expire) {
        return (long) redisTemplate.execute((RedisCallback<Long>) connection -> {
            connection.hSet(key, field, value);
            if (expire > 0) {
                connection.expire(key, expire);
            }
            return 1L;
        });
    }

    @Override
    public <T> T hget(final String key, final String field) {
        return (T) redisTemplate.execute((RedisCallback) connection -> {
            return deserialize(connection.hGet(rawKey(key), rawKey(field)));
        });
    }

    @Override
    public boolean hdel(String key, String field) {
        return (Boolean) redisTemplate.execute((RedisCallback) connection -> {
            return connection.hDel(rawKey(key), rawKey(field)) > 0;
        });
    }

    @Override
    public <HK, HV> Map<HK, HV> hgetAll(final String key) {
        return (Map<HK, HV>) redisTemplate.execute((RedisCallback) connection -> {
            return deserialize(connection.hGetAll(rawKey(key)));
        });
    }

    public <T> T get(final String key, final long expire) {
        return (T) redisTemplate.execute((RedisCallback) connection -> {
            if (expire > 0) {
                connection.expire(rawKey(key), expire);
            }
            return deserialize(connection.get(rawKey(key)));
        });
    }

    @Override
    public <T> T get(String key) {
        return get(key, 0);
    }

    @Override
    public <T> long lPush(final String key, final T t) {
        return (Long) redisTemplate.execute((RedisCallback) connection -> connection.lPush(rawKey(key), rawValue(t)));
    }

    @Override
    public <T> long rPush(final String key, final T t) {
        return (Long) redisTemplate.execute((RedisCallback<Long>) connection -> connection.rPush(rawKey(key), rawValue(t)));
    }

    @Override
    public <T> T lPop(final String key) {
        return (T) redisTemplate.execute((RedisCallback<T>) connection -> deserialize(connection.lPop(rawKey(key))));
    }

    @Override
    public boolean expire(final String key, final long expire) {
        return (boolean) redisTemplate.execute((RedisCallback<Boolean>) connection -> {
            if (expire > 0) {
                return connection.expire(key.getBytes(), expire);
            }
            return false;
        });
    }

    @Override
    public boolean expire(final String prefix, final String key, final long expire) {
        return (boolean) redisTemplate.execute((RedisCallback<Boolean>) connection -> {
            if (expire > 0) {
                return connection.expire((prefix + key).getBytes(), expire);
            }
            return false;
        });
    }


    @Override
    public boolean exists(final String key) {
        if ((boolean) redisTemplate.execute(new RedisCallback() {
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.exists(key.getBytes());
            }
        })) return true;
        else return false;
    }

    @Override
    public boolean exists(final String prefix, final String key) {
        if ((boolean) redisTemplate.execute(new RedisCallback() {
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.exists((prefix + key).getBytes());
            }
        })) return true;
        else return false;
    }

    @Override
    public <T> Set<T> keys(final String pattern) {
        return (Set<T>) redisTemplate.execute((RedisCallback<Object>) connection -> deserialize(connection.keys(pattern.getBytes())));
    }

    @Override
    public String flushDB() {
        return (String) redisTemplate.execute((RedisCallback) connection -> {
            connection.flushDb();
            return "ok";
        });
    }

    @Override
    public long dbSize() {
        return (long) redisTemplate.execute((RedisCallback) connection -> connection.dbSize());
    }

    @Override
    public String ping() {
        return (String) redisTemplate.execute((RedisCallback) connection -> connection.ping());
    }

    private String stringSerializer(final String key) {
        return (String) redisTemplate.execute((RedisCallback<String>) connection -> {
            RedisSerializer<String> redisSerializer = redisTemplate.getStringSerializer();
            return redisSerializer.deserialize(connection.get(key.getBytes()));
        });
    }

    private <T> T deserialize(final String key) {
        return (T) redisTemplate.execute((RedisCallback<T>) connection -> {
            RedisSerializer<T> redisSerializer = redisTemplate.getDefaultSerializer();
            return redisSerializer.deserialize(connection.get(key.getBytes()));
        });
    }

    private <T> T deserialize(byte[] key) {
        RedisSerializer<T> redisSerializer = redisTemplate.getDefaultSerializer();
        return redisSerializer.deserialize(key);
    }

    private <T> Set<T> deserialize(Set<byte[]> key) {
        return SerializationUtils.deserialize(key, redisTemplate.getStringSerializer());

    }

    private <HK, HV> Map<HK, HV> deserialize(Map<byte[], byte[]> key) {
        return SerializationUtils.deserialize(key, redisTemplate.getKeySerializer(), redisTemplate.getDefaultSerializer());

    }

    private byte[] rawKey(String key) {
        Assert.notNull(key, "non null key required");
        return key.getBytes();
    }

    private <T> byte[] rawValue(T t) {
        if (t instanceof byte[]) {
            return (byte[]) t;
        }
        RedisSerializer<T> serializer = redisTemplate.getDefaultSerializer();
        return serializer.serialize(t);
    }


    public static void main(String[] args) {
        JedisPool jedisPool = new JedisPool(new JedisPoolConfig(), "127.0.0.1", 6379);
        Jedis jedis = jedisPool.getResource();
        jedis.flushDB();
       /* Set<String> keys = jedis.keys("*account_session:*");
        System.out.println(keys.size());
        for (String kes : keys) {
            //  System.out.println(kes);
            jedis.del(kes);
        }*/
    }

}
