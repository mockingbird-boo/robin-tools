package cn.com.mockingbird.robin.model;

import cn.com.mockingbird.robin.model.config.Package;
import cn.com.mockingbird.robin.model.config.*;
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
     * 数据表配置
     */
    private Table table;

    /**
     * 包名配置
     */
    private Package pkg;

    /**
     * 实体策略配置
     */
    private Entity entity;

    /**
     * Controller 策略配置
     */
    private Controller controller;

    /**
     * Service 策略配置
     */
    private Service service;

    /**
     * Mapper 策略配置
     */
    private Mapper mapper;

}
