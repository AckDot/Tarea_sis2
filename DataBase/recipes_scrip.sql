DROP DATABASE if EXISTS receta; 
CREATE DATABASE recipe;

use recipe;

CREATE TABLE recipes (
  id SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT,
  name_recipe VARCHAR(50)NOT NULL UNIQUE,
  preparation TEXT NOT NULL,
  last_update TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
     
CREATE TABLE ingredients (
  id SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT, 
  name_ingredient VARCHAR(100)NOT NULL,
  amount VARCHAR(15),
  recipes_id SMALLINT UNSIGNED NOT NULL,
  last_update TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY  (id),
  KEY idx_fk_id_recipes (recipes_id),
  CONSTRAINT fk_recipes_ingredients FOREIGN KEY (recipes_id) REFERENCES ingredients (id) ON DELETE RESTRICT ON UPDATE CASCADE
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

DELIMITER $$
CREATE PROCEDURE insert_recipe(IN _id SMALLINT, IN _name VARCHAR(50), IN _preparation TEXT)
BEGIN
   INSERT INTO recipes (id, name_recipe, preparation ) VALUES(_id , _name, _preparation);
END$$

DELIMITER $$
CREATE PROCEDURE insert_ingredients(IN _id SMALLINT, IN _name VARCHAR(100), IN _amount VARCHAR(15), IN _recipe_id SMALLINT)
BEGIN
   INSERT INTO ingredients (id, name_ingredient, amount, recipes_id ) VALUES(_id , _name, _amount, _recipe_id);
END$$

DELIMITER $$
CREATE PROCEDURE get_last_recipes_id(OUT _id)
BEGIN
  SELECT id into _id from recipes ORDER BY id DESC limit 1;
END$$

