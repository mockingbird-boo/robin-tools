package cn.com.mockingbird.robin.model.config;

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
     * 数据库连接地址
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
     * schema
     */
    private String schema;

}
