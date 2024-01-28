package cn.com.mockingbird.robin.model.config;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 其他配置
 *
 * @author zhao peng
 * @date 2024/1/22 16:03
 **/
@Data
@Accessors(chain = true)
public class Other {

    /**
     * 是否开启 @Mapper 注解
     */
    private Boolean enableMapperAnnotation;

}
