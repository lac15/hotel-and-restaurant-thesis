INSERT INTO "public".users (id, name, email, active, password) VALUES (-1, 'admin', 'admin@restaurant.hu', true, '$2a$10$7z8ZcKhYICuyb5rkKhqo7uUh4je3HQocio0f5onZdugR0QTrxHDLi');

INSERT INTO "public".roles (id, name) VALUES (1, 'ROLE_USER');
INSERT INTO "public".roles (id, name) VALUES (2, 'ROLE_ADMIN');

INSERT INTO "public".users_roles (userentity_id, roles_id) VALUES (-1, 1);
INSERT INTO "public".users_roles (userentity_id, roles_id) VALUES (-1, 2);


INSERT INTO "public".foodtypes (id, name) VALUES (1, 'Soup');
INSERT INTO "public".foodtypes (id, name) VALUES (2, 'Main course');
INSERT INTO "public".foodtypes (id, name) VALUES (3, 'Dessert');

INSERT INTO "public".foods (id, name, price, type_id) VALUES (1, 'Bableves', 500, 1);

INSERT INTO "public".drinks (id, name, price) VALUES (1, 'Fanta', 250);
