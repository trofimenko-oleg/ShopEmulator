--1
INSERT INTO DRINKS
(NAME, PURCHASEPRICE, QUANTITY, VOLUME, DRINK_TYPE)
VALUES
('Вода минеральная Хорошо', 9.99, 570, 0.3, 'n');

INSERT INTO NONALCOHOLICDRINKS
(TYPE, COMPOSITION, ID)
VALUES
('MINERAL_WATER', 'вода минеральная, лечебно-столовая', currval('drinks_id_seq'));
-- 2
INSERT INTO DRINKS
(NAME, PURCHASEPRICE, QUANTITY, VOLUME, DRINK_TYPE)
VALUES
('Пиво Одесское Новое', 13.25, 120, 0.5, 'a');

INSERT INTO ALCOHOLICDRINKS
(TYPE, ABV, ID)
VALUES
('BEER', 4.3, currval('drinks_id_seq'));
--3
INSERT INTO DRINKS
(NAME, PURCHASEPRICE, QUANTITY, VOLUME, DRINK_TYPE)
VALUES
('Красная испанка', 80.00, 92, 0.75, 'a');

INSERT INTO ALCOHOLICDRINKS
(TYPE, ABV, ID)
VALUES
('WINE', 14, currval('drinks_id_seq'));
--4
INSERT INTO DRINKS
(NAME, PURCHASEPRICE, QUANTITY, VOLUME, DRINK_TYPE)
VALUES
('Сок Богач Грейпфрутовый очень вкусный молодящий, здоровья придающий. Оптом дешевле. Еще много бесполезного текста, случайно вставленного в поле названия. вода, сок грейпфрутовый концентрированный, фруктоза, лимонная кислота. И еще раз вода, сок грейп', 22.00, 156, 0.95, 'n');

INSERT INTO NONALCOHOLICDRINKS
(TYPE, COMPOSITION, ID)
VALUES
('JUICE', 'вода, сок грейпфрутовый концентрированный, фруктоза, лимонная кислота', currval('drinks_id_seq'));
--5
INSERT INTO DRINKS
(NAME, PURCHASEPRICE, QUANTITY, VOLUME, DRINK_TYPE)
VALUES
('Енерджи бум Плюс', 24.15, 78, 0.33, 'n');

INSERT INTO NONALCOHOLICDRINKS
(TYPE, COMPOSITION, ID)
VALUES
('OTHER', 'вода, лимонная кислота, ароматизатор Яблоко, Е-345, Е-120, Е-630, Е-632, краситель Вишня', currval('drinks_id_seq'));
--6
INSERT INTO DRINKS
(NAME, PURCHASEPRICE, QUANTITY, VOLUME, DRINK_TYPE)
VALUES
('Мартини Биссе', 205.00, 12, 1.0, 'a');

INSERT INTO ALCOHOLICDRINKS
(TYPE, ABV, ID)
VALUES
('LIQUOR', 13, currval('drinks_id_seq'));
--7
INSERT INTO DRINKS
(NAME, PURCHASEPRICE, QUANTITY, VOLUME, DRINK_TYPE)
VALUES
('Два моря', 195.00, 0, 0.75, 'a');

INSERT INTO ALCOHOLICDRINKS
(TYPE, ABV, ID)
VALUES
('WINE', 12, currval('drinks_id_seq'));

INSERT INTO Orders
(date, dayofweek, time, check_value, shipping_info)
VALUES
(current_date, extract(dow from now()), localtime, 324.56, 'some useful information');


INSERT INTO Ordersdetails
(order_id, drink_id, drink_quantity, drink_price)
VALUES
(1, 1, 21, 5.67);
