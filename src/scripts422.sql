CREATE TABLE cars
(
    id serial PRIMARY KEY NOT NULL,
    brand text not null ,
    model text,
    price INTEGER
);

CREATE TABLE people
(
    id serial PRIMARY KEY NOT NULL,
    name text not null ,
    age integer,
    drivers_license boolean not null,
    carsId INTEGER references cars(id)
);
select * from cars;

