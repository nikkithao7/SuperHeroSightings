/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.controller;

import com.sg.superherosightings.dao.OrganizationDao;
import com.sg.superherosightings.dao.SightingDao;
import com.sg.superherosightings.dao.SuperPersonDao;
import com.sg.superherosightings.model.Organization;
import com.sg.superherosightings.model.Sighting;
import com.sg.superherosightings.model.SuperPerson;
import static java.lang.Integer.parseInt;
import java.util.ArrayList;
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
public class SuperPersonController {

    SuperPersonDao superPersonDao;
    OrganizationDao organizationDao;
    SightingDao sightingDao;

    @Inject
    public SuperPersonController(SuperPersonDao superPersonDao, OrganizationDao organizationDao,
            SightingDao sightingDao) {
        this.superPersonDao = superPersonDao;
        this.organizationDao = organizationDao;
        this.sightingDao = sightingDao;
    }

    @RequestMapping(value = "/superperson", method = RequestMethod.GET)
    public String displayAllSuperPersonsPage(Model model) {

        List<SuperPerson> superPersonList = superPersonDao.getAllSuperPersons();
        model.addAttribute("superPersonList", superPersonList);

        return "superperson";
    }

    @RequestMapping(value = "/displaySuperPersonDetails", method = RequestMethod.GET)
    public String displaySuperPersonDetails(HttpServletRequest request, Model model) {
        String superPersonIdParameter = request.getParameter("superPersonId");
        int superPersonId = Integer.parseInt(superPersonIdParameter);

        SuperPerson superperson = superPersonDao.getSuperPersonById(superPersonId);

        model.addAttribute("superPerson", superperson);

        return "superPersonDetails";
    }

    @RequestMapping(value = "/displayAddSuperPerson", method = RequestMethod.GET)
    public String displayAddSuperPersonPage(Locale locale, Model model) {
        List<Organization> orgList = organizationDao.getAllOrganizations();

        model.addAttribute("superperson", new SuperPerson());
        model.addAttribute("orgList", orgList);
        return "addSuperPerson";
    }

    @RequestMapping(value = "/createSuperPerson", method = RequestMethod.POST)
    public String createSuperPerson(@Valid @ModelAttribute("superperson") SuperPerson superperson,
            BindingResult result, HttpServletRequest request) {
        List<Organization> orgList = new ArrayList<>();

        if (result.hasErrors()) {
            return "addSuperPerson";
        }
        String[] org = request.getParameterValues("orgSelection");
        for (String org1 : org) {
            if (!org1.equalsIgnoreCase("none")) {
                int orgId = parseInt(org1);
                Organization orgToAdd = organizationDao.getOrganizationById(orgId);
                orgList.add(orgToAdd);
            }
        }
        superperson.setOrganizations(orgList);
        superPersonDao.createSuperPerson(superperson);

        return "redirect:superperson";
    }

    @RequestMapping(value = "/deleteSuperPerson", method = RequestMethod.GET)
    public String deleteSuperPerson(HttpServletRequest request) {
        String superPersonIdParameter = request.getParameter("superPersonId");
        int superPersonId = parseInt(superPersonIdParameter);

        superPersonDao.deleteSuperPersonById(superPersonId);

        return "redirect:superperson";
    }

    @RequestMapping(value = "/displayEditSuperPerson", method = RequestMethod.GET)
    public String displayEditSuperPerson(HttpServletRequest request, Model model) {
        String superPersonIdParameter = request.getParameter("superPersonId");
        int superPersonId = parseInt(superPersonIdParameter);
        SuperPerson superperson = superPersonDao.getSuperPersonById(superPersonId);

        List<Organization> orgListForSuperPerson = superperson.getOrganizations();
        List<Organization> allOrgList = organizationDao.getAllOrganizations();
        for (Organization org : orgListForSuperPerson) {
            allOrgList.remove(org);
        }

        model.addAttribute("orgsSelected", orgListForSuperPerson);
        model.addAttribute("orgsNotSelected", allOrgList);
        model.addAttribute("superperson", superperson);

        return "editSuperPerson";
    }

    @RequestMapping(value = "/editSuperPerson", method = RequestMethod.POST)
    public String editSuperPerson(@Valid @ModelAttribute("superperson") SuperPerson superperson,
            BindingResult result, HttpServletRequest request) {
        List<Organization> orgList = new ArrayList<>();

        if (result.hasErrors()) {
            return "editSuperPerson";
        }
        String[] org = request.getParameterValues("orgSelection");
        for (String org1 : org) {
            if (!org1.equalsIgnoreCase("none")) {
                int orgId = parseInt(org1);
                Organization organization = organizationDao.getOrganizationById(orgId);
                orgList.add(organization);
            }
        }
        superperson.setOrganizations(orgList);
        List<Sighting> sightings = sightingDao.getAllSightingsBySuperPerson(superperson);
        superperson.setSightings(sightings);
        superPersonDao.updateSuperPerson(superperson);
        return "redirect:superperson";
    }

}
