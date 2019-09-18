(ns json-to-sql.core-test
  (:require [midje.sweet :refer [fact throws]]
            [cheshire.core :refer :all]
            [json-to-sql.core :refer :all]))

(fact "json->select"
      (json->select select-json-file)
      => "SELECT name, address, date_of_birth, is_admin, card_no FROM Users WHERE name LIKE 'Fake' AND card_no = 123")

(fact "json->insert"
      (json->insert insert-json-file)
      => "INSERT INTO Users (name, address, date_of_birth, is_admin, card_no) VALUES ('Joe Doe', 'Fake street 123', '01.01.1900', 1, 123)")

(fact "json->update"
      (json->update update-json-file)
      => "UPDATE Users SET name = 'Joe Doe', address = 'Fake street 123', date_of_birth = '01.01.1900', is_admin = 1, card_no = 123 WHERE name LIKE 'Fake' AND card_no = 123")

(fact "json->delete"
      (json->delete delete-json-file)
      => "DELETE FROM Users WHERE name LIKE 'Fake' AND card_no = 123")