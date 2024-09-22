-- Create the database
CREATE DATABASE ocp3;

-- Create user for the database
CREATE USER ocp3 WITH PASSWORD 'ocp3';

-- Create users table
CREATE TABLE users
(
    id         BIGSERIAL PRIMARY KEY,
    name       VARCHAR,
    email      VARCHAR UNIQUE,
    password   VARCHAR,
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);

-- Create rentals table
CREATE TABLE rentals
(
    id          BIGSERIAL PRIMARY KEY,
    name        VARCHAR,
    surface     INTEGER,
    price       INTEGER,
    picture     VARCHAR,
    description TEXT,
    owner_id    BIGINT,
    created_at  TIMESTAMP,
    updated_at  TIMESTAMP,
    FOREIGN KEY (owner_id) REFERENCES users (id)
);

-- Create messages table
CREATE TABLE messages
(
    id         BIGSERIAL PRIMARY KEY,
    message    TEXT,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    user_id    BIGINT,
    rental_id  BIGINT,
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (rental_id) REFERENCES rentals (id)
);

-- Create index for foreign keys
CREATE INDEX idx_rentals_owner_id ON rentals (owner_id);
CREATE INDEX idx_messages_user_id ON messages (user_id);
CREATE INDEX idx_messages_rental_id ON messages (rental_id);

-- Grant permissions to user
GRANT ALL ON TABLE public.messages TO ocp3;
GRANT ALL ON TABLE public.rentals TO ocp3;
GRANT ALL ON TABLE public.users TO ocp3;
GRANT ALL ON SEQUENCE public.messages_id_seq TO ocp3;
GRANT ALL ON SEQUENCE public.rentals_id_seq TO ocp3;
GRANT ALL ON SEQUENCE public.users_id_seq TO ocp3;

