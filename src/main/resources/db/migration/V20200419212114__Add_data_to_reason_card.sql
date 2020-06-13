BEGIN;

INSERT INTO mpi.reason_card (name, create_date, active) VALUES
('Оформление документов', now(), true)
,('Садовый участок', now(), true)
,('Родственник требующий ухода', now(), true)
,('Работа в организации, деятельность которой не приостановлена', now(), true)
;

COMMIT;