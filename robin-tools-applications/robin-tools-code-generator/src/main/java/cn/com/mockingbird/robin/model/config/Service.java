package cn.com.mockingbird.robin.model.config;

import cn.com.mockingbird.robin.common.Constants;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Service 策略配置
 *
 * @author zhao peng
 * @date 2024/1/29 21:17
 **/
@Data
@Accessors(chain = true)
public class Service {

    /**
     * service 接口父类
     */
    private String superServiceClass;

    /**
     * service 实现类父类
     */
    private String superServiceImplClass;

    /**
     * 是否覆盖已生成文件
     */
    private Boolean enableFileOverride = Boolean.FALSE;

    /**
     * 格式化 service 接口文件名称
     */
    private String formatServiceFileName = Constants.DEFAULT_SERVICE_FILE_NAME;

    /**
     * 格式化 service 实现类文件名称
     */
    private String formatServiceImplFileName = Constants.DEFAULT_SERVICE_IMPL_FILE_NAME;

}
