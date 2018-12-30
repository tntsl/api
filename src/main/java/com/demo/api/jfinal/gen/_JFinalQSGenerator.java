package com.demo.api.jfinal.gen;

import com.google.common.collect.Lists;
import com.jfinal.kit.PathKit;
import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.generator.Generator;

import javax.sql.DataSource;
import java.io.File;
import java.util.List;

/**
 * 在数据库表有任何变动时，运行一下 main 方法，极速响应变化进行代码重构
 */
public class _JFinalQSGenerator {

    private static DataSource getMasterDataSource() {
        Prop prop = PropKit.use("db-conf-generator.properties");
        String url = prop.get("spring.datasource.url");
        String username = prop.get("spring.datasource.username");
        String password = prop.get("spring.datasource.password");
        HikariCpPlugin druidPlugin = new HikariCpPlugin(url, username, password);
        druidPlugin.start();
        return druidPlugin.getDataSource();
    }

    private static void configExcludingTable(Generator generator) {
        generator.addExcludedTable("vm_crm_room_filter","dt_citys_home_module","dt_contract_property_owner","dt_mortgage_information_bak");
    }

    public static void main(String[] args) {
        // base model 所使用的包名
        String baseModelPackageName = "com.demo.api.jfinal.model.base";
        // base model 文件保存路径
        String baseModelOutputDir = PathKit.getWebRootPath() + "/src/main/java/com/demo/api/jfinal/model/base";
        File directory = new File(PathKit.getWebRootPath() + "/src/main/java/com/demo/api/jfinal/model");
        List<File> deleteFailedFiles = Lists.newArrayList();
        deleteFile(directory, deleteFailedFiles);
        if (!deleteFailedFiles.isEmpty()) {
            for (File file : deleteFailedFiles) {
                System.err.println("以下文件删除失败：" + file.getName());
            }
        }
        // model 所使用的包名 (MappingKit 默认使用的包名)
        String modelPackageName = "com.demo.api.jfinal.model";
        // model 文件保存路径 (MappingKit 与 DataDictionary 文件默认保存路径)
        String modelOutputDir = baseModelOutputDir + "/..";

        // 创建生成器
        DataSource dataSource = getMasterDataSource();
        MyGenerator generator = new MyGenerator(dataSource, baseModelPackageName, baseModelOutputDir, modelPackageName, modelOutputDir);
        // 添加不需要生成的表名
        configExcludingTable(generator);
        // 设置是否在 Model 中生成 dao 对象
        generator.setGenerateDaoInModel(true);
        // 设置是否生成字典文件
        generator.setGenerateDataDictionary(true);
        // 生成
        generator.generate();
    }

    public static void deleteFile(File file, List<File> failedFiles) {
        if (file.exists()) {
            if (file.isDirectory()) {
                File[] files = file.listFiles();
                for (File subFile : files) {
                    deleteFile(subFile, failedFiles);
                }
            } else {
                boolean deleteFlag = file.delete();
                if (!deleteFlag) {
                    failedFiles.add(file);
                }
            }
        }
    }
}
