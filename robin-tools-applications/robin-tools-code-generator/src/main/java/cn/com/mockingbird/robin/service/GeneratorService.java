package cn.com.mockingbird.robin.service;

import cn.com.mockingbird.robin.model.GeneratorParam;

/**
 * 生成器 Service
 *
 * @author zhao peng
 * @date 2024/1/30 1:30
 **/
public interface GeneratorService {

    /**
     * 代码生成
     * @param generatorParam 生成器参数
     */
    void generate(GeneratorParam generatorParam);

}
