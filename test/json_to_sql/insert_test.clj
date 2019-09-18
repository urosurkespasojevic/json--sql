(ns json-to-sql.insert-test
  (:require [midje.sweet :refer [fact throws]]
            [cheshire.core :refer :all]
            [json-to-sql.insert :refer :all]))

(fact "get-column-values"
      (get-column-values {:name "mika", :age 20 :is_admin true}) => "'mika', 20, 1"
      (get-column-values {}) => ""
      (get-column-values nil) => "")

(fact "statement"
      (statement "Users" {:name "mika", :age 20 :is_admin true}) => "INSERT INTO Users (name, age, is_admin) VALUES ('mika', 20, 1)")
