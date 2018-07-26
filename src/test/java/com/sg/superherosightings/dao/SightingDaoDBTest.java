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
public class SightingDaoDBTest {

    @Inject
    SightingDao sightingDao;

    @Inject
    LocationDao locationDao;

    @Inject
    SuperPersonDao superPersonDao;

    @Inject
    OrganizationDao organizationDao;

    public SightingDaoDBTest() {
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
     * Test of getAllSightings method, of class SightingDaoDB.
     */
    @Test
    public void testGetAllSightings() {
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
        LocalDate date = LocalDate.of(2018, 07, 01);
        sighting.setDateSeen(date);
        sighting.setLocation(location);

        sighting = sightingDao.createSighting(sighting);

        Sighting sighting2 = new Sighting();
        LocalDate date2 = LocalDate.of(2018, 07, 02);
        sighting2.setDateSeen(date2);
        sighting2.setLocation(location);

        sighting2 = sightingDao.createSighting(sighting2);

        assertEquals(2, sightingDao.getAllSightings().size());
    }

    /**
     * Test of createSighting & getSightingById method, of class SightingDaoDB.
     */
    @Test
    public void testCreateAndGetSighting() {
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
        LocalDate date = LocalDate.of(2018, 07, 01);
        sighting.setDateSeen(date);
        sighting.setLocation(location);

        sighting = sightingDao.createSighting(sighting);
        Sighting fromDao = sightingDao.getSightingById(sighting.getSightingId());

        assertEquals(sighting, fromDao);
    }

    /**
     * Test of deleteSightingById method, of class SightingDaoDB.
     */
    @Test
    public void testDeleteSightingById() {
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
        LocalDate date = LocalDate.of(2018, 07, 01);
        sighting.setDateSeen(date);
        sighting.setLocation(location);
        sighting = sightingDao.createSighting(sighting);
        List<Sighting> sightings = new ArrayList();
        sightings.add(sighting);

        SuperPerson person = new SuperPerson();
        person.setName("Batman");
        person.setDescription("Black suit");
        person.setSuperpower("Super wealthy");
        person.setOrganizations(orgs);
        person.setSightings(sightings);
        person = superPersonDao.createSuperPerson(person);

        sightingDao.deleteSightingById(sighting.getSightingId());

        assertEquals(0, sightingDao.getAllSightings().size());

    }

    /**
     * Test of updateSighting method, of class SightingDaoDB.
     */
    @Test
    public void testUpdateSighting() {
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
        LocalDate date = LocalDate.of(2018, 07, 01);
        sighting.setDateSeen(date);
        sighting.setLocation(location);
        sighting = sightingDao.createSighting(sighting);

        Location location2 = new Location();
        location2.setName("Candyland");
        location2.setDescription("A super colorful place.");
        location2.setStreetInfo("555 Candy Lane");
        location2.setCity("Unicornia");
        location2.setState("MN");
        location2.setZipcode(55777);
        location2.setLatitude(new BigDecimal("90.555888"));
        location2.setLongitude(new BigDecimal("177.777554"));
        location2 = locationDao.createLocation(location2);

        //update sighting starts here
        LocalDate date2 = LocalDate.of(2018, 06, 30);
        sighting.setDateSeen(date2);
        sighting.setLocation(location2);
        sightingDao.updateSighting(sighting);

        Sighting fromDao = sightingDao.getSightingById(sighting.getSightingId());

        assertEquals(sighting, fromDao);
        assertEquals(location2.getLocationId(), sighting.getLocation().getLocationId());
    }

    /**
     * Test of getAllsightingsByDate method, of class SightingDaoDB.
     */
    @Test
    public void testGetAllsightingsByDate() {
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
        LocalDate date = LocalDate.of(2018, 07, 01);
        sighting.setDateSeen(date);
        sighting.setLocation(location);
        sighting = sightingDao.createSighting(sighting);

        Sighting sighting2 = new Sighting();
        LocalDate date2 = LocalDate.of(2018, 07, 02);
        sighting2.setDateSeen(date2);
        sighting2.setLocation(location);
        sighting2 = sightingDao.createSighting(sighting2);

        Sighting sighting3 = new Sighting();
        sighting3.setDateSeen(date2);
        sighting3.setLocation(location);
        sighting3 = sightingDao.createSighting(sighting3);

        assertEquals(2, sightingDao.getAllsightingsByDate(date2).size());
    }

    /**
     * Test of getAllSightingsByLocation method, of class SightingDaoDB.
     */
    @Test
    public void testGetAllSightingsByLocation() {
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
        location2.setName("Candyland");
        location2.setDescription("A super colorful place.");
        location2.setStreetInfo("555 Candy Lane");
        location2.setCity("Unicornia");
        location2.setState("MN");
        location2.setZipcode(55777);
        location2.setLatitude(new BigDecimal("90.555888"));
        location2.setLongitude(new BigDecimal("177.777554"));
        location2 = locationDao.createLocation(location2);

        Sighting sighting = new Sighting();
        LocalDate date = LocalDate.of(2018, 07, 01);
        sighting.setDateSeen(date);
        sighting.setLocation(location2);
        sighting = sightingDao.createSighting(sighting);

        Sighting sighting2 = new Sighting();
        LocalDate date2 = LocalDate.of(2018, 07, 02);
        sighting2.setDateSeen(date2);
        sighting2.setLocation(location2);
        sighting2 = sightingDao.createSighting(sighting2);

        Sighting sighting3 = new Sighting();
        sighting3.setDateSeen(date2);
        sighting3.setLocation(location);
        sighting3 = sightingDao.createSighting(sighting3);

        assertEquals(2, sightingDao.getAllSightingsByLocation(location2).size());
        assertEquals(1, sightingDao.getAllSightingsByLocation(location).size());
        assertEquals("Arkham Asylum", sightingDao.getAllSightingsByLocation(location).get(0).getLocation().getName());
    }

    /**
     * Test of getAllSightingsBySuperPerson method, of class SightingDaoDB.
     */
    @Test
    public void testGetAllSightingsBySuperPerson() {
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
        sighting3 = sightingDao.createSighting(sighting3);
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
        
        assertEquals(2, sightingDao.getAllSightingsBySuperPerson(person).size());
        assertEquals(1, sightingDao.getAllSightingsBySuperPerson(person2).size());
    }

}
