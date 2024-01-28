package cn.com.mockingbird.robin.generator;

import cn.com.mockingbird.robin.common.Constants;
import cn.com.mockingbird.robin.common.DateTimeType;
import cn.com.mockingbird.robin.common.util.BranchUtils;
import cn.com.mockingbird.robin.model.config.*;
import cn.com.mockingbird.robin.model.*;
import cn.com.mockingbird.robin.model.config.Package;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.builder.Entity;
import com.baomidou.mybatisplus.generator.config.builder.Mapper;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
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
                .execute();
    }

    public DataSourceConfig.Builder datasourceConfigBuilder() {
        Database database = generatorParam.getDatabase();
        return new DataSourceConfig.Builder(database.getUrl(), database.getUsername(), database.getPassword());
    }

    public void globalConfigBuilder(GlobalConfig.Builder builder) {
        Global global = generatorParam.getGlobal();
        String author = global.getAuthor();
        builder.author(author);
        String outputDirectory = global.getOutputDirectory();
        BranchUtils.isTureOrFalse(StringUtils.isNotBlank(outputDirectory)).trueOrFalseHandle(
                () -> builder.outputDir(outputDirectory),
                () -> builder.outputDir(Constants.DEFAULT_OUTPUT_DIRECTORY)
        );
        DateTimeType dateTimeType = global.getDateTimeType();
        BranchUtils.isTureOrFalse(dateTimeType == DateTimeType.DATE).trueOrFalseHandle(
                () -> builder.dateType(DateType.ONLY_DATE),
                () -> builder.dateType(DateType.TIME_PACK)
        );
        if (global.getEnableSwagger()) {
            builder.enableSwagger();
        }
    }

    public void packageConfigBuilder(PackageConfig.Builder builder) {
        Package pkg = generatorParam.getPkg();
        builder.parent(generatorParam.getGlobal().getPkg())
                .controller(pkg.getController())
                .service(pkg.getService())
                .serviceImpl(pkg.getServiceImpl())
                .mapper(pkg.getMapper())
                .xml(pkg.getXml())
                .entity(pkg.getEntity());
    }

    /**
     * 生成策略配置
     * @param builder 生成策略构建者
     */
    public void strategyConfigBuilder(StrategyConfig.Builder builder) {
        Table table = generatorParam.getTable();
        File file = generatorParam.getFile();
        Field field = generatorParam.getField();
        builder.addInclude(table.getTables())
                .addTablePrefix(table.getTablePrefixes())
                .addFieldPrefix(table.getColumnPrefixes())
                .addExclude(table.getExcludeTables())
                .entityBuilder()
                .naming(NamingStrategy.underline_to_camel)
                .formatFileName(file.getEntity())
                .idType(IdType.ASSIGN_ID)
                .logicDeleteColumnName(field.getLogicDelete())
                .versionColumnName(field.getOptimisticLock())
                .superClass(generatorParam.getGlobal().getEntitySuperClass())
                .addIgnoreColumns(table.getIgnoredColumns())
                .mapperBuilder()
                .formatMapperFileName(file.getMapper())
                .formatXmlFileName(file.getXml())
                .serviceBuilder()
                .formatServiceFileName(file.getService())
                .formatServiceImplFileName(file.getServiceImpl())
                .controllerBuilder()
                .formatFileName(file.getController())
                .enableRestStyle()
                .enableHyphenStyle();

        Entity.Builder entityBuilder = builder.entityBuilder();
        if (generatorParam.getEntity().getEnableChain()) {
            entityBuilder.enableChainModel();
        }
        if (generatorParam.getEntity().getEnableLombok()) {
            entityBuilder.enableLombok();
        }
        if (generatorParam.getEntity().getEnableColumnConstant()) {
            entityBuilder.enableColumnConstant();
        }
        if (generatorParam.getEntity().getEnableFieldAnnotation()) {
            entityBuilder.enableTableFieldAnnotation();
        }

        Mapper.Builder mapperBuilder = builder.mapperBuilder();

        if (generatorParam.getXml().getEnableBaseResultMap()) {
            mapperBuilder.enableBaseResultMap();
        }
        if (generatorParam.getXml().getEnableBaseColumnList()) {
            mapperBuilder.enableBaseColumnList();
        }
        if (generatorParam.getOther().getEnableMapperAnnotation()) {
            mapperBuilder.mapperAnnotation(org.apache.ibatis.annotations.Mapper.class);
        }
    }
}
