(ns json-to-sql.json-util-test
  (:require [midje.sweet :refer [fact throws]]
            [cheshire.core :refer :all]
            [json-to-sql.json-util :refer :all]))

(fact
  "get-table-name-keyword"
  (get-table-name-keyword {:Users {:a 1}, :conditions {:a 1}}) => :Users
      (get-table-name-keyword {:conditions {}}) => nil
      (get-table-name-keyword nil) => nil
      (get-table-name-keyword {:Users {}, :UserTypes {}, :conditions {}}) => :Users
      (get-table-name-keyword {:conditions {}, :Users {}}) => :Users)
