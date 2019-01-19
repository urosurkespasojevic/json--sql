(ns json-to-sql.insert
  (:require [json-to-sql.util :as util]
            [clojure.string :as s]))

(def template "INSERT INTO {table_name} ({column_names}) VALUES ({column_values})")

(def placeholders
  {:table_name "{table_name}"
   :column_names "{column_names}"
   :column_values "{column_values}"})

(defn get-column-names
  "Gets string of column names joined with ,"
  [map]
  (let [seq-columns (into [] (util/map->seq-columns map))]
    (s/join ", " seq-columns)))


(defn get-column-values
  "Gets string of column values joined with ,"
  [map]
  (let [seq-values (into [] (util/map->seq-values map))]
    (s/join ", " seq-values)))

(defn statement
  "Gets INSERT SQL statement for table name"
  [table-name map]
  (let [names (get-column-names map)
        values (get-column-values map)]
    (util/map->sql
      (util/map->sql
        (util/map->sql template (placeholders :column_values) values)
        (placeholders :column_names) names)
      (placeholders :table_name) table-name)))


;(defn build-statement
;  [operation table-name map]
;  "Builds SQL statements depends of value of :operation"
;  (case operation
;    "insert" (build-insert-statement (converter/get-statement-template operation) table-name map)))

