CREATE TABLE orders (
  id BIGINT(20) NOT NULL AUTO_INCREMENT,
  creationDate DATETIME NOT NULL,
  status VARCHAR(255) NOT NULL,
  PRIMARY KEY (id)
)