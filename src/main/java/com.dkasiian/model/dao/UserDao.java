package com.dkasiian.model.dao;

import com.dkasiian.model.dto.UserDto;
import com.dkasiian.model.entities.Role;
import com.dkasiian.model.entities.User;

import java.util.List;
import java.util.Map;

public interface UserDao extends GenericDao<User> {

    User getByLogin(String login, String language);

    Role getUserRole(String login);

    boolean add(UserDto user) throws RuntimeException;

    boolean isUserExist(String login);

    boolean checkUserPassword(String login, String password);

    List<User> getAllSpeakers(String language);

    List<Long> getConferencesIdsForUser(long userId);

    void registerForConference(long userId, long conferenceId);

    void unregisterFromConference(long userId, long conferenceId);

    List<Long> getSpeakersIdsForRating(List<Long> conferencesIds);

    List<Long> getSpeakersIds();

    Map<Long, Integer> getSpeakersRating(List<Long> allSpeakersIds);

    Map<Long, Integer> getSpeakersRatingByUser(long userId);

    void setRating(long userId, long speakerId, int rating);

    void deleteRating(long userId, long speakerId);

    boolean checkIsUserAlreadySetRating(long userId, long speakerId);

    void updateRating(long userId, long speakerId, int rating);

    List<User> getPaginatedSpeakers(Integer begin, Integer recordsPerPage, String language);
}
