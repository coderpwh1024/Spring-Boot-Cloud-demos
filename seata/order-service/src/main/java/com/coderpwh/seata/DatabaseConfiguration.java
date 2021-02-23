package com.coderpwh.seata;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * @author coderpwh
 */
@Configuration
public class DatabaseConfiguration {


    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource")
    public DataSource storageDataSource() {
        return new DruidDataSource();
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.execute("TRUNCATE TABLE order_tbl");

        return jdbcTemplate;
    }


}
