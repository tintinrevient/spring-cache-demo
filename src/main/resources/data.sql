DROP TABLE IF EXISTS CACHE;

CREATE TABLE CACHE (
  id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
  cachekey VARCHAR(128),
  cachevalue VARCHAR(128),
  type VARCHAR(128)
);