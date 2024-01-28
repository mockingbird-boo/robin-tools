package cn.com.mockingbird.robin.common;

import lombok.Getter;

/**
 * 日期时间类型枚举
 *
 * @author zhao peng
 * @date 2024/1/22 16:31
 **/
@Getter
public enum DateTimeType {

    DATE(1, "java.util.Date"),
    LOCAL_DATE_TIME(2, "java.time.LocalDateTime")
    ;
    private final int value;
    private final String description;

    DateTimeType(int value, String description) {
        this.value = value;
        this.description = description;
    }
}
