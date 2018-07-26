/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.dao.OrganizationDaoDB.OrganizationMapper;
import com.sg.superherosightings.dao.SightingDaoDB.SightingMapper;
import com.sg.superherosightings.model.Location;
import com.sg.superherosightings.model.Organization;
import com.sg.superherosightings.model.Sighting;
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
public class SuperPersonDaoDB implements SuperPersonDao {

    @Inject
    JdbcTemplate jdbc;

    @Override
    public List<SuperPerson> getAllSuperPersons() {
        final String SELECT_ALL_SUPERPERSONS = "SELECT * FROM SuperPerson";
        List<SuperPerson> superPersons = jdbc.query(SELECT_ALL_SUPERPERSONS, new SuperPersonMapper());
        associateOrganizationsToSuperPersons(superPersons);
        associateSightingsToSuperPersons(superPersons);
        return superPersons;
    }

    private void associateOrganizationsToSuperPersons(List<SuperPerson> persons) {
        for (SuperPerson person : persons) {
            person.setOrganizations(getOrganizationsForSuperPerson(person));
        }
    }

    private void associateSightingsToSuperPersons(List<SuperPerson> persons) {
        for (SuperPerson person : persons) {
            person.setSightings(getSightingsForSuperPerson(person));
        }
    }

    @Override
    public SuperPerson getSuperPersonById(int superPersonId) {
        final String SELECT_SUPERPERSON_BY_ID = "SELECT * FROM SuperPerson WHERE superPersonId = ?";
        try {
            SuperPerson person = jdbc.queryForObject(SELECT_SUPERPERSON_BY_ID, new SuperPersonMapper(), superPersonId);
            person.setOrganizations(getOrganizationsForSuperPerson(person));
            person.setSightings(getSightingsForSuperPerson(person));
            return person;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    private List<Organization> getOrganizationsForSuperPerson(SuperPerson person) {
        final String SELECT_ORGANIZATIONS_FOR_SUPERPERSON = "SELECT o.* FROM SuperPersonOrganization spo "
                + "INNER JOIN organization o ON spo.organizationId = o.organizationId "
                + "WHERE spo.superPersonId = ?";
        List<Organization> superPersonOrganizations = jdbc.query(SELECT_ORGANIZATIONS_FOR_SUPERPERSON,
                new OrganizationMapper(), person.getSuperPersonId());
        return superPersonOrganizations;
    }

    private List<Sighting> getSightingsForSuperPerson(SuperPerson person) {
        final String SELECT_SIGHTINGS_FOR_SUPERPERSON = "SELECT s.* FROM SuperPersonSighting sps "
                + "INNER JOIN sighting s ON sps.sightingId = s.sightingId "
                + "WHERE sps.superPersonId = ?";
        List<Sighting> superPersonSightings = jdbc.query(SELECT_SIGHTINGS_FOR_SUPERPERSON,
                new SightingMapper(), person.getSuperPersonId());

        for (Sighting sighting : superPersonSightings) {
            sighting.setLocation(getLocationForSighting(sighting.getSightingId()));
        }
        return superPersonSightings;
    }

    private Location getLocationForSighting(int sightingId) {
        final String SELECT_LOCATION_FOR_SIGHTING = "SELECT l.* FROM sighting s "
                + "INNER JOIN location l ON s.locationId = l.locationId "
                + "WHERE s.sightingId = ?";
        try {
            Location location = jdbc.queryForObject(SELECT_LOCATION_FOR_SIGHTING, new LocationDaoDB.LocationMapper(), sightingId);
            return location;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    @Transactional
    public SuperPerson createSuperPerson(SuperPerson person) {
        final String INSERT_SUPERPERSON = "INSERT INTO SuperPerson(superPersonName, description, superpower) "
                + "VALUES(?, ?, ?)";
        jdbc.update(INSERT_SUPERPERSON, person.getName(), person.getDescription(), person.getSuperpower());
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()",
                Integer.class);
        person.setSuperPersonId(newId);

        insertSuperPersonOrganization(person);
        insertSuperPersonSighting(person);

        return person;
    }

    private void insertSuperPersonOrganization(SuperPerson person) {
        final String INSERT_SUPERPERSON_ORGANIZATION = "INSERT INTO SuperPersonOrganization(superPersonId, "
                + "organizationId) VALUES(?, ?)";
        for (Organization organization : person.getOrganizations()) {
            jdbc.update(INSERT_SUPERPERSON_ORGANIZATION, person.getSuperPersonId(),
                    organization.getOrganizationId());
        }
    }

    @Override
    public void insertSuperPersonSighting(SuperPerson person) {
        final String INSERT_SUPERPERSON_SIGHTING = "INSERT INTO SuperPersonSighting(superPersonId, "
                + "sightingId) VALUES(?, ?)";
        for (Sighting sighting : person.getSightings()) {
            jdbc.update(INSERT_SUPERPERSON_SIGHTING, person.getSuperPersonId(),
                    sighting.getSightingId());
        }
    }

    @Override
    public void deleteSuperPersonById(int superPersonId) {
        final String DELETE_SUPERPERSON_ORGANIZATION_BY_SUPERPERSON = "DELETE FROM SuperPersonOrganization "
                + "WHERE superPersonId = ?";
        jdbc.update(DELETE_SUPERPERSON_ORGANIZATION_BY_SUPERPERSON, superPersonId);

        final String DELETE_SUPERPERSON_SIGHTING_BY_SUPERPERSON = "DELETE FROM SuperPersonSighting "
                + "WHERE superPersonId = ?";
        jdbc.update(DELETE_SUPERPERSON_SIGHTING_BY_SUPERPERSON, superPersonId);

        final String DELETE_SUPERPERSON_BY_ID = "DELETE FROM SuperPerson WHERE superPersonId = ?";
        jdbc.update(DELETE_SUPERPERSON_BY_ID, superPersonId);
    }

    @Override
    @Transactional
    public void updateSuperPerson(SuperPerson person) {
        final String UPDATE_SUPERPERSON = "UPDATE SuperPerson SET superPersonName = ?, description = ?, "
                + "superpower = ? WHERE superPersonId = ?";
        jdbc.update(UPDATE_SUPERPERSON,
                person.getName(),
                person.getDescription(),
                person.getSuperpower(),
                person.getSuperPersonId());

        final String DELETE_SUPERPERSON_ORGANIZATION_BY_SUPERPERSON = "DELETE FROM SuperPersonOrganization "
                + "where superPersonId = ?";
        jdbc.update(DELETE_SUPERPERSON_ORGANIZATION_BY_SUPERPERSON, person.getSuperPersonId());

        final String DELETE_SUPERPERSON_SIGHTING_BY_SUPERPERSON = "DELETE FROM SuperPersonSighting "
                + "where superPersonId = ?";
        jdbc.update(DELETE_SUPERPERSON_SIGHTING_BY_SUPERPERSON, person.getSuperPersonId());

        insertSuperPersonOrganization(person);
        insertSuperPersonSighting(person);
    }

    @Override
    public List<SuperPerson> getAllSuperPersonsByOrganization(Organization organization) {
        final String SELECT_SUPERPERSONS_FOR_ORGANIZATION = "SELECT sp.* FROM SuperPersonOrganization spo "
                + "INNER JOIN SuperPerson sp ON spo.superPersonId = sp.superPersonId "
                + "WHERE spo.organizationId = ?";
        List<SuperPerson> persons = jdbc.query(SELECT_SUPERPERSONS_FOR_ORGANIZATION,
                new SuperPersonMapper(), organization.getOrganizationId());
        associateOrganizationsToSuperPersons(persons);
        associateSightingsToSuperPersons(persons);
        return persons;
    }

    @Override
    public List<SuperPerson> getAllSuperPersonsByLocation(Location location) {
        final String SELECT_SUPERPERSONS_BY_LOCATION = "SELECT sp.* FROM SuperPerson sp "
                + "INNER JOIN SuperPersonSighting sps ON sp.superPersonId = sps.superPersonId "
                + "INNER JOIN Sighting s ON sps.sightingId = s.sightingId "
                + "INNER JOIN Location l ON s.locationId = l.locationId "
                + "WHERE l.locationId = ? GROUP BY sp.superPersonName";
        List<SuperPerson> persons = jdbc.query(SELECT_SUPERPERSONS_BY_LOCATION, new SuperPersonMapper(),
                location.getLocationId());
        associateOrganizationsToSuperPersons(persons);
        associateSightingsToSuperPersons(persons);
        return persons;
    }

    @Override
    public List<SuperPerson> getAllSuperPersonForSighting(Sighting sighting) {
        final String GET_SUPERPERSON_FOR_SIGHTING = "SELECT sp.* FROM SuperPersonSighting sps "
                + "INNER JOIN superperson sp ON sps.superpersonId = sp.superpersonId "
                + "WHERE sps.sightingId = ?";
        List<SuperPerson> persons = jdbc.query(GET_SUPERPERSON_FOR_SIGHTING, new SuperPersonMapper(),
                sighting.getSightingId());
        associateOrganizationsToSuperPersons(persons);
        associateSightingsToSuperPersons(persons);
        return persons;
    }

    public static final class SuperPersonMapper implements RowMapper<SuperPerson> {

        @Override
        public SuperPerson mapRow(ResultSet rs, int i) throws SQLException {
            SuperPerson person = new SuperPerson();
            person.setSuperPersonId(rs.getInt("superPersonId"));
            person.setName(rs.getString("superPersonName"));
            person.setDescription(rs.getString("description"));
            person.setSuperpower(rs.getString("superpower"));
            return person;
        }

    }

}
