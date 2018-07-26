/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.model.Location;
import com.sg.superherosightings.model.SuperPerson;
import java.util.List;

/**
 *
 * @author nthao
 */
public interface LocationDao {

    List<Location> getAllLocations();

    Location getLocationById(int locationId);

    Location createLocation(Location location);

    void deleteLocationById(int locationId);

    void updateLocation(Location location);

    List<Location> getAllLocationsBySuperPerson(SuperPerson person);

}
