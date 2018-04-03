package tianmao.common;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Sha512Hash;

/**
 * 用户密码验证工具类
 * <p>
 * Created by roach on 2017/7/29.
 */
public class Sha512HashPasswordUtil {

    public static final String HASH_ALGORITHM_NAME = "SHA-512";
    public static final int HAS_HITERATIONS = 1024;
    public static final int NUM_BYTES = 32;

    /**
     * 密码比较
     *
     * @param password   输入的密码
     * @param dbPassword 数据库密码
     * @return
     */
    public static boolean checkPassword(String password, String dbPassword, String dbPasswordSalt) {
        if (password == null || "".equals(password)) {
            throw new IllegalArgumentException("要比较的密码不能为空");
        }
        if (password == null || "".equals(password)) {
            throw new IllegalArgumentException("数据库密码不能为空");
        }
        String hash = getHashPassword(password, dbPasswordSalt);
        return hash.equals(dbPassword);
    }

    /**
     * 获取
     *
     * @return
     */
    public static String getSalt() {
        return new SecureRandomNumberGenerator().nextBytes(NUM_BYTES).toBase64();
    }

    public static String getHashPassword(String plaintext, String salt) {
        return new Sha512Hash(plaintext, salt, HAS_HITERATIONS).toBase64();
    }

    public static void main(String[] args) {
        String salt = getSalt();
        String password = getHashPassword("istore168", salt);
        System.out.println("salt = " + salt);
        System.out.println("password = " + password);

    }


}
