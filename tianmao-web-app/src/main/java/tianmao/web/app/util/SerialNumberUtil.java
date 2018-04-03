package tianmao.web.app.util;
import java.text.SimpleDateFormat;
import java.util.UUID;

/**
 * 订单流水号
 *
 * @author roach
 * @date 2018/1/3
 */
public final class SerialNumberUtil {

    private SerialNumberUtil() {

    }

    /**
     * 时间格式生成序列
     *
     * @return String
     */
    public static String generate() {
        String currentTime = new SimpleDateFormat("yyMMddHHmmssSSS").format(System.currentTimeMillis());
        String uuidCode = String.valueOf(UUID.randomUUID().toString().replaceAll("-", "").hashCode() / 1000).replace("-", "");
        String result;
        if (uuidCode.length() < 7) {
            StringBuilder stringBuilder = new StringBuilder(currentTime);
            int many = 7 - uuidCode.length();
            for (int j = 0; j < many; j++) {
                stringBuilder.append("0");
            }
            result = stringBuilder.append(uuidCode).toString();
        } else {
            result = currentTime + uuidCode;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(generate());
        System.out.println(generate());
        System.out.println(generate());
        System.out.println(generate());
        System.out.println(generate().length());
    }
}
