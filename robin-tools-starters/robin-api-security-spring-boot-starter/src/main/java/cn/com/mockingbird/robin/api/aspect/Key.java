package cn.com.mockingbird.robin.api.aspect;

import cn.com.mockingbird.robin.common.util.encrypt.DigestUtils;

/**
 * Key 工具类
 *
 * @author zhaopeng
 * @date 2023/10/18 1:11
 **/
public final class Key {

    public static final String TOKEN_KEY_PATTERN = "idempotent:token:%s:%s";

    public static final String LOCK_KEY_PREFIX = "idempotent:lock:";

    public static final String LOCK_KEY_SUFFIX = "%s-%s-%s-%s";

    public static final String NONCE_KEY = "idempotent:nonce:%s";

    public static String generateTokenKey(String username, String token) {
        return String.format(TOKEN_KEY_PATTERN, username, token);
    }

    public static String generateLockKey(String username, String clientIp, String className, String method) {
        return LOCK_KEY_PREFIX + DigestUtils.md5(String.format(LOCK_KEY_SUFFIX, username, clientIp, className, method));
    }

    public static String generateNonceKey(String nonce) {
        return String.format(NONCE_KEY, nonce);
    }


}
