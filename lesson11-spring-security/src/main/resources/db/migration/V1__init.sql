------------------------------------------------------------
-- Таблицы пользователей и прав доступа для Spring Security
------------------------------------------------------------
CREATE TABLE users (
    id                    BIGSERIAL,
    username              VARCHAR(30) NOT NULL UNIQUE,
    password              VARCHAR(80) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE roles (
    id                    SERIAL,
    name                  VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE users_roles (
    user_id               BIGINT NOT NULL,
    role_id               INT NOT NULL,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (role_id) REFERENCES roles (id)
);


------------------------------------------------------------
INSERT INTO users (username, password)
VALUES
('user',   '$2y$10$Sz1TQsiV8d8BY8OnBL.4OewCyS1NY96cI6kIn5tuogevPx6lXv4wi'), -- 1
('admin',  '$2y$10$kbpEaBvFhyKnQFj4Ro3zo.LvaD6BwOR6TgGOXk32Kva9PE2iGHqhm'), -- 2
('sadmin', '$2y$10$N7sfDI6PDNbV.6CCmmp9LO9IIubmFLVLf8iHzK3EGL80KvYBHTnMa'); -- 3


INSERT INTO roles (name)
VALUES
('ROLE_USER'),
('ROLE_ADMIN'),
('ROLE_SUPER_ADMIN');


INSERT INTO users_roles (user_id, role_id)
VALUES
(1, 1),
(2, 2),
(3, 3);




------------------------------------------------------------
-- Таблица для описания продуктов
------------------------------------------------------------
CREATE TABLE products (
    id      BIGSERIAL,
    title   VARCHAR(255),
    price   NUMERIC(38,2)
);

INSERT INTO products (title, price)
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