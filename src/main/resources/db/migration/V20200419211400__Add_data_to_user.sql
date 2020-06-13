BEGIN;

INSERT INTO mpi.user (username, password, first_name, last_name, middle_name, photo_path,
                     birth, email, address_id, create_date, active) VALUES
('admin@mail.ru', 'vfGt@8462', 'Антон', 'Хмель', 'Васильевич', null, '1998-07-07', 'admin@mail.ru', 1, now(), true)
,('ivan@mail.ru', 'vfGt@8462', 'Иван', 'Иванов', 'Иванович', null, '1997-08-08', 'ivan@mail.ru', 2, now(), true)
,('petr@mail.ru', 'vfGt@8462', 'Петр', 'Петров', 'Петрович', null, '1996-09-09', 'petr@mail.ru', 3, now(), true)
;

COMMIT;