(ns json-to-sql.core
  (:require [json-to-sql.insert :as insert]
            [json-to-sql.update :as update]
            [json-to-sql.json-util :as json-util]
            [json-to-sql.json-util :as sql-util]))

(def insert-json-file "resources\\insert.json")
(def update-json-file "resources\\update.json")

(defn read-json
  [filename]
  (cheshire/parse-string (slurp filename) true))

(defn json->select
  [json]
  "Converts json to SQL SELECT query"
  (println "json->select"))

(defn json->insert
  [json]
  "Converts json to SQL INSERT statement"
  (let [map (json-util/json->map json)
        table-name-keyword (json-util/get-table-name-keyword map)
        data (table-name-keyword map)]
    (insert/statement (name table-name-keyword) data)))



(defn json->update
  [json]
  (let [map (json-util/json->map json)
        table-name-keyword (json-util/get-table-name-keyword map)
        data (table-name-keyword map)]
    (update/statement (name table-name-keyword) (sql-util/map->conditions (json-util/get-conditions-keyword map)) data)))

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