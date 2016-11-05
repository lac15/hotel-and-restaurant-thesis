INSERT INTO "public".users (id, name, email, active, password) VALUES (-1, 'admin', 'admin@fourseasons.hu', true, '$2a$10$7z8ZcKhYICuyb5rkKhqo7uUh4je3HQocio0f5onZdugR0QTrxHDLi');
INSERT INTO "public".users (id, name, email, active, password) VALUES (-2, 'owner', 'owner@fourseasons.hu', true, '$2a$08$MMXBQeDpYUkQwtsWCvi5pO5ot8jNm.YgfvT9IotlGsu9RRCSD7Lcm');

INSERT INTO "public".roles (id, name) VALUES (1, 'ROLE_USER');
INSERT INTO "public".roles (id, name) VALUES (2, 'ROLE_ADMIN');
INSERT INTO "public".roles (id, name) VALUES (3, 'ROLE_OWNER');

INSERT INTO "public".users_roles (userentity_id, roles_id) VALUES (-1, 1);
INSERT INTO "public".users_roles (userentity_id, roles_id) VALUES (-1, 2);

INSERT INTO "public".users_roles (userentity_id, roles_id) VALUES (-2, 1);
INSERT INTO "public".users_roles (userentity_id, roles_id) VALUES (-2, 3);


INSERT INTO "public".foodtypes (id, name) VALUES (1, 'Soup');
INSERT INTO "public".foodtypes (id, name) VALUES (2, 'Main course');
INSERT INTO "public".foodtypes (id, name) VALUES (3, 'Dessert');

INSERT INTO "public".foods (id, name, price) VALUES (1, 'Bableves', 500);

INSERT INTO "public".foodtypes_foods (foodtypeentity_id, foods_id) VALUES (1, 1);

INSERT INTO "public".drinks (id, name, price) VALUES (1, 'Fanta', 250);
