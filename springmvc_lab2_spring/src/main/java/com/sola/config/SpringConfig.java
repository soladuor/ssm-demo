package com.sola.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@ComponentScan({"com.sola.service"})
@Import({JdbcConfig.class, MybatisConfig.class})
@EnableTransactionManagement // 开启事务注解
public class SpringConfig {
}
