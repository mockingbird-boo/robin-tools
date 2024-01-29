package cn.com.mockingbird.robin.model.config;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 数据表配置
 *
 * @author zhao peng
 * @date 2024/1/22 15:35
 **/
@Data
@Accessors(chain = true)
public class Table {

    /**
     * 表匹配
     */
    private String[] tables;

    /**
     * 过滤表前缀
     */
    private String[] tablePrefixes;

    /**
     * 过滤字段前缀
     */
    private String[] fieldPrefixes;

    /**
     * 排除的数据表名
     */
    private String[] excludeTables;

}
