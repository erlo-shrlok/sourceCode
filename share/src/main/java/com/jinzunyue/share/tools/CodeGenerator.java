package com.jinzunyue.share.tools;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;

import java.util.Collections;

/**
 * @author 李忠文
 * @version 1.0
 * @date 2022-08-06
 */
public class CodeGenerator {
    static final String URL = "jdbc:mysql://localhost:3306/mulsys2?zeroDateTimeBehavior=convertToNull&useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=GMT%2B8";

    public static void main(String[] args) {
        //String path = "/Users/haoxueyang/IdeaProjects/tea_admin/iot_perception_platform";
        String path = System.getProperty("user.dir");
        FastAutoGenerator.create(URL, "root", "root")
                .globalConfig(builder -> {
                    builder.author("") // 设置作者
//                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir(path + "/sys1/src/main/java"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder
                            .parent("com.jinzunyue.sys1") // 设置父包名
//                            .moduleName("iot_perception_platform") // 设置父包模块名
                            .entity("bean")
                            .pathInfo(Collections.singletonMap(OutputFile.mapper, path + "/sys1/src/main/resources/mapper")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("area_code") // 设置需要生成的表名
                            .addTablePrefix("iot_", "c_") // 设置过滤表前缀
                            .entityBuilder().enableLombok()//开启 lombok 模型
                            .entityBuilder().enableChainModel() //开启链式模型
                            .mapperBuilder().enableBaseResultMap() //启用 BaseResultMap
                            .controllerBuilder().enableRestStyle()//开启生成@RestController 控制器
                            .serviceBuilder().formatServiceFileName("%sService");//格式化 service 接口文件名称
                })
//                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }

}
