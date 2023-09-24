-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2023-09-23 16:26:26.748

-- tables
-- Table: coordinate
CREATE TABLE coordinate (
    id serial  NOT NULL,
    latitude decimal(9,6)  NOT NULL,
    longitude decimal(9,6)  NOT NULL,
    CONSTRAINT coordinate_pk PRIMARY KEY (id)
);

-- Table: location
CREATE TABLE location (
    id serial  NOT NULL,
    coordinate_id int  NOT NULL,
    title varchar(255)  NOT NULL,
    description varchar(255)  NOT NULL,
    CONSTRAINT location_pk PRIMARY KEY (id)
);

-- foreign keys
-- Reference: location_coordinate (table: location)
ALTER TABLE location ADD CONSTRAINT location_coordinate
    FOREIGN KEY (coordinate_id)
    REFERENCES coordinate (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- End of file.

