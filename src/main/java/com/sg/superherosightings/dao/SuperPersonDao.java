/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.model.Location;
import com.sg.superherosightings.model.Organization;
import com.sg.superherosightings.model.Sighting;
import com.sg.superherosightings.model.SuperPerson;
import java.util.List;

/**
 *
 * @author nthao
 */
public interface SuperPersonDao {

    List<SuperPerson> getAllSuperPersons();

    SuperPerson getSuperPersonById(int superPersonId);

    SuperPerson createSuperPerson(SuperPerson person);

    void deleteSuperPersonById(int superPersonId);

    void updateSuperPerson(SuperPerson person);

    List<SuperPerson> getAllSuperPersonsByOrganization(Organization organization);
    
    List<SuperPerson> getAllSuperPersonsByLocation(Location location);

    void insertSuperPersonSighting(SuperPerson superperson);
    
    List <SuperPerson> getAllSuperPersonForSighting(Sighting sighting);
}
