package cn.com.mockingbird.robin.model.config;

import cn.com.mockingbird.robin.common.Constants;
import com.baomidou.mybatisplus.annotation.IdType;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 实体策略配置
 *
 * @author zhao peng
 * @date 2024/1/22 16:02
 **/
@Data
@Accessors(chain = true)
public class Entity {

    /**
     * 实体超类
     */
    private String superClass;

    /**
     * 是否禁用生成 serialVersionUID
     */
    private Boolean disableSerialVersionUID = true;

    /**
     * 是否覆盖已生成文件
     */
    private Boolean enableFileOverride = false;

    /**
     * 是否开启链式调用
     */
    private Boolean enableChainModel = false;

    /**
     * 是否开启 Lombok
     */
    private Boolean enableLombok = false;

    /**
     * 是否开启 Boolean 类型字段移除 is 前缀
     */
    private Boolean enableRemoveIsPrefix = false;

    /**
     * 是否开启生成实体时生成字段注解
     */
    private Boolean enableTableFieldAnnotation = false;

    /**
     * 是否开启生成字段常量
     */
    private Boolean enableColumnConstant = false;

    /**
     * 是否开启 ActiveRecord 模型
     */
    private Boolean enableActiveRecord = false;

    /**
     * 乐观锁字段名（数据库字段）
     */
    private String versionColumnName;

    /**
     * 逻辑删除字段名（数据库字段）
     */
    private String logicDeleteColumnName;

    /**
     * 父类公共字段
     */
    private String[] superEntityColumns;

    /**
     * 忽略字段
     */
    private String[] ignoreColumns;

    /**
     * 全局主键类型
     */
    private IdType idType = IdType.AUTO;

    /**
     * 格式文件名称
     */
    private String formatFileName = Constants.DEFAULT_ENTITY_FILE_NAME;
}
