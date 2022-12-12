drop database if exists travel_genie_test;
create database travel_genie_test;
use travel_genie_test;

-- create tables and relationships
create table app_user (
    app_user_id int primary key auto_increment,
    username varchar(25) not null,
    nickname varchar(250) not null,
    password_hash varchar(1024)
);

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

delimiter //
create procedure set_known_good_state()
begin

	delete from city_to_entertainment;
	delete from wish;
    alter table wish auto_increment = 1;
    delete from city;
	delete from entertainment;
    delete from country;
    delete from scenery;
    delete from app_user;
    alter table app_user auto_increment = 1;
    
	-- data
	insert into app_user (app_user_id, username, nickname, password_hash) values
		(1, 'user1', 'Josh', '$2a$10$O07BeyVEy.eGy9zmJQR/WeIDdb5Q6PMDbTZlUXOMQ0B.EAkbiuUK6'),
		(2, 'user2', 'Audrey', '$2a$10$O07BeyVEy.eGy9zmJQR/WeIDdb5Q6PMDbTZlUXOMQ0B.EAkbiuUK6'),
        (3, 'user3', 'Star', '$2a$10$O07BeyVEy.eGy9zmJQR/WeIDdb5Q6PMDbTZlUXOMQ0B.EAkbiuUK6');

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
        (5, 'Ski', 'MEDIUM', '$$', true),
        (6, 'Museum', 'LOW', '$', true);

	insert into city (city_id, city_name, country_id, scenery_id) values
		(1, 'Washington DC', 1, 1),
		(2, 'Santa Monica', 1, 2),
		(3, 'Asheville', 1, 3),
		(4, 'Las Vegas', 1, 4),
		(5, 'Portland', 1, 5);

   insert into city_to_entertainment (city_id, entertainment_id) values
		(1, 2),
        (1, 6),
        (2, 1),
        (2, 2),
        (3, 2),
        (3, 3),
        (4, 2),
        (4, 4),
        (5, 2),
        (5, 3),
        (5, 5);

    insert into wish (wish_id, app_user_id, city_name, country_name, scenery_name, entertainment_name, activity_level, price_range, kid_friendly) values
		(1, 1, 'Las Vegas', 'United States', 'DESERT', 'Casino', 'LOW', '$$$', false),
        (2, 1, 'Portland', 'United States', 'SNOW', 'Ski', 'MEDIUM', '$$', true),
		(3, 2, 'Santa Monica', 'United States', 'BEACH', 'Sun Bathing', 'LOW', '$', true),
        (4, 2, 'Asheville', 'United States', 'MOUNTAIN', 'Hiking', 'HIGH', '$', false),
        (5, 3, 'Washington DC', 'United States', 'METROPOLITAN', 'Museum', 'LOW', '$', true),
        (6, 3, 'Washington DC', 'United States', 'METROPOLITAN', 'Sight Seeing', 'MEDIUM', '$', true);

end //
delimiter ;