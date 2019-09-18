(ns json-to-sql.update-test
  (:require [midje.sweet :refer [fact throws]]
            [cheshire.core :refer :all]
            [json-to-sql.update :refer :all]))

(fact "get-column-values"
      (get-column-values {:name "mika", :age 20, :is_admin false}) => ["name = 'mika'" "age = 20" "is_admin = 0"]
      (get-column-values {}) => nil
      (get-column-values nil) => nil)

(fact "statement"
      (statement "Users" [{:column "name", :operation "like" :value "mika"}
                          {:column "age", :operation "gt" :value 20}]
                 {:name "pera", :age 30 :is_admin false})
      => "UPDATE Users SET name = 'pera', age = 30, is_admin = 0 WHERE name LIKE 'mika' AND age > 20"
      (statement "Users" [] {:name "pera", :age 30 :is_admin false})
      => "UPDATE Users SET name = 'pera', age = 30, is_admin = 0")
