CREATE TABLE franchise (
                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                           name VARCHAR(255) NOT NULL
);

CREATE TABLE branch (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        name VARCHAR(255) NOT NULL,
                        franchise_id BIGINT NOT NULL,
                        FOREIGN KEY (franchise_id) REFERENCES franchise(id)
);

CREATE TABLE product (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         name VARCHAR(255) NOT NULL,
                         stock_quantity INT NOT NULL,
                         branch_id BIGINT NOT NULL,
                         FOREIGN KEY (branch_id) REFERENCES branch(id)
);