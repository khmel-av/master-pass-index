BEGIN;

INSERT INTO mpi.street (name, locality_id, district_locality_id, create_date, active) VALUES
('Азина', 1, 5, now(), true)
,('Некрасова', 1, 1, now(), true)
,('Комсомольская', 1, 1, now(), true)
,('Кузнечная', 1, 1, now(), true)
,('Политехническая', 1, 3, now(), true)
,('Техническая', 1, 4, now(), true)
;

COMMIT;