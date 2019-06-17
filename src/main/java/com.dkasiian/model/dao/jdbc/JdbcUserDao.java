package com.dkasiian.model.dao.jdbc;

import com.dkasiian.model.ResourceName;
import com.dkasiian.model.dao.UserDao;
import com.dkasiian.model.dao.mappers.UserMapper;
import com.dkasiian.model.dto.UserDto;
import com.dkasiian.model.entities.Conference;
import com.dkasiian.model.entities.Role;
import com.dkasiian.model.entities.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class JdbcUserDao implements UserDao {

    private static final Logger LOG = LogManager.getLogger(JdbcUserDao.class);
    private DataSource dataSource = ConnectionPool.getDataSource();
    private ResourceBundle sqlStatementsBundle = ResourceBundle.getBundle(ResourceName.SQL_STATEMENTS_BUNDLE);
    private UserMapper userMapper = new UserMapper();

    @Override
    public User getById(long id, String language) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(sqlStatementsBundle.getString("users.select.by.id"))) {
            User user = null;
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
                user = userMapper.mapToObject(resultSet, language);
            return user;
        } catch (SQLException exc) {
            LOG.error("JdbcUserDao :: getById :: " + exc);
            throw new RuntimeException();
        }
    }

    @Override
    public boolean add(User item) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void update(User item) { throw new UnsupportedOperationException(); }

    @Override
    public boolean delete(long id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<User> getAll(String language) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(sqlStatementsBundle.getString("users.select.all"))) {
            List<User> users = new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
                users.add(userMapper.mapToObject(resultSet, language));
            return users;
        } catch (SQLException exc) {
            LOG.error("JdbcUserDao :: getAll :: " + exc);
            throw new RuntimeException();
        }
    }

    @Override
    public boolean add(UserDto user) throws RuntimeException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(sqlStatementsBundle.getString("users.insert"))) {
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getNameEn());
            preparedStatement.setString(4, user.getNameUa());
            preparedStatement.setString(5, user.getSurnameEn());
            preparedStatement.setString(6, user.getSurnameUa());
            preparedStatement.setString(7, user.getEmail());
            preparedStatement.setString(8, user.getRole().name());
            preparedStatement.execute();
            return true;
        } catch (SQLException exc) {
            LOG.error("JdbcUserDao :: add :: " + exc);
            return false;
        }
    }

    @Override
    public User getByLogin(String login, String language) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(sqlStatementsBundle.getString("users.select.by.login"))) {
            User user = null;
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
                user = userMapper.mapToObject(resultSet, language);
            return user;
        } catch (SQLException exc) {
            LOG.error("JdbcUserDao :: add :: " + exc);
            throw new RuntimeException();
        }
    }

    @Override
    public boolean isUserExist(String login) {
        return getByLogin(login, "en_US") != null;
    }

    @Override
    public boolean checkUserPassword(String login, String password) {
        return BCrypt.checkpw(password, getByLogin(login, "en_US").getPassword());
    }

    @Override
    public Role getUserRole(String login) { return getByLogin(login, "en_US").getRole(); }

    @Override
    public List<User> getAllSpeakers(String language) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(sqlStatementsBundle.getString("users.select.all.speakers"))) {
            List<User> users = new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
                users.add(userMapper.mapToObject(resultSet, language));
            return users;
        } catch (SQLException exc) {
            LOG.error("JdbcUserDao :: getAll :: " + exc);
            throw new RuntimeException();
        }
    }

    @Override
    public List<Long> getConferencesIdsForUser(long userId) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(sqlStatementsBundle.getString("users.conferences.select.conferences.ids"))) {
            List<Long> conferencesIds = new ArrayList<>();
            preparedStatement.setLong(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
                conferencesIds.add(resultSet.getLong("conference_id"));
            return conferencesIds;
        } catch (SQLException exc) {
            LOG.error("JdbcUserDao :: getConferencesIdsForUser :: " + exc);
            throw new RuntimeException();
        }
    }

    @Override
    public void registerForConference(long userId, long conferenceId) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(sqlStatementsBundle.getString("users.conferences.register.user.for.conference"))) {
            preparedStatement.setLong(1, userId);
            preparedStatement.setLong(2, conferenceId);
            preparedStatement.executeQuery();
        } catch (SQLException exc) {
            LOG.error("JdbcUserDao :: registerForConference :: " + exc);
            throw new RuntimeException();
        }
    }

    @Override
    public void unregisterFromConference(long userId, long conferenceId) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     sqlStatementsBundle.getString("users.conferences.unregister.user.from.conference"))) {
            preparedStatement.setLong(1, userId);
            preparedStatement.setLong(2, conferenceId);
            preparedStatement.executeQuery();
        } catch (SQLException exc) {
            LOG.error("JdbcUserDao :: unregisterFromConference :: " + exc);
            throw new RuntimeException();
        }
    }

    @Override
    public List<Long> getSpeakersIdsForRating(List<Long> conferencesIds) {
        try (Connection connection = dataSource.getConnection()) {
            List<Long> speakersIds = new ArrayList<>();
            StringBuilder builder = new StringBuilder();
            for(int i = 0 ; i < conferencesIds.size(); i++ )
                builder.append("?,");

            String statement =
                    sqlStatementsBundle.getString("reports.reports.conferences.select.speakers.ids.for.rating") +
                            builder.deleteCharAt(builder.length()-1).toString() + "))";
            PreparedStatement preparedStatement = connection.prepareStatement(statement);
            int index = 1;
            for(Long conferenceId : conferencesIds)
                preparedStatement.setLong(index++, conferenceId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
                speakersIds.add(resultSet.getLong("speaker_id"));
            preparedStatement.close();
            return speakersIds;
        } catch (SQLException exc) {
            LOG.error("JdbcUserDao :: getSpeakersIdsForRating :: " + exc);
            throw new RuntimeException();
        }
    }

    @Override
    public List<Long> getSpeakersIds() {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     sqlStatementsBundle.getString("users.select.all.speakers.ids"))) {
            List<Long> speakersIds = new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
                speakersIds.add(resultSet.getLong("user_id"));
            return speakersIds;
        } catch (SQLException exc) {
            LOG.error("JdbcUserDao :: getSpeakersIds :: " + exc);
            throw new RuntimeException();
        }
    }

    @Override
    public Map<Long, Integer> getSpeakersRating(List<Long> allSpeakersIds) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection
                     .prepareStatement(sqlStatementsBundle.getString("users.speakers.rating.select.rating"))) {
            Map<Long, Integer> speakersIdsToRating = new HashMap<>();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
                speakersIdsToRating.put(resultSet.getLong("speaker_id"), Math.round((float)resultSet.getDouble("avg")));
            return speakersIdsToRating;
        } catch (SQLException exc) {
            LOG.error("JdbcUserDao :: getSpeakersRating :: " + exc);
            throw new RuntimeException();
        }
    }

    @Override
    public Map<Long, Integer> getSpeakersRatingByUser(long userId) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection
                     .prepareStatement(sqlStatementsBundle.getString("users.speakers.rating.select.rating.by.user"))) {
            Map<Long, Integer> speakersIdsToRating = new HashMap<>();
            preparedStatement.setLong(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
                speakersIdsToRating.put(resultSet.getLong("speaker_id"), resultSet.getInt("speaker_rating"));
            return speakersIdsToRating;
        } catch (SQLException exc) {
            LOG.error("JdbcUserDao :: getSpeakersRatingByUser :: " + exc);
            throw new RuntimeException();
        }
    }

    @Override
    public Map<Long, Integer> getSpeakersBonuses(List<Long> allSpeakersIds) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection
                     .prepareStatement(sqlStatementsBundle.getString("users.speakers.rating.select.bonuses"))) {
            Map<Long, Integer> speakersIdsToRating = new HashMap<>();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
                speakersIdsToRating.put(resultSet.getLong("speaker_id"), Math.round((float)resultSet.getDouble("avg")));
            return speakersIdsToRating;
        } catch (SQLException exc) {
            LOG.error("JdbcUserDao :: getSpeakersBonuses :: " + exc);
            throw new RuntimeException();
        }
    }

    @Override
    public void setRating(long userId, long speakerId, int rating) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection
                     .prepareStatement(sqlStatementsBundle.getString("users.speakers.rating.insert"))) {
            preparedStatement.setLong(1, userId);
            preparedStatement.setLong(2, speakerId);
            preparedStatement.setInt(3, rating);
            preparedStatement.setInt(4, rating * 100);
            preparedStatement.executeQuery();
        } catch (SQLException exc) {
            LOG.error("JdbcUserDao :: setRating :: " + exc);
            throw new RuntimeException();
        }
    }

    @Override
    public void deleteRating(long userId, long speakerId) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection
                     .prepareStatement(sqlStatementsBundle.getString("users.speakers.rating.delete"))) {
            preparedStatement.setLong(1, userId);
            preparedStatement.setLong(2, speakerId);
            preparedStatement.executeQuery();
        } catch (SQLException exc) {
            LOG.error("JdbcUserDao :: deleteRating :: " + exc);
            throw new RuntimeException();
        }
    }

    @Override
    public boolean checkIsUserAlreadySetRating(long userId, long speakerId) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection
                     .prepareStatement(sqlStatementsBundle.getString("users.speakers.rating.check"))) {
            preparedStatement.setLong(1, userId);
            preparedStatement.setLong(2, speakerId);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException exc) {
            LOG.error("JdbcUserDao :: checkIsUserAlreadySetRating :: " + exc);
            throw new RuntimeException();
        }
    }

    @Override
    public void updateRating(long userId, long speakerId, int rating) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection
                     .prepareStatement(sqlStatementsBundle.getString("users.speakers.rating.update"))) {
            preparedStatement.setInt(1, rating);
            preparedStatement.setInt(2, rating * 100);
            preparedStatement.setLong(3, userId);
            preparedStatement.setLong(4, speakerId);
            preparedStatement.executeQuery();
        } catch (SQLException exc) {
            LOG.error("JdbcUserDao :: updateRating :: " + exc);
            throw new RuntimeException();
        }
    }

    @Override
    public List<User> getPaginatedSpeakers(Integer begin, Integer recordsPerPage, String language) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     sqlStatementsBundle.getString("users.select.speakers.with.pagination"))) {
            List<User> speakers = new ArrayList<>();
            preparedStatement.setInt(1, begin);
            preparedStatement.setInt(2, recordsPerPage);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
                speakers.add(userMapper.mapToObject(resultSet, language));
            return speakers;
        } catch (SQLException exc) {
            LOG.error("JdbcUserDao :: getPaginatedSpeakers :: " + exc);
            throw new RuntimeException();
        }
    }
}



