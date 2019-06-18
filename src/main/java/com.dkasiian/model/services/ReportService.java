package com.dkasiian.model.services;

import com.dkasiian.model.dao.DaoFactory;
import com.dkasiian.model.dao.ReportDao;
import com.dkasiian.model.dto.ReportDto;
import com.dkasiian.model.entities.Report;

import java.util.List;
import java.util.Map;

public class ReportService {

    private ReportDao reportDao = DaoFactory.getInstance().createReportDao();


    public ReportDto getReportDtoById(Long reportId) { return reportDao.getReportDtoById(reportId); }

    public void addReportToConference(long conferenceId, ReportDto report) {
        reportDao.add(conferenceId, report);
    }

    public void update(ReportDto report) { reportDao.update(report); }

    public boolean delete(long id) {
        return reportDao.delete(id);
    }

    public List<Report> getAllReports(String language) {
        return reportDao.getAll(language);
    }

    public Map<Long, List<Report>> getSpeakersReports(List<Long> speakersIds, String language) {
        return reportDao.getSpeakersReports(speakersIds, language);
    }

    public int getReportsAmount() { return reportDao.getReportsCount(); }

    public int getReportsCountLinkedToConference(long conferenceId) {
        return reportDao.getReportsCountLinkedToConference(conferenceId);
    }

    public List<Report> getAllReportsLinkedToConference(long conferenceId, String language) {
        return reportDao.getAllLinkedToConference(conferenceId, language);
    }

    public List<Report> getPaginatedReportsLinkedToConference(long conferenceId,
                                                              Integer begin,
                                                              Integer recordsPerPage,
                                                              String language) {
        return reportDao.getPaginatedReportsLinkedToConference(conferenceId, begin, recordsPerPage, language);
    }
}
