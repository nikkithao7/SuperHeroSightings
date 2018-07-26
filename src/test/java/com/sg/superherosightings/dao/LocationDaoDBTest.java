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
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author nthao
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:test-applicationContext.xml")
public class LocationDaoDBTest {

    @Inject
    LocationDao locationDao;

    @Inject
    SuperPersonDao superPersonDao;

    @Inject
    OrganizationDao organizationDao;

    @Inject
    SightingDao sightingDao;

    public LocationDaoDBTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        List<SuperPerson> persons = superPersonDao.getAllSuperPersons();
        for (SuperPerson p : persons) {
            superPersonDao.deleteSuperPersonById(p.getSuperPersonId());
        }

        List<Organization> organizations = organizationDao.getAllOrganizations();
        for (Organization o : organizations) {
            organizationDao.deleteOrganizationById(o.getOrganizationId());
        }

        List<Sighting> sightings = sightingDao.getAllSightings();
        for (Sighting s : sightings) {
            sightingDao.deleteSightingById(s.getSightingId());
        }

        List<Location> locations = locationDao.getAllLocations();
        for (Location l : locations) {
            locationDao.deleteLocationById(l.getLocationId());
        }
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getAllLocations method, of class LocationDaoDB.
     */
    @Test
    public void testGetAllLocations() {
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

        Location location2 = new Location();
        location2.setName("Software Guild");
        location2.setDescription("Learn Java & .NET here!");
        location2.setStreetInfo("15 S 5th St");
        location2.setCity("Minneapolis");
        location2.setState("MN");
        location2.setZipcode(55402);
        location2.setLatitude(new BigDecimal("44.979006"));
        location2.setLongitude(new BigDecimal("-93.271816"));

        location2 = locationDao.createLocation(location2);

        assertEquals(2, locationDao.getAllLocations().size());
    }

    /**
     * Test of createLocation & getLocationById method, of class LocationDaoDB.
     */
    @Test
    public void testCreateLocationAndGetLocationById() {
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
        Location fromDao = locationDao.getLocationById(location.getLocationId());

        assertEquals(location, fromDao);
    }

    /**
     * Test of deleteLocationById method, of class LocationDaoDB.
     */
    @Test
    public void testDeleteLocationById() {
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
        locationDao.deleteLocationById(location.getLocationId());
        assertNull(locationDao.getLocationById(location.getLocationId()));
    }

    /**
     * Test of updateLocation method, of class LocationDaoDB.
     */
    @Test
    public void testUpdateLocation() {
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
        location.setDescription("A super dark asylum.");
        locationDao.updateLocation(location);

        Location fromDao = locationDao.getLocationById(location.getLocationId());

        assertEquals(location, fromDao);
    }

    /**
     * Test of getAllLocationsBySuperPerson method, of class LocationDaoDB.
     */
    @Test
    public void testGetAllLocationsBySuperPerson() {
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
        List<Sighting> sightings = new ArrayList();
        sightings.add(sighting);

        Sighting sighting2 = new Sighting();
        sighting2.setDateSeen(LocalDate.parse("2018-06-30"));
        sighting2.setLocation(location);
        sighting2 = sightingDao.createSighting(sighting2);
        sightings.add(sighting2);

        Sighting sighting3 = new Sighting();
        sighting3.setDateSeen(LocalDate.parse("2018-06-07"));
        sighting3.setLocation(location);
        sighting3 = sightingDao.createSighting(sighting2);
        List<Sighting> sightingBatman = new ArrayList();
        sightingBatman.add(sighting3);

        SuperPerson person = new SuperPerson();
        person.setName("Superman");
        person.setDescription("Curly bang with glasses");
        person.setSuperpower("X-ray vision");
        person.setOrganizations(orgs);
        person.setSightings(sightings);
        person = superPersonDao.createSuperPerson(person);

        SuperPerson person2 = new SuperPerson();
        person2.setName("Batman");
        person2.setDescription("Black suit");
        person2.setSuperpower("Super wealthy");
        person2.setOrganizations(orgs);
        person2.setSightings(sightingBatman);
        person2 = superPersonDao.createSuperPerson(person2);

        assertEquals(1, locationDao.getAllLocationsBySuperPerson(person2).size());
        assertEquals(2, locationDao.getAllLocationsBySuperPerson(person).size());

    }

}
