package com.demo.api.jfinal.gen;

import com.jfinal.plugin.activerecord.generator.BaseModelGenerator;
import com.jfinal.plugin.activerecord.generator.Generator;
import com.jfinal.plugin.activerecord.generator.ModelGenerator;

import javax.sql.DataSource;

public class MyGenerator extends Generator {

    public MyGenerator(DataSource dataSource, String baseModelPackageName, String baseModelOutputDir, String modelPackageName, String modelOutputDir) {
        super(dataSource, baseModelPackageName, baseModelOutputDir, modelPackageName, modelOutputDir);
        setMetaBuilder(new MyMetaBuilder(dataSource));
    }

    public MyGenerator(DataSource dataSource, String baseModelPackageName, String baseModelOutputDir) {
        super(dataSource, baseModelPackageName, baseModelOutputDir);
        setMetaBuilder(new MyMetaBuilder(dataSource));
    }

    public MyGenerator(DataSource dataSource, BaseModelGenerator baseModelGenerator) {
        super(dataSource, baseModelGenerator);
        setMetaBuilder(new MyMetaBuilder(dataSource));
    }

    public MyGenerator(DataSource dataSource, BaseModelGenerator baseModelGenerator, ModelGenerator modelGenerator) {
        super(dataSource, baseModelGenerator, modelGenerator);
        setMetaBuilder(new MyMetaBuilder(dataSource));
    }

    public void addIncludedTable(String tableName) {
        MyMetaBuilder builder = (MyMetaBuilder) metaBuilder;
        builder.addIncludedTable(tableName);
    }
}
