package tianmao.common;
import java.math.BigDecimal;


/**
 * BigDecimal 加、减、乘、除工具类
 *
 * @author roach
 * @date 2017/12/8
 */
public class BigDecimalUtil {

    private BigDecimalUtil() {

    }

    /**
     * 进行加法运算
     *
     * @param val
     * @param val2
     * @return
     */
    public static BigDecimal add(BigDecimal val, BigDecimal val2) {
        return round(val.add(val2));
    }

    /**
     * 进行减法运算
     *
     * @param val
     * @param val2
     * @return
     */
    public static BigDecimal sub(BigDecimal val, BigDecimal val2) {
        return round(val.subtract(val2));
    }

    /**
     * 进行乘法运算
     *
     * @param val
     * @param val2
     * @return
     */
    public static BigDecimal mul(BigDecimal val, BigDecimal val2, int scale) {
        return round(val.multiply(val2), scale);
    }

    public static BigDecimal mul(BigDecimal val, BigDecimal val2) {
        return round(val.multiply(val2));
    }

    // 进行除法运算
    public static BigDecimal div(BigDecimal val, BigDecimal val2) {
        return (val.divide(val2));
    }

    // 进行除法运算
    public static BigDecimal div(BigDecimal val, BigDecimal val2, int len) {
        return (val.divide(val2, len));
    }

    /**
     * 进行四舍五入操作
     *
     * @param val
     * @return
     */
    public static BigDecimal round(BigDecimal val) {
        // 任何一个数字除以1都是原数字
        // ROUND_HALF_UP是BigDecimal的一个常量，表示进行四舍五入的操作
        return val.divide(new BigDecimal(1), 2, BigDecimal.ROUND_HALF_UP);
    }

    public static BigDecimal round(BigDecimal val, int scale) {
        // 任何一个数字除以1都是原数字
        // ROUND_HALF_UP是BigDecimal的一个常量，表示进行四舍五入的操作
        return val.divide(new BigDecimal(1), scale, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 小于或等于0
     *
     * @param val
     * @return
     */
    public static boolean isLetZero(BigDecimal val) {
        int num = val.compareTo(new BigDecimal(0));
        return (num <= 0) ? true : false;
    }

    /**
     * 小于某个值
     *
     * @param val
     * @param val2
     * @return
     */
    public static boolean isLet(BigDecimal val, BigDecimal val2) {
        int num = val.compareTo(val2);
        return (num < 0) ? true : false;
    }

    public static boolean isLet(BigDecimal val, double val2) {
        int num = val.compareTo(new BigDecimal(val2));
        return (num < 0) ? true : false;
    }

    /**
     * 大于或等于0
     *
     * @param val
     * @return
     */
    public static boolean isGetZero(BigDecimal val) {
        int num = val.compareTo(new BigDecimal(0));
        return (num >= 0) ? true : false;
    }

    /**
     * 大于0
     *
     * @param val
     * @return
     */
    public static boolean isGtZero(BigDecimal val) {
        int num = val.compareTo(new BigDecimal(0));
        return (num > 0) ? true : false;
    }

    /**
     * 等于0
     *
     * @param val
     * @return
     */
    public static boolean isZero(BigDecimal val) {
        int num = val.compareTo(new BigDecimal(0));
        return (num == 0) ? true : false;
    }

    //测试
    public static void main(String[] args) {


    }

}