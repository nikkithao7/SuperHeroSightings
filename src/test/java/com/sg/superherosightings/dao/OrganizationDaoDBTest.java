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
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author nthao
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:test-applicationContext.xml")
public class OrganizationDaoDBTest {

    @Inject
    OrganizationDao organizationDao;

    @Inject
    SuperPersonDao superPersonDao;

    @Inject
    LocationDao locationDao;

    @Inject
    SightingDao sightingDao;

    public OrganizationDaoDBTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        List<Sighting> sightings = sightingDao.getAllSightings();
        for (Sighting s : sightings) {
            sightingDao.deleteSightingById(s.getSightingId());
        }

        List<Location> locations = locationDao.getAllLocations();
        for (Location l : locations) {
            locationDao.deleteLocationById(l.getLocationId());
        }

        List<SuperPerson> persons = superPersonDao.getAllSuperPersons();
        for (SuperPerson p : persons) {
            superPersonDao.deleteSuperPersonById(p.getSuperPersonId());
        }

        List<Organization> organizations = organizationDao.getAllOrganizations();
        for (Organization o : organizations) {
            organizationDao.deleteOrganizationById(o.getOrganizationId());
        }
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getAllOrganizations method, of class OrganizationDaoDB.
     */
    @Test
    public void testGetAllOrganizations() {
        Organization org = new Organization();
        org.setName("The Agency");
        org.setDescription("A shadow committee consisting of military leaders, intelligence experts "
                + "and government officials.");
        org.setStreetInfo("1111 Agency St.");
        org.setCity("Gotham City");
        org.setState("Gotham");
        org.setZipcode(55107);
        org.setPhoneNumber("777-777-7777");
        org = organizationDao.createOrganization(org);

        Organization org2 = new Organization();
        org2.setName("Suicide Squad");
        org2.setDescription("A group of bad guys becoming super heroes.");
        org2.setStreetInfo("122 Unknown St.");
        org2.setCity("Gotham City");
        org2.setState("Gotham");
        org2.setZipcode(12345);
        org2.setPhoneNumber("123-555-7777");
        org2 = organizationDao.createOrganization(org2);

        assertEquals(2, organizationDao.getAllOrganizations().size());
    }

    /**
     * Test of createOrganization & getOrganizationById method, of class
     * OrganizationDaoDB.
     */
    @Test
    public void testCreateAndGetOrganization() {
        Organization org = new Organization();
        org.setName("The Agency");
        org.setDescription("A shadow committee consisting of military leaders, intelligence experts "
                + "and government officials.");
        org.setStreetInfo("1111 Agency St.");
        org.setCity("Gotham City");
        org.setState("Gotham");
        org.setZipcode(55107);
        org.setPhoneNumber("777-777-7777");

        org = organizationDao.createOrganization(org);
        Organization fromDao = organizationDao.getOrganizationById(org.getOrganizationId());

        assertEquals(org, fromDao);
    }

    /**
     * Test of deleteOrganizationById method, of class OrganizationDaoDB.
     */
    @Test
    public void testDeleteOrganizationById() {
        Organization org = new Organization();
        org.setName("The Agency");
        org.setDescription("A shadow committee consisting of military leaders, intelligence experts "
                + "and government officials.");
        org.setStreetInfo("1111 Agency St.");
        org.setCity("Gotham City");
        org.setState("Gotham");
        org.setZipcode(55107);
        org.setPhoneNumber("777-777-7777");

        org = organizationDao.createOrganization(org);
        organizationDao.deleteOrganizationById(org.getOrganizationId());
        assertEquals(0, organizationDao.getAllOrganizations().size());
    }

    /**
     * Test of updateOrganization method, of class OrganizationDaoDB.
     */
    @Test
    public void testUpdateOrganization() {
        Organization org = new Organization();
        org.setName("The Agency");
        org.setDescription("A shadow committee consisting of military leaders, intelligence experts "
                + "and government officials.");
        org.setStreetInfo("1111 Agency St.");
        org.setCity("Gotham City");
        org.setState("Gotham");
        org.setZipcode(55107);
        org.setPhoneNumber("777-777-7777");

        org = organizationDao.createOrganization(org);
        org.setZipcode(55117);
        organizationDao.updateOrganization(org);

        Organization fromDao = organizationDao.getOrganizationById(org.getOrganizationId());

        assertEquals(org, fromDao);

    }

    /**
     * Test of getAllOrganizationsBySuperPerson method, of class
     * OrganizationDaoDB.
     */
    @Test
    public void testGetAllOrganizationsBySuperPerson() {
        Organization org = new Organization();
        org.setName("The Agency");
        org.setDescription("A shadow committee consisting of military leaders, intelligence experts "
                + "and government officials.");
        org.setStreetInfo("1111 Agency St.");
        org.setCity("Gotham City");
        org.setState("Gotham");
        org.setZipcode(55107);
        org.setPhoneNumber("777-777-7777");
        org = organizationDao.createOrganization(org);
        List<Organization> orgs = new ArrayList();
        orgs.add(org);

        Organization org2 = new Organization();
        org2.setName("DC");
        org2.setDescription("Warner Bro company. Superheros include Batman & Superman.");
        org2.setStreetInfo("123 DC St.");
        org2.setCity("St.Paul");
        org2.setState("MN");
        org2.setZipcode(55117);
        org2.setPhoneNumber("555-777-7777");
        org2 = organizationDao.createOrganization(org2);
        orgs.add(org2);
        
        List<Organization> batmanOrgList = new ArrayList(); 
        batmanOrgList.add(org2);

        Location location = new Location();
        location.setName("Arkham Asylum");
        location.setDescription("A dark asylum.");
        location.setStreetInfo("1234 Asylum St.");
        location.setCity("Gotham City");
        location.setState("Gotham");
        location.setZipcode(55107);
        location.setLatitude(new BigDecimal("78.444435"));
        location.setLongitude(new BigDecimal("123.333444"));
        location = locationDao.createLocation(location);

        Sighting sighting = new Sighting();
        sighting.setDateSeen(LocalDate.now());
        sighting.setLocation(location);
        sighting = sightingDao.createSighting(sighting);
        List<Sighting> sightingsSuperman = new ArrayList();
        sightingsSuperman.add(sighting);

        Sighting sighting2 = new Sighting();
        sighting2.setDateSeen(LocalDate.parse("2018-06-30"));
        sighting2.setLocation(location);
        sighting2 = sightingDao.createSighting(sighting2);
        List<Sighting> sightingsBatman = new ArrayList();
        sightingsBatman.add(sighting2);

        SuperPerson person = new SuperPerson();
        person.setName("Superman");
        person.setDescription("Curly bang with glasses");
        person.setSuperpower("X-ray vision");
        person.setOrganizations(orgs);
        person.setSightings(sightingsSuperman);
        person = superPersonDao.createSuperPerson(person);

        SuperPerson person2 = new SuperPerson();
        person2.setName("Batman");
        person2.setDescription("Black suit");
        person2.setSuperpower("Super wealthy");
        person2.setOrganizations(batmanOrgList);
        person2.setSightings(sightingsBatman);
        person2 = superPersonDao.createSuperPerson(person2);
        
        assertEquals(2, organizationDao.getAllOrganizationsBySuperPerson(person).size());
        assertEquals(1, organizationDao.getAllOrganizationsBySuperPerson(person2).size());
    }

}
