package cn.com.mockingbird.robin.model.config;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * XML 配置
 *
 * @author zhao peng
 * @date 2024/1/22 16:02
 **/
@Data
@Accessors(chain = true)
public class Xml {

    /**
     * 是否启用 BaseResultMap
     */
    private Boolean enableBaseResultMap;

    /**
     * 是否启用 BaseColumnList
     */
    private Boolean enableBaseColumnList;

}
