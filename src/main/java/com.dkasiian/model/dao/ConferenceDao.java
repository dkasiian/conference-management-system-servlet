package com.dkasiian.model.dao;

import com.dkasiian.model.dto.ConferenceDto;
import com.dkasiian.model.entities.Conference;

import java.util.List;
import java.util.Map;

public interface ConferenceDao extends GenericDao<Conference> {

    ConferenceDto getConferenceDtoById(long conferenceId);

    List<Conference> getAll(String language);

    List<Conference> getAllPast(String language);

    List<Conference> getAllFuture(String language);

    boolean add(ConferenceDto conference);

    void update(ConferenceDto conference);

    Map<Long, Integer> getReportsNumber();

    Map<Long, Integer> getUsersNumber();

    Map<Long, Integer> getSpeakersNumber();

    int getConferencesCount();

    int getPastConferencesCount();

    int getFutureConferencesCount();

    List<Conference> getPaginatedConferences(int begin, int recordsPerPage, String language);

    List<Conference> getPastPaginatedConferences(int begin, int recordsPerPage, String language);

    List<Conference> getFuturePaginatedConferences(int begin, int recordsPerPage, String language);
}
