package com.dkasiian.model.dao;

import com.dkasiian.model.dto.ReportDto;
import com.dkasiian.model.entities.Report;

import java.util.List;
import java.util.Map;

public interface ReportDao extends GenericDao<Report>{

    void add(long conferenceId, ReportDto report);

    void update(ReportDto reportDto);

    List<Report> getAllLinkedToConference(long conferenceId, String language);

    ReportDto getReportDtoById(long reportId);

    Map<Long, List<Report>> getSpeakersReports(List<Long> speakersIds, String language);

    int getReportsAmount();

    int getReportsAmountLinkedToConference(long conferenceId);

    List<Report> getPaginatedReportsLinkedToConference(long conferenceId,
                                                       Integer begin,
                                                       Integer recordsPerPage,
                                                       String language);
}
