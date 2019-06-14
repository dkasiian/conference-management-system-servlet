package com.dkasiian.model.dao.jdbc;

import com.dkasiian.model.ResourceName;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.util.ResourceBundle;

class ConnectionPool {
    private volatile static DataSource dataSource;

    static DataSource getDataSource() {
        if (dataSource == null) {
            synchronized (ConnectionPool.class) {
                if (dataSource == null) {
                    BasicDataSource basicDataSource = new BasicDataSource();
                    ResourceBundle resourceBundle = ResourceBundle.getBundle(ResourceName.DATABASE_BUNDLE);
                    basicDataSource.setDriverClassName(resourceBundle.getString("sql.driver"));
                    basicDataSource.setUrl(resourceBundle.getString("sql.url"));
                    basicDataSource.setUsername(resourceBundle.getString("sql.user"));
                    basicDataSource.setPassword(resourceBundle.getString("sql.password"));
                    basicDataSource.setMinIdle(5);
                    basicDataSource.setMaxIdle(10);
                    basicDataSource.setMaxOpenPreparedStatements(100);
                    dataSource = basicDataSource;
                }
            }
        }
        return dataSource;
    }
}
