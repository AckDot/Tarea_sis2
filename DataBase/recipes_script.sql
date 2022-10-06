DROP DATABASE if EXISTS receta; 
CREATE DATABASE recipe;

use recipe;

CREATE TABLE recipes (
  id SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT,
  name_recipe VARCHAR(50)NOT NULL UNIQUE,
  ingredients TEXT NOT NULL,
  score SMALLINT NOT NULL,
  last_update TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

DELIMITER $$
CREATE PROCEDURE insert_recipes (IN _name VARCHAR(50), IN 
_ingredients TEXT, IN _score SMALLINT)
BEGIN
   INSERT INTO recipes (name_recipe, ingredients, score) VALUES(_name, _ingredients, _score);
END$$

DELIMITER $$
CREATE PROCEDURE get_last_id{OUT _id}
BEGIN
    SELECT id INTO _id FROM recipes ORDER BY id DESC limit 1
END$$
