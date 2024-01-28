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
     * 表名
     */
    private String[] tables;

    /**
     * 忽略的数据列
     */
    private String[] ignoredColumns;

    /**
     * 数据表前缀
     */
    private String[] tablePrefixes;

    /**
     * 数据列前缀
     */
    private String[] columnPrefixes;

    /**
     * 排除的数据表名
     */
    private String[] excludeTables;

}
