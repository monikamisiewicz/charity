# category
INSERT INTO category(id, name) VALUES (1, 'Ubrania, które nadają się do ponownego użycia');
INSERT INTO category(id, name) VALUES (2, 'Ubrania do wyrzucenia');
INSERT INTO category(id, name) VALUES (3, 'Sprzęt');
INSERT INTO category(id, name) VALUES (4, 'Książki');
INSERT INTO category(id, name) VALUES (5, 'Zabawki');
INSERT INTO category(id, name) VALUES (6, 'Inne');

# donation
INSERT INTO donation(id, pick_up_comment, city, pick_up_date, pick_up_time, quantity, street, zip_code, institution_id) VALUES (1,'','Wrocław', '2020/03/15', '12:00:00', 3, 'Kornela Makuszyńskiego 2', '72-530', 1);
INSERT INTO donation(id, pick_up_comment, city, pick_up_date, pick_up_time, quantity, street, zip_code, institution_id) VALUES (2,'','Wrocław', '2020/03/17', '08:30:00', 1, 'Wiśniowa 5/12', '71-470', 2);
INSERT INTO donation(id, pick_up_comment, city, pick_up_date, pick_up_time, quantity, street, zip_code, institution_id) VALUES (3,'','Wrocław', '2020/03/20', '17:00:00', 2, 'Przejazdowa 55/5', '73-230', 3);
INSERT INTO donation(id, pick_up_comment, city, pick_up_date, pick_up_time, quantity, street, zip_code, institution_id) VALUES (4,'','Wrocław', '2020/03/23', '15:30:00', 4, 'Zielona 17', '70-830', 4);

# institution
INSERT INTO institution (id, name, description) VALUES (1, 'Fundacja "Dbam o Zdrowie"', 'Cel i misja: Pomoc dzieciom z ubogich rodzin.');
INSERT INTO institution (id, name, description) VALUES (2, 'Fundacja "A kogo"', 'Cel i misja: Pomoc wybudzaniu dzieci ze śpiączki.');
INSERT INTO institution (id, name, description) VALUES (3, 'Fundacja “Dla dzieci"', 'Cel i misja: Pomoc osobom znajdującym się w trudnej sytuacji życiowej.');
INSERT INTO institution (id, name, description) VALUES (4, 'Fundacja “Bez domu”', 'Cel i misja: Pomoc dla osób nie posiadających miejsca zamieszkania');

# donation-categories
INSERT INTO donation_categories (donation_id, categories_id) VALUES (1,2);
INSERT INTO donation_categories (donation_id, categories_id) VALUES (1,1);
INSERT INTO donation_categories (donation_id, categories_id) VALUES (2,4);
INSERT INTO donation_categories (donation_id, categories_id) VALUES (3,3);
INSERT INTO donation_categories (donation_id, categories_id) VALUES (3,2);
INSERT INTO donation_categories (donation_id, categories_id) VALUES (4,1);
INSERT INTO donation_categories (donation_id, categories_id) VALUES (4,5);