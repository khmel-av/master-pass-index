BEGIN;

INSERT INTO mpi.district_locality (name, locality_id, create_date, active) VALUES
('Волжский', 1, now(), true)
,('Фрунзенский', 1, now(), true)
,('Октябрьский', 1, now(), true)
,('Ленинский', 1, now(), true)
,('Заводской', 1, now(), true)
;

COMMIT;