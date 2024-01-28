package cn.com.mockingbird.robin.model.config;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 文件名配置
 *
 * @author zhao peng
 * @date 2024/1/22 15:52
 **/
@Data
@Accessors(chain = true)
public class File {

    /**
     * 实体类文件名
     */
    private String entity;

    /**
     * Mapper 接口文件名
     */
    private String mapper;

    /**
     * Service 接口文件名
     */
    private String service;

    /**
     * Service 接口实现类文件名
     */
    private String serviceImpl;

    /**
     * Controller 文件名
     */
    private String controller;

    /**
     * Mapper XML 文件名
     */
    private String xml;

}
