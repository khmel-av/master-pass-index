BEGIN;

INSERT INTO mpi.pass_card (number, status_card_id, user_id, start_date, expiration_date, create_date, active) VALUES
('40127ef7-b7a0-449c-b2d0-8c562366787e', 0, 1, '2020-05-15 12:00:00', '2020-05-17 12:00:00', now(), true)
,('b7367c52-d557-4884-bf42-37a0cf3edcfa', 0, 2, '2020-04-22 12:00:00', '2020-04-28 14:00:00', now(), true)
,('1296e3d4-c98a-436e-9944-be0f57ad3b4e', 0, 3, '2020-05-04 12:00:00', '2020-05-07 18:00:00', now(), true)
;

COMMIT;