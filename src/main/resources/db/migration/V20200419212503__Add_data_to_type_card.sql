BEGIN;

INSERT INTO mpi.type_card (name, create_date, active) VALUES
('Пешее перемещение', now(), true)
,('Общественный транспорт', now(), true)
,('Личный транспорт', now(), true)
,('Метро', now(), true)
,('Железнодорожное сообщение', now(), true)
,('Авиа сообщение', now(), true)
;

COMMIT;