BEGIN;

INSERT INTO mpi.district (name, region_id, create_date, active) VALUES
('Ершовский', 2, now(), true)
,('Федоровский', 2, now(), true)
,('Татищевский', 2, now(), true)
;

COMMIT;