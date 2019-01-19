(ns json-to-sql.util
  (:require [clojure.string :as s]))

(defn get-column-names
  "Gets string of column names joined with ,"
  [row-map]
  (let [map (into {} row-map)
        keys (keys map)
        mapped-keys (into [] (map name keys))]
    (s/join ", " mapped-keys)))


(defn get-column-values
  "Gets string of column values joined with ,"
  [map]
  (s/join ", " (seq (vals map))))

(defn map->csv
  "Formats string replacing placeholder with value value"
  [text placeholder value]
  (s/replace text placeholder value))
