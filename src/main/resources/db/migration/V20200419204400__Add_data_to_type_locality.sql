BEGIN;

INSERT INTO mpi.type_locality (id, name) VALUES
(0, 'Город')
,(1, 'Поселок городского типа')
,(2, 'Поселок')
,(3, 'Село')
;

COMMIT;