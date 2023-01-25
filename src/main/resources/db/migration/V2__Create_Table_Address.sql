CREATE TABLE `address` (
  id bigint AUTO_INCREMENT PRIMARY KEY,
  street varchar(255),
  zip_code varchar(255),
  number INT,
  city varchar(255),
  main_address boolean
);