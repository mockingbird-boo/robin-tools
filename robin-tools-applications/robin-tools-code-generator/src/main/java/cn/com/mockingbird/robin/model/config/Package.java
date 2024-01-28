package cn.com.mockingbird.robin.model.config;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 包名配置
 *
 * @author zhao peng
 * @date 2024/1/22 15:40
 **/
@Data
@Accessors(chain = true)
public class Package {

    /**
     * 实体包名
     */
    private String entity;

    /**
     * Mapper 接口包名
     */
    private String mapper;

    /**
     * Service 接口包名
     */
    private String service;

    /**
     * Service 实现类包名
     */
    private String serviceImpl;

    /**
     * Controller 包名
     */
    private String controller;

    /**
     * Mapper XML 包（文件目录）名
     */
    private String xml;

}
