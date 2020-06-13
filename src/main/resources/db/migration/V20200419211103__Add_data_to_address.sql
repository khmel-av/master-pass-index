BEGIN;

INSERT INTO mpi.address (country_id, region_id, district_id, locality_id, district_locality_id,
                         street_id, home, bild, flat, room, zipcode, create_date, active) VALUES
(1, 2, null, 1, 1, 2, '317', null, null, null, '410003', now(), true)
,(1, 2, null, 1, 5, 1, '52', null, 37, null, '410052', now(), true)
,(1, 2, null, 1, 4, 6, '14а', 4, 95, null, '410014', now(), true)
;

COMMIT;