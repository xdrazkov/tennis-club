-- Insert into SURFACE_TYPE if not exists
INSERT INTO SURFACE_TYPE (SURFACE_TYPE_ID, name, price_per_minute)
SELECT 1, 'Clay', 12
WHERE NOT EXISTS (SELECT 1 FROM SURFACE_TYPE WHERE SURFACE_TYPE_ID = 1);

INSERT INTO SURFACE_TYPE (SURFACE_TYPE_ID, name, price_per_minute)
SELECT 2, 'Grass', 15
WHERE NOT EXISTS (SELECT 1 FROM SURFACE_TYPE WHERE SURFACE_TYPE_ID = 2);

-- Insert into COURT if not exists
INSERT INTO COURT (COURT_ID, name, surface_type_id, deleted)
SELECT 1, 'Court 1', 1, 'false'
WHERE NOT EXISTS (SELECT 1 FROM COURT WHERE COURT_ID = 1);

INSERT INTO COURT (COURT_ID, name, surface_type_id, deleted)
SELECT 2, 'Court 2', 2, 'false'
WHERE NOT EXISTS (SELECT 1 FROM COURT WHERE COURT_ID = 2);

INSERT INTO COURT (COURT_ID, name, surface_type_id, deleted)
SELECT 3, 'Court 3', 1, 'false'
WHERE NOT EXISTS (SELECT 1 FROM COURT WHERE COURT_ID = 3);

INSERT INTO COURT (COURT_ID, name, surface_type_id, deleted)
SELECT 4, 'Court 4', 2, 'false'
WHERE NOT EXISTS (SELECT 1 FROM COURT WHERE COURT_ID = 4);
