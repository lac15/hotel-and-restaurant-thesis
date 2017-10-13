/*users and roles*/
INSERT INTO "public".users (id, username, firstname, lastname, email, active, hotelcustomer, password) VALUES (-1, 'admin', 'Admin', 'Miszter', 'admin@fourseasons.hu', true, true, '$2a$10$7z8ZcKhYICuyb5rkKhqo7uUh4je3HQocio0f5onZdugR0QTrxHDLi');
INSERT INTO "public".users (id, username, firstname, lastname, email, active, hotelcustomer, password, address, phone) VALUES (-2, 'owner', 'Owner', 'Miszter', 'fourseasons.hotelandrestaurant@gmail.com', true, true, '$2a$08$MMXBQeDpYUkQwtsWCvi5pO5ot8jNm.YgfvT9IotlGsu9RRCSD7Lcm', '4028 Debrecen, Kassai ut 26.', '06 90 666 9696');
INSERT INTO "public".users (id, username, firstname, lastname, email, active, hotelcustomer, password) VALUES (-3, 'user', 'User', 'Miszter', 'user@fourseasons.hu', true, true, '$2a$06$fVSryWjOXodMbHdVpjUGi.KIhAnXjx.QYxa.ANQEWHiWNDXn5h9mG');

INSERT INTO "public".roles (id, name) VALUES (1, 'ROLE_USER');
INSERT INTO "public".roles (id, name) VALUES (2, 'ROLE_ADMIN');
INSERT INTO "public".roles (id, name) VALUES (3, 'ROLE_OWNER');

INSERT INTO "public".users_roles (userentity_id, roles_id) VALUES (-1, 1);
INSERT INTO "public".users_roles (userentity_id, roles_id) VALUES (-1, 2);
INSERT INTO "public".users_roles (userentity_id, roles_id) VALUES (-2, 1);
INSERT INTO "public".users_roles (userentity_id, roles_id) VALUES (-2, 3);
INSERT INTO "public".users_roles (userentity_id, roles_id) VALUES (-3, 1);
/*foods and drinks*/
INSERT INTO "public".foodtypes (id, name) VALUES (1, 'Soup');
INSERT INTO "public".foodtypes (id, name) VALUES (2, 'Main course');
INSERT INTO "public".foodtypes (id, name) VALUES (3, 'Dessert');

INSERT INTO "public".foods (id, name, price) VALUES (-4, 'Babgulyás', 800);
INSERT INTO "public".foods (id, name, price) VALUES (-5, 'Csontleves', 450);
INSERT INTO "public".foods (id, name, price) VALUES (-6, 'Málygaluska leves', 600);
INSERT INTO "public".foods (id, name, price) VALUES (-7, 'Újházi tyúkhúsleves', 600);
INSERT INTO "public".foods (id, name, price) VALUES (-8, 'Harcsahalászlé', 1000);

INSERT INTO "public".foodtypes_foods (foodtypeentity_id, foods_id) VALUES (1, -4);
INSERT INTO "public".foodtypes_foods (foodtypeentity_id, foods_id) VALUES (1, -5);
INSERT INTO "public".foodtypes_foods (foodtypeentity_id, foods_id) VALUES (1, -6);
INSERT INTO "public".foodtypes_foods (foodtypeentity_id, foods_id) VALUES (1, -7);
INSERT INTO "public".foodtypes_foods (foodtypeentity_id, foods_id) VALUES (1, -8);

INSERT INTO "public".foods (id, name, price) VALUES (-9, 'Zúzapörkölt tarhonyával', 1000);
INSERT INTO "public".foods (id, name, price) VALUES (-10, 'Sertéspörkölt nokedlivel', 1000);
INSERT INTO "public".foods (id, name, price) VALUES (-11, 'Túróscsusza', 950);
INSERT INTO "public".foods (id, name, price) VALUES (-12, 'Juhtúrós sztrapacska', 1000);
INSERT INTO "public".foods (id, name, price) VALUES (-13, 'Rántott csirkemellfilé, vegyesköret', 1000);

INSERT INTO "public".foodtypes_foods (foodtypeentity_id, foods_id) VALUES (2, -9);
INSERT INTO "public".foodtypes_foods (foodtypeentity_id, foods_id) VALUES (2, -10);
INSERT INTO "public".foodtypes_foods (foodtypeentity_id, foods_id) VALUES (2, -11);
INSERT INTO "public".foodtypes_foods (foodtypeentity_id, foods_id) VALUES (2, -12);
INSERT INTO "public".foodtypes_foods (foodtypeentity_id, foods_id) VALUES (2, -13);

INSERT INTO "public".foods (id, name, price) VALUES (-14, 'Mákos-diós guba vaniliasodóval', 500);
INSERT INTO "public".foods (id, name, price) VALUES (-15, 'Gesztenyepüré', 480);
INSERT INTO "public".foods (id, name, price) VALUES (-16, 'Gundel palacsinta (2 darab)', 540);
INSERT INTO "public".foods (id, name, price) VALUES (-17, 'Somlói galuska', 580);
INSERT INTO "public".foods (id, name, price) VALUES (-18, 'Gofri (4 darab)', 550);

INSERT INTO "public".foodtypes_foods (foodtypeentity_id, foods_id) VALUES (3, -14);
INSERT INTO "public".foodtypes_foods (foodtypeentity_id, foods_id) VALUES (3, -15);
INSERT INTO "public".foodtypes_foods (foodtypeentity_id, foods_id) VALUES (3, -16);
INSERT INTO "public".foodtypes_foods (foodtypeentity_id, foods_id) VALUES (3, -17);
INSERT INTO "public".foodtypes_foods (foodtypeentity_id, foods_id) VALUES (3, -18);

INSERT INTO "public".drinks (id, name, price) VALUES (-19, 'Coca-Cola (5 dl)', 350);
INSERT INTO "public".drinks (id, name, price) VALUES (-20, 'Ásványvíz (5 dl)', 160);
INSERT INTO "public".drinks (id, name, price) VALUES (-21, 'Fanta (5 dl)', 330);
INSERT INTO "public".drinks (id, name, price) VALUES (-22, 'Soproni sör (5 dl)', 550);
INSERT INTO "public".drinks (id, name, price) VALUES (-23, 'Heineken 0% (5 dl)', 600);
/*rooms*/
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

/*INSERT INTO "public".roomreserves (id, starttime, endtime, totalprice, rooms_id) VALUES (1, '2017-08-20', '2017-08-22', 15000, 1);
INSERT INTO "public".roomreserves (id, starttime, endtime, totalprice, rooms_id) VALUES (2, '2017-08-22', '2017-08-22', 5000, 2);

INSERT INTO "public".users_roomreserves (userentity_id, roomreserves_id) VALUES (-3, 1);
INSERT INTO "public".users_roomreserves (userentity_id, roomreserves_id) VALUES (-3, 2);

INSERT INTO "public".reserveddates (id, reserveddate) VALUES (1, '2017-08-20');
INSERT INTO "public".reserveddates (id, reserveddate) VALUES (2, '2017-08-21');
INSERT INTO "public".reserveddates (id, reserveddate) VALUES (3, '2017-08-22');

INSERT INTO "public".rooms_reserveddates (roomentity_id, reserveddates_id) VALUES (1, 1);
INSERT INTO "public".rooms_reserveddates (roomentity_id, reserveddates_id) VALUES (1, 2);
INSERT INTO "public".rooms_reserveddates (roomentity_id, reserveddates_id) VALUES (1, 3);
INSERT INTO "public".rooms_reserveddates (roomentity_id, reserveddates_id) VALUES (2, 3);*/
/*tables*/
INSERT INTO "public".tabletypes (id, seats) VALUES (1, 2);
INSERT INTO "public".tabletypes (id, seats) VALUES (2, 4);
INSERT INTO "public".tabletypes (id, seats) VALUES (3, 6);

INSERT INTO "public".tables (id, number) VALUES (1, 1);
INSERT INTO "public".tables (id, number) VALUES (2, 2);
INSERT INTO "public".tables (id, number) VALUES (3, 3);
INSERT INTO "public".tables (id, number) VALUES (4, 4);
INSERT INTO "public".tables (id, number) VALUES (5, 5);
INSERT INTO "public".tables (id, number) VALUES (6, 6);
INSERT INTO "public".tables (id, number) VALUES (7, 7);
INSERT INTO "public".tables (id, number) VALUES (8, 8);
INSERT INTO "public".tables (id, number) VALUES (9, 9);
INSERT INTO "public".tables (id, number) VALUES (10, 10);
INSERT INTO "public".tables (id, number) VALUES (11, 11);
INSERT INTO "public".tables (id, number) VALUES (12, 12);
INSERT INTO "public".tables (id, number) VALUES (13, 13);
INSERT INTO "public".tables (id, number) VALUES (14, 14);
INSERT INTO "public".tables (id, number) VALUES (15, 15);
INSERT INTO "public".tables (id, number) VALUES (16, 16);
INSERT INTO "public".tables (id, number) VALUES (17, 17);

INSERT INTO "public".tabletypes_tables (tabletypeentity_id, tables_id) VALUES (1, 1);
INSERT INTO "public".tabletypes_tables (tabletypeentity_id, tables_id) VALUES (1, 2);
INSERT INTO "public".tabletypes_tables (tabletypeentity_id, tables_id) VALUES (1, 3);
INSERT INTO "public".tabletypes_tables (tabletypeentity_id, tables_id) VALUES (3, 4);
INSERT INTO "public".tabletypes_tables (tabletypeentity_id, tables_id) VALUES (3, 5);
INSERT INTO "public".tabletypes_tables (tabletypeentity_id, tables_id) VALUES (3, 6);
INSERT INTO "public".tabletypes_tables (tabletypeentity_id, tables_id) VALUES (2, 7);
INSERT INTO "public".tabletypes_tables (tabletypeentity_id, tables_id) VALUES (2, 8);
INSERT INTO "public".tabletypes_tables (tabletypeentity_id, tables_id) VALUES (2, 9);
INSERT INTO "public".tabletypes_tables (tabletypeentity_id, tables_id) VALUES (3, 10);
INSERT INTO "public".tabletypes_tables (tabletypeentity_id, tables_id) VALUES (3, 11);
INSERT INTO "public".tabletypes_tables (tabletypeentity_id, tables_id) VALUES (3, 12);
INSERT INTO "public".tabletypes_tables (tabletypeentity_id, tables_id) VALUES (1, 13);
INSERT INTO "public".tabletypes_tables (tabletypeentity_id, tables_id) VALUES (1, 14);
INSERT INTO "public".tabletypes_tables (tabletypeentity_id, tables_id) VALUES (1, 15);
INSERT INTO "public".tabletypes_tables (tabletypeentity_id, tables_id) VALUES (3, 16);
INSERT INTO "public".tabletypes_tables (tabletypeentity_id, tables_id) VALUES (3, 17);

/*INSERT INTO "public".tablereserves (id, starttime, endtime, tables_id) VALUES (1, '2017-08-20 10:00:00', '2017-08-20 11:00:00', 1);
INSERT INTO "public".tablereserves (id, starttime, endtime, tables_id) VALUES (2, '2017-08-22 14:00:00', '2017-08-22 15:00:00', 2);

INSERT INTO "public".users_tablereserves (userentity_id, tablereserves_id) VALUES (-3, 1);
INSERT INTO "public".users_tablereserves (userentity_id, tablereserves_id) VALUES (-3, 2);

INSERT INTO "public".reservedtimes (id, reservedtime) VALUES (1, '2017-08-20 10:00:00');
INSERT INTO "public".reservedtimes (id, reservedtime) VALUES (2, '2017-08-22 14:00:00');

INSERT INTO "public".tables_reservedtimes (tableentity_id, reservedtimes_id) VALUES (1, 1);
INSERT INTO "public".tables_reservedtimes (tableentity_id, reservedtimes_id) VALUES (2, 2);*/
