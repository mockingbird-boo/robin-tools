package cn.com.mockingbird.robin.common.util.string;

import cn.com.mockingbird.robin.common.constant.Standard;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * 字符串工具类
 * <p>
 * 更多的字符串操作的可以使用 {@link org.apache.commons.lang3.StringUtils} 等框架提供的字符串工具类
 * 本工具类主要用来提供一些特殊的字符串操作方法，用于支持当前项目中开发的一些组件
 *
 * @author zhaopeng
 * @date 2023/10/9 23:15
 **/
@UtilityClass
public class StringUtils {

    public final String COMMA = ",";

    public final String ALL_CHAR_NUM = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    /**
     * 首字符大写
     * @param str 目标字符串
     * @return 首字符大写后的字符串
     */
    public String upperTheFirstChar(String str) {
        // 进行字母的 ascii 编码前移，效率略高于截取字符串进行转换的操作
        char[] cs = str.toCharArray();
        cs[0] -= 32;
        return String.valueOf(cs);
    }

    /**
     * 逗号分隔的字符串转 List<String>
     * @param str 逗号分隔的字符
     * @return List<String> 实例
     */
    public List<String> commaStringToList(String str) {
        if (str == null) {
            return null;
        }
        return Arrays.asList(str.trim().split(COMMA));
    }

    /**
     * List<String> 转逗号分隔的字符串
     * @param strList List<String>
     * @return 逗号分隔的字符串
     */
    public String listToCommaString(List<String> strList) {
        if (strList == null) {
            return null;
        }
        if (strList.isEmpty()) {
            return "";
        }
        return String.join(COMMA, strList);
    }

    /**
     * 毫秒格式化成日期时间字符串
     * @param millis 毫秒时间戳
     * @return 日期时间字符串
     */
    public String millisToString(long millis) {
        return DateFormatUtils.format(millis, Standard.DateTimePattern.DATETIME);
    }

    /**
     * 生成指定长度的随机字符串
     * @param length 长度，要求 >= 1
     * @return 指定长度的随机字符串
     * @see RandomStringUtils#randomAlphabetic(int)
     */
    @Deprecated
    public String getStringNumRandom(int length) {
        Random random = new Random();
        StringBuilder saltString = new StringBuilder(length);
        for (int i = 1; i <= length; ++i) {
            saltString.append(ALL_CHAR_NUM.charAt(random.nextInt(ALL_CHAR_NUM.length())));
        }
        return saltString.toString();
    }

    /**
     * 二进制转十六进制
     * @param bytes 二进制数据
     * @return 十六进制数据
     */
    public String byteToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            String hex = Integer.toHexString(b & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    /**
     * 返回字符串的长度
     * @param cs 参数字符串
     * @return 参数字符串为 null，返回 0，不为 null 就返回实际长度
     */
    public int length(final CharSequence cs) {
        return cs == null ? 0 : cs.length();
    }

    /**
     * 判断字符串为 null 或空字符串（包括""和"  "）
     * @param cs 参数字符串
     * @return true - 字符串为 null 或空字符串
     */
    public boolean isBlank(final CharSequence cs) {
        final int strLen = length(cs);
        if (strLen == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(cs.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断字符串不为 null 或空字符串（包括""和"  "）
     * @param cs 参数字符串
     * @return true - 字符串不为 null 或空字符串
     */
    public boolean isNotBlank(final CharSequence cs) {
        return !isBlank(cs);
    }
}
