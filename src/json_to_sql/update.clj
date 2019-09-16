(ns json-to-sql.update
  (:require [json-to-sql.sql-util :as sql-util]
            [clojure.string :as s]
            [clojure.string :as str]))

(def template "UPDATE {table_name} SET {column_values} WHERE {conditions}")

(defn get-column-values
  "Gets string of column names and values joined with , ('a = 1, b = 2 , ...')"
  [map]
  (seq (for [[key value] map]
           (str (name key) " = " (sql-util/map-value->sql-value value)))))

(defn statement
  "Gets UPDATE SQL statement for table name with conditions"
  [table-name conditions map]
  (println conditions)
  (let [values (s/join ", " (get-column-values map))]
    (sql-util/map->sql
      (sql-util/map->sql
        (sql-util/map->sql template (sql-util/placeholders :column_values) values)
        (sql-util/placeholders :conditions) (str/join " AND " (sql-util/seq-of-map->conditions conditions)))
      (sql-util/placeholders :table_name) table-name)))


