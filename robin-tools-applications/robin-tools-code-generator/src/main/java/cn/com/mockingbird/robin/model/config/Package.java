package cn.com.mockingbird.robin.model.config;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 包配置
 *
 * @author zhao peng
 * @date 2024/1/22 15:40
 **/
@Data
@Accessors(chain = true)
public class Package {

    /**
     * 父包名
     */
    private String parent;

    /**
     * 父包模块名
     */
    private String module;

    /**
     * 实体包名
     */
    private String entity;

    /**
     * Service 接口包名
     */
    private String service;

    /**
     * Service 实现类包名
     */
    private String serviceImpl;

    /**
     * Mapper 接口包名
     */
    private String mapper;

    /**
     * Mapper XML 包（文件目录）名
     */
    private String xml;

    /**
     * Controller 包名
     */
    private String controller;
}
