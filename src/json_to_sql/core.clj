(ns json-to-sql.core
  (:require [json-to-sql.select :as select]
            [json-to-sql.insert :as insert]
            [json-to-sql.update :as update]
            [json-to-sql.delete :as delete]
            [json-to-sql.json-util :as json-util]))

(def select-json-file "resources\\select.json")
(def insert-json-file "resources\\insert.json")
(def update-json-file "resources\\update.json")
(def delete-json-file "resources\\delete.json")

(defn json->select
  [json]
  "Converts json to SQL SELECT query"
  (let [map (json-util/json->map json)
        table-name-keyword (json-util/get-table-name-keyword map)
        data (table-name-keyword map)]
    (select/statement (name table-name-keyword) (:conditions map) data)))

(defn json->insert
  [json]
  "Converts json to SQL INSERT statement"
  (let [map (json-util/json->map json)
        table-name-keyword (json-util/get-table-name-keyword map)
        data (table-name-keyword map)]
    (insert/statement (name table-name-keyword) data)))

(defn json->update
  [json]
  "Converts json to SQL UPDATE statement"
  (let [map (json-util/json->map json)
        table-name-keyword (json-util/get-table-name-keyword map)
        data (table-name-keyword map)]
    (update/statement (name table-name-keyword) (:conditions map) data)))

(defn json->delete
  [json]
  "Converts json to SQL DELETE statement"
  (let [map (json-util/json->map json)
        table-name-keyword (json-util/get-table-name-keyword map)]
    (delete/statement (name table-name-keyword) (:conditions map))))