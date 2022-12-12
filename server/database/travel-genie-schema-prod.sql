drop database if exists travel_genie;
create database travel_genie;
use travel_genie;

-- create tables and relationships
create table app_user (
    app_user_id int primary key auto_increment,
    username varchar(50) not null unique,
    nickname varchar(50) not null,
    password_hash varchar(2048) not null,
    enabled bit not null default(1)
);

create table app_role (
    app_role_id int primary key auto_increment,
    `name` varchar(50) not null unique
);

create table app_user_role (
    app_user_id int not null,
    app_role_id int not null,
    constraint pk_app_user_role
        primary key (app_user_id, app_role_id),
    constraint fk_app_user_role_user_id
        foreign key (app_user_id)
        references app_user(app_user_id),
	constraint fk_app_user_role_role_id
        foreign key (app_role_id)
        references app_role(app_role_id)
);

insert into app_role (`name`) values
    ('USER');

-- passwords are set to "P@ssw0rd!"
insert into app_user (username, nickname, password_hash, enabled)
    values
    ('user1', 'Josh', '$2a$10$ntB7CsRKQzuLoKY3rfoAQen5nNyiC/U60wBsWnnYrtQQi8Z3IZzQa', 1),
    ('user2', 'Audrey', '$2a$10$ntB7CsRKQzuLoKY3rfoAQen5nNyiC/U60wBsWnnYrtQQi8Z3IZzQa', 1),
    ('user3', 'Star', '$2a$10$ntB7CsRKQzuLoKY3rfoAQen5nNyiC/U60wBsWnnYrtQQi8Z3IZzQa', 1);

insert into app_user_role
    values
    (1, 1),
    (2, 1),
    (3, 1);

create table scenery (
    scenery_id int primary key auto_increment,
    scenery_name varchar(50) not null
);

create table country (
    country_id int primary key auto_increment,
    country_name varchar(50) not null
);

create table entertainment (
	entertainment_id int primary key auto_increment,
    entertainment_name varchar(50) not null,
    activity_level varchar(10) not null,
    price_range varchar(10) not null,
    kid_friendly bit not null 
);

create table city (
    city_id int primary key auto_increment,
    city_name varchar(50) not null,
    country_id int not null,
    constraint fk_country_id
        foreign key (country_id)
        references country(country_id),
    scenery_id int not null,
    constraint fk_scenery_id
        foreign key (scenery_id)
        references scenery(scenery_id)
);

create table wish (
    wish_id int primary key auto_increment,
    app_user_id int not null,
    constraint fk_wish_app_user_id
        foreign key (app_user_id)
        references app_user(app_user_id),
    city_name varchar(50) not null,
    country_name varchar(50) not null,
    scenery_name varchar(50) not null,
    entertainment_name varchar(50) not null,
    activity_level varchar(10) not null,
    price_range varchar(10) not null,
    kid_friendly bit not null
);

create table city_to_entertainment (
	city_id int not null,
    constraint fk_city_to_entertainment_city_id
        foreign key (city_id)
        references city(city_id),
    entertainment_id int not null,    
	constraint fk_city_to_entertainment_entertainment_id
        foreign key (entertainment_id)
        references entertainment(entertainment_id)
);

	insert into scenery (scenery_id, scenery_name) values
        (1, 'METROPOLITAN'),
        (2, 'BEACH'),
        (3, 'MOUNTAIN'),
        (4, 'DESERT'),
        (5, 'SNOW');

    insert into country (country_id, country_name) values
        (1, 'United States');

	insert into entertainment (entertainment_id, entertainment_name, activity_level, price_range, kid_friendly) values
		(1, 'Sun Bathing', 'LOW', '$', true),
        (2, 'Sight Seeing', 'MEDIUM', '$', true),
        (3, 'Hiking', 'HIGH', '$', false),
        (4, 'Casino', 'LOW', '$$$', false),
        (5, 'Ski', 'HIGH', '$$', true),
        (6, 'Museum', 'LOW', '$', true),
        (7, 'Surfing', 'HIGH', '$$', true),
        (8, 'Food Tour', 'LOW', '$$', true),
        (9, 'Camping', 'MEDIUM', '$', true),
        (10, 'Zoo', 'MEDIUM', '$$', true),
        (11, 'Aquarium', 'MEDIUM', '$$', true),
        (12, 'Night Clubs', 'MEDIUM', '$$$', false),
        (13, 'Golf', 'LOW', '$$', false),
        (14, 'Snow Boarding', 'HIGH', '$$', true),
        (15, 'Shopping', 'LOW', '$$$', false),
        (16, 'Fishing', 'LOW', '$', false);
        

	insert into city (city_id, city_name, country_id, scenery_id) values
		(1, 'Washington DC', 1, 1),
		(2, 'Santa Monica', 1, 2),
		(3, 'Asheville', 1, 3),
		(4, 'Las Vegas', 1, 4),
		(5, 'Portland', 1, 5),
        (6, 'Honolulu', 1, 2),
        (7, 'Austin', 1, 1),
        (8, 'Aspen', 1, 5),
        (9, 'Palm Springs', 1, 4),
        (10, 'Stowe', 1, 3);

   insert into city_to_entertainment (city_id, entertainment_id) values
		(1, 2),(1, 6),(1, 8),(1, 10),(1, 11),(1, 12),(1, 15),
        (2, 1),(2, 2),(2, 7),(2, 8),(2, 10),(2, 11),(2, 12),(2, 15),(2, 16),
        (3, 2),(3, 3),(3, 8),(3, 9),(3, 13),(3,14),(3, 15),(3, 16),
        (4, 2),(4, 4),(4, 8),(4, 12),(4, 13),(4, 15),
        (5, 2),(5, 3),(5, 5),(5, 8),(5, 10),(5, 11),(5, 12),(5, 13),(5, 14),(5, 15),
        (6, 1),(6, 2),(6, 3),(6, 7),(6, 8),(6, 10),(6, 11),(6, 12),(6, 13),(6, 15),(6, 16),
        (7, 2),(7, 6),(7, 8),(7, 10),(7, 11),(7, 12),(7, 13),(7, 15),
        (8, 2),(8, 3),(8, 5),(8, 8),(8, 9),(8, 14),(8, 16),
        (9, 2),(9, 3),(9, 6),(9, 8),(9, 12),(9, 15),
        (10, 2),(10, 3),(10, 5),(10, 6),(10, 8),(10, 9),(10, 13),(10, 14),(10, 16);

    insert into wish (wish_id, app_user_id, city_name, country_name, scenery_name, entertainment_name, activity_level, price_range, kid_friendly) values
		(1, 1, 'Las Vegas', 'United States', 'DESERT', 'Casino', 'LOW', '$$$', false),
        (2, 1, 'Portland', 'United States', 'SNOW', 'Ski', 'HIGH', '$$', true),
		(3, 2, 'Santa Monica', 'United States', 'BEACH', 'Sun Bathing', 'LOW', '$', true),
        (4, 2, 'Asheville', 'United States', 'MOUNTAIN', 'Hiking', 'HIGH', '$', false),
        (5, 3, 'Washington DC', 'United States', 'METROPOLITAN', 'Museum', 'LOW', '$', true),
        (6, 3, 'Washington DC', 'United States', 'METROPOLITAN', 'Sight Seeing', 'MEDIUM', '$', true);