package com.dkasiian.model.services;

import com.dkasiian.model.dao.DaoFactory;
import com.dkasiian.model.dao.UserDao;
import com.dkasiian.model.dto.UserDto;
import com.dkasiian.model.entities.Role;
import com.dkasiian.model.entities.User;

import java.util.List;
import java.util.Map;

public class UserService {

    private UserDao userDao = DaoFactory.getInstance().createUserDao();


    public User getById(long userId, String language){
        return userDao.getById(userId, language);
    }

    public String getRoleByLogin(String login, String language) {
        return userDao.getByLogin(login, language).getRole().toString().toLowerCase();
    }

    public boolean isUserExist(String login) {
        return userDao.isUserExist(login);
    }

    public boolean checkPassword(String login, String password) {
        return userDao.checkUserPassword(login, password);
    }

    public Role getRole(String login) { return userDao.getUserRole(login); }

    public void register(UserDto user) {
        userDao.add(user);
    }

    public long getUserId(String login, String language) {
        return userDao.getByLogin(login, language).getId();
    }

    public List<User> getAllUsers(String language) {
        return userDao.getAll(language);
    }

    public List<User> getAllSpeakers(String language) {
        return userDao.getAllSpeakers(language);
    }

    public List<Long> getConferencesIdsForWhichUserHasRegistration(long userId) {
        return userDao.getConferencesIdsForUser(userId);
    }

    public void registerForConference(long userId, long conferenceId) {
        userDao.registerForConference(userId, conferenceId);
    }

    public void unregisterFromConference(long userId, long conferenceId) {
        userDao.unregisterFromConference(userId, conferenceId);
    }

    public List<Long> getSpeakersIdsForRating(List<Long> conferencesIds) {
        return userDao.getSpeakersIdsForRating(conferencesIds);
    }

    public List<Long> getSpeakersIds() {
        return userDao.getSpeakersIds();
    }

    public Map<Long, Integer> getSpeakersRating(List<Long> allSpeakersIds) {
        return userDao.getSpeakersRating(allSpeakersIds);
    }

    public Map<Long, Integer> getSpeakersBonuses(List<Long> allSpeakersIds) {
        return userDao.getSpeakersBonuses(allSpeakersIds);
    }

    public Map<Long, Integer> getSpeakersRatingByUser(long userId) {
        return userDao.getSpeakersRatingByUser(userId);
    }

    public void setRating(long userId, long speakerId, int rating) {
        userDao.setRating(userId, speakerId, rating);
    }

    public void deleteRating(long userId, long speakerId) {
        userDao.deleteRating(userId, speakerId);
    }

    public boolean checkIsUserAlreadySetRating(long userId, long speakerId) {
        return userDao.checkIsUserAlreadySetRating(userId, speakerId);
    }

    public void updateRating(long userId, long speakerId, int rating) {
        userDao.updateRating(userId, speakerId, rating);
    }

    public List<User> getPaginatedSpeakers(Integer begin, Integer recordsPerPage, String language) {
        return userDao.getPaginatedSpeakers(begin, recordsPerPage, language);
    }
}
