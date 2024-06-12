INSERT INTO SURFACE_TYPE (surface_type_id, name, price_per_minute)
VALUES
    (1, 'Clay', 12),
    (2, 'Grass', 15),
    (3, 'Hard', 10),
    (4, 'Carpet', 8),
    (5, 'Artificial Grass', 13);

INSERT INTO COURT (court_id, name, surface_type_id)
VALUES
    (1, 'Court 1', 1),
    (2, 'Court 2', 2),
    (3, 'Court 3', 3),
    (4, 'Court 4', 4),
    (5, 'Court 5', 5);
