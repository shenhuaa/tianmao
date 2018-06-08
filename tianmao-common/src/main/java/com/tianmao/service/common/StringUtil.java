package com.tianmao.service.common;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @ClassName: StringUtils
 * @Description: 字符串工具类
 * @author chenming
 * @date 2017年8月30日 下午3:15:52
 * 
 */
public class StringUtil {
	/**逗号*/
	public static final String UNDERLINE_DH = ",";
	/**中文逗号*/
	public static final String UNDERLINE_ZWDH = ",";
	/**竖线*/
	public static final String UNDERLINE_SX = "|";
	/**下划线*/
	public static final String UNDERLINE_XHX = "_";
	/**空格*/
	public static final String UNDERLINE_KG = " ";
	/**冒号*/
	public static final String UNDERLINE_MH = ":";
	/**英文 点*/
	public static final String UNDERLINE_D = ".";
	/**英文 横杠*/
	public static final String UNDERLINE_HG = "-";
	/**英文  x*/
	public static final String UNDERLINE_X = "x";
	/**英文  斜杠/*/
	public static final String UNDERLINE_XG = "/";
	

	/**英文转义 点*/
	public static final String UNDERLINE_ESCAPE_D = "\\.";
	/**转义竖线*/
	public static final String UNDERLINE_ESCAPE_SX = "\\|";
	

	
	/**
	 * 首字母小写
	 * 
	 * @return String
	 * @throws
	 */
	public static String firstToLowerCase(String str) {
		if (str != null && str.length() != 0) {
			return Character.toLowerCase(str.charAt(0)) + str.substring(1);
		}
		return str;
	}

	/**
	 * 首字母大些
	 * 
	 * @return String
	 * @throws
	 */
	public static String firstToUpperCase(String str) {
		if (str != null && str.length() != 0) {
			return Character.toUpperCase(str.charAt(0)) + str.substring(1);
		}
		return str;
	}

	/**
	 * 将页面或PO字段转换成全大写并以下划线分隔的数据库字段
	 * 
	 * @param param 例如： custName
	 * @return 换换后字符串 CUST_NAME
	 */
	public static String capitalConvertUnderline(String dtoName) {
		StringBuffer sub = null;
		if (isNotBlank(dtoName)) {
			char[] ch = dtoName.toCharArray();
			sub = new StringBuffer();
			String temp = null; //
			for (int i = 0; i < dtoName.length(); i++) {
				temp = String.valueOf(ch[i]);
				if (StringUtil.isNotBlank(temp)
						&& Character.isUpperCase(ch[i])) {
					sub.append(UNDERLINE_XHX + ch[i]);
				} else {
					sub.append(ch[i]);
				}
			}
			return sub.toString().toUpperCase();
		}
		return null;
	}
	
	/**
	 * 将页面或PO字段转换成以下划线分隔的数据库字段
	 * 
	 * @param param 例如： custName
	 * @return 换换后字符串 CUST_NAME
	 */
	public static String camelToUnderline(String param) {
		if (param == null || "".equals(param.trim())) {
			return "";
		}
		int len = param.length();
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++) {
			char c = param.charAt(i);
			if (Character.isUpperCase(c)) {
				sb.append(UNDERLINE_XHX);
				sb.append(Character.toLowerCase(c));
			} else {
				sb.append(c);
			}
		}
		return sb.toString();
	}

	/**
	 * 判断字符串是否是null或者""
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isBlank(String str) {
		if (str == null) {
			return true;
		} else if (str.length() == 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 判断字符串是否不为空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNotBlank(String str) {
		return !isBlank(str);
	}

	/**
	 * 用分隔符expr 分割str字符串，返回string[]数组
	 * 
	 * @param str
	 *            ：要分割的字符串
	 * @param expr
	 *            ：分隔符
	 * @return String[]
	 */
	public static String[] stringToArray(String str, String expr) {
		return str.split(expr);
	}

	/**
	 * 将字符串数组以分隔符方式返回字符串
	 * 
	 * @param arr
	 * @param expr
	 * @return
	 */
	public static String arrayToString(String[] arr, String expr) {
		String strInfo = "";
		if (arr != null && arr.length > 0) {
			StringBuffer sf = new StringBuffer();
			for (String str : arr) {
				sf.append(str);
				sf.append(expr);
			}
			strInfo = sf.substring(0, sf.length() - 1);
		}
		return strInfo;
	}

	/**
	 * 将list集合里字符串用expr分隔符连接，返回字符串
	 * 
	 * @param arr
	 * @param expr
	 * @return
	 */
	public static String listToString(List<String> list, String expr) {
		String strInfo = "";
		if (list != null && list.size() > 0) {
			StringBuffer sf = new StringBuffer();
			for (String str : list) {
				sf.append(str);
				sf.append(expr);
			}
			strInfo = sf.substring(0, sf.length() - 1);
		}
		return strInfo;
	}

	/**
	 * 屏蔽相关信息
	 * 
	 * @param arr
	 * @param expr
	 * @return
	 */
	public static String shieldAll(String str) {
		str = shieldEmail(str);
		str = shieldMobile(str);
		str = shieldTelephone(str);
		str = shieldQQ(str);
		return str;
	}

	/**
	 * 屏蔽EMAIL
	 * 
	 * @param email
	 * @return
	 */
	public static String shieldEmail(String email) {
		if (!email.isEmpty()) {
			String emailRegEx = "([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)";
			Pattern emailPattern = Pattern.compile(emailRegEx);
			Matcher emailMatcher = emailPattern.matcher(email);
			while (emailMatcher.find()) {
				String oldSubStr = emailMatcher.group(); // 获得邮箱地址
				int location = oldSubStr.indexOf("@");
				String temp = oldSubStr.substring(0, location); // 获得邮箱账号
				String newSubStr = oldSubStr.replace(temp, "****"); // 用星号替代邮箱账号
				email = email.replace(oldSubStr, newSubStr); // 替换补充说明中的邮箱地址
			}
		}
		return email;
	}

	/**
	 * 屏蔽手机号码
	 * 
	 * @param mobile
	 * @return
	 */
	public static String shieldMobile(String mobile) {
		if (!mobile.isEmpty()) {
			String mobileRegEx = "((13[0-9])|(15[0-9])|(18[0-9])|(17[0-9]))\\d{8}";
			Pattern mobilePattern = Pattern.compile(mobileRegEx);
			Matcher mobileMatcher = mobilePattern.matcher(mobile);
			while (mobileMatcher.find()) {
				String oldSubStr = mobileMatcher.group(); // 获得手机号码
				String temp = oldSubStr.substring(3, oldSubStr.length()); // 截取手机号码后八位
				String newSubStr = oldSubStr.replace(temp, "********"); // 用星号替代手机号码后八位
				mobile = mobile.replace(oldSubStr, newSubStr); // 替换补充说明中的手机号码
			}
		}
		return mobile;
	}

	/**
	 * 屏蔽座机号码
	 * 
	 * @param telephone
	 * @return
	 */
	public static String shieldTelephone(String telephone) {
		if (!telephone.isEmpty()) {
			String telephoneRegEx = "(0[0-9]{2,3})?[-| ]([2-9][0-9]{6,7})+(-[0-9]{1,4})?";
			Pattern telephonePattern = Pattern.compile(telephoneRegEx);
			Matcher telephoneMatcher = telephonePattern.matcher(telephone);
			while (telephoneMatcher.find()) {
				String oldSubStr = telephoneMatcher.group(); // 获得座机号码
				String temp = oldSubStr.substring(5, oldSubStr.length()); // 截取座机号码后7位
				String newSubStr = oldSubStr.replace(temp, "*******"); // 用星号替代座机号码后7位
				telephone = telephone.replace(oldSubStr, newSubStr); // 替换补充说明中的座机号码
			}
		}
		return telephone;
	}

	/**
	 * 屏蔽QQ
	 * 
	 * @param telephone
	 * @return
	 */
	public static String shieldQQ(String qq) {
		if (!qq.isEmpty()) {
			String qqRegEx = "[1-9][0-9]{5,9}";
			Pattern qqPattern = Pattern.compile(qqRegEx);
			Matcher qqMatcher = qqPattern.matcher(qq);
			while (qqMatcher.find()) {
				String oldSubStr = qqMatcher.group(); // 获得QQ号码
				String temp = oldSubStr.substring(2, oldSubStr.length()); // 截取QQ号码
				String newSubStr = oldSubStr.replace(temp, "******"); // 用星号替代
				qq = qq.replace(oldSubStr, newSubStr); // 替换补充说明中的QQ号码
			}
		}
		return qq;
	}

	public static String toGb2312(String str) {
		if (str == null)
			return null;
		String retStr = str;
		byte b[];
		try {
			b = str.getBytes("ISO8859_1");
			for (int i = 0; i < b.length; i++) {
				byte b1 = b[i];
				if (b1 == 63)
					break; // 1
				else if (b1 > 0)
					continue;// 2
				else if (b1 < 0) { // 不可能为0，0为字符串结束符
					// 小于0乱码
					retStr = new String(b, "GB2312");
					break;
				}
			}
		} catch (UnsupportedEncodingException e) {
			// e.printStackTrace();
		}
		return retStr;
	}

	/**
	 * 判断字符是否是中文
	 *
	 * @param c
	 *            字符
	 * @return 是否是中文
	 */
	public static boolean isChinese(char c) {
		Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
		if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
				|| ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
				|| ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
				|| ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
			return true;
		}
		return false;
	}

	/**
	 * 判断字符串是否是乱码
	 *
	 * @param strName
	 *            字符串
	 * @return 是否是乱码
	 */
	public static boolean isMessyCode(String strName) {
		Pattern p = Pattern.compile("\\s*|\t*|\r*|\n*");
		Matcher m = p.matcher(strName);
		String after = m.replaceAll("");
		String temp = after.replaceAll("\\p{P}", "");
		char[] ch = temp.trim().toCharArray();
		float chLength = ch.length;
		float count = 0;
		for (int i = 0; i < ch.length; i++) {
			char c = ch[i];
			if (!Character.isLetterOrDigit(c)) {
				if (!isChinese(c)) {
					count = count + 1;
				}
			}
		}
		float result = count / chLength;
		if (result > 0.4) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * 判断是否为数字
	 * 
	 * @param object
	 * @return
	 */
	public static boolean isNumber(Object object) {
		if (object == null)
			return false;
		String value = object.toString();
		return value.matches("\\d+");
	}

	/**
	 * 计算采用编码方式时字符串所占字节数
	 * 
	 * @param content
	 * @return
	 */
	public static int getByteSize(String encode, String content) {
		int size = 0;
		if (null != content) {
			try {
				// 汉字采用utf-8编码时占3个字节
				size = content.getBytes(encode).length;
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return size;
	}

	public static String objToString(Object obj) {
		if (obj != null) {
			return obj.toString();
		} else {
			return "";
		}
	}
}
