package com.tianmao.web.app.token;
import com.tianmao.common.RandomUtil;
import com.tianmao.web.app.util.redis.Redis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.tianmao.RedisService;

/**
 * 用户登陆token管理器
 *
 * @author roach
 * @date 2017/12/11
 */
@Component
public class UserTokenManager {

    @Autowired
    private RedisService redisService;

    /**
     * 创建token
     *
     * @param userId 用户id
     * @return
     */
    public String createToken(Long userId) {
        String uid = userId.toString();
        String token = RandomUtil.buildToken();
        redisService.hset(Redis.TOKEN_USER, uid, token, Redis.TOKEN_TIMEOUT);
        redisService.hset(Redis.USER_TOKEN, token, uid, Redis.TOKEN_TIMEOUT);
        return token;
    }

    /**
     * 根据token获取用户id
     *
     * @param token
     * @return
     */
    public Long getUserId(String token) {
        String userId = redisService.hget(Redis.USER_TOKEN, token);
        return Long.parseLong(userId);
    }

    public String getToken(Long userId) {
        return redisService.hget(Redis.TOKEN_USER, userId.toString());
    }

    /**
     * 验证token
     *
     * @param token
     * @return
     */
    public boolean validateToken(String token) {
        return redisService.exists(Redis.TOKEN_USER, token);
    }

    /**
     * 刷新token
     *
     * @param token
     * @return
     */
    public boolean refreshToken(String token) {
        return redisService.expire(Redis.TOKEN_USER, token, Redis.TOKEN_TIMEOUT);
    }

    /**
     * 删除token
     *
     * @param userId 用户id
     * @return
     */
    public boolean deleteToken(Long userId) {
        String token = getToken(userId);
        redisService.hdel(Redis.TOKEN_USER, token);
        redisService.hdel(Redis.USER_TOKEN, userId.toString());
        return true;
    }
}
