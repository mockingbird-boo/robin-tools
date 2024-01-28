package cn.com.mockingbird.robin.model.config;

import cn.com.mockingbird.robin.common.DateTimeType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
     * 项目包名
     */
    @NotBlank(message = "项目包名要求非空")
    private String pkg;

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
    @NotNull(message = "是否启用 Swagger 要求非空")
    private Boolean enableSwagger = false;

}
