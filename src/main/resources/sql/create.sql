CREATE TABLE car_make
(
    id   BIGSERIAL PRIMARY KEY,
    name varchar(45) NOT NULL
);

CREATE TABLE car_model
(
    id      BIGSERIAL PRIMARY KEY,
    name    VARCHAR(45) NOT NULL,
    make_id INT         NOT NULL,
    CONSTRAINT fk_car_make_car_model FOREIGN KEY (make_id) REFERENCES car_make (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE car
(
    id              BIGSERIAL PRIMARY KEY,
    model_id        INT         NOT NULL,
    plate_number    VARCHAR(10) NOT NULL,
    price_per_day   INT         NOT NULL,
    experience      FLOAT       NOT NULL,
    transmission    VARCHAR(45),
    air_conditioner BOOLEAN,
    body            VARCHAR(45),
    seats           INT,
    production_year INT,
    class           VARCHAR(45),
    engine_capacity FLOAT,
    engine_type     VARCHAR(45),
    consumption     FLOAT,
    CONSTRAINT fk_car_model_car FOREIGN KEY (model_id) REFERENCES car_model (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE role
(
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR(45) NOT NULL
);

CREATE TABLE client
(
    id              BIGSERIAL PRIMARY KEY,
    username        VARCHAR(45)  NOT NULL,
    password        VARCHAR(255) NOT NULL,
    first_name      VARCHAR(45)  NOT NULL,
    last_name       VARCHAR(45)  NOT NULL,
    document        VARCHAR(45)  NOT NULL,
    document_number VARCHAR(45)  NOT NULL,
    phone           VARCHAR(45)  NOT NULL,
    experience      INT          NOT NULL,
    role_id         INT          NOT NULL,
    CONSTRAINT fk_role_client FOREIGN KEY (role_id) REFERENCES role (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE contract
(
    id         BIGSERIAL PRIMARY KEY,
    client_id  INT  NOT NULL,
    car_id     INT  NOT NULL,
    start_date DATE NOT NULL,
    end_date   DATE NOT NULL,
    CONSTRAINT fk_client_contract FOREIGN KEY (client_id) REFERENCES client (id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_car_contract FOREIGN KEY (car_id) REFERENCES car (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE manager
(
    id       BIGSERIAL PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    role_id  INT          NOT NULL,
    CONSTRAINT fk_role_manager FOREIGN KEY (role_id) REFERENCES role (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE car_photo
(
    id     BIGSERIAL PRIMARY KEY,
    car_id BIGSERIAL,
    path   VARCHAR(255),
    CONSTRAINT fk_car_car_photo FOREIGN KEY (car_id) REFERENCES car (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE address
(
    id        BIGSERIAL PRIMARY KEY,
    city      VARCHAR(45) NOT NULL,
    street    VARCHAR(45) NOT NULL,
    building  VARCHAR(10) NOT NULL,
    apartment INT         NOT NULL,
    client_id INT         NOT NULL,
    CONSTRAINT fk_client_address FOREIGN KEY (client_id) REFERENCES client (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE accident
(
    id          BIGSERIAL PRIMARY KEY,
    damage      INT  NOT NULL,
    date        DATE NOT NULL,
    contract_id INT  NOT NULL,
    CONSTRAINT fk_contract_accident FOREIGN KEY (contract_id) REFERENCES contract (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE news
(
    id    BIGSERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    text  TEXT         NOT NULL,
    date  DATE         NOT NULL
);

INSERT INTO car_make(name)
VALUES ('BMW'),
       ('Lada'),
       ('Audi'),
       ('Honda'),
       ('Audi'),
       ('Ford'),
       ('Volvo'),
       ('Mazda'),
       ('Kia'),
       ('Mitsubishi'),
       ('Volkswagen');

INSERT INTO car_model(name, make_id)
VALUES ('M5 седан', 1),
       ('4 SERIES', 1),
       ('X1', 1),
       ('X2', 1),
       ('X5', 1),
       ('Vesta седан', 2),
       ('Priora', 2);

INSERT INTO car(model_id, plate_number, price_per_day, transmission, air_conditioner, body, seats, production_year,
                class, engine_capacity, engine_type, consumption)
VALUES (1, '0816BH-7', 100, 'автомат', true, 'Подогрев сидений', 4, 2019, 'Спортивный седан', 2.4, 'Бензин', 12);

INSERT INTO role(name)
VALUES ('ROLE_MANAGER'),
       ('ROLE_CLIENT');

INSERT INTO client(username, password, first_name, last_name, document, document_number, phone, experience, role_id)
VALUES ('client', '$2a$10$TmkPCXasKMmpD4Qb/px5A.SxpshdOmxvgimD5dLsswogjpYJz0xn2', 'client', 'client', 'prava',
        '15.09.2020', '+375334589615', 2, 2);

INSERT INTO manager(username, password, role_id)
VALUES ('manager', '$2a$10$uRPtRqlDgfIp9ssMUrgo0es2Bz2EB98qvkYxoRLfow.zgEM28x24O', 1);
