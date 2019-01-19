(ns json-to-sql.builder
  (:require [json-to-sql.util :as util]
            [json-to-sql.converter :as converter]
            [clojure.string :as s]))


(defn build-insert-statement
  "Gets INSERT SQL statement for table name"
  [template table-name map]
  (let [names (util/get-column-names map)
        values (util/get-column-values map)]
    (println (str names ""))
    (util/map->csv
      (util/map->csv
        (util/map->csv template (converter/get-placeholder :column_values) values)
        (converter/get-placeholder :column_names) names)
      (converter/get-placeholder :table_name) table-name)))

(defn build-statement
  [operation table-name map]
  "Builds SQL statements depends of value of :operation"
  (case operation
    "insert" (build-insert-statement (converter/get-statement-template operation) table-name map)))

