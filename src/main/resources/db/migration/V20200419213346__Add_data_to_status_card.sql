BEGIN;

INSERT INTO mpi.status_card (id, name) VALUES
(0, 'Действует')
,(1, 'Истек срок')
,(2, 'Удален')
;

COMMIT;