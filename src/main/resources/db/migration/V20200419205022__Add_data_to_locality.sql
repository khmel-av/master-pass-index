BEGIN;

INSERT INTO mpi.locality (name, type_locality_id, region_id, district_id, create_date, active) VALUES
('Саратов', 0, 2, null, now(), true)
,('Воронеж', 0, 3, null, now(), true)
,('Пенза', 0, 4, null, now(), true)
,('Москва', 0, 1, null, now(), true)
,('Ершов', 0, 2, 1, now(), true)
,('Мокроус', 1, 2, 2, now(), true)
,('Татищево', 1, 2, 3, now(), true)
,('Уральск', 0, 6, null, now(), true)
,('Актюбинск', 0, 5, null, now(), true)
,('Брест', 0, 7, null, now(), true)
,('Гомель', 0, 8, null, now(), true)
;

COMMIT;