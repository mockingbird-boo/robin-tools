package cn.com.mockingbird.robin.mqtt.util;

import lombok.experimental.UtilityClass;
import org.springframework.util.Assert;

/**
 * Topic 工具类
 *
 * @author zhaopeng
 * @date 2023/12/12 16:18
 **/
@SuppressWarnings("unused")
@UtilityClass
public class TopicUtils {

    private static final String WILDCARD_ALL = "#";
    private static final String WILDCARD_PART = "+";
    private static final String SEPARATOR = "/";

    /**
     * 判断 topic 是否和 topicPattern 匹配
     * @param topic 主题名
     * @param topicPattern 订阅的主题模式
     * @return true - 匹配
     */
    public boolean isMatch(String topic, String topicPattern) {
        Assert.hasText(topic, "topic 要求非空");
        Assert.hasText(topicPattern, "topicPattern 要求非空");
        if (!topicPattern.contains(WILDCARD_ALL) && !topicPattern.contains(WILDCARD_PART)) {
            return topicPattern.equals(topic);
        }
        if (topicPattern.equals(WILDCARD_ALL)) {
            return true;
        }
        String[] patternSegments = topicPattern.split(SEPARATOR);
        String[] topicSegments = topic.split(SEPARATOR);
        for (int i = 0; i < patternSegments.length; i ++) {
            // 对各个主题层级进行匹配
            String curPatternSeg = patternSegments[i];
            String curTopicSeg = topicSegments.length > i ? topicSegments[i]: null;
            if (curTopicSeg == null && !curPatternSeg.equals(WILDCARD_ALL)) {
                // 主题层级比订阅层级少且相应的订阅层级不是 #
                return false;
            }
            if ("".equals(curTopicSeg) && "".equals(curPatternSeg)) {
                // 可能以 / 开头
                continue;
            }
            if (curPatternSeg.equals(WILDCARD_ALL)) {
                // 是#通配，则#必须是最后一级
                return i == patternSegments.length - 1;
            }
            // 当前层级不是通配，需要字符串相等
            if (!curPatternSeg.equals(WILDCARD_PART) && !curPatternSeg.equals(curTopicSeg)) {
                return false;
            }
        }
        return patternSegments.length == topicSegments.length;
    }

}
