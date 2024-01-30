package cn.com.mockingbird.robin.service.impl;

import cn.com.mockingbird.robin.generator.AutoGenerator;
import cn.com.mockingbird.robin.model.GeneratorParam;
import cn.com.mockingbird.robin.service.GeneratorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 生成器 Service 实现类
 *
 * @author zhao peng
 * @date 2024/1/30 17:21
 **/
@Slf4j
@Service
public class GeneratorServiceImpl implements GeneratorService {
    @Override
    public void generate(GeneratorParam generatorParam) {
        AutoGenerator autoGenerator = new AutoGenerator(generatorParam);
        autoGenerator.execute();
    }

}
