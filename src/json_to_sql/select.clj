(ns json-to-sql.select
  (:require [json-to-sql.sql-util :as sql-util]
            [clojure.string :as s]))

(def template "SELECT {column_names} FROM {table_name} WHERE {conditions}")

(defn get-column-names
  "Gets string of column names joined with ,"
  [map]
  (let [seq-columns (into [] (sql-util/map->seq-columns map))]
    (s/join ", " seq-columns)))

(defn statement
  "Gets SELECT SQL query for table name with conditions"
  [table-name conditions map]
  (let [names (get-column-names map)]
    (sql-util/map->sql
      (sql-util/map->sql
        (sql-util/map->sql template (sql-util/placeholders :conditions) (s/join " AND " (sql-util/seq-of-map->conditions conditions)))
        (sql-util/placeholders :column_names) names)
      (sql-util/placeholders :table_name) table-name)))
