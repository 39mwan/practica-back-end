CREATE TABLE friends(
    id int NOT NULL,
    name VARCHAR(50) NOT NULL,
    surname VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE expenses(
    id int NOT NULL,
    amount DECIMAL(5,2) NOT NULL,
    description VARCHAR(120) NOT NULL,
    date DATE NOT NULL,
    FOREIGN KEY (id) REFERENCES friends(id)
);
/*
INSERT INTO friends VALUES ("123e4567-e89b-12d3-a456-426614174000","Luis", "Merino");
INSERT INTO friends VALUES ("123e4567-e89b-12d3-a456-426614174001", "Sonia", "Zhang");*/
