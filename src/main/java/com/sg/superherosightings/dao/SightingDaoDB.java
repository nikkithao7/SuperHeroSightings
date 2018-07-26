/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.dao.LocationDaoDB.LocationMapper;
import com.sg.superherosightings.model.Location;
import com.sg.superherosightings.model.Sighting;
import com.sg.superherosightings.model.SuperPerson;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import javax.inject.Inject;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author nthao
 */
@Component
public class SightingDaoDB implements SightingDao {

    @Inject
    JdbcTemplate jdbc;

    @Override
    public List<Sighting> getAllSightings() {
        final String SELECT_ALL_SIGHTINGS = "SELECT * FROM sighting";
        List<Sighting> sightings = jdbc.query(SELECT_ALL_SIGHTINGS, new SightingMapper());
        associateLocationToSightings(sightings);
        return sightings;
    }

    @Override
    public List<Sighting> getMostRecentSightings() {
        final String SELECT_MOST_RECENT_SIGHTINGS = "SELECT * FROM sighting "
                + "ORDER BY dateSeen DESC LIMIT 10";
        List<Sighting> sightings = jdbc.query(SELECT_MOST_RECENT_SIGHTINGS, new SightingMapper());
        associateLocationToSightings(sightings);
        return sightings;
    }

    private void associateLocationToSightings(List<Sighting> sightings) {
        for (Sighting sighting : sightings) {
            sighting.setLocation(getLocationForSighting(sighting.getSightingId()));
        }
    }

    @Override
    public Sighting getSightingById(int sightingId) {
        final String SELECT_SIGHTING_BY_ID = "SELECT * FROM sighting WHERE sightingId = ?";
        try {
            Sighting sighting = jdbc.queryForObject(SELECT_SIGHTING_BY_ID, new SightingMapper(), sightingId);
            Location location = getLocationForSighting(sightingId);
            sighting.setLocation(location);
            return sighting;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    private Location getLocationForSighting(int sightingId) {
        final String SELECT_LOCATION_FOR_SIGHTING = "SELECT l.* FROM sighting s "
                + "INNER JOIN location l ON s.locationId = l.locationId "
                + "WHERE s.sightingId = ?";
        try {
            Location location = jdbc.queryForObject(SELECT_LOCATION_FOR_SIGHTING, new LocationMapper(), sightingId);
            return location;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    @Transactional
    public Sighting createSighting(Sighting sighting) {
        final String INSERT_SIGHTING = "INSERT INTO sighting(dateSeen, locationId) "
                + "VALUES(?, ?)";
        jdbc.update(INSERT_SIGHTING, Date.valueOf(sighting.getDateSeen()), sighting.getLocation().getLocationId());
        int newId = jdbc.queryForObject("select LAST_INSERT_ID()", Integer.class);
        sighting.setSightingId(newId);
        Location location = getLocationForSighting(sighting.getSightingId());
        sighting.setLocation(location);
        return sighting;
    }

    @Override
    public void deleteSightingById(int sightingId) {
        final String DELETE_SUPERPERSON_SIGHTING_BY_SIGHTING = "DELETE FROM SuperPersonSighting "
                + "where sightingId = ?";
        jdbc.update(DELETE_SUPERPERSON_SIGHTING_BY_SIGHTING, sightingId);

        final String DELETE_SIGHTING_BY_ID = "DELETE FROM sighting where sightingId = ?";
        jdbc.update(DELETE_SIGHTING_BY_ID, sightingId);
    }

    @Override
    @Transactional
    public void updateSighting(Sighting sighting) {
        final String UPDATE_SIGHTING = "UPDATE sighting SET dateSeen = ?, locationId = ? WHERE sightingId = ?";
        jdbc.update(UPDATE_SIGHTING, Date.valueOf(sighting.getDateSeen()), sighting.getLocation().getLocationId(),
                sighting.getSightingId());

        final String DELETE_SUPERPERSON_SIGHTING_BY_SIGHTING = "DELETE FROM SuperPersonSighting "
                + "where sightingId = ?";
        jdbc.update(DELETE_SUPERPERSON_SIGHTING_BY_SIGHTING, sighting.getSightingId());
        Location location = getLocationForSighting(sighting.getSightingId());
        sighting.setLocation(location);

    }

    @Override
    public List<Sighting> getAllsightingsByDate(LocalDate date) {
        final String SELECT_ALL_SIGHTINGS_BY_DATE = "SELECT * FROM sighting WHERE dateSeen = ?";
        List<Sighting> sightings = jdbc.query(SELECT_ALL_SIGHTINGS_BY_DATE, new SightingMapper(), Date.valueOf(date));
        associateLocationToSightings(sightings);
        return sightings;
    }

    @Override
    public List<Sighting> getAllSightingsByLocation(Location location) {
        final String SELECT_ALL_SIGHTINGS_BY_LOCATION = "SELECT * FROM sighting WHERE locationId = ?";
        List<Sighting> sightings = jdbc.query(SELECT_ALL_SIGHTINGS_BY_LOCATION, new SightingMapper(), location.getLocationId());
        associateLocationToSightings(sightings);
        return sightings;
    }

    @Override
    public List<Sighting> getAllSightingsBySuperPerson(SuperPerson person) {
        final String SELECT_ALL_SIGHTINGS_BY_SUPERPERSON = "SELECT s.* FROM SuperPersonSighting sps "
                + "INNER JOIN sighting s ON sps.sightingId = s.sightingId "
                + "WHERE sps.superPersonId = ?";
        List<Sighting> sightings = jdbc.query(SELECT_ALL_SIGHTINGS_BY_SUPERPERSON,
                new SightingMapper(), person.getSuperPersonId());
        associateLocationToSightings(sightings);
        return sightings;
    }

    public static final class SightingMapper implements RowMapper<Sighting> {

        @Override
        public Sighting mapRow(ResultSet rs, int i) throws SQLException {
            Sighting sighting = new Sighting();
            sighting.setSightingId(rs.getInt("sightingId"));
            sighting.setDateSeen(rs.getDate("dateSeen").toLocalDate());
            return sighting;
        }
    }

}
