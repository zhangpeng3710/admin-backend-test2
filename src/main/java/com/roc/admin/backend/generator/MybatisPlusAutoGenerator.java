package com.roc.admin.backend.generator;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.TemplateType;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.apache.ibatis.jdbc.ScriptRunner;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static com.baomidou.mybatisplus.generator.config.builder.GeneratorBuilder.globalConfig;
import static com.baomidou.mybatisplus.generator.config.builder.GeneratorBuilder.strategyConfig;

/**
 * <p>
 * 快速生成
 * </p>
 *
 * @author lanjerry
 * @since 2021-09-16
 */
public class MybatisPlusAutoGenerator {
    /**
     * 执行数据库脚本
     */
    protected static void initDataSource(DataSourceConfig dataSourceConfig) throws SQLException {
        Connection conn = dataSourceConfig.getConn();
        InputStream inputStream = com.roc.admin.backend.AdminBackendApplication.class.getResourceAsStream("/db/schema.sql");
        ScriptRunner scriptRunner = new ScriptRunner(conn);
        scriptRunner.setAutoCommit(true);
        assert inputStream != null;
        scriptRunner.runScript(new InputStreamReader(inputStream));
        conn.close();
    }

    /**
     * 数据源配置
     */
    private static final DataSourceConfig.Builder DATA_SOURCE_CONFIG = new DataSourceConfig
            .Builder("jdbc:mysql://localhost:3306/tomin", "tomin", "F-p6AE!om");

    /**
     * 执行 run
     */
    public static void main(String[] args) throws SQLException {
        List<String> tables = Arrays.asList(
                "rbac_tenant",
                "rbac_user",
                "rbac_role",
                "rbac_user_role_tenant_relation",
                "rbac_auth",
                "rbac_role_auth_relation",
                "rbac_element",
                "rbac_auth_element_relation",
                "rbac_interface",
                "rbac_auth_interface_relation",
                "meta_app",
                "meta_cluster",
                "meta_tomcat",
                "meta_baseline",
                "meta_tomcat_baseline_relation",
                "meta_baseline_detail",
                "meta_agent",
                "meta_agent_config",
                "meta_agent_config_relation",
                "mgr_inst",
                "mgr_app_view",
                "mgr_cluster_view",
                "mgr_agent",
                "mgr_task",
                "mgr_task_detail",
                "sys_audit_login",
                "sys_audit_access",
                "sys_audit_data",
                "sys_message"
        );
        String path = System.getProperty("user.dir");


        // 初始化数据库脚本
        //    initDataSource(DATA_SOURCE_CONFIG.build());
        FastAutoGenerator.create(DATA_SOURCE_CONFIG)
                // 全局配置
                .globalConfig(builder -> builder
                        .author("mybatis-plus-auto-generator")
                        .disableOpenDir()
                        .commentDate("yyyy--MM-dd HH:mm:ss")
                        .dateType(DateType.ONLY_DATE)
                        .outputDir(path + "/src/main/java")
                        .enableSwagger()
                )
                // 包配置
                .packageConfig(builder ->
                                builder.parent("com.roc.admin.backend")
                                        .moduleName("dao")
                        //设置mapper xml位置
                        //.pathInfo(Collections.singletonMap(OutputFile.xml,"/Users/zp/IdeaProjects/mybatis-plus-samples/demo/src/main/resources/mapper"))
                )
                // 策略配置
                .strategyConfig(builder -> builder
                        .addInclude(tables)
                        .entityBuilder()
                        .enableLombok()
                        .enableTableFieldAnnotation()
                        .enableActiveRecord()
                        .enableChainModel()
                        .mapperBuilder()
                        .enableMapperAnnotation()
                )
                /*
                    模板引擎配置，默认 Velocity 可选模板引擎 Beetl 或 Freemarker 或 Enjoy
                   .templateEngine(new BeetlTemplateEngine())
                   .templateEngine(new FreemarkerTemplateEngine())
                   .templateEngine(new EnjoyTemplateEngine())
                 */
                .templateEngine(new FreemarkerTemplateEngine())
                .templateConfig(builder -> {
                    builder.disable(TemplateType.CONTROLLER)
                            .disable(TemplateType.SERVICE)
                            .disable(TemplateType.SERVICE_IMPL).disable(TemplateType.XML);
                })
                .execute();
    }
}
