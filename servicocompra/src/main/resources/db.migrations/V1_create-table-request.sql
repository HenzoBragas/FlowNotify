
CREATE TABLE request (
    id_buy BIGINT PRIMARY KEY AUTO_INCREMENT,
    id_user BIGINT NOT NULL,
    total_price DECIMAL(10, 2) NOT NULL,
    data_request TIMESTAMP NOT NULL
);

CREATE TABLE request_items (
    id_buy BIGINT NOT NULL,
    id_product VARCHAR(255) NOT NULL,
    quantity BIGINT NOT NULL,
    unit_price DECIMAL(10, 2) NOT NULL,

    PRIMARY KEY (id_buy, id_product),

    FOREIGN KEY (id_buy) REFERENCES request(id_buy) ON DELETE CASCADE

);