package cn.com.mockingbird.robin.model.config;

import cn.com.mockingbird.robin.common.DateTimeType;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 全局配置
 *
 * @author zhao peng
 * @date 2024/1/22 12:35
 **/
@Data
@Accessors(chain = true)
public class Global {

    /**
     * 代码作者
     */
    private String author;

    /**
     * 输出目录
     */
    private String outputDirectory;

    /**
     * 日期时间类型
     */
    private DateTimeType dateTimeType;

    /**
     * 是否启用 Swagger
     */
    private Boolean enableSwagger = false;

}
