package cn.com.mockingbird.robin.model.config;

import cn.com.mockingbird.robin.common.Constants;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Mapper 策略配置
 *
 * @author zhao peng
 * @date 2024/1/29 21:22
 **/
@Data
@Accessors(chain = true)
public class Mapper {

    /**
     * 超类
     */
    private String superClass;

    /**
     * 是否覆盖已生成文件
     */
    private Boolean enableFileOverride = Boolean.FALSE;

    /**
     * 是否开启 @Mapper 注解
     */
    private Boolean enableMapperAnnotation = Boolean.FALSE;

    /**
     * 是否启用 BaseResultMap 生成
     */
    private Boolean enableBaseResultMap = Boolean.FALSE;

    /**
     * 是否启用 BaseColumnList 生成
     */
    private Boolean enableBaseColumnList = Boolean.FALSE;

    /**
     * 格式化 mapper 文件名称
     */
    private String formatMapperFileName = Constants.DEFAULT_MAPPER_FILE_NAME;

    /**
     * 格式化 mapper xml 文件名称
     */
    private String formatXmlFileName = Constants.DEFAULT_MAPPER_XML_FILE_NAME;

}
