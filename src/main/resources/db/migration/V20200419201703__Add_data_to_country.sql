BEGIN;

INSERT INTO mpi.country (name, create_date, active) VALUES
('Российская Федерация', now(), true)
,('Казахстан', now(), true)
,('Беларусь', now(), true)
;

COMMIT;