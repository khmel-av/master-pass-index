BEGIN;
CREATE ROLE pass_card_dev LOGIN PASSWORD 'pass_card_dev';
CREATE ROLE pass_card_srv LOGIN PASSWORD 'pass_card_srv';
COMMIT;