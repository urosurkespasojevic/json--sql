(ns json-to-sql.select-test
  (:require [midje.sweet :refer [fact throws]]
            [cheshire.core :refer :all]
            [json-to-sql.select :refer :all]))

(fact "statement"
      (statement "Users" [{:column "name", :operation "like" :value "mika"}
                          {:column "age", :operation "gt" :value 20}]
                 {:name "", :age "" :email ""}) => "SELECT name, age, email FROM Users WHERE name LIKE 'mika' AND age > 20"
      (statement "Users" [{:column "name", :operation "like" :value "mika"}
                          {:column "age", :operation "gt" :value 20}]
                 {:* ""}) => "SELECT * FROM Users WHERE name LIKE 'mika' AND age > 20"
      (statement "Users" [] {:* ""}) => "SELECT * FROM Users"
      (statement "Users" [] {:name "", :age "" :email ""}) => "SELECT name, age, email FROM Users")
