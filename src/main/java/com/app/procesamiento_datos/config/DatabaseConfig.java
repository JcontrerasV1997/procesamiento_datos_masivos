package com.app.procesamiento_datos.config;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

public interface DatabaseConfig {

        DataSource crearDatsource();

        JdbcTemplate crearJdbcTemplate(DataSource dataSource);

}
