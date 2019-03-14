(ns json-to-sql.update
  (:require [json-to-sql.sql-util :as sql-util]
            [clojure.string :as s]))

(def template "UPDATE {table_name} SET {column_values} {conditions}")

(defn get-column-values
  "Gets string of column names and values joined with , ('a = 1, b = 2 , ...')"
  [map]
  (for [seq-map (seq map)]
    seq-map))

(defn statement
  "Gets UPDATE SQL statement for table name with conditions"
  [table-name conditions map]
  (let [values (s/join ", " (into [] (s/join " = " (get-column-values map))))]
    (sql-util/map->sql
      (sql-util/map->sql
        (sql-util/map->sql template (sql-util/placeholders :column_values) values)
        (sql-util/placeholders :conditions) (sql-util/seq-of-map->conditions conditions))
      (sql-util/placeholders :table_name) table-name)))


