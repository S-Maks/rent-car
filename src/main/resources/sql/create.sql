CREATE TABLE car_make
(
    id   SERIAL PRIMARY KEY,
    name varchar(45) NOT NULL
);

CREATE TABLE car_model
(
    id      SERIAL PRIMARY KEY,
    name    VARCHAR(45) NOT NULL,
    make_id INT         NOT NULL,
    FOREIGN KEY (make_id) REFERENCES car_make (id)
);

CREATE TABLE car
(
    id              SERIAL PRIMARY KEY,
    model_id        INT         NOT NULL,
    plate_number    VARCHAR(10) NOT NULL,
    price_per_day   INT         NOT NULL,
    transmission    VARCHAR(45),
    air_conditioner BOOLEAN,
    body            VARCHAR(45),
    seats           INT,
    production_year INT,
    class           VARCHAR(45),
    engine_capacity FLOAT,
    engine_type     VARCHAR(45),
    consumption     FLOAT,
    FOREIGN KEY (model_id) REFERENCES car_model (id)
);

CREATE TABLE role
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(45) NOT NULL
);

CREATE TABLE client
(
    id              SERIAL PRIMARY KEY,
    username        VARCHAR(45) NOT NULL,
    password        VARCHAR(45) NOT NULL,
    first_name      VARCHAR(45) NOT NULL,
    last_name       VARCHAR(45) NOT NULL,
    document        VARCHAR(45) NOT NULL,
    document_number VARCHAR(45) NOT NULL,
    phone           VARCHAR(45) NOT NULL,
    experience      INT         NOT NULL,
    role_id         INT         NOT NULL,
    FOREIGN KEY (role_id) REFERENCES role (id)
);

CREATE TABLE contract
(
    id         SERIAL PRIMARY KEY,
    client_id  INT  NOT NULL,
    car_id     INT  NOT NULL,
    start_date DATE NOT NULL,
    end_date   DATE NOT NULL,
    FOREIGN KEY (client_id) REFERENCES client (id),
    FOREIGN KEY (car_id) REFERENCES car (id)
);

CREATE TABLE manager
(
    id       SERIAL PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(60)  NOT NULL,
    role_id  INT          NOT NULL,
    FOREIGN KEY (role_id) REFERENCES role (id)
);

CREATE TABLE car_photo
(
    car_id SERIAL PRIMARY KEY,
    path   VARCHAR(255) PRIMARY KEY,
    FOREIGN KEY (car_id) REFERENCES car (id)
);

CREATE TABLE address
(
    city      VARCHAR(45) NOT NULL,
    street    VARCHAR(45) NOT NULL,
    building  VARCHAR(10) NOT NULL,
    apartment INT         NOT NULL,
    client_id INT         NOT NULL PRIMARY KEY,
    FOREIGN KEY (client_id) REFERENCES client (id)
);

CREATE TABLE accident
(
    id          SERIAL PRIMARY KEY ,
    damage      INT  NOT NULL,
    date        DATE NOT NULL,
    contract_id INT  NOT NULL,
    FOREIGN KEY (contract_id) REFERENCES contract(id)
);

CREATE TABLE news
(
    id    SERIAL PRIMARY KEY ,
    title VARCHAR(255) NOT NULL,
    text  TEXT         NOT NULL,
    date  DATE         NOT NULL
);





