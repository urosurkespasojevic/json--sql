(ns json-to-sql.core
  (:require [json-to-sql.insert :as insert]
            [json-to-sql.update :as update]
            [cheshire.core]))

(defn json->select
  [json]
  "Converts json to SQL SELECT query"
  (println "json->select"))

(defn json->insert
  []
  "Converts json to SQL INSERT statement"
  (insert/statement "Users" {:name "Pera" :address "Fake Street 213" :link 2}))

(defn json->update
  [json]
  "Converts json to SQL UPDATE statement"
  (println "json->update"))

(defn json->delete
  [json]
  "Converts json to SQL DELETE statement"
  (println "json->select"))


;SELECT clomn1, column2, column3
;FROM table_name
;WHERE column1 = 'some_value' AND/OR
;
;{
; "operation": "add"
; "User": {
;          "name": "Pera",
;          "address": "Fake Street 213"
;          }
; "condition": {
;               "operation": "eq",
;               "columnName": "id",
;               "columnType": "number",
;               "value": "1"
;               }
; }

;INSERT INTO table_name (column1, column2)
;VALUES (column1_value, column2_value)
;WHERE columnName operation value