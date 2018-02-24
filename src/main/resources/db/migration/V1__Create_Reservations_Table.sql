-- This DDL is used by flyway to initially setup the table
-- which will hold the reservation entries

-- Simple table for holding reservations
create table reservation (
 -- id for this table
    id integer not null generated always as identity (start with 1, increment BY 1),
 -- reservation owner
    reservation_owner varchar(300),
 -- restaurant name
    restaurant_name varchar(300),
 -- reservation date
    reservation_date date,
 -- diners number
    diners_number varchar(5),
 -- confirmed reservation
    confirmed boolean,
 -- the primary key
    constraint primary_key primary key (id)
);
