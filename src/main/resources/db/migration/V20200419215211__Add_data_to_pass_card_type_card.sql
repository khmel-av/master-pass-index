BEGIN;

INSERT INTO mpi.pass_card_type_card (pass_card_id, type_card_id) VALUES
(1, 1)
,(1, 2)
,(1, 3)
,(2, 1)
,(2, 2)
,(3, 1)
,(3, 2)
;

COMMIT;