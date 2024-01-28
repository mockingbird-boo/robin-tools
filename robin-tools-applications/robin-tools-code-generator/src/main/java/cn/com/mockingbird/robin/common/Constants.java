package cn.com.mockingbird.robin.common;

/**
 * 常量接口
 *
 * @author zhao peng
 * @date 2024/1/28 18:14
 **/
public interface Constants {

    /**
     * 项目路径
     */
    String PROJECT_PATH = System.getProperty("user.dir");

    /**
     * 默认的输出目录
     */
    String DEFAULT_OUTPUT_DIRECTORY = PROJECT_PATH + "/temp/code-generator";

}
