package com.dkasiian.model.dao.jdbc;

import com.dkasiian.model.dao.ConferenceDao;
import com.dkasiian.model.dao.DaoFactory;
import com.dkasiian.model.dao.ReportDao;
import com.dkasiian.model.dao.UserDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.DataSource;

public class JdbcDaoFactory extends DaoFactory {

    @Override
    public ConferenceDao createConferenceDao() {
        return new JdbcConferenceDao();
    }

    @Override
    public ReportDao createReportDao() {
        return new JdbcReportDao();
    }

    @Override
    public UserDao createUserDao() {
        return new JdbcUserDao();
    }

}
