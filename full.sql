BEGIN;

DROP TABLE IF EXISTS customer CASCADE;
CREATE TABLE customer (id bigserial PRIMARY KEY, name VARCHAR(255));
INSERT INTO customer (name) VALUES
('Arnold S.'),
('Silvester S.'),
('Willis B.');

DROP TABLE IF EXISTS product CASCADE;
CREATE TABLE product (id bigserial PRIMARY KEY, title VARCHAR(255), price int);
INSERT INTO product (title, price, customer_id) VALUES
('box', 10),
('sphere', 20),
('maul', 100),
('door', 50),
('camera', 500);

DROP TABLE IF EXISTS product CASCADE;
CREATE TABLE buy ( id BIGSERIAL PRIMARY KEY, customer_id  INTEGER REFERENCES customer (id) ON DELETE CASCADE, product_id INTEGER REFERENCES product (id) ON DELETE CASCADE);
INSERT INTO product (customer_id, product_id) VALUES
(1, 1),
(1, 2),
(2, 3),
(1, 4),
(3, 5);

COMMIT;