CREATE TABLE client (
    id SERIAL PRIMARY KEY,
    name VARCHAR(200) NOT NULL CHECK (LENGTH(name) >= 3),
    CONSTRAINT unique_name UNIQUE (name)
);

CREATE TABLE planet (
    id VARCHAR(10) PRIMARY KEY CHECK (id ~ '^[A-Z0-9]+$'),
    name VARCHAR(500) NOT NULL)
);

CREATE TABLE ticket (
    id SERIAL PRIMARY KEY,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    client_id INTEGER REFERENCES Client(id),
    from_planet_id VARCHAR(10) REFERENCES Planet(id),
    to_planet_id VARCHAR(20) REFERENCES Planet(id)
);