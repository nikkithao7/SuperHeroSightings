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
public class SuperPersonDaoDBTest {

    @Inject
    SuperPersonDao superPersonDao;

    @Inject
    OrganizationDao organizationDao;

    @Inject
    SightingDao sightingDao;

    @Inject
    LocationDao locationDao;

    public SuperPersonDaoDBTest() {
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
     * Test of getAllSuperPersons method, of class SuperPersonDaoDB.
     */
    @Test
    public void testGetAllSuperPersons() {
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
        List<Organization> orgs = organizationDao.getAllOrganizations();

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
        person2.setSightings(sightings);
        person2 = superPersonDao.createSuperPerson(person2);

        assertEquals(2, superPersonDao.getAllSuperPersons().size());

    }

    /**
     * Test of createSuperPerson & getSuperPersonById method, of class
     * SuperPersonDaoDB.
     */
    @Test
    public void testCreateAndGetSuperPerson() {
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

        SuperPerson person = new SuperPerson();
        person.setName("Superman");
        person.setDescription("Curly bang with glasses");
        person.setSuperpower("X-ray vision");
        person.setOrganizations(orgs);
        person.setSightings(sightings);
        person = superPersonDao.createSuperPerson(person);

        SuperPerson fromDao = superPersonDao.getSuperPersonById(person.getSuperPersonId());

        assertEquals(person, fromDao);
    }

    /**
     * Test of deleteSuperPersonById method, of class SuperPersonDaoDB.
     */
    @Test
    public void testDeleteSuperPersonById() {
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

        SuperPerson person = new SuperPerson();
        person.setName("Superman");
        person.setDescription("Curly bang with glasses");
        person.setSuperpower("X-ray vision");
        person.setOrganizations(orgs);
        person.setSightings(sightings);
        person = superPersonDao.createSuperPerson(person);

        superPersonDao.deleteSuperPersonById(person.getSuperPersonId());

        assertEquals(0, organizationDao.getAllOrganizationsBySuperPerson(person).size());
        assertEquals(0, sightingDao.getAllSightingsBySuperPerson(person).size());
        assertEquals(0, superPersonDao.getAllSuperPersons().size());
    }

    /**
     * Test of updateSuperPerson method, of class SuperPersonDaoDB.
     */
    @Test
    public void testUpdateSuperPerson() {
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

        SuperPerson person = new SuperPerson();
        person.setName("Superman");
        person.setDescription("Curly bang with glasses");
        person.setSuperpower("X-ray vision");
        person.setOrganizations(orgs);
        person.setSightings(sightings);
        person = superPersonDao.createSuperPerson(person);

        //updates to superperson happen here
        person.setSuperpower("Can fly super fast");
        Sighting sighting2 = new Sighting();
        sighting2.setDateSeen(LocalDate.parse("2018-06-30"));
        sighting2.setLocation(location);
        sighting2 = sightingDao.createSighting(sighting2);
        sightings.add(sighting2);
        person.setSightings(sightings);
        superPersonDao.updateSuperPerson(person);

        assertEquals("Can fly super fast", person.getSuperpower());
        assertEquals(2, person.getSightings().size());
    }

    /**
     * Test of getAllSuperPersonsByOrganization method, of class
     * SuperPersonDaoDB.
     */
    @Test
    public void testGetAllSuperPersonsByOrganization() {
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
        person2.setOrganizations(orgs);
        person2.setSightings(sightingsBatman);
        person2 = superPersonDao.createSuperPerson(person2);

        assertEquals("Superman", superPersonDao.getAllSuperPersonsByOrganization(org).get(0).getName());
        assertEquals("Batman", superPersonDao.getAllSuperPersonsByOrganization(org).get(1).getName());
        assertEquals(2, superPersonDao.getAllSuperPersonsByOrganization(org).size());
    }

    /**
     * Test of getAllSuperPersonsByLocation method, of class SuperPersonDaoDB.
     */
    @Test
    public void testGetAllSuperPersonsByLocation() {
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
        person2.setOrganizations(orgs);
        person2.setSightings(sightingsBatman);
        person2 = superPersonDao.createSuperPerson(person2);
        
        assertEquals(2, superPersonDao.getAllSuperPersonsByLocation(location).size());
    }

}
