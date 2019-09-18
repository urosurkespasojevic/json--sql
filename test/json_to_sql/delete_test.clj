(ns json-to-sql.delete-test
  (:require [midje.sweet :refer [fact throws]]
            [cheshire.core :refer :all]
            [json-to-sql.delete :refer :all]))

(fact "statement"
      (statement "Users" [{:column "name", :operation "like" :value "mika"}
                          {:column "age", :operation "gt" :value 20}])
      => "DELETE FROM Users WHERE name LIKE 'mika' AND age > 20"
      (statement "Users" [])
      => "DELETE FROM Users")
