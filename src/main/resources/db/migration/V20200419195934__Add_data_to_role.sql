BEGIN;

INSERT INTO mpi.role (name, create_date, active) VALUES
('ROLE_ADMIN', now(), true)
,('ROLE_SUB_ADMIN', now(), true)
,('ROLE_USER', now(), true)
;

COMMIT;