/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.model.Location;
import com.sg.superherosightings.model.SuperPerson;
import java.sql.ResultSet;
import java.sql.SQLException;
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
public class LocationDaoDB implements LocationDao {

    @Inject
    JdbcTemplate jdbc;

    @Override
    public List<Location> getAllLocations() {
        final String SELECT_ALL_LOCATIONS = "SELECT * FROM location";
        return jdbc.query(SELECT_ALL_LOCATIONS, new LocationMapper());
    }

    @Override
    public Location getLocationById(int locationId) {
        final String SELECT_LOCATION_BY_ID = "SELECT * FROM location WHERE locationId = ?";
        try {
            return jdbc.queryForObject(SELECT_LOCATION_BY_ID, new LocationMapper(), locationId);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    @Transactional
    public Location createLocation(Location location) {
        final String INSERT_LOCATION = "INSERT INTO location (locationName, description, streetInfo, city, state, zipcode, "
                + "latitude, longitude) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        jdbc.update(INSERT_LOCATION, location.getName(), location.getDescription(), location.getStreetInfo(),
                location.getCity(), location.getState(), location.getZipcode(), location.getLatitude(),
                location.getLongitude());
        int newId = jdbc.queryForObject("select LAST_INSERT_ID()", Integer.class);
        location.setLocationId(newId);
        return location;
    }

    @Override
    @Transactional
    public void deleteLocationById(int locationId) {
        final String DELETE_SIGHTING_ID_FROM_SIGHTINGPERSONSIGHTING = "DELETE sps.* FROM "
                + "SuperPersonSighting sps INNER JOIN Sighting s ON sps.sightingId = s.sightingId "
                + "INNER JOIN Location l ON s.locationId = l.locationId "
                + "WHERE l.locationId = ?";
        jdbc.update(DELETE_SIGHTING_ID_FROM_SIGHTINGPERSONSIGHTING, locationId);
        
        final String DELETE_LOCATION_ID_FROM_SIGHTING = "DELETE FROM sighting WHERE locationId = ?";
        jdbc.update(DELETE_LOCATION_ID_FROM_SIGHTING, locationId);

        final String DELETE_LOCATION_BY_ID = "DELETE FROM location WHERE locationId = ?";
        jdbc.update(DELETE_LOCATION_BY_ID, locationId);
    }

    @Override
    public void updateLocation(Location location) {
        final String UPDATE_LOCATION = "UPDATE location SET locationName = ?, description = ?, streetInfo = ?, city = ?, "
                + "state = ?, zipcode = ?,latitude = ?, longitude = ? WHERE locationId = ?";
        jdbc.update(UPDATE_LOCATION, location.getName(), location.getDescription(), location.getStreetInfo(),
                location.getCity(), location.getState(), location.getZipcode(), location.getLatitude(),
                location.getLongitude(), location.getLocationId());
    }

    @Override
    public List<Location> getAllLocationsBySuperPerson(SuperPerson person) {
        final String SELECT_ALL_LOCATIONS_BY_SUPERPERSON = "SELECT l.* FROM location l "
                + "INNER JOIN Sighting s ON l.locationId = s.locationId "
                + "INNER JOIN SuperPersonSighting sps ON s.sightingId = sps.sightingId "
                + "WHERE sps.superPersonId = ?";
        return jdbc.query(SELECT_ALL_LOCATIONS_BY_SUPERPERSON, new LocationMapper(), person.getSuperPersonId());
    }

    public static final class LocationMapper implements RowMapper<Location> {

        @Override
        public Location mapRow(ResultSet rs, int i) throws SQLException {
            Location location = new Location();
            location.setLocationId(rs.getInt("locationId"));
            location.setName(rs.getString("locationName"));
            location.setDescription(rs.getString("description"));
            location.setStreetInfo(rs.getString("streetInfo"));
            location.setCity(rs.getString("city"));
            location.setState(rs.getString("state"));
            location.setZipcode(rs.getInt("zipcode"));
            location.setLatitude(rs.getBigDecimal("latitude"));
            location.setLongitude(rs.getBigDecimal("longitude"));
            return location;
        }

    }

}
