package com.demo.api.common.config;

import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.demo.api.jfinal.model._MappingKit;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * @author Lye
 * HikariCP连接池配置
 */
@Configuration
public class DataSourceConfig {

    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource.ds1")
    public DataSourceProperties firstDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "ds1")
    @Primary
    public DataSource firstDataSource() {
        return firstDataSourceProperties().initializeDataSourceBuilder().build();
    }

    @Bean
    @DependsOn("ds1")
    public ActiveRecordPlugin activeFirstDatasource() {
        ActiveRecordPlugin plugin = new ActiveRecordPlugin("ds1", firstDataSource());
        plugin.setShowSql(true);
        _MappingKit.mapping(plugin);
        plugin.start();
        return plugin;
    }
}
