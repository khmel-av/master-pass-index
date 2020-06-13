BEGIN;

INSERT INTO mpi.region (name, country_id, create_date, active) VALUES
('Московская', 1, now(), true)
,('Саратовская', 1, now(), true)
,('Воронежская', 1, now(), true)
,('Пензенская', 1, now(), true)
,('Актюбинская область', 2, now(), true)
,('Западно-Казахстанская область', 2, now(), true)
,('Брестская', 3, now(), true)
,('Гомельская', 3, now(), true)
;

COMMIT;