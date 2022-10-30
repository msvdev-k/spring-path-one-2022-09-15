CREATE TABLE products (id BIGSERIAL PRIMARY KEY, title VARCHAR(255), cost NUMERIC(38,2));

INSERT INTO products (title, cost)
VALUES
('Картофель', 55),
('Помидоры', 180),
('Морковь', 60),
('Огурцы', 150),
('Свёкла', 90),
('Редис', 240),
('Капуста', 75),
('Лук', 65),
('Чеснок', 128),
('Яблоки', 260),
('Груши', 270),
('Бананы', 148),
('Виноград', 340),
('Мандарины', 320),
('Апельсины', 300),
('Киви', 360),
('Персики', 280),
('Слива', 250),
('Помело', 390),
('Изюм', 210);



CREATE TABLE cart_items (id BIGSERIAL PRIMARY KEY, product_id BIGINT, reserved_cost NUMERIC(38,2), quantity INTEGER);