package com.dkasiian.model;

import com.dkasiian.model.dto.ReportDto;
import com.dkasiian.model.services.ReportService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ReportServiceTest {

    private static ReportService reportService;

    @BeforeAll
    static void init(){
        reportService = new ReportService();
    }

    @Test
    void getNonExistenceReportDtoById(){
        ReportDto reportDto = reportService.getReportDtoById(1000L);
        assertNull(reportDto);
    }

    @Test
    void getExistenceReportDtoById(){
        ReportDto reportDto = reportService.getReportDtoById(4L);
        assertEquals(reportDto.getId(), 4);
    }
}
