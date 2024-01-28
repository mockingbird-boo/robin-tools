package cn.com.mockingbird.robin.model.config;

import cn.com.mockingbird.robin.common.DatabaseType;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 数据库配置
 *
 * @author zhao peng
 * @date 2024/1/22 2:39
 **/
@Data
@Accessors(chain = true)
public class Database {

    /**
     * 连接名
     */
    private String name;

    /**
     * 数据库类型
     */
    private DatabaseType databaseType;

    /**
     * 数据库服务器地址
     */
    private String url;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 数据库
     */
    private String database;

}
