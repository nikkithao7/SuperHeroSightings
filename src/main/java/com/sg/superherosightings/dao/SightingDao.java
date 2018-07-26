/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.model.Location;
import com.sg.superherosightings.model.Sighting;
import com.sg.superherosightings.model.SuperPerson;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author nthao
 */
public interface SightingDao {
    
    List<Sighting> getAllSightings();
    
    List<Sighting> getMostRecentSightings();
    
    Sighting getSightingById(int sightingId);
    
    Sighting createSighting(Sighting sighting);
    
    void deleteSightingById(int sightingId);
    
    void updateSighting(Sighting sighting);
    
    List<Sighting> getAllsightingsByDate(LocalDate date);
    
    List<Sighting> getAllSightingsByLocation(Location location);
    
    List<Sighting> getAllSightingsBySuperPerson(SuperPerson person);
}
