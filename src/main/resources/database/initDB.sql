CREATE TABLE IF NOT EXISTS users
(
    id BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(200) NOT NULL ,
    last_name VARCHAR(254) NOT NULL ,
    birth_date DATE NOT NULL
);
