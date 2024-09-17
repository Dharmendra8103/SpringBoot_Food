
CREATE DATABASE CIBO; 
USE CIBO;

CREATE TABLE USERS (

USER_ID INT AUTO_INCREMENT,

USER_NAME VARCHAR (20) NOT NULL, 
EMAIL_ID VARCHAR(30) NOT NULL,
CONTACT_NUMBER VARCHAR(10) NOT NULL, 
PASSWORD VARCHAR(78) NOT NULL, 
constraint CIBO_USERS_PK primary key (USER_ID)
);

CREATE TABLE ROLES(

ROLE_ID INT AUTO_INCREMENT,
ROLE_TYPE VARCHAR(10) not null,
USER_ID INT, 
constraint CIBO_ROLES_PK primary key (ROLE_ID)
);

CREATE TABLE Restaurant (

restaurant_id int AUTO_INCREMENT,

USER_ID int,

restaurant_name varchar(20) NOT NULL, 
restaurant_contact varchar(30) NOT NULL,

restaurant_type varchar(10) NOT NULL, 
address_line1 varchar(20) NOT NULL,

area varchar(15) NOT NULL,

city varchar(15) NOT NULL,

res_state varchar(17) NOT NULL,
pincode int NOT NULL,

avg_rating double,

approval_status varchar(10) not null,
photo_urls varchar(200) not null, 
constraint CIBO_RESTAURANT_Pk primary key (RESTAURANT_ID)
);

CREATE TABLE Restaurant_Transaction(

restaurant_transaction_id int AUTO_INCREMENT,
restaurant_id int, 
restaurant_order_counter int NOT NULL,
restaurant_approx_cost int NOT NULL, 
restaurant_status boolean DEFAULT false, 
constraint CIBO_RESTAURANT_T_PK primary key (restaurant_transaction_id)

);

CREATE TABLE COUPON (

coupon_id int AUTO_INCREMENT,
coupon code varchar(10) NOT NULL,
minimum bill int NOT NULL,
maximum_redemption int NOT NULL,
start_date_datetime default CURRENT_TIMESTAMP,
end_date_datetime default CURRENT_TIMESTAMP, 
constraint CIBO_COUPON_PK primary key (COUPON_ID)

);

CREATE TABLE User_Address (
User_Address_id int AUTO_INCREMENT,
user_id int, 
User_Address_name varchar(10) NOT NULL,

address_line1 varchar(30) NOT NULL,
address_line2 varchar(30) NOT NULL,

area varchar(20) NOT NULL,

pincode int NOT NULL,

constraint CIBO_USER_address_Pk primary key (USER_ADDRESS_ID)
);

create table orders (

order_id int AUTO_INCREMENT,
user_id int,
order bill int not null,
order_status varchar(30) not null, 
constraint CIBO_order_pk primary key (order_id)

);

create table order_items (
order_items_id int AUTO_INCREMENT,
dish_id int,
qty int not null,
 constraint CIBO_order_items_pk primary key (order_items_id)
);

create table dish (

dish_id int AUTO_INCREMENT,

restaurant_id int,

dish_name varchar(30) not null, 
dish_cuisine varchar(20) not null,

dish_type varchar(10) not null, 
dish_description varchar(150) not null,

speciality varchar(30) not null, 
price double not null,

avg rating double not null, 
image_url varchar(50) not null, 
constraint CIBO_dish_pk primary key (dish_id)
);

create table dish_rating ( 
dish_rating id int AUTO_INCREMENT, 
user_id int NOT NULL, 
rating int default 0, 
constraint CIBO_dish_rating_fk primary key (dish_rating_id)
);

create table wallet (
wallet_id int AUTO_INCREMENT, 
user_id int NOT NULL, 
available_amount int(10) not null, 
constraint CIBO_wallet_pk primary key (wallet_id)

);

CREATE TABLE USER_LIKES(
LIKE_ID INT AUTO_INCREMENT, 
VEG_NONVEG VARCHAR(10) NOT NULL,
DISH_ID INT,
USER_ID INT NOT NULL,
restaurant_id int NOT NULL,
CONSTRAINT CIBO_USER_LIKES_PK primary key (LIKE_ID) );

CREATE TABLE TABLE_BOOKING(

BOOKING ID INT AUTO_INCREMENT,
BOOKING_DATE date NOT NULL,
NO_OF_PEOPLE INT NOT NULL, 
TIME_OF_BOOKING datetime default CURRENT_TIMESTAMP,
RESTAURANT_ID int NOT NULL,
USER_ID INT NOT NULL,

constraint CIBO_BOOKINGS_PK primary key (BOOKING_ID)
);


ALTER TABLE ROLES ADD CONSTRAINT CIBO_USERS_ROLE_FK FOREIGN KEY (USER_ID) REFERENCES USERS (USER_ID);
ALTER TABLE ORDERS ADD CONSTRAINT CIBO_ORDER_USER_FK FOREIGN KEY (USER_ID) REFERENCES USERS (USER_ID);

ALTER TABLE DISH ADD CONSTRAINT CIBO RESTAURANT FK FOREIGN KEY (RESTAURANT ID) REFERENCES RESTAURANT (RESTAURANT_ID);
ALTER TABLE DISH RATING ADD CONSTRAINT CIBO DISH_RATING DISH FK FOREIGN KEY (DISH_ID) REFERENCES DISH (DISH_ID);

ALTER TABLE DISH RATING ADD CONSTRAINT CIBO_DISH_RATING USER FK FOREIGN KEY (USER_ID) REFERENCES USERS (USER_ID); 
ALTER TABLE WALLET ADD CONSTRAINT CIBO_WALLET_USER_FK FOREIGN KEY (USER_ID) REFERENCES USERS (USER_ID);

ALTER TABLE RESTAURANT TRANSACTION ADD CONSTRAINT CIBO_REST_TRANSACTION_REST_FK FOREIGN KEY (RESTAURANT_ID) REFERENCES RESTAURANT (RESTAURANT_ID);

ALTER TABLE USER ADDRESS ADD CONSTRAINT CIBO_USER_ADDRESS_USERS FK FOREIGN KEY (USER_ID) REFERENCES USERS (USER_ID);
ALTER TABLE ORDER ITEMS ADD CONSTRAINT CIBO_ORDER_ITEMS_DISH_FK FOREIGN KEY (DISH_ID) REFERENCES DISH(DISH_ID);

ALTER TABLE ORDER ITEMS ADD CONSTRAINT CIBO ORDER ITEMS_ORDERS FK FOREIGN KEY (ORDER_ID) REFERENCES ORDERS (ORDER_ID); 
ALTER TABLE RESTAURANT ADD CONSTRAINT CIBO RESTAURANT OWNER FK FOREIGN KEY (USER_ID) REFERENCES USERS (USER_ID);

ALTER TABLE TABLE BOOKING ADD CONSTRAINT CIBO BOOKING USER FK FOREIGN KEY (USER_ID) REFERENCES USERS (USER_ID); 
ALTER TABLE TABLE BOOKING ADD CONSTRAINT CIBO BOOKING RESTAURANT FK FOREIGN KEY (RESTAURANT ID) REFERENCES RESTAURANT (RESTAURANT_ID);

ALTER TABLE USER LIKES ADD CONSTRAINT CIBO_USER_LIKES_USER_FK FOREIGN KEY (USER_ID) REFERENCES USERS (USER_ID);

ALTER TABLE USER LIKES ADD CONSTRAINT CIBO_USER_LIKES DISH FK FOREIGN KEY(DISH_ID) REFERENCES DISH (DISH_ID);

ALTER TABLE USER LIKES ADD CONSTRAINT CIBO USER LIKES RESTAURANT FK FOREIGN KEY (RESTAURANT ID) REFERENCES RESTAURANT (RESTAURANT_ID);

-- INSERT CODE TO USERS TABLE

INSERT INTO USERS VALUES (101, 'SCOTT', 'scott@stark.com', '8884967823, 3284cbd43ac6fc733d7c3d2176e7a52bbaeba81471702ec332a0a65689cf16e3'); 
-- Scott@123

INSERT INTO USERS VALUES (102, 'TONY', 'tony@stark.com, 8875632142', '1f7cbaa9168ffce48872d8fc4e5429dee55ed8f21d8d84bccd6aaa2a72ae1d79'); 
-- Tony@123
 
INSERT INTO USERS VALUES (103, 'STEVE', 'steve@gmail.com', '9880253413, 97661249431ccedba1711b78fb58eceb2c03054a07a7b684ad53848691b34435');

-- Steve@123 

INSERT INTO USERS VALUES (104, 'ISABELLE', 'banner@Uv.com, 8882039476, 9a8d7e96acac7b73f1f9c994dd512df9068bb0549961e42333745c67a994e6f1'); 
-- Banner@123

 INSERT INTO USERS VALUES (105, 'ROSE', 'rose@uv.com, 9476888203, 9a8d7e96acac7b73f1f9c994dd512df9868bb0549961e42333745c67a994e6f1'); 
--  Banner@123

-- INSERT CODE TO ROLES TABLE

INSERT INTO ROLES VALUES (1, 'CUSTOMER',104);

INSERT INTO ROLES VALUES (2, 'VENDOR', 102);

INSERT INTO ROLES VALUES (3, 'CUSTOMER',101);

INSERT INTO ROLES VALUES (4, 'VENDOR',104);

INSERT INTO ROLES VALUES (5, 'CUSTOMER', 103);

INSERT INTO ROLES VALUES (6, 'ADMIN',105);

-- INSERT CODE TO RESTAURANT TABLE

INSERT INTO RESTAURANT VALUES (1,102, 'KFC', '9823414141', 'Nonveg', '23, Shastri Nagar', 'Baner', 'Pune',' Maharashtra', 411841, 'Accepted',4.2, 'assets/kfcal.jpg-

assets/kfca2.jpg-assets/kfca3.jpg');

INSERT INTO RESTAURANT VALUES (2,102, 'KFC', '8934217843', 'Nonveg', '3, Gajanan Nagar', 'Kothrud', 'Pune',' Maharashtra',411838, 'Accepted', 4.2, 'assets/kfcb1.jpg-

assets/kfcb2.jpg-assets/kfcb3.jpg');

INSERT INTO RESTAURANT VALUES (3,104, 'Pizza Hut', '8784393421', 'Veg', '21, Adalat road', 'Rajouri Garden', 'Delhi', 110027, 'Accepted',
 4.5, 'assets/pizzahuti.jpg-assets/pizzahut2.jpg-assets/pizzahut3.jpg');

INSERT INTO RESTAURANT VALUES (4,104, 'Master Kitchen', '8777772771', 'Nonveg', '52, Sandesh road', 'Vasant Vihar', 'Delhi', 'Delhi', 110057, 'Accepted', 4.1, 'assets/masterkitchen1.jpg-assets/masterkitchen2.jpg-assets/masterkitchen3.jpg');

INSERT INTO RESTAURANT VALUES (5,104, 'Diamond Cafe', '8977772771', 'Veg', '11, Bandana circle', 'Vasant

Vihar', 'Delhi', 'Delhi', 110057, 'Accepted', 4.3, 'assets/cafel.jpg-assets/cafe2.jpg-assets/cafe3.jpg');

INSERT INTO RESTAURANT VALUES (6,104, Empire Restaurant', '9877226354', 'Veg', '2, Amol Complex', 'Vasant

Vihar', 'Delhi', 'Delhi', 110057, Accepted',4.3, 'assets/empire1.jpg-assets/empire2.jpg');

INSERT INTO RESTAURANT VALUES (7,102, 'Bake N Shake', '8823414141', 'Nonveg', '33 Mayura

Circle', 'Baner', 'Pune', 'Maharashtra',411041, 'Accepted',4.2, 'assets/bns.jpg');

 INSERT INTO RESTAURANT VALUES (8,102, 'Ten Downing Street',' 7823414141', 'Nonveg', '3 Madge

Circle', 'Kothrud', 'Pune', 'Maharashtra', 411038, 'Accepted',3.6, 'assets/tds.jpg');

INSERT INTO RESTAURANT VALUES (9,184, 'Urban Socialite', '8989898976', 'Veg', '21 Upper Lake, Shyamla Hills', 'Bhopal', 'Madhya

Pradesh', 462039, 'Accepted',4.3, 'assets/mys3.jpg');

INSERT INTO RESTAURANT VALUES (10,102, 'Over The Moon', '8765678987', 'Egg', '3 Ishwar Nagar', 'Hebbal', 'Mysore', 'Karnataka, 570023, 'Accepted, 3.8, 'assets/mys2.jpg');

INSERT INTO RESTAURANT VALUES (11,102, 'The Public House', '7654567876', 'Veg', '51 Abhishek Circle', 'Hootagalli', 'Mysore', 'Karnataka', 570027, 'Accepted',4.0, 'assets/mys1.jpg');


-- INSERT CODE TO RESTAURANT TRANSACTION TABLE

INSERT INTO RESTAURANT TRANSACTION VALUES (1,1,2,500, true); 
INSERT INTO RESTAURANT TRANSACTION VALUES (2,2,1,500, false); 
INSERT INTO RESTAURANT TRANSACTION VALUES (3,3,2,350, true);

INSERT INTO RESTAURANT TRANSACTION VALUES (4,4,1,500, false);

INSERT INTO RESTAURANT TRANSACTION VALUES (5,5,2,350, true); 
INSERT INTO RESTAURANT TRANSACTION VALUES (6,6,0,400, true);

INSERT INTO RESTAURANT TRANSACTION VALUES (7,1,2,500, true);

INSERT INTO RESTAURANT TRANSACTION VALUES (8,2,1,500, false);

INSERT INTO RESTAURANT TRANSACTION VALUES (9,3,2,350, true);

INSERT INTO RESTAURANT TRANSACTION VALUES (10,4,1,500, false);

INSERT INTO RESTAURANT TRANSACTION VALUES (11,5,2,350, true);

-- INSERT CODE TO COUPON TABLE

INSERT INTO COUPON VALUES (1001, 'CIBO20', 200, 100, sysdate() interval 12 day, sysdate()- interval 5 day);
INSERT INTO COUPON VALUES (10082, 'FOOD30', 300, 125, sysdate() interval 15 day, sysdate()- interval 3 day);
INSERT INTO COUPON VALUES (1003, 'HUNGRY25',250, 100, sysdate()- interval 10 day, sysdate()- interval 2 day);

--INSERT CODE TO USER_ADDRESS TABLE

INSERT INTO USER_ADDRESS VALUES (1,101,' Home',' 407, Rajhans Apartments', 'Lashkar Mohalla', 'Vasant Vihar', 'Delhi', 'Delhi',110029); 
INSERT INTO USER_ADDRESS VALUES (2,101, 'Work ','Bajaj Industrial Area',' Navneeth nagar', 'Vasant Vihar', 'Delhi', 'Delhi',110029);
INSERT INTO USER_ADDRESS VALUES (3,103, 'Home',' A2/1, Indira Soceity', 'Lalvani nagar', 'Baner', 'Pune', 'Maharashtra',411037);
 INSERT INTO USER_ADDRESS VALUES (4,103, 'Work', 'A705, AjayDeep Complex', 'Lalvani nagar' ,'Kothrud',' Pune', 'Maharashtra',411037); 
INSERT INTO USER_ADDRESS VALUES (5,105,' Home', '309, Leo Janani Apartments', 'Lakshmikanth nagar', 'Rajouri Garden', 'Delhi', 'Delhi',110027);

INSERT INTO USER_ADDRESS VALUES (6,104, 'Home', '304, Rajhans Apartments', 'Lashkar Mohalla', 'Vasant Vihar', 'Delhi', 'Delhi',110029);

--INSERT CODE TO ORDERS TABLE

INSERT INTO ORDERS VALUES (1001,101,300, 'INACTIVE');

INSERT INTO ORDERS VALUES (1002,105,750, 'ACTIVE');

INSERT INTO ORDERS VALUES (1003,103,800, 'ACTIVE');

INSERT INTO ORDERS VALUES (1004,103,550, 'INACTIVE');

INSERT INTO ORDERS VALUES (1005,105,700, 'INACTIVE');

-- INSERT CODE TO DISH TABLE

INSERT INTO DISH VALUES (1001,1, 'Chicken Burger', 'Burger', 'Nonveg', 'Spicy and chrunchy chicken tikki in soft bun with fresh lettuce and mustard sauce', 'Chef

Special',150.0,4.5, 'assets/chicken_burger.jpg'); 

INSERT INTO DISH VALUES (1002, 1, 'Chicken Cheese Burger', 'Burger', 'Nonveg', 'Spicy and chrunchy chicken tikki with soft bun with fresh lettuce and extra

cheese', 'Chef Special',200.0,4.2, 'assets/chicken_cheese burger.jpg');
INSERT INTO DISH VALUES (1003,1, 'Chicken Wings', 'chicken', 'Nonveg', 'Spicy and chrunchy chicken wings', 'Chef Special',550.0,4.6, 'assets/chicken wings.jpg'); 
INSERT INTO DISH VALUES (1804, 3, 'Margarita Pizza', 'Pizza', 'Veg', 'Plain and classic cheese pizza', 'Chef Special',258.0,4.4, 'assets/margarita_pizza.jpg');

INSERT INTO DISH VALUES (1005, 3, 'Peppy Paneer Pizza', 'Pizza', 'Veg', 'Pizza topped with fresh cottage cheese, capsicum and red paprika', 'Chef

Special',350.0,4.3, 'assets/paneer_pizza.jpg');

INSERT INTO DISH VALUES (1006, 2, 'Chicken Burger', 'Burger', 'Nonveg', 'Spicy and chrunchy chicken tikki with soft bun with fresh lettuce and mustard

sauce', 'Chef Special', 150.0,4.5,' assets/chicken_burger.jpg');
INSERT INTO DISH VALUES (1007, 2, 'Chicken Cheese Burger',' Burger', 'Nonveg', 'Spicy and chrunchy chicken tikki with soft bun with fresh lettuce and extra

cheese', 'Chef Special',200.0,4.2,' assets/chicken_cheese burger.jpg'); 
INSERT INTO DISH VALUES (1008,2, 'Chicken Wings', 'chicken', 'Nonveg', 'Spicy and chrunchy chicken wings', 'Chef Special',550.0,4.6, 'assets/chicken_wings.jpg');

INSERT INTO DISH VALUES (1009, 3, 'Farmhouse Pizza', 'Pizza', 'Veg', 'Pizza topped with fresh green olives, tomatoes and onions', 'Chef

Special',400.0,3.8, 'assets/farmhouse_pizza.jpg');

INSERT INTO DISH VALUES (1018, 3, 'Exotica Pizza', 'Pizza', 'Veg', 'Pizza topped with fresh cottage cheese, onions, capsicum, tomatoes and corn', 'Chef

Special',410.0,4.3, 'assets/exotica_pizza.jpg');

INSERT INTO DISH VALUES (1011,3, 'Veg Loaded Pizza', 'Pizza', 'Veg', 'Pizza topped with fresh corn, black olives, zuchini, capsicum, yellow and red bell

peppers', 'Chef Special',450.0,4.5, 'assets/veg loaded_pizza.jpg');

INSERT INTO DISH VALUES (1012,1, 'French Fries', 'Fries', 'Veg', 'Classic crunchy and tasty french fries', 'Chef Special', 200.0,4.5, 'assets/french_fries.jpg');

INTO DISH VALUES (1813,2, 'French Fries', 'Fries', 'Veg Classic crunchy and tasty french fries', 'Chef Special ',200.0,4.5, 'assets/french_fries.jpg');

INSERT INTO DISH VALUES (1014,6, 'Veg Biryani', 'Biryani', 'Veg', 'Flavoured rice with marinated vegetables garnished with fried onions and cashews', 'Chef

Special',450.0,4.5, 'assets/veg_biryani.jpg');

INSERT INTO DISH VALUES (1015,6, 'Paneer Chilly', 'Starter', 'Veg', 'Fresh cottage cheese tossed in tasty hot and sweet sauce', 'Chef Special',350.0,4.5, 'assets/paneer_chilly.jpg'); 
INSERT INTO DISH VALUES (1016,6, 'Caesar Salad', 'Salad', 'Veg', 'Romaine lettuce and croutons dressed with lemon juice, worcestershire sauce, olive oil and

parmesan cheese', 'Chef Special',550.0,4.5,' assets/caesar salad.jpg');

INSERT INTO DISH VALUES (1018,4, 'Chicken Biryani', 'North Indian', 'Nonveg', 'Delicious savory rice dish loaded with spicy marinated chicken and caramelized

onions', 'Chef Special', 300.0,4.5, 'assets/chicken biryani.jpg'); 
INSERT INTO DISH VALUES (1019,4, 'Butter Chicken', 'North Indian', 'Nonveg', 'Marinated chicken roasted and cooked in tomato puree, cream, butter and a host of masalas', 'Chef Special',250.0,4.2, 'assets/chicken_lababdar.jpg');
 INSERT INTO DISH VALUES (1020, 4, 'Lemon Chicken', 'North Indian', 'Nonveg', 'Pan seared chicken breasts are coated with a tangy and rich lemon butter
 sauce', 'Chef Special',250.8,4.3, 'assets/lemon_chicken.jpg');

INSERT INTO DISH VALUES (1021,4, 'Fish Curry', 'North Indian', 'Nonveg', 'Exclusive taste of roasted spices seeped into juicy fish pieces', 'Chef Special',235.0,4.2, 'assets/fish curry.jpg');

INSERT INTO DISH VALUES (1022,4, 'Fish Platter', 'North Indian', 'Nonveg', 'Smoked Fish Platter with honey-mustard dill sauce and cucumber-coriander yogurt', 'Chef Special',270.0,4.3, 'assets/fish_platter.jpg'); 
INSERT INTO DISH VALUES (1023,8, Grilled Fish', 'North Indian, 'Nonveg', 'Delicious, crispy and fried fish served with flavoured sauce', 'Chef

Special',200.0,3.8, 'assets/grilled_fish.jpg'); 
INSERT INTO DISH VALUES (1024,8, 'Fried Chicken', 'North Indian', 'Nonveg', 'Chicken marinated in beautiful gravy and deep fried', 'Chef

Special',250.0,4.6, 'assets/fried chicken.jpg'); 
INSERT INTO DISH VALUES (1025,8, 'Chicken Platter', 'North Indian', 'Nonveg', 'Smoked Fish Platter with honey-mustard dill sauce and cucumber-coriander

yogurt', 'Chef Special',250.0,4.4, 'assets/chicken_platter.jpg'); 
INSERT INTO DISH VALUES (1026,8, 'Fish N Chips',' Starter', 'Nonveg', 'Fried marinated fish served with fries and veggies', 'Chef Special', 230.0,4.2, 'assets/fish_n_chips.jpg');

INSERT INTO DISH VALUES (1827,4, 'Chicken Salad', 'Salad', 'Nonveg', 'Seasoned chicken breast, creamy mayonnaise, lemon juice, crunchy celery, and almonds', 'Chef Special',200.0,4.3, 'assets/chicken_salad.jpg');

INSERT INTO DISH VALUES (1029,5, 'Dal Makhani', 'North Indian', 'Veg', 'Classic Indian dish made with whole urad dal, rajma, butter and spices', 'Chef Special',150.0,4.6, 'assets/dal_makhani.jpg');
 INSERT INTO DISH VALUES (1030,5, 'Gobi Masala',' North Indian',' Veg', 'Cauliflower fried and sauted in spicy masala gravy', 'Chef

Special',150.0,3.8, 'assets/gobhi_masala.jpg');

INSERT INTO DISH VALUES (1031,5, 'Paneer Mushroom', 'North Indian', 'Veg', 'Classic Indian gravy prepared with mushroom, peas and paneer', 'Chef

Special',200.0,3.7, 'assets/paneer_mushroom.jpg'); 
INSERT INTO DISH VALUES (1032,5, 'Mix Veg', 'North Indian', 'Veg', 'Mixture of vegetables together in a traditional Indian onion-tomato gravy', 'Chef

Special',150.0,4.6, 'assets/mix_veg.jpg');

INSERT INTO DISH VALUES (1033,5, 'Dal Fry', 'North Indian', 'Veg', 'Spicy punjabi dish made from mixed dals like toor, chana, moong, masoor dal', 'Chef

Special',160.0,4.4,' assets/dal_fry.jpg');

INSERT INTO DISH VALUES (1034,5, 'Butter Paneer', 'North Indian', 'Veg', 'Rich and creamy dish of paneer in a tomato, butter and cashew sauce', 'Chef

Special',200.0,4.2, 'assets/butter_paneer.jpg'); 
INSERT INTO DISH VALUES (1035,5, 'Veg Kofhta, North Indian', 'Veg', 'Exotic Indian gravy dish made from mix vegetable dumplings dunked in a onion-tomato based

gravy', 'Chef Special',200.0, 3.8, 'assets/kofhta.jpg');

INSERT INTO DISH VALUES (1036,5, 'Kadai Paneer', 'North Indian', 'Veg', 'Indian cottage cheese and bell peppers cooked in a spicy masala', 'Chef Special',200.0,4.3, 'assets/kadai_paneer.jpg'); 
INSERT INTO DISH VALUES (1037,9, 'Kadai Veg', 'North Indian', 'Veg', 'A mixture of veggies in cooked in a spicy gravy flavored with a special kadai
 masala', 'Chef Special',230.0,4.8, 'assets/veg tadka.jpg'); 
 INSERT INTO DISH VALUES (1038,9, 'Chole Masala', 'North Indian', 'Veg', 'Delicious & flavorful curry made by cooking chickpeas in a spicy onion tomato masala gravy', 'Chef Special', 250.0, 3.6, 'assets/chole_masala.jpg');

INSERT INTO DISH VALUES (1039,9, 'Navratna Curry', 'North Indian', 'Veg', 'Nine gems and korma is a creamy nutty curry', 'Chef

Special',250.0,4.3, 'assets/navratna_curry.jpg');

INSERT INTO DISH VALUES (1040,9, 'Rajma Rice', 'North Indian', 'Veg', 'Delicious & flavorful curry made with red kidney beans aka rajma', 'Chef Special',250.0,4.6, 'assets/rajma_chawal.jpg'); 
INSERT INTO DISH VALUES (1041,9, 'Paneer Tikka', 'North Indian', 'Veg', 'Indian dish of marinated paneer cheese served in a spiced gravy', 'Chef

Special',200.0,4.1, 'assets/tikka.jpg');

INSERT INTO DISH VALUES (1042,9, 'Veg Korma', 'North Indian', 'Veg', 'Boiled vegetables cooked in creamy and aromatic gravy of yogurt, coconut and cashew nuts 'Chef Special',200.0,4.0, assets/korma.jpg');
INSERT INTO DISH VALUES (1028,5, 'Palak Paneer', 'North Indian', 'Veg', 'Indian cottage cheese cooked with spinach and spices in creamy and flavorful curry', 'Chef Special',200.0,4.3, 'assets/palak_paneer.jpg');

INSERT INTO DISH VALUES (1017,11, 'Tandoori Paneer', 'Tandoor', 'Veg', 'Fresh cottage cheese marinated in spices and cooked in the tandoor', 'Chef

Special',450.0,4.5, 'assets/ptm.jpg');

INSERT INTO DISH VALUES (1043,7, 'Maple Butter Waffle', 'Bakery', 'Egg', 'Classic crispy waffle with warm melted butter and maple syrup drizzle', 'Chef Special',170.0,4.3, 'assets/maple_waffle.jpg'); 
INSERT INTO DISH VALUES (1044,7, 'Triple Chocolate Waffle', 'Bakery', 'Egg', 'Classic crispy waffle with warm melted white and dark chocolate', 'Chef Special',200.0,4.5, 'assets/choco_waffle.jpg');
 INSERT INTO DISH VALUES (1045,7, 'Apple Tart', 'Bakery', 'Egg', 'Soft, yet crisp base with a sweet taste and a slightly tart finish tuopped with apple', 'Chef Special',150.0,4.8, 'assets/apple_tart.jpg');
INSERT INTO DISH VALUES (1046,7, 'Chocolate Mousse', 'Bakery', 'Egg', 'Decadently creamy, light and billowy, and indulgently chocolaty', 'Chef

Special',150.0,4.2, 'assets/choco_mousse.jpg'); 
INSERT INTO DISH VALUES (1047,7,' Death By Chocolate', 'Bakery', 'Egg', 'Rich layered chocolaty dessert', 'Chef Special',258.0,4.3, 'assets/dbc.jpg'); 
INSERT INTO DISH VALUES (1048, 18, 'Schezwan Noodle', 'Chinese', 'Veg', 'Classic noodle prepared in spicy schezwan sauce', 'Chef

Special',230.0,4.2, 'assets/schz_noodles.jpg'); 
INSERT INTO DISH VALUES (1049, 10, 'Hakka Noodle', 'Chinese', 'Veg', 'Classic noodle prepared in variety of sauce', 'Chef Special', 270.0,4.8, 'assets/hakka_noodle.jpg');

INSERT INTO DISH VALUES (1054, 11, 'Veg Lasagna', 'Italian', 'Veg', 'Sauted cheesy veggies arraged in between layers of lasagne sheets', 'Chef

Special',200.0,4.5, 'assets/1sg.jpg');

INSERT INTO DISH VALUES (1050, 18, 'Momos', 'Chinese', 'Veg', 'Soft dumplings filled with sauted veggies, served with spicy sauce', 'Chef
 Special',100.0,3.3, 'assets/momo.jpg');

INSERT INTO DISH VALUES (1053, 10, 'Munchurian', 'Chinese', 'Veg', 'Soft vegetable balls served in rich spicy machurian gravy', 'Chef

Special',150.0,4.3, 'assets/munchurian.jpg');

INSERT INTO DISH VALUES (1051,11, 'Pasta Alfredo', 'Italian', 'Veg', 'Soft boiled pasta tossed in cheesy creamy white sauce, olives and veggies', 
'Chef Special',250.0,4.5, 'assets/alf_pasta.jpg');

 INSERT INTO DISH VALUES (1052, 11, 'Garlic Bread', 'Bakery', 'Veg', 'Cheesy stuffed garlic bread shallow grilled', 'Chef Special', 158.8,4.3, 'assets/gb.jpg');

-- INSERT CODE TO ORDER ITEMS TABLE

INSERT INTO ORDER_ITEMS VALUES (1,1001, 1001, 2);

INSERT INTO ORDER_ITEMS VALUES (2,1004, 1002,3);

INSERT INTO ORDER_ITEMS VALUES (3,1002,1003,4); 
INSERT INTO ORDER_ITEMS VALUES (4,1003, 1004,1); 
INSERT INTO ORDER_ITEMS VALUES (5,1005, 1005,2);

-- INSERT CODE TO DISH RATING TABLE

INSERT INTO DISH_RATING VALUES (1,1002,101,4);

INSERT INTO DISH_RATING VALUES (2,1001,101,4);

INSERT INTO DISH_RATING VALUES (3,1002,182,4);

INSERT INTO DISH_RATING VALUES (4,1002,103,4);
INSERT INTO DISH_RATING VALUES (5,1003,101,4);

INSERT INTO DISH_RATING VALUES (6,1004,101,4);

-- INSERT CODE TO WALLET TABLE

INSERT INTO WALLET VALUES (1,101,700);

INSERT INTO WALLET VALUES (2,102,700);

INSERT INTO WALLET VALUES (3,103,700);

INSERT INTO WALLET VALUES (4,104,700);

-- INSERT CODE TO USER LIKES TABLE
 TNSERT INTO USER_ITKES VALUES (1 'Nonved' 1901 101 11:
INSERT INTO USER_LIKES VALUES (1, 'Nonveg',1001,101,1);
INSERT INTO USER_LIKES VALUES (2, 'Nonveg', 1002, 103,2);

INSERT INTO USER_LIKES VALUES (3, 'Veg', 1004, 101,3); 
INSERT INTO USER_LIKES VALUES (4, 'Veg',1005,105,3);

-- INSERT CODE TO TABLE_BOOKING TABLE

INSERT INTO TABLE_BOOKING VALUES (1, sysdate(),4, current_time(),1,101);
INSERT INTO TABLE_BOOKING VALUES (2, sysdate(), 2, current_time(),2,103); 
INSERT INTO TABLE_BOOKING VALUES (3, sysdate(), 4, current_time(),3,105);

-- SELECT STATEMENTS FOR ALL TABLES

SELECT* FROM ROLES;

SELECT* FROM USERS;

SELECT* FROM RESTAURANT;

SELECT FROM RESTAURANT_TRANSACTION;

SELECT* FROM COUPON;

SELECT* FROM USER_ADDRESS;

SELECT* FROM ORDERS;

SELECT* FROM ORDER_ITEMS;

SELECT* FROM DISH;
SELECT* FROM DISH_RATING;

SELECT* FROM WALLET;

SELECT* FROM USER_LIKES;

SELECT* FROM TABLE_BOOKING;