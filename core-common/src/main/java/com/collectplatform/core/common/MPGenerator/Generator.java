package com.collectplatform.core.common.MPGenerator;

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
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.*;

/**
 * <p>
 *  Mybatis生成器
 * </p>
 *
 * @author Taoz
 * @since 2021-04-13
 */
public class Generator {
    private final static String parentName = "com.collectplatform.";

    public static String getValue(Object config, String func) throws Exception {
        return (String) config.getClass().getDeclaredMethod(func).invoke(config);
    }

    public static Object getConfig(String configName) throws Exception {
        Class Config = Class.forName("com.collectplatform.core.common.MPGenerator." + configName);
        return Config.newInstance();
    }

    public static void main(String[] args) throws Exception{
        String fileSep = System.getProperties().getProperty("file.separator");
        String[] dirArray = System.getProperty("user.dir").split("/|\\\\");

        String[] parentDirArray = Arrays.copyOf(dirArray, dirArray.length - 1);
        String parentDir = StringUtils.join(parentDirArray, fileSep);
        System.out.println(parentDir);


        Scanner sc = new Scanner(System.in);
        System.out.println("请输入配置类名称");
        String configName = sc.next();


        Object config = getConfig(configName);

        System.out.println("成功加载 " + configName + " 类");
        String author = getValue(config, "getAuthor");
        String dbUrl = getValue(config, "getDbUrl");
        String dbName = getValue(config, "getDbName");
        String dbPassword = getValue(config, "getDbPassword");
        System.out.println("配置人: " + author);
        System.out.println("数据库地址: " + dbUrl);
        System.out.println("数据库用户名: " + dbName);
        System.out.println("数据库密码: " + dbPassword + "\n");


        System.out.println("请输入模块/服务名称");
        String moduleName = sc.next();
        System.out.println("请输入映射的数据库表名");
        String tableName = sc.next();
        System.out.println("请输入表前缀（没有则不输入）");
        String tablePrefix = sc.next();


        AutoGenerator mpg = new AutoGenerator();
        //配置策略
        //1、全局配置
        GlobalConfig gc = new GlobalConfig();

        gc.setOutputDir(parentDir);  //设置项目生成路径
        gc.setAuthor(author);	//设置作者姓名
        gc.setOpen(false);
        gc.setFileOverride(false);  //是否覆盖
        gc.setServiceName("%sService");     //去除service的I前缀（如果不设置service前面将会多一个I）
        gc.setIdType(IdType.ASSIGN_ID);
        gc.setDateType(DateType.ONLY_DATE);
        gc.setSwagger2(false);
        mpg.setGlobalConfig(gc);

        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(dbUrl);
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername(dbName);
        dsc.setPassword(dbPassword);
        dsc.setDbType(DbType.MYSQL);
        mpg.setDataSource(dsc);

        //3、配置包
        PackageConfig pc = new PackageConfig();

        pc.setParent(parentName + moduleName);
        pc.setEntity("entity");	//设置实体类包名
        pc.setMapper("dao");
        pc.setService("service");
        pc.setController("controller");
        mpg.setPackageInfo(pc);

        //4、策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setInclude(tableName);    //设置要映射的表(可包含多个)
//        strategy.setExclude("article");	//也可设置不需要映射的表
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        if (!tablePrefix.equals("")) {
            System.out.println("表前缀" + tablePrefix);
            strategy.setTablePrefix(tablePrefix);
        }

        strategy.setEntityLombokModel(true);
        //strategy.setLogicDeleteFieldName("deleted");   //设置逻辑删除字段
        TableFill createTime = new TableFill("create_time", FieldFill.INSERT);
        TableFill updateTime = new TableFill("update_time", FieldFill.INSERT_UPDATE);
        ArrayList<TableFill> tableFills = new ArrayList<>();
        tableFills.add(createTime);
        tableFills.add(updateTime);
        strategy.setTableFillList(tableFills);
        //乐观锁
//        strategy.setVersionFieldName("version");
        strategy.setRestControllerStyle(true);
        strategy.setControllerMappingHyphenStyle(true);
        mpg.setStrategy(strategy);

        //执行
        mpg.execute();
    }
}

