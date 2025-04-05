CREATE TABLE personal (
personal_id SERIAL PRIMARY KEY,
name TEXT not null ,
age INTEGER CHECK (age>0),
rights BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE TABLE car (
id SERIAL PRIMARY KEY,
mark VARCHAR(100) NOT NULL,
model VARCHAR (100) NOT NULL,
prise INTEGER NOT NULL
);

CREATE TABLE PersonalCar(
    personal_id INT,
    car_id INT,
    PRIMARY KEY (personal_id, car_id),
    FOREIGN KEY (personal_id) REFERENCES personal(personal_id) on delete cascade,
    FOREIGN KEY (car_id) REFERENCES car(id) on delete cascade
)







