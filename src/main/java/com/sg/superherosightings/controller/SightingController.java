/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.controller;

import com.sg.superherosightings.dao.SightingDao;
import com.sg.superherosightings.dao.LocationDao;
import com.sg.superherosightings.dao.SuperPersonDao;
import com.sg.superherosightings.model.Location;
import com.sg.superherosightings.model.Sighting;
import com.sg.superherosightings.model.SuperPerson;
import static java.lang.Integer.parseInt;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
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
public class SightingController {

    SightingDao sightingDao;
    LocationDao locationDao;
    SuperPersonDao superPersonDao;

    @Inject
    public SightingController(SightingDao sightingDao, LocationDao locationDao, SuperPersonDao superPersonDao) {
        this.sightingDao = sightingDao;
        this.locationDao = locationDao;
        this.superPersonDao = superPersonDao;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String displayMostRecentSightings(Model model) {
        List<Sighting> mostRecentSightings = sightingDao.getMostRecentSightings();
        model.addAttribute("mostRecentSightings", mostRecentSightings);

        return "index";
    }

    @RequestMapping(value = "/sighting", method = RequestMethod.GET)
    public String displayAllSightingsPage(Model model) {

        List<Sighting> sightingToDisplay = sightingDao.getAllSightings();
        model.addAttribute("spSightList", sightingToDisplay);

        return "sighting";
    }

    @RequestMapping(value = "/displayAddSighting", method = RequestMethod.GET)
    public String displayAddSightingPage(Locale locale, Model model) {
        List<Location> locationList = locationDao.getAllLocations();
        List<SuperPerson> superPersonList = superPersonDao.getAllSuperPersons();

        model.addAttribute("sighting", new Sighting());
        model.addAttribute("locationList", locationList);
        model.addAttribute("superPersonList", superPersonList);
        return "addSighting";
    }

    @RequestMapping(value = "/createSighting", method = RequestMethod.POST)
    public String createSighting(@ModelAttribute("sighting") Sighting sighting, BindingResult result,
            HttpServletRequest request) {

        if (result.hasErrors()) {
            return "addSighting";
        }
        LocalDate dateJsp = LocalDate.parse(request.getParameter("dateSeenInput"));
        sighting.setDateSeen(dateJsp);

        int locationId = parseInt(request.getParameter("locationSelected"));
        Location location = locationDao.getLocationById(locationId);
        sighting.setLocation(location);

        List<Sighting> sights = new ArrayList<>();
        sighting = sightingDao.createSighting(sighting);
        sights.add(sighting);

        String[] superpersonSelected = request.getParameterValues("superPersonSelected");
        for (int i = 0; i < superpersonSelected.length; i++) {
            int spId = Integer.parseInt(superpersonSelected[i]);
            SuperPerson superperson = superPersonDao.getSuperPersonById(spId);
            superperson.setSightings(sights);
            superPersonDao.insertSuperPersonSighting(superperson);
        }

        return "redirect:sighting";
    }

    @RequestMapping(value = "/deleteSighting", method = RequestMethod.GET)
    public String deleteSighting(HttpServletRequest request) {
        String sightingIdParameter = request.getParameter("sightingId");
        int sightingId = parseInt(sightingIdParameter);

        sightingDao.deleteSightingById(sightingId);

        return "redirect:sighting";
    }

    @RequestMapping(value = "/displayEditSighting", method = RequestMethod.GET)
    public String displayEditSighting(HttpServletRequest request, Model model) {
        String sightingIdParameter = request.getParameter("sightingId");
        int sightingId = parseInt(sightingIdParameter);
        Sighting sighting = sightingDao.getSightingById(sightingId);

        List<Location> locationList = locationDao.getAllLocations();
        Location selectedLocation = sighting.getLocation();
        locationList.remove(selectedLocation);

        List<SuperPerson> selectedSuperPersons = superPersonDao.getAllSuperPersonForSighting(sighting);
        List<SuperPerson> allSuperPersonList = superPersonDao.getAllSuperPersons();

        for (SuperPerson sp : selectedSuperPersons) {
            SuperPerson spToRemove = superPersonDao.getSuperPersonById(sp.getSuperPersonId());
            allSuperPersonList.remove(spToRemove);
        }

        model.addAttribute("sighting", sighting);
        model.addAttribute("locationList", locationList);
        model.addAttribute("superPersonList", allSuperPersonList);
        model.addAttribute("selectedSP", selectedSuperPersons);

        return "editSighting";
    }

    @RequestMapping(value = "/editSighting", method = RequestMethod.POST)
    public String editSighting(@ModelAttribute("sighting") Sighting sighting, BindingResult result,
            HttpServletRequest request) {

        if (result.hasErrors()) {
            return "addSighting";
        }
        LocalDate dateJsp = LocalDate.parse(request.getParameter("dateSeenInput"));
        sighting.setDateSeen(dateJsp);

        int locationId = parseInt(request.getParameter("locationSelected"));
        Location location = locationDao.getLocationById(locationId);
        sighting.setLocation(location);

        List<Sighting> sights = new ArrayList<>();
        sightingDao.updateSighting(sighting);
        sights.add(sighting);

        String[] superpersonSelected = request.getParameterValues("superPersonSelected");
        for (int i = 0; i < superpersonSelected.length; i++) {
            int spId = Integer.parseInt(superpersonSelected[i]);
            SuperPerson superperson = superPersonDao.getSuperPersonById(spId);
            superperson.setSightings(sights);
            superPersonDao.insertSuperPersonSighting(superperson);
        }

        return "redirect:sighting";
    }

    @RequestMapping(value = "/displaySightingDetails", method = RequestMethod.GET)
    public String displaySightingDetails(HttpServletRequest request, Model model) {
        String sightingIdParameter = request.getParameter("sightingId");
        int sightingId = Integer.parseInt(sightingIdParameter);

        Sighting sighting = sightingDao.getSightingById(sightingId);
        model.addAttribute("sighting", sighting);

        List<SuperPerson> superPersonsAtSighting = superPersonDao.getAllSuperPersonForSighting(sighting);
        model.addAttribute("superPersonsAtSighting", superPersonsAtSighting);

        return "sightingDetails";
    }

}
