INSERT INTO SURFACE_TYPE (name, price_per_minute)
VALUES
    ('Clay', 12),
    ('Grass', 15);

INSERT INTO COURT (name, surface_type_id, deleted)
VALUES
    ('Court 1', 1, false),
    ('Court 2', 2, false),
    ('Court 3', 1, false),
    ('Court 4', 2, false);
