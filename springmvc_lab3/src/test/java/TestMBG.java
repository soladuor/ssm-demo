import org.junit.Test;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class TestMBG {

/*
MBG（MyBatis Generator）：
    1. 一个专门为MyBatis框架使用者定制的代码生成器
    2. 可以快速的根据表生成对应的映射文件，接口，以及bean类。
    3. 可以生成单表CRUD【也能生成单表QBC风格CRUD】，但是表连接、存储过程等这些复杂sq的定义需要手工编写
    4. 官方文档地址 http://www.mybatis.org/generator/

MBG基本使用
    1. 导入jar包
    2. 编写配置文件
        ① 配置文件名称：mbg.xml（官方给的名字叫 generatorConfig.xml）
        ② 配置文件位置：resources
    3. 运行程序【代码生成器】

 */

    @Test
    public void testMBG() throws Exception {
        // 注：mybatis-generator-core 版本如果再1.3.7以上，则这个项目的log4j不会打印日志（自己测的）
        List<String> warnings = new ArrayList<String>();
        boolean overwrite = true;
        // file路径加载方式是从项目根路径开始【项目根具体得看项目和模块之间的逻辑】
        File configFile = new File("src/main/resources/mbg.xml");
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);
    }
}
