/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.controller;

import com.sg.superherosightings.dao.OrganizationDao;
import com.sg.superherosightings.model.Organization;
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
public class OrganizationController {

    OrganizationDao organizationDao;

    @Inject
    public OrganizationController(OrganizationDao organizationDao) {
        this.organizationDao = organizationDao;
    }

    @RequestMapping(value = "/organization", method = RequestMethod.GET)
    public String displayAllOrganizationsPage(Model model) {

        List<Organization> organizationList = organizationDao.getAllOrganizations();
        model.addAttribute("organizationList", organizationList);

        return "organization";
    }

    @RequestMapping(value = "/displayAddOrganization", method = RequestMethod.GET)
    public String displayAddOrganizationPage(Locale locale, Model model) {
        model.addAttribute("organization", new Organization());
        return "addOrganization";
    }

    @RequestMapping(value = "/createOrganization", method = RequestMethod.POST)
    public String createOrganization(@Valid @ModelAttribute("organization") Organization organization, BindingResult result) {

        if (result.hasErrors()) {
            return "addOrganization";
        }

        organizationDao.createOrganization(organization);

        return "redirect:organization";
    }

    @RequestMapping(value = "/deleteOrganization", method = RequestMethod.GET)
    public String deleteOrganization(HttpServletRequest request) {
        String OrganizationIdParameter = request.getParameter("organizationId");
        int OrganizationId = parseInt(OrganizationIdParameter);

        organizationDao.deleteOrganizationById(OrganizationId);

        return "redirect:organization";
    }

    @RequestMapping(value = "/displayEditOrganization", method = RequestMethod.GET)
    public String displayEditOrganization(HttpServletRequest request, Model model) {
        String organizationIdParameter = request.getParameter("organizationId");
        int organizationId = parseInt(organizationIdParameter);
        Organization organization = organizationDao.getOrganizationById(organizationId);
        model.addAttribute("organization", organization);
        
        return "editOrganization";
    }

    @RequestMapping(value = "/editOrganization", method = RequestMethod.POST)
    public String editOrganization(@Valid @ModelAttribute("organization") Organization organization, BindingResult result) {

        if (result.hasErrors()) {
            return "editOrganization";
        }

        organizationDao.updateOrganization(organization);
        
        return "redirect:organization";
    }
}