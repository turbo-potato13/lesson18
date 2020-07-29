BEGIN;

DROP TABLE IF EXISTS customer CASCADE;
CREATE TABLE customer (id bigserial PRIMARY KEY, name VARCHAR(255));
INSERT INTO customer (name) VALUES
('Arnold S.'),
('Silvester S.'),
('Willis B.');

DROP TABLE IF EXISTS product CASCADE;
CREATE TABLE product (id bigserial PRIMARY KEY, title VARCHAR(255), price int, customer_id bigint, FOREIGN KEY (customer_id) REFERENCES customer (id));
INSERT INTO product (title, price, customer_id) VALUES
('box', 10, 1),
('sphere', 20, 1),
('maul', 100, 2),
('door', 50, 1),
('camera', 500, 3);

COMMIT;