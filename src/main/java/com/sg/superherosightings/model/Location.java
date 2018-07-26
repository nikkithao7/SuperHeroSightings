/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.model;

import java.math.BigDecimal;
import java.util.Objects;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author nthao
 */
public class Location {

    private int locationId;
    @NotEmpty(message = "You must supply a value for the location name.")
    @Length(max = 45, message = "Location name must be no more than 45 characters in length.")
    private String name;
    @Length(max = 100, message = "Location description must be no more than 100 characters in length.")
    private String description;
    @NotEmpty(message = "You must supply a value for the street address.")
    @Length(max = 45, message = "Street address must be no more than 45 characters in length.")
    private String streetInfo;
    @NotEmpty(message = "You must supply a value for the city.")
    @Pattern(regexp = "[^0-9]*", message="Please input a valid city.")
    @Length(max = 45, message = "City must be no more than 45 characters in length.")
    private String city;
    @NotEmpty(message = "You must supply a value for the state.")
    @Pattern(regexp = "[^0-9]*", message="Please input a valid state.")
    @Length(max = 45, message = "State must be no more than 45 characters in length.")
    private String state;
    @NotNull
    @Min(501)
    @Max(99999)
    @Digits(integer = 5, fraction = 0, message = "Zipcode must be no more than 5 characters in length.")
    private int zipcode;
    @NotNull(message = "You must supply a value for the latitude.")
    @Digits(integer = 8, fraction = 6, message = "Latitude must be no more than 8 characters in length.")
    private BigDecimal latitude;
    @NotNull(message = "You must supply a value for the longitude.")
    @Digits(integer = 9, fraction = 6, message = "Longitude must be no more than 9 characters in length.")
    private BigDecimal longitude;

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStreetInfo() {
        return streetInfo;
    }

    public void setStreetInfo(String streetInfo) {
        this.streetInfo = streetInfo;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getZipcode() {
        return zipcode;
    }

    public void setZipcode(int zipcode) {
        this.zipcode = zipcode;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + this.locationId;
        hash = 97 * hash + Objects.hashCode(this.name);
        hash = 97 * hash + Objects.hashCode(this.description);
        hash = 97 * hash + Objects.hashCode(this.streetInfo);
        hash = 97 * hash + Objects.hashCode(this.city);
        hash = 97 * hash + Objects.hashCode(this.state);
        hash = 97 * hash + this.zipcode;
        hash = 97 * hash + Objects.hashCode(this.latitude);
        hash = 97 * hash + Objects.hashCode(this.longitude);
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
        final Location other = (Location) obj;
        if (this.locationId != other.locationId) {
            return false;
        }
        if (this.zipcode != other.zipcode) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.streetInfo, other.streetInfo)) {
            return false;
        }
        if (!Objects.equals(this.city, other.city)) {
            return false;
        }
        if (!Objects.equals(this.state, other.state)) {
            return false;
        }
        if (!Objects.equals(this.latitude, other.latitude)) {
            return false;
        }
        if (!Objects.equals(this.longitude, other.longitude)) {
            return false;
        }
        return true;
    }

}
