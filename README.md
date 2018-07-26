# SuperHeroSightings
A Spring MVC web application tracking where and when a superhero has been sighted. This is my first web application that 
I've created on my own to track complex and interrelated persisted data connected to a MySQL database. 
I enjoyed being able to design and develop the entire program from the back end to the front end. 

## Complexities I ran into
During the project, I ended up running into a problem on the front end with my sightings. Because I had set up my 'superperson' model to 
have the list of sightings instead for my 'one-to-many' relationship, I ended up having to work around this on the front end. What I ended
up doing was have the user on the front end, enter in the date, location, and superhero(s) sighted for the 'add a new sighting' form. When
the user submitted the form, the sighting would first be created and added to a list of sightings. Once that was done, I would then have
to loop through each superhero that was selected for that sighting and add the list of sightings I just created to the superhero(s)
selected. This made it difficult to be able to display to include the superhero(s) a user selected for their sighting that they just added
on the main page. The way I worked around this was to allow the user to click on each sighting to go into the details of that sighting, 
which I was able to then include the superhero(s) for that specific sighting as I now had a sighting id to refer to. 

## Lessons Learned/Improvements I would like to make
One of the biggest lessons that I learned with this project was how important it was to keep in mind how a user was going to interact with 
the application on the front end when designing the back end. While it wasn't impossible to figure out a workaround or solution, it 
definitely would require less code had I had the front end in mind more when I was designing and developing the back end of the application.
Some improvements I would like to make to this application would definitely be some additional design and color as well as an added
feature to show on a map where each superhero was sighted.

## Dependencies
- NetBeans (or Java IDE of your choice)
- MySQL Workbench
- Spring MVC

## Getting Started
1. Pull this `SuperHeroSightings` repository to your local directory.
2. Open up MySQL Workbench and run both `createSuperHeroSightingDatabaseSQL` and `createSuperHeroSightingDatabaseSQL_test` queries.
3. Import the project `src/SuperHeroSightings` into NetBeans or a Java IDE of your choice.
4. You will need to update the username and password for the **bean id**, `"datasource"` to match what you've set your username and password to in MySQL workbench for both the production and test database. 
    - Go to the `src/main/resources` folder and open up the `spring-persistence.xml` file to update the username & password.
    - Go to the `src/test/resources` folder and open up the `test-applicationContext.xml` file to update the username & password. 
5. Run the application in NetBeans or a Java IDE of your choice.     
