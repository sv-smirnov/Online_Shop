create table if not exists products (
    id          bigserial primary key,
    title       varchar(255),
    price       int
);

insert into products (title, price)
values ('Milk', 100),
       ('Bread', 80),
       ('Cheese', 90);

create table orders(
    id bigserial primary key,
    username varchar(255) not null,
    total_price int not null,
    address varchar,
    phone varchar(255),
    created_at timestamp,
    updated_at timestamp,
    bill_id varchar(255) DEFAULT '0',
    status varchar(255) DEFAULT 'not paid'
);

create table order_items(
                            id bigserial primary key,
                            product_id bigint not null references products(id),
                            quantity int not null,
                            order_id bigint not null references orders(id),
                            price_per_product int not null,
                            price int not null,
                            created_at timestamp,
                            updated_at timestamp
);

insert into orders (username, total_price, address, phone, bill_id, status)
values ('bob', 200, 'address', '12345', '0', 'not paid');

insert into order_items (product_id, order_id, quantity, price_per_product, price)
values (1, 1, 2, 100, 200);
