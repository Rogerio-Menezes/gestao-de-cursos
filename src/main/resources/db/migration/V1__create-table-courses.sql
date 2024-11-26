create table courses(
    id UUID primary key,
    name VARCHAR(100) not null unique,
    category VARCHAR(100) not null,
    shift VARCHAR(100) not null,
    situation VARCHAR(50) NOT NULL,
    created_at TIMESTAMP not null ,
    updated_at TIMESTAMP not null
);