(ns json-to-sql.select
  (:require [json-to-sql.sql-util :as sql-util]
            [clojure.string :as s]))

(def template "SELECT {column_names} FROM {table_name}{conditions}")

(defn statement
  "Gets SELECT SQL query for table name with conditions"
  [table-name conditions map]
  (let [names (sql-util/get-column-names map)]
    (sql-util/map->sql
      (sql-util/map->sql
        (sql-util/map->sql template (sql-util/placeholders :conditions)
                           (if (empty? conditions)
                             (str "")
                             (str " WHERE " (s/join " AND " (sql-util/seq-of-map->conditions conditions)))))
        (sql-util/placeholders :column_names) names)
      (sql-util/placeholders :table_name) table-name)))
