/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.controller;

import com.sg.superherosightings.dao.LocationDao;
import com.sg.superherosightings.model.Location;
import static java.lang.Integer.parseInt;
import java.util.List;
import java.util.Locale;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author nthao
 */
@Controller
public class LocationController {

    LocationDao locationDao;

    @Inject
    public LocationController(LocationDao locationDao) {
        this.locationDao = locationDao;
    }

    @RequestMapping(value = "/location", method = RequestMethod.GET)
    public String displayAllLocationsPage(Model model) {

        List<Location> locationList = locationDao.getAllLocations();
        model.addAttribute("locationList", locationList);

        return "location";
    }

    @RequestMapping(value = "/displayAddLocation", method = RequestMethod.GET)
    public String displayAddLocationPage(Locale locale, Model model) {
        model.addAttribute("location", new Location());
        return "addLocation";
    }

    @RequestMapping(value = "/createLocation", method = RequestMethod.POST)
    public String createLocation(@Valid @ModelAttribute("location") Location location, BindingResult result) {


        if (result.hasErrors()) {
            return "addLocation";
        }

        locationDao.createLocation(location);

        return "redirect:location";
    }

    @RequestMapping(value = "/deleteLocation", method = RequestMethod.GET)
    public String deleteLocation(HttpServletRequest request) {
        String locationIdParameter = request.getParameter("locationId");
        int locationId = parseInt(locationIdParameter);

        locationDao.deleteLocationById(locationId);

        return "redirect:location";
    }

    @RequestMapping(value = "/displayEditLocation", method = RequestMethod.GET)
    public String displayEditLocation(HttpServletRequest request, Model model) {
        String locationIdParameter = request.getParameter("locationId");
        int locationId = parseInt(locationIdParameter);
        Location location = locationDao.getLocationById(locationId);
        model.addAttribute("location", location);
        return "editLocation";
    }

    @RequestMapping(value = "/editLocation", method = RequestMethod.POST)
    public String editLocation(@Valid @ModelAttribute("location") Location location, BindingResult result) {

        if (result.hasErrors()) {
            return "editLocation";
        }

        locationDao.updateLocation(location);
        
        return "redirect:location";
    }
}
