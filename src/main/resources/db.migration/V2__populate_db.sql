INSERT INTO Client (name) VALUES
    ('Anatole'),
    ('Katrin'),
    ('Elina'),
    ('Viktor'),
    ('Julia'),
    ('Lubov'),
    ('Anton'),
    ('Oksana'),
    ('Khrystina'),
    ('Kate');

-- Вставка даних для таблиці Planet
INSERT INTO Planet (id, name) VALUES
       ('MERCURY', 'MERCURY'),
       ('VENUS', 'VENUS'),
       ('EARTH', 'EARTH'),
       ('MARS', 'MARS'),
       ('JUPITER', 'JUPITER'),
       ('SATURN', 'SATURN'),
       ('URANUS', 'URANUS'),
       ('NEPTUNE', 'NEPTUNE');

-- Вставка даних для таблиці Ticket
INSERT INTO Ticket (created_at, client_id, from_planet_id, to_planet_id) VALUES
    (CURRENT_TIMESTAMP, 1, 'MARS', 'EARTH'),
    (CURRENT_TIMESTAMP, 2, 'EARTH', 'MARS'),
    (CURRENT_TIMESTAMP, 3, 'VENUS', 'SATURN'),
    (CURRENT_TIMESTAMP, 4, 'JUPITER', 'EARTH'),
    (CURRENT_TIMESTAMP, 5, 'SATURN', 'MARS'),
    (CURRENT_TIMESTAMP, 6, 'EARTH', 'SATURN'),
    (CURRENT_TIMESTAMP, 7, 'MARS', 'VENUS'),
    (CURRENT_TIMESTAMP, 8, 'VENUS', 'EARTH'),
    (CURRENT_TIMESTAMP, 9, 'SATURN', 'JUPITER'),
    (CURRENT_TIMESTAMP, 10, 'EARTH', 'JUPITER');