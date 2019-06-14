package com.dkasiian.model.dao.jdbc;

import com.dkasiian.model.ResourceName;
import com.dkasiian.model.dao.ConferenceDao;
import com.dkasiian.model.dao.mappers.ConferenceMapper;
import com.dkasiian.model.dao.mappers.Mapper;
import com.dkasiian.model.dto.ConferenceDto;
import com.dkasiian.model.entities.Conference;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.DataSource;
import java.sql.*;
import java.util.*;

public class JdbcConferenceDao implements ConferenceDao {

    private final static Logger LOG = LogManager.getLogger(JdbcConferenceDao.class);
    private DataSource dataSource = ConnectionPool.getDataSource();
    private ResourceBundle sqlStatementsBundle = ResourceBundle.getBundle(ResourceName.SQL_STATEMENTS_BUNDLE);
    private Mapper<Conference> conferenceMapper = new ConferenceMapper();


    @Override
    public boolean add(Conference item) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void update(Conference item) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean delete(long id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     sqlStatementsBundle.getString("conferences.delete"))) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException exc) {
            LOG.error("JdbcConferenceDao :: delete :: " + exc);
            throw new RuntimeException();
        }
    }

    @Override
    public Conference getById(long id, String language) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement =connection.prepareStatement(
                     sqlStatementsBundle.getString("conferences.select.by.id"))) {
            Conference conference = null;
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
                conference = conferenceMapper.mapToObject(resultSet, language);
            return conference;
        } catch (SQLException exc) {
            LOG.error("JdbcConferenceDao :: getById :: " + exc);
            throw new RuntimeException();
        }
    }

    @Override
    public ConferenceDto getConferenceDtoById(long conferenceId) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     sqlStatementsBundle.getString("conferences.select.by.id"))) {
            ConferenceDto conferenceDto = null;
            preparedStatement.setLong(1, conferenceId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
                conferenceDto = new ConferenceMapper().mapToDtoObject(resultSet);
            return conferenceDto;
        } catch (SQLException exc) {
            LOG.error("JdbcConferenceDao :: getConferenceDtoById :: " + exc);
            throw new RuntimeException();
        }
    }

    @Override
    public List<Conference> getAll(String language) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     sqlStatementsBundle.getString("conferences.select.all"))) {
            List<Conference> conferences = new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
                conferences.add(conferenceMapper.mapToObject(resultSet, language));
            return conferences;
        } catch (SQLException exc) {
            LOG.error("JdbcConferenceDao :: getAll :: " + exc);
            throw new RuntimeException();
        }
    }

    @Override
    public List<Conference> getAllPast(String language) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     sqlStatementsBundle.getString("conferences.select.all.past"))) {
            List<Conference> conferences = new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
                conferences.add(conferenceMapper.mapToObject(resultSet, language));
            return conferences;
        } catch (SQLException exc) {
            LOG.error("JdbcConferenceDao :: getAllPast :: " + exc);
            throw new RuntimeException();
        }
    }

    @Override
    public List<Conference> getAllFuture(String language) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     sqlStatementsBundle.getString("conferences.select.all.future"))) {
            List<Conference> conferences = new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
                conferences.add(conferenceMapper.mapToObject(resultSet, language));
            return conferences;
        } catch (SQLException exc) {
            LOG.error("JdbcConferenceDao :: getAllFuture :: " + exc);
            throw new RuntimeException();
        }
    }

    @Override
    public boolean add(ConferenceDto conference) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     sqlStatementsBundle.getString("conferences.insert"))) {
            preparedStatement.setString(1, conference.getNameEn());
            preparedStatement.setString(2, conference.getNameUa());
            preparedStatement.setTimestamp(3, Timestamp.valueOf(conference.getDateTime()));
            preparedStatement.setString(4, conference.getLocationEn());
            preparedStatement.setString(5, conference.getLocationUa());
            preparedStatement.executeQuery();
            return true;
        } catch (SQLException exc) {
            LOG.error("JdbcConferenceDao :: add :: " + exc);
        }
        return false;
    }

    @Override
    public void update(ConferenceDto conference) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     sqlStatementsBundle.getString("conferences.update"))) {
            preparedStatement.setString(1, conference.getNameEn());
            preparedStatement.setString(2, conference.getNameUa());
            preparedStatement.setTimestamp(3, Timestamp.valueOf(conference.getDateTime()));
            preparedStatement.setString(4, conference.getLocationEn());
            preparedStatement.setString(5, conference.getLocationUa());
            preparedStatement.setLong(6, conference.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException exc) {
            LOG.error("JdbcConferenceDao :: update :: " + exc);
            throw new RuntimeException();
        }
    }

    @Override
    public int getConferencesCount() {
        int conferenceAmount = 0;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     sqlStatementsBundle.getString("conferences.count"))) {
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
                conferenceAmount = resultSet.getInt(1);
            return conferenceAmount;
        } catch (SQLException exc) {
            LOG.error("dbcConferenceDao :: getConferencesCount :: " + exc);
            throw new RuntimeException();
        }
    }

    @Override
    public int getFutureConferencesCount() {
        int conferenceAmount = 0;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     sqlStatementsBundle.getString("conferences.count.future"))) {
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
                conferenceAmount = resultSet.getInt(1);
            return conferenceAmount;
        } catch (SQLException exc) {
            LOG.error("JdbcConferenceDao :: getFutureConferencesCount :: " + exc);
            throw new RuntimeException();
        }
    }

    @Override
    public int getPastConferencesCount() {
        int conferenceAmount = 0;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     sqlStatementsBundle.getString("conferences.count.past"))) {
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
                conferenceAmount = resultSet.getInt(1);
            return conferenceAmount;
        } catch (SQLException exc) {
            LOG.error("JdbcConferenceDao :: getPastConferencesCount :: " + exc);
            throw new RuntimeException();
        }
    }

    @Override
    public Map<Long, Integer> getReportsNumber() {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     sqlStatementsBundle.getString("conferences.reports.conferences.select.report.count"))) {
            ResultSet resultSet = preparedStatement.executeQuery();
            Map<Long, Integer> reportNumber = new HashMap<>();
            while (resultSet.next())
                reportNumber.put(resultSet.getLong("conference_id"), resultSet.getInt("report_count"));
            return reportNumber;
        } catch (SQLException exc) {
            LOG.error("JdbcConferenceDao :: getReportsNumber :: " + exc);
            throw new RuntimeException();
        }
    }

    @Override
    public Map<Long, Integer> getUsersNumber() {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     sqlStatementsBundle.getString("conferences.users.conferences.select.user.count"))) {
            ResultSet resultSet = preparedStatement.executeQuery();
            Map<Long, Integer> reportNumber = new HashMap<>();
            while (resultSet.next())
                reportNumber.put(resultSet.getLong("conference_id"), resultSet.getInt("user_count"));
            return reportNumber;
        } catch (SQLException exc) {
            LOG.error("JdbcConferenceDao :: getUsersNumber :: " + exc);
            throw new RuntimeException();
        }
    }

    @Override
    public Map<Long, Integer> getSpeakersNumber() {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     sqlStatementsBundle.getString("reports.reports.conferences.select.speakers.count"))) {
            Map<Long, Integer> reportNumber = new HashMap<>();
            List<Long> conferencesIds = getConferencesIds();
            for (Long conferenceId : conferencesIds){
                preparedStatement.setLong(1, conferenceId);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next())
                    reportNumber.put(conferenceId, resultSet.getInt("speaker_count"));
            }
            return reportNumber;
        } catch (SQLException exc) {
            LOG.error("JdbcConferenceDao :: getSpeakersNumber :: " + exc);
            throw new RuntimeException();
        }
    }

    private List<Long> getConferencesIds() {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     sqlStatementsBundle.getString("conferences.select.all"))) {
            List<Long> conferencesIds = new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
                conferencesIds.add(resultSet.getLong("conference_id"));
            return conferencesIds;
        } catch (SQLException exc) {
            LOG.error("JdbcUserDao :: getConferencesIds :: " + exc);
            throw new RuntimeException();
        }
    }

    @Override
    public List<Conference> getPaginatedConferences(int begin, int recordsPerPage, String language) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     sqlStatementsBundle.getString("conferences.select.with.paginated"))) {
            return getConferences(begin, recordsPerPage, language, preparedStatement);
        } catch (SQLException exc) {
            LOG.error("JdbcConferenceDao :: getPaginatedConferences :: " + exc);
            throw new RuntimeException();
        }
    }

    @Override
    public List<Conference> getPastPaginatedConferences(int begin, int recordsPerPage, String language) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     sqlStatementsBundle.getString("conferences.select.past.with.paginated"))) {
            return getConferences(begin, recordsPerPage, language, preparedStatement);
        } catch (SQLException e) {
            LOG.error("JdbcUserDao :: getPastPaginatedConferences :: " + e);
            throw new RuntimeException();
        }
    }

    @Override
    public List<Conference> getFuturePaginatedConferences(int begin, int recordsPerPage, String language) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     sqlStatementsBundle.getString("conferences.select.future.with.paginated"))) {
            return getConferences(begin, recordsPerPage, language, preparedStatement);
        } catch (SQLException e) {
            LOG.error("JdbcUserDao :: getFuturePaginatedConferences :: " + e);
            throw new RuntimeException();
        }
    }

    private List<Conference> getConferences(int begin, int recordsPerPage, String language, PreparedStatement statement) throws SQLException {
        List<Conference> conferences = new ArrayList<>();
        statement.setInt(1, begin);
        statement.setInt(2, recordsPerPage);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next())
            conferences.add(conferenceMapper.mapToObject(resultSet, language));
        return conferences;
    }
}

