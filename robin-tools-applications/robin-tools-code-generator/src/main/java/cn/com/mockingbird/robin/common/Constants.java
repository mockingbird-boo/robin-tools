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

    String DEFAULT_ENTITY_FILE_NAME = "%sEntity";

    String DEFAULT_CONTROLLER_FILE_NAME = "%sController";

    String DEFAULT_SERVICE_FILE_NAME = "%sService";

    String DEFAULT_SERVICE_IMPL_FILE_NAME = "%sServiceImpl";

    String DEFAULT_MAPPER_FILE_NAME = "%sMapper";

    String DEFAULT_MAPPER_XML_FILE_NAME = "%sMapper";

}
