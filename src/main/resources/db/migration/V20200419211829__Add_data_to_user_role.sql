BEGIN;

INSERT INTO mpi.user_role (user_id, role_id) VALUES
(1, 1)
,(2, 3)
,(3, 3)
;

COMMIT;