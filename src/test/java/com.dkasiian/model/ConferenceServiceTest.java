package com.dkasiian.model;

import com.dkasiian.model.dto.ConferenceDto;
import com.dkasiian.model.entities.Conference;
import com.dkasiian.model.services.ConferenceService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ConferenceServiceTest {

    private static ConferenceService conferenceService;
    private static final String LANGUAGE = "en_US";

    @BeforeAll
    static void init(){
        conferenceService = new ConferenceService();
    }

    @Test
    void getNonExistenceConferenceById(){
        Conference conference = conferenceService.getConferenceById(1000, LANGUAGE);
        assertNull(conference);
    }

    @Test
    void getExistenceConferenceById(){
        Conference conference = conferenceService.getConferenceById(1, LANGUAGE);
        assertEquals(conference.getId(), 1);
    }

    @Test
    void getNonExistenceConferenceDtoById(){
        ConferenceDto conference = conferenceService.getConferenceDtoById(1000);
        assertNull(conference);
    }

    @Test
    void getExistenceConferenceDtoById(){
        ConferenceDto conference = conferenceService.getConferenceDtoById(1);
        assertEquals(conference.getId(), 1);
    }

}
