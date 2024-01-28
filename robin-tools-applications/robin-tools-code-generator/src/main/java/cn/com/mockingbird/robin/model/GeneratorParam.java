package cn.com.mockingbird.robin.model;

import cn.com.mockingbird.robin.model.config.*;
import cn.com.mockingbird.robin.model.config.Package;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 生成器参数
 *
 * @author zhao peng
 * @date 2024/1/22 2:09
 **/
@Data
@Accessors(chain = true)
public class GeneratorParam {

    /**
     * 全局配置
     */
    private Global global;

    /**
     * 数据库配置
     */
    private Database database;

    /**
     * 可选配置
     */
    private Optional optional;

    /**
     * 数据表配置
     */
    private Table table;

    /**
     * 包名配置
     */
    private Package pkg;

    /**
     * 文件名配置
     */
    private File file;

    /**
     * 字段名配置
     */
    private Field field;

    /**
     * 实体类配置
     */
    private Entity entity;

    /**
     * XML 文件配置
     */
    private Xml xml;

    /**
     * 其他配置
     */
    private Other other;
}
