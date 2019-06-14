package com.dkasiian.model.services;

import com.dkasiian.model.dao.ConferenceDao;
import com.dkasiian.model.dao.DaoFactory;
import com.dkasiian.model.dto.ConferenceDto;
import com.dkasiian.model.entities.Conference;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Map;

public class ConferenceService {

    private ConferenceDao conferenceDao = DaoFactory.getInstance().createConferenceDao();


    public Conference getConferenceById(long conferenceId, String language) {
        return conferenceDao.getById(conferenceId, language);
    }

    public ConferenceDto getConferenceDtoById(long conferenceId){
        return conferenceDao.getConferenceDtoById(conferenceId);
    }

    public List<Conference> getAllConferences(String language){
        return conferenceDao.getAll(language);
    }

    public boolean add(ConferenceDto conference) {
        return conferenceDao.add(conference);
    }

    public void update(ConferenceDto conference) {
        conferenceDao.update(conference);
    }

    public boolean delete(Long id) {
        return conferenceDao.delete(id);
    }

    public int getConferencesCount() {
        return conferenceDao.getConferencesCount();
    }

    public List<Conference> getAllPastConferences(String language) {
        return conferenceDao.getAllPast(language);
    }

    public List<Conference> getAllFutureConferences(String language) {
        return conferenceDao.getAllFuture(language);
    }

    public Map<Long, Integer> getReportsNumber() {
        return conferenceDao.getReportsNumber();
    }

    public Map<Long, Integer> getUsersNumber() {
        return conferenceDao.getUsersNumber();
    }

    public Map<Long, Integer> getSpeakersNumber() {
        return conferenceDao.getSpeakersNumber();
    }

    public int getPastConferencesCount(){
        return conferenceDao.getPastConferencesCount();
    }

    public int getFutureConferencesCount(){
        return conferenceDao.getFutureConferencesCount();
    }

    public List<Conference> getPaginatedConferences(Integer begin, Integer recordsPerPage, String language) {
        return conferenceDao.getPaginatedConferences(begin, recordsPerPage, language);
    }

    public List<Conference> getPastPaginatedConferences(Integer begin, Integer recordsPerPage, String language) {
        return conferenceDao.getPastPaginatedConferences(begin, recordsPerPage, language);
    }

    public List<Conference> getFuturePaginatedConferences(Integer begin, Integer recordsPerPage, String language) {
        return conferenceDao.getFuturePaginatedConferences(begin, recordsPerPage, language);
    }
}
