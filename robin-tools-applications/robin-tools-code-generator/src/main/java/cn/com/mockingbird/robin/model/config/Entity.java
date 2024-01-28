package cn.com.mockingbird.robin.model.config;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 实体类配置
 *
 * @author zhao peng
 * @date 2024/1/22 16:02
 **/
@Data
@Accessors(chain = true)
public class Entity {

    /**
     * 实体超类
     */
    private String superClass;

    /**
     * 是否开启 Lombok
     */
    private Boolean enableLombok;

    /**
     * 是否开启链式调用
     */
    private Boolean enableChain;

    /**
     * 是否开启字段注解
     */
    private Boolean enableFieldAnnotation;

    /**
     * 是否开启列常量
     */
    private Boolean enableColumnConstant;

}
