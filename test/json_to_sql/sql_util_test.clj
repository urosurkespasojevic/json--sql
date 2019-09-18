(ns json-to-sql.sql-util-test
  (:require [midje.sweet :refer [fact throws]]
            [cheshire.core :refer :all]
            [json-to-sql.sql-util :refer :all]))

(fact "map->sql"
      (map->sql "select * from {table_name}" (placeholders :table_name) "Users")
      => "select * from Users"
      (map->sql "insert into Users ({column_names}) VALUES ({column_values})" (placeholders :column_names) "name, email")
      => "insert into Users (name, email) VALUES ({column_values})"
      (map->sql "insert into Users (name, email) VALUES ({column_values})" (placeholders :column_values) "'pera', 'pera@gmail.com'")
      => "insert into Users (name, email) VALUES ('pera', 'pera@gmail.com')")

(fact "map-value->sql-value"
      (map-value->sql-value "pera") => "'pera'"
      (map-value->sql-value true) => 1
      (map-value->sql-value false) => 0
      (map-value->sql-value 1) => 1
      (map-value->sql-value nil) => nil)

(fact "map->seq-columns"
      (map->seq-columns {:name "pera", :is_admin true}) => ["name" "is_admin"]
      (map->seq-columns {}) => []
      (map->seq-columns nil) => [])

(fact "map->seq-values"
      (map->seq-values {:name "pera", :is_admin true, :age 20}) => ["'pera'" 1, 20]
      (map->seq-values {}) => []
      (map->seq-values nil) => [])

(fact "seq-of-map->conditions"
      (seq-of-map->conditions [{:column "name", :operation "eq" :value "mika"},
                               {:column "age" :operation "gt" :value 20}]) => ["name = 'mika'" "age > 20"]
      (seq-of-map->conditions [{:column "is_admin", :operation "not_eq" :value true},
                               {:column "date" :operation "like" :value "2019-01-01"}]) => ["is_admin <> 1" "date LIKE '2019-01-01'"]
      (seq-of-map->conditions []) => nil
      (seq-of-map->conditions nil) => nil)

(fact "get-column-names"
      (get-column-names {:name "pera", :age 20, :is_admin true}) => "name, age, is_admin"
      (get-column-names {}) => ""
      (get-column-names nil) => "")
