package com.app.procesamiento_datos.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class DataBaseConfig2 implements DatabaseConfig{

    private final String url="jdbc:h2:mem:DB2;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE";
    private final String driverClassName="org.h2.Driver";
    private final String username="sa";
    private final String password="";
    @Override
    public DataSource crearDatsource() {
        return DataSourceBuilder.create()
                .url(url)
                .driverClassName(driverClassName)
                .username(username)
                .password(password)
                .build();
    }

    @Override
    public JdbcTemplate crearJdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
