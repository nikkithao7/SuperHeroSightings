/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.model.Organization;
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
public class OrganizationDaoDB implements OrganizationDao {

    @Inject
    JdbcTemplate jdbc;

    @Override
    public List<Organization> getAllOrganizations() {
        final String SELECT_ALL_ORGANIZATIONS = "SELECT * FROM organization";
        return jdbc.query(SELECT_ALL_ORGANIZATIONS, new OrganizationMapper());
    }

    @Override
    public Organization getOrganizationById(int organizationId) {
        final String SELECT_ORGANIZATION_BY_ID = "SELECT * FROM organization WHERE organizationId = ?";
        try {
            return jdbc.queryForObject(SELECT_ORGANIZATION_BY_ID, new OrganizationMapper(), organizationId);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    @Transactional
    public Organization createOrganization(Organization organization) {
        final String INSERT_ORGANIZATION = "INSERT INTO organization(orgName, description, streetInfo, "
                + "city, state, zipcode, phoneNumber) VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbc.update(INSERT_ORGANIZATION, organization.getName(), organization.getDescription(),
                organization.getStreetInfo(), organization.getCity(), organization.getState(),
                organization.getZipcode(), organization.getPhoneNumber());
        int newId = jdbc.queryForObject("select LAST_INSERT_ID()", Integer.class);
        organization.setOrganizationId(newId);
        return organization;
    }

    @Override
    public void deleteOrganizationById(int organizationId) {
        final String DELETE_SUPERPERSON_ORGANIZATION_BY_ORGANIZATION = "DELETE FROM SuperPersonOrganization "
                + "where organizationId = ?";
        jdbc.update(DELETE_SUPERPERSON_ORGANIZATION_BY_ORGANIZATION, organizationId);

        final String DELETE_ORGANIZATION_BY_ID = "DELETE FROM organization where organizationId = ?";
        jdbc.update(DELETE_ORGANIZATION_BY_ID, organizationId);
    }

    @Override
    public void updateOrganization(Organization organization) {
        final String UPDATE_ORGANIZATION = "UPDATE organization SET orgName = ?, description = ?, "
                + "streetInfo = ?, city = ?, state = ?, zipcode = ?, phoneNumber = ? "
                + "WHERE organizationId = ?";
        jdbc.update(UPDATE_ORGANIZATION,
                organization.getName(), organization.getDescription(),
                organization.getStreetInfo(), organization.getCity(), organization.getState(),
                organization.getZipcode(), organization.getPhoneNumber(), organization.getOrganizationId());
    }

    @Override
    public List<Organization> getAllOrganizationsBySuperPerson(SuperPerson person) {
        final String SELECT_ALL_ORGANIZATIONS_BY_SUPERPERSON = "SELECT o.* FROM SuperPersonOrganization spo "
                + "INNER JOIN organization o ON spo.organizationId = o.organizationId "
                + "WHERE spo.superPersonId = ?";
        return jdbc.query(SELECT_ALL_ORGANIZATIONS_BY_SUPERPERSON, 
                new OrganizationMapper(), person.getSuperPersonId());
    }

    public static final class OrganizationMapper implements RowMapper<Organization> {

        @Override
        public Organization mapRow(ResultSet rs, int i) throws SQLException {
            Organization organization = new Organization();
            organization.setOrganizationId(rs.getInt("organizationId"));
            organization.setName(rs.getString("orgName"));
            organization.setDescription(rs.getString("description"));
            organization.setStreetInfo(rs.getString("streetInfo"));
            organization.setCity(rs.getString("city"));
            organization.setState(rs.getString("state"));
            organization.setZipcode(rs.getInt("zipcode"));
            organization.setPhoneNumber(rs.getString("phoneNumber"));
            return organization;
        }

    }
}
