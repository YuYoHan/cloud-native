create table orders (
    id bigint primary key not null ,
    book_isbn varchar(255) not null ,
    book_name varchar(255),
    book_price float8,
    quantity int not null ,
    status varchar(255) not null ,
    created_date timestamp not null ,
    last_created_date timestamp not null ,
    version integer not null
);