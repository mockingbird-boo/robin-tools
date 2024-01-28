package cn.com.mockingbird.robin.common;

import lombok.Getter;

/**
 * 数据库类型枚举
 *
 * @author zhao peng
 * @date 2024/1/22 15:21
 **/
@Getter
public enum DatabaseType {

    MYSQL(1, "MySQL"),
    ORACLE(2, "Oracle"),
    SQLSERVER(3, "SQLServer"),
    POSTGRESQL(4, "PostgreSQL")
    ;
    private final int value;

    private final String description;

    DatabaseType(int value, String description) {
        this.value = value;
        this.description = description;
    }

}
