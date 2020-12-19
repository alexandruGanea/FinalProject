drop database if exists travel_agency;

create database travel_agency;

use travel_agency;

create table continents(
id int not null primary key auto_increment,
name varchar(20) not null unique
);

create table countries(
id int not null primary key auto_increment,
name varchar(30) not null unique,
continent_id int not null,
constraint fk_continent_id
foreign key (continent_id)
references continents(id)
);

create table cities(
id int not null primary key auto_increment,
name varchar(20) not null,
country_id int not null,
constraint fk_country_id
foreign key (country_id)
references countries(id)
);

create table airports(
id int not null primary key auto_increment,
name varchar(30) not null,
city_airport_id int not null,
constraint fk_city_airport_id
foreign key (city_airport_id)
references cities(id)
);

create table flights(
id int not null primary key auto_increment,
flight_name varchar(10) not null,
flight_departure int not null,
flight_destination int not null,
departure_date datetime not null,
arrival_date datetime not null,
available_seats int not null,
seat_price double not null,
constraint fk_airport_inbound
foreign key (flight_destination)
references airports(id),
constraint fk_airport_outbound
foreign key (flight_departure)
references airports(id)
);


create table hotels(
id int not null primary key auto_increment,
hotel_name varchar(30) not null,
hotel_rating int not null,
description varchar(100) not null,
city_hotel_id int not null,
constraint fk_city_id
foreign key (city_hotel_id)
references cities(id)
);


create table rooms(
id int not null primary key auto_increment,
hotel_id int not null,
constraint fk_rooms_hotels
foreign key (hotel_id)
references hotels(id),
room_type varchar(20) not null,
max_guests int not null,
guest_price double not null,
available_rooms int not null
);

create table meals(
id int not null primary key auto_increment,
hotel_id int not null,
constraint fk_meals_hotels
foreign key (hotel_id)
references hotels(id),
BB_price double,
HB_price double,
FB_price double,
AI_price double
);


create table travel_packages(
id int not null primary key auto_increment,
inbound_flight_id int not null,
constraint fk_inbound_flight
foreign key (inbound_flight_id)
references flights(id),
outbound_flight_id int not null,
constraint fk_outbound_flight
foreign key (outbound_flight_id)
references flights(id),
hotel_id int not null,
constraint fk_hotels_packages
foreign key (hotel_id)
references hotels(id),
package_type varchar(20) not null,
adult_price double not null,
child_price double not null,
is_promoted tinyint,
available_packages int not null
);

create table accounts(
id int not null primary key unique auto_increment,
account_name varchar(30) not null unique,
account_password varchar(30) not null,
is_admin_login tinyint,
is_user_login tinyint
);

create table clients(
id int not null primary key unique auto_increment,
first_name varchar(20) not null,
last_name varchar(20) not null,
year_of_birth int not null,
address varchar(100) not null,
phone varchar(20) not null,
email varchar(30) not null unique,
account_id int not null unique,
constraint fk_accounts
foreign key (account_id)
references accounts(id)
);

create table purchases(
id int not null primary key auto_increment,
total_price double not null,
package_id int not null,
constraint fk_packages_purchases
foreign key (package_id)
references travel_packages(id)
);


create table clients_purchases(
id int not null primary key auto_increment,
client_id int,
constraint fk_clients_purchases
foreign key(client_id)
references clients(id),
purchase_id int,
constraint fk_purchases_clients
foreign key(purchase_id)
references purchases(id)
);
