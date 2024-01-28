package cn.com.mockingbird.robin.model.config;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 字段名配置
 *
 * @author zhao peng
 * @date 2024/1/22 15:58
 **/
@Data
@Accessors(chain = true)
public class Field {

    /**
     * 逻辑删除字段
     */
    private String logicDelete;

    /**
     * 乐观锁字段
     */
    private String optimisticLock;

}
