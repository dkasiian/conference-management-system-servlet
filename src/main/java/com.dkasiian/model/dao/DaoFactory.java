package com.dkasiian.model.dao;

import com.dkasiian.model.dao.jdbc.JdbcDaoFactory;

public abstract class DaoFactory {

    private static volatile DaoFactory daoFactory;

    public abstract ConferenceDao createConferenceDao();

    public abstract ReportDao createReportDao();

    public abstract UserDao createUserDao();

    public static DaoFactory getInstance() {
        if (daoFactory == null) {
            synchronized (DaoFactory.class) {
                if (daoFactory == null) {
                    daoFactory = new JdbcDaoFactory();
                }
            }
        }
        return daoFactory;
    }
}

