(ns json-to-sql.delete
  (:require [json-to-sql.sql-util :as sql-util]
            [clojure.string :as s]))

(def template "DELETE FROM {table_name}{conditions}")

(defn statement
  "Gets DELETE SQL statement for table name with conditions"
  [table-name conditions]
  (sql-util/map->sql
    (sql-util/map->sql
      template (sql-util/placeholders :conditions) (if (empty? conditions)
                                                     (str "")
                                                     (str " WHERE " (s/join " AND " (sql-util/seq-of-map->conditions conditions)))))
    (sql-util/placeholders :table_name) table-name))