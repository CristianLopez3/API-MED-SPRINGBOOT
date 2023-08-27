CREATE TABLE medicos(
    id  BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    email varchar(100) NOT NULL UNIQUE,
    document varchar(20) NOT NULL UNIQUE,
    specialty VARCHAR(100) NOT NULL,
    street VARCHAR(100) NOT NULL,
    district VARCHAR(100) NOT NULL,
    complement VARCHAR(100),
    number VARCHAR(10),
    city VARCHAR(100) NOT NULL,
    PRIMARY KEY(id)
);