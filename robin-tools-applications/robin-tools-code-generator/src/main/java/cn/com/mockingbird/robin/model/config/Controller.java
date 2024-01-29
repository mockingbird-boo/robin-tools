package cn.com.mockingbird.robin.model.config;

import cn.com.mockingbird.robin.common.Constants;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Controller 策略配置
 *
 * @author zhao peng
 * @date 2024/1/29 20:24
 **/
@Data
@Accessors(chain = true)
public class Controller {

    /**
     * Controller 超类
     */
    private String superClass;

    /**
     * 是否覆盖已生成文件
     */
    private Boolean enableFileOverride = Boolean.FALSE;

    /**
     * 是否开启驼峰转连字符
     */
    private Boolean enableHyphenStyle = Boolean.FALSE;

    /**
     * 是否开启生成 @RestController 控制器
     */
    private Boolean enableRestStyle = Boolean.FALSE;

    /**
     * 格式化文件名称
     */
    private String formatFileName = Constants.DEFAULT_CONTROLLER_FILE_NAME;

}
