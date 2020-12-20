package com.springboot.express;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

/**
 * @author 魅力彩虹派
 * @create 2020-10-19 14:14
 */
public class Gengrator {
    @Test
    public void run() {

        // 1、创建代码生成器
        AutoGenerator mpg = new AutoGenerator();
        String projectPath = System.getProperty("user.dir");
        // 2、全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(projectPath+ "/src/main/java");
        // gc.setOutputDir("E:\\WorkPlace\\IdeaWorkPlace\\findTeam\\service\\service_meili" + "/src/main/java");

        gc.setAuthor("lbl");
        gc.setOpen(false); //生成后是否打开资源管理器
        gc.setFileOverride(true); //重新生成时文件是否覆盖

        //UserServie
        gc.setServiceName("%sService");	//去掉Service接口的首字母I

        gc.setIdType(IdType.ID_WORKER); //主键策略
        gc.setDateType(DateType.ONLY_DATE);//定义生成的实体类中日期类型
        gc.setSwagger2(true);//开启Swagger2模式,包含注释

        mpg.setGlobalConfig(gc);

        // 3、数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:3306/rsc?serverTimezone=GMT%2B8");
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("lbl123...");
        dsc.setDbType(DbType.MYSQL);
        mpg.setDataSource(dsc);

        // 4、包配置
        PackageConfig pc = new PackageConfig();
      //  pc.setModuleName("findteam"); //模块名
        //包  home.sise.servicemeili
        pc.setParent("com.springboot.express");
        //包  home.sise.servicemeili.controller
        pc.setController("controller");
        pc.setEntity("entity");
        pc.setService("service");
        pc.setMapper("mapper");
        mpg.setPackageInfo(pc);

        // 5、策略配置
        StrategyConfig strategy = new StrategyConfig();

        //要设置生成哪些表 如果不设置就是生成所有的表
        strategy.setInclude("attendance");

        strategy.setNaming(NamingStrategy.underline_to_camel);//数据库表映射到实体的命名策略
        strategy.setTablePrefix(pc.getModuleName() + "_"); //生成实体时去掉表前缀

        strategy.setColumnNaming(NamingStrategy.underline_to_camel);//数据库表字段映射到实体的命名策略
        strategy.setEntityLombokModel(true); // lombok 模型 @Accessors(chain = true) setter链式操作



        //strategy.setLogicDeleteFieldName("deleted");//设置逻辑删除

        //设置自动生成创建、修改时间
        //TableFill create = new TableFill("create_time", FieldFill.INSERT);
        //TableFill update = new TableFill("update_time", FieldFill.INSERT_UPDATE);
        //ArrayList<TableFill> tableFills = new ArrayList();
        //tableFills.add(create);
        //tableFills.add(update);
        //strategy.setTableFillList(tableFills);

        //strategy.setVersionFieldName("version");//设置乐观锁

        strategy.setRestControllerStyle(true); //restful api风格控制器
        //strategy.setControllerMappingHyphenStyle(true); //url中驼峰转连字符
        mpg.setStrategy(strategy);
        // 6、执行
        mpg.execute();
    }
}
