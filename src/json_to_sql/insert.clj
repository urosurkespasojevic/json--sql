(ns json-to-sql.insert
  (:require [json-to-sql.sql-util :as sql-util]
            [clojure.string :as s]))

(def template "INSERT INTO {table_name} ({column_names}) VALUES ({column_values})")

(defn get-column-values
  "Gets string of column values joined with ,"
  [map]
  (let [seq-values (into [] (sql-util/map->seq-values map))]
    (s/join ", " seq-values)))

(defn statement
  "Gets INSERT SQL statement for table name"
  [table-name map]
  (let [names (sql-util/get-column-names map)
        values (get-column-values map)]
    (sql-util/map->sql
      (sql-util/map->sql
        (sql-util/map->sql template (sql-util/placeholders :column_values) values)
        (sql-util/placeholders :column_names) names)
      (sql-util/placeholders :table_name) table-name)))
