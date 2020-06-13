BEGIN;

INSERT INTO mpi.scope_card (country_id, region_id, district_id, locality_id, create_date, active) VALUES
(1, 2, null, 1, now(), true)
;

COMMIT;