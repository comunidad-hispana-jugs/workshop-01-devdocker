-- This DDL is used by flyway to initially setup the table
-- which will hold the reservation entries
--


CREATE SEQUENCE reservation_id_seq;

-- Simple table for holding reservations
create table public.reservation (
 -- id for this table
 	id integer NOT NULL DEFAULT nextval('reservation_id_seq'),
 -- reservation owner
    reservation_owner varchar(300) null,
 -- restaurant name
    restaurant_name varchar(300) null,
 -- reservation date
    reservation_date date null,
 -- diners number
    diners_number varchar(5) null,
 -- confirmed reservation
    confirmed boolean
);
ALTER SEQUENCE reservation_id_seq OWNED BY Reservation.id;
