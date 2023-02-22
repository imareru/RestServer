create table film(
	film_id serial primary key,
	film_name varchar(20) not null,
	genre varchar(20),
	price numeric(18,2),
	age_limit varchar(3),
	description text
);

create table cinema(
	cinema_id serial primary key,
	cinema_name varchar(20),
	cinema_address varchar(50)
);

create table client(
	client_id serial primary key,
	client_name varchar(40),
	phone varchar(11) unique check (phone !='') not null,
	email varchar(30) unique check (email !='') not null,
	age integer default 12
);

create table room(
	room_id serial primary key,
	number_of_seats int,
	cinema_id int,
	foreign key (cinema_id) references cinema(cinema_id)
);

create table new_order(
	order_id serial primary key,
	cinema_id int,
	film_id int,
	room_id int,
	quantity int,
	discount decimal,
	payment_method varchar(16),
	client_id int,
	foreign key (cinema_id) references cinema(cinema_id),
	foreign key (film_id) references film(film_id),
	foreign key (room_id) references room(room_id),
	foreign key (client_id) references client(client_id)
);