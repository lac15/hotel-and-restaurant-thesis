INSERT INTO "public".users (id, username, firstname, lastname, email, active, hotelcustomer, password) VALUES (-1, 'admin', 'Admin', 'Miszter', 'admin@fourseasons.hu', true, true, '$2a$10$7z8ZcKhYICuyb5rkKhqo7uUh4je3HQocio0f5onZdugR0QTrxHDLi');
INSERT INTO "public".users (id, username, firstname, lastname, email, active, hotelcustomer, password, address, phone) VALUES (-2, 'owner', 'Owner', 'Miszter', 'owner@fourseasons.hu', true, true, '$2a$08$MMXBQeDpYUkQwtsWCvi5pO5ot8jNm.YgfvT9IotlGsu9RRCSD7Lcm', '4028 Debrecen, Kassai ut 26.', '06 90 666 9696');
INSERT INTO "public".users (id, username, firstname, lastname, email, active, hotelcustomer, password) VALUES (-3, 'user', 'User', 'Miszter', 'user@fourseasons.hu', true, true, '$2a$06$fVSryWjOXodMbHdVpjUGi.KIhAnXjx.QYxa.ANQEWHiWNDXn5h9mG');

INSERT INTO "public".roles (id, name) VALUES (1, 'ROLE_USER');
INSERT INTO "public".roles (id, name) VALUES (2, 'ROLE_ADMIN');
INSERT INTO "public".roles (id, name) VALUES (3, 'ROLE_OWNER');

INSERT INTO "public".users_roles (userentity_id, roles_id) VALUES (-1, 1);
INSERT INTO "public".users_roles (userentity_id, roles_id) VALUES (-1, 2);
INSERT INTO "public".users_roles (userentity_id, roles_id) VALUES (-2, 1);
INSERT INTO "public".users_roles (userentity_id, roles_id) VALUES (-2, 3);
INSERT INTO "public".users_roles (userentity_id, roles_id) VALUES (-3, 1);

INSERT INTO "public".foodtypes (id, name) VALUES (1, 'Soup');
INSERT INTO "public".foodtypes (id, name) VALUES (2, 'Main course');
INSERT INTO "public".foodtypes (id, name) VALUES (3, 'Dessert');

INSERT INTO "public".foods (id, name, price) VALUES (1, 'Bableves', 500);

INSERT INTO "public".foodtypes_foods (foodtypeentity_id, foods_id) VALUES (1, 1);

INSERT INTO "public".drinks (id, name, price) VALUES (1, 'Fanta', 250);

INSERT INTO "public".roomtypes (id, capacity, price, image) VALUES (1, 2, 5000, 'room_2');
INSERT INTO "public".roomtypes (id, capacity, price, image) VALUES (2, 3, 10000, 'room_3');
INSERT INTO "public".roomtypes (id, capacity, price, image) VALUES (3, 4, 15000, 'room_4');

INSERT INTO "public".rooms (id, number) VALUES (1, 1);
INSERT INTO "public".rooms (id, number) VALUES (2, 2);
INSERT INTO "public".rooms (id, number) VALUES (3, 3);
INSERT INTO "public".rooms (id, number) VALUES (4, 4);
INSERT INTO "public".rooms (id, number) VALUES (5, 5);
INSERT INTO "public".rooms (id, number) VALUES (6, 6);
INSERT INTO "public".rooms (id, number) VALUES (7, 7);
INSERT INTO "public".rooms (id, number) VALUES (8, 8);

INSERT INTO "public".roomtypes_rooms (roomtypeentity_id, rooms_id) VALUES (1, 1);
INSERT INTO "public".roomtypes_rooms (roomtypeentity_id, rooms_id) VALUES (1, 2);
INSERT INTO "public".roomtypes_rooms (roomtypeentity_id, rooms_id) VALUES (2, 3);
INSERT INTO "public".roomtypes_rooms (roomtypeentity_id, rooms_id) VALUES (2, 4);
INSERT INTO "public".roomtypes_rooms (roomtypeentity_id, rooms_id) VALUES (2, 5);
INSERT INTO "public".roomtypes_rooms (roomtypeentity_id, rooms_id) VALUES (3, 6);
INSERT INTO "public".roomtypes_rooms (roomtypeentity_id, rooms_id) VALUES (3, 7);
INSERT INTO "public".roomtypes_rooms (roomtypeentity_id, rooms_id) VALUES (3, 8);

INSERT INTO "public".roomreserves (id, starttime, endtime, totalprice, rooms_id) VALUES (1, '2017-08-10', '2017-08-12', 15000, 1);
INSERT INTO "public".roomreserves (id, starttime, endtime, totalprice, rooms_id) VALUES (2, '2017-08-12', '2017-08-12', 5000, 2);

INSERT INTO "public".users_roomreserves (userentity_id, roomreserves_id) VALUES (-3, 1);
INSERT INTO "public".users_roomreserves (userentity_id, roomreserves_id) VALUES (-3, 2);

INSERT INTO "public".reserveddates (id, reserveddate) VALUES (1, '2017-08-10');
INSERT INTO "public".reserveddates (id, reserveddate) VALUES (2, '2017-08-11');
INSERT INTO "public".reserveddates (id, reserveddate) VALUES (3, '2017-08-12');

INSERT INTO "public".rooms_reserveddates (roomentity_id, reserveddates_id) VALUES (1, 1);
INSERT INTO "public".rooms_reserveddates (roomentity_id, reserveddates_id) VALUES (1, 2);
INSERT INTO "public".rooms_reserveddates (roomentity_id, reserveddates_id) VALUES (1, 3);
INSERT INTO "public".rooms_reserveddates (roomentity_id, reserveddates_id) VALUES (2, 3);
