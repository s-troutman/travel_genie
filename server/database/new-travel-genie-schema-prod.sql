drop database if exists new_travel_genie;
create database new_travel_genie;
use new_travel_genie;

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
    
    create table wish (
    wish_id int primary key auto_increment,
    scenery_name varchar(50) not null,
    activity_level varchar(10) not null,
    price_range varchar(10) not null,
    kid_friendly bit not null
);

create table entertainment (
	entertainment_id int primary key auto_increment,
    entertainment_name varchar(50) not null
);

create table destination (
    destination_id int primary key auto_increment,
    app_user_id int not null,
    wish_id int not null,
    entertainment_id int not null,
    city_name varchar(50) not null,
    constraint fk_app_user_id
        foreign key (app_user_id)
        references app_user(app_user_id),
    constraint fk_wish_id
        foreign key (wish_id)
        references wish(wish_id),
    constraint fk_entertainment_id
        foreign key (entertainment_id)
        references entertainment(entertainment_id)
);

insert into wish (scenery_name, activity_level, price_range, kid_friendly) values
		('METROPOLITAN', 'LOW', '$', true),
		('BEACH', 'MEDIUM', '$$', false),
        ('MOUNTAIN', 'LOW', '$$$', true),
        ('DESSERT', 'HIGH', '$$', true),
        ('SNOW', 'HIGH', '$', false);
        
	insert into entertainment (entertainment_id, entertainment_name) values
		(1, 'Sun Bathing'),
        (2, 'Sight Seeing'),
        (3, 'Hiking'),
        (4, 'Casino'),
        (5, 'Ski');
    
	
        insert into destination (destination_id, app_user_id, city_name, wish_id, entertainment_id) values
		(1, 1, 'Washington DC', 1, 1),
		(2, 2, 'Santa Monica', 1, 2),
		(3, 3, 'Ashville', 1, 3),
		(4, 3, 'Las Vegas', 1, 4),
		(5, 2, 'Portland', 1, 5);
     