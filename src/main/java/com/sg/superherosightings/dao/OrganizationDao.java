/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.model.Organization;
import com.sg.superherosightings.model.SuperPerson;
import java.util.List;

/**
 *
 * @author nthao
 */
public interface OrganizationDao {
    
    List<Organization> getAllOrganizations();
    
    Organization getOrganizationById(int organizationId);
    
    Organization createOrganization(Organization organization);
    
    void deleteOrganizationById(int organizationId);
    
    void updateOrganization(Organization organization);
    
    List<Organization> getAllOrganizationsBySuperPerson(SuperPerson person);
}
