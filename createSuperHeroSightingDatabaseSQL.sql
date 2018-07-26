drop database if exists SuperHeroSighting;

create database SuperHeroSighting;

use SuperHeroSighting;

create table SuperPerson
(superPersonId int not null auto_increment,
superPersonName varchar(45) not null,
description varchar(100) null,
superpower varchar(45) not null,
primary key (superPersonId)
);

create table Organization
(organizationId int not null auto_increment,
orgName varchar(45) not null,
description varchar(100) null,
streetInfo varchar(45) not null,
city varchar(45) not null,
state varchar(45) not null,
zipcode int(5) not null,
phoneNumber char(12) not null,
primary key (organizationId) 
);

create table Sighting
(sightingId int not null auto_increment,
dateSeen date not null,
locationId int not null,
primary key (sightingId)
);

create table Location
(locationId int not null auto_increment,
locationName varchar(45) not null,
description varchar(100) null,
streetInfo varchar(45) not null,
city varchar(45) not null,
state varchar(45) not null,
zipcode int(5) not null,
latitude decimal(8,6) not null,
longitude decimal(9,6) not null,
primary key (locationId)
);

-- Bridge Tables & Foreign Keys start here
create table SuperPersonOrganization
(superPersonId int not null,
organizationId int not null,
primary key (superPersonId, organizationId)
);
	alter table SuperPersonOrganization add constraint fk_SuperPersonOrganization_SuperPerson foreign key (superPersonId) references SuperPerson(superPersonId);
    alter table SuperPersonOrganization add constraint fk_SuperPersonOrganization_Organization foreign key (organizationId) references Organization(organizationId);
		
create table SuperPersonSighting
(superPersonId int not null,
sightingId int not null,
primary key (superPersonId, sightingId)
);    
	alter table SuperPersonSighting add constraint fk_SuperPersonSighting_SuperPerson foreign key (superPersonId) references SuperPerson(superPersonId);
    alter table SuperPersonSighting add constraint fk_SuperPersonSighting_Sighting foreign key (sightingId) references Sighting(sightingId);
    
    alter table Sighting add constraint fk_Sighting_Location foreign key (locationId) references Location(locationId);