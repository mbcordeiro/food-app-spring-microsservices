CREATE TABLE order_item (
  id BIGINT(20) NOT NULL AUTO_INCREMENT,
  description VARCHAR(255) DEFAULT NULL,
  quantity INT(11) NOT NULL,
  order_id BIGINT(20) NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (order_id) REFERENCES orders(id)
)