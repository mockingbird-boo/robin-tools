package cn.com.mockingbird.robin.generator;

import cn.com.mockingbird.robin.common.Constants;
import cn.com.mockingbird.robin.common.DateTimeType;
import cn.com.mockingbird.robin.common.constant.Standard;
import cn.com.mockingbird.robin.common.util.BranchUtils;
import cn.com.mockingbird.robin.model.GeneratorParam;
import cn.com.mockingbird.robin.model.config.Database;
import cn.com.mockingbird.robin.model.config.Global;
import cn.com.mockingbird.robin.model.config.Package;
import cn.com.mockingbird.robin.model.config.Table;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.builder.Controller;
import com.baomidou.mybatisplus.generator.config.builder.Entity;
import com.baomidou.mybatisplus.generator.config.builder.Mapper;
import com.baomidou.mybatisplus.generator.config.builder.Service;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import org.apache.commons.lang3.StringUtils;

/**
 * 自动生成器
 *
 * @author zhao peng
 * @date 2024/1/18 0:43
 **/
public class AutoGenerator {

    private final GeneratorParam generatorParam;

    public AutoGenerator(GeneratorParam generatorParam) {
        this.generatorParam = generatorParam;
    }

    public void execute() {
        FastAutoGenerator.create(datasourceConfigBuilder())
                .globalConfig(this::globalConfigBuilder)
                .packageConfig(this::packageConfigBuilder)
                .strategyConfig(this::strategyConfigBuilder)
                .execute();
    }

    public DataSourceConfig.Builder datasourceConfigBuilder() {
        Database database = generatorParam.getDatabase();
        return new DataSourceConfig.Builder(
                database.getUrl(),
                database.getUsername(),
                database.getPassword())
                .schema(database.getSchema());
    }

    public void globalConfigBuilder(GlobalConfig.Builder builder) {
        Global global = generatorParam.getGlobal();
        // 输出目录
        String outputDirectory = global.getOutputDirectory();
        BranchUtils.isTureOrFalse(StringUtils.isNotBlank(outputDirectory)).trueOrFalseHandle(
                () -> builder.outputDir(outputDirectory),
                () -> builder.outputDir(Constants.DEFAULT_OUTPUT_DIRECTORY)
        );
        // 作者名
        String author = global.getAuthor();
        builder.author(author);
        // 是否开启 Swagger
        if (global.getEnableSwagger()) {
            builder.enableSwagger();
        }
        // 时间策略
        DateTimeType dateTimeType = global.getDateTimeType();
        BranchUtils.isTureOrFalse(dateTimeType == DateTimeType.DATE).trueOrFalseHandle(
                () -> builder.dateType(DateType.ONLY_DATE),
                () -> builder.dateType(DateType.TIME_PACK)
        );
        // 注释日期
        builder.commentDate(Standard.DateTimePattern.DATETIME);
    }

    public void packageConfigBuilder(PackageConfig.Builder builder) {
        Package pkg = generatorParam.getPkg();
        builder.parent(pkg.getParent())
                .entity(pkg.getEntity())
                .service(pkg.getService())
                .serviceImpl(pkg.getServiceImpl())
                .mapper(pkg.getMapper())
                .xml(pkg.getXml())
                .controller(pkg.getController());
    }

    /**
     * 生成策略配置
     * @param builder 生成策略构建者
     */
    public void strategyConfigBuilder(StrategyConfig.Builder builder) {
        Table table = generatorParam.getTable();

        builder // 数据表匹配
                .addInclude(table.getTables())
                // 过滤表前缀
                .addTablePrefix(table.getTablePrefixes())
                // 过滤字段前缀
                .addFieldPrefix(table.getFieldPrefixes());

        // 实体策略配置
        Entity.Builder entityBuilder = builder.entityBuilder();
        entityStrategyConfig(entityBuilder, generatorParam);

        // Controller 策略配置
        Controller.Builder controllerBuilder = builder.controllerBuilder();
        controllerStrategyConfig(controllerBuilder, generatorParam);

        // Service 策略配置
        Service.Builder serviceBuilder = builder.serviceBuilder();
        serviceStrategyConfig(serviceBuilder, generatorParam);

        // Mapper 策略配置
        Mapper.Builder mapperBuilder = builder.mapperBuilder();
        mapperStrategyConfig(mapperBuilder, generatorParam);
    }

    private void mapperStrategyConfig(Mapper.Builder mapperBuilder, GeneratorParam generatorParam) {
        mapperBuilder.superClass(generatorParam.getMapper().getSuperClass())
                .formatMapperFileName(generatorParam.getMapper().getFormatMapperFileName())
                .formatXmlFileName(generatorParam.getMapper().getFormatXmlFileName());
        if (Boolean.TRUE.equals(generatorParam.getMapper().getEnableMapperAnnotation())) {
            mapperBuilder.mapperAnnotation(org.apache.ibatis.annotations.Mapper.class);
        }
        if (Boolean.TRUE.equals(generatorParam.getMapper().getEnableBaseResultMap())) {
            mapperBuilder.enableBaseResultMap();
        }
        if (Boolean.TRUE.equals(generatorParam.getMapper().getEnableBaseColumnList())) {
            mapperBuilder.enableBaseColumnList();
        }
        if (Boolean.TRUE.equals(generatorParam.getMapper().getEnableFileOverride())) {
            mapperBuilder.enableFileOverride();
        }
    }

    private void serviceStrategyConfig(Service.Builder serviceBuilder, GeneratorParam generatorParam) {
        serviceBuilder.superServiceClass(generatorParam.getService().getSuperServiceClass())
                .superServiceImplClass(generatorParam.getService().getSuperServiceImplClass())
                .formatServiceFileName(generatorParam.getService().getFormatServiceFileName())
                .formatServiceImplFileName(generatorParam.getService().getFormatServiceImplFileName());
        if (Boolean.TRUE.equals(generatorParam.getService().getEnableFileOverride())) {
            serviceBuilder.enableFileOverride();
        }
    }

    private void controllerStrategyConfig(Controller.Builder controllerBuilder, GeneratorParam generatorParam) {
        controllerBuilder.superClass(generatorParam.getController().getSuperClass())
                .formatFileName(generatorParam.getController().getFormatFileName());
        if (Boolean.TRUE.equals(generatorParam.getController().getEnableFileOverride())) {
            controllerBuilder.enableFileOverride();
        }
        if (Boolean.TRUE.equals(generatorParam.getController().getEnableHyphenStyle())) {
            controllerBuilder.enableHyphenStyle();
        }
        if (Boolean.TRUE.equals(generatorParam.getController().getEnableRestStyle())) {
            controllerBuilder.enableRestStyle();
        }
    }


    private void entityStrategyConfig(Entity.Builder entityBuilder, GeneratorParam generatorParam) {
        // 超类设置
        entityBuilder.superClass(generatorParam.getEntity().getSuperClass());
        // 是否禁用生成 serialVersionUID
        if (Boolean.TRUE.equals(generatorParam.getEntity().getDisableSerialVersionUID())) {
            entityBuilder.disableSerialVersionUID();
        }
        // 是否覆盖已生成文件
        if (Boolean.TRUE.equals(generatorParam.getEntity().getEnableFileOverride())) {
            entityBuilder.enableFileOverride();
        }
        // 是否启用链式模型
        if (Boolean.TRUE.equals(generatorParam.getEntity().getEnableChainModel())) {
            entityBuilder.enableChainModel();
        }
        // 是否启用 Lombok 模型
        if (Boolean.TRUE.equals(generatorParam.getEntity().getEnableLombok())) {
            entityBuilder.enableLombok();
        }
        // 是否开启 Boolean 类型字段移除 is 前缀
        if (Boolean.TRUE.equals(generatorParam.getEntity().getEnableRemoveIsPrefix())) {
            entityBuilder.enableRemoveIsPrefix();
        }
        // 是否开启生成实体时生成字段注解
        if (Boolean.TRUE.equals(generatorParam.getEntity().getEnableTableFieldAnnotation())) {
            entityBuilder.enableTableFieldAnnotation();
        }
        // 是否开启生成字段常量
        if (Boolean.TRUE.equals(generatorParam.getEntity().getEnableColumnConstant())) {
            entityBuilder.enableColumnConstant();
        }
        // 是否开启 ActiveRecord 模型
        if (Boolean.TRUE.equals(generatorParam.getEntity().getEnableActiveRecord())) {
            entityBuilder.enableActiveRecord();
        }
        // 乐观锁字段
        entityBuilder.versionColumnName(generatorParam.getEntity().getVersionColumnName());
        // 逻辑删除字段
        entityBuilder.logicDeleteColumnName(generatorParam.getEntity().getLogicDeleteColumnName());
        // 父类公共字段
        entityBuilder.addSuperEntityColumns(generatorParam.getEntity().getSuperEntityColumns());
        // 忽略字段
        entityBuilder.addIgnoreColumns(generatorParam.getEntity().getIgnoreColumns());
        // 全局 ID 策略
        entityBuilder.idType(generatorParam.getEntity().getIdType());
        // 格式化文件名称
        entityBuilder.formatFileName(generatorParam.getEntity().getFormatFileName());
    }
}
