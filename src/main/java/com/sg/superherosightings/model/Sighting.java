/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.model;

import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author nthao
 */
public class Sighting {
    private int sightingId;
    private LocalDate dateSeen;
    private Location location;

    public int getSightingId() {
        return sightingId;
    }

    public void setSightingId(int sightingId) {
        this.sightingId = sightingId;
    }

    public LocalDate getDateSeen() {
        return dateSeen;
    }

    public void setDateSeen(LocalDate dateSeen) {
        this.dateSeen = dateSeen;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + this.sightingId;
        hash = 97 * hash + Objects.hashCode(this.dateSeen);
        hash = 97 * hash + Objects.hashCode(this.location);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Sighting other = (Sighting) obj;
        if (this.sightingId != other.sightingId) {
            return false;
        }
        if (!Objects.equals(this.dateSeen, other.dateSeen)) {
            return false;
        }
        if (!Objects.equals(this.location, other.location)) {
            return false;
        }
        return true;
    }

    


}
