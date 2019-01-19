(ns json-to-sql.util
  (:require [clojure.string :as s]))

(defn map->sql
  "Formats string replacing placeholder with value value"
  [text placeholder value]
  (s/replace text placeholder value))

(defn map-value->sql-value
  "Converts value of map to SQL value"
  [value]
  (if (string? value)
    (str "'" value "'")
    value))

(defn map->seq-columns
  "Converts map keywords to column names"
  [raw-map]
  (let [map (into {} raw-map)]
    (for [key (keys map)]
      (name key))))

(defn map->seq-values
  "Converts map values to vector of values"
  [raw-map]
  (let [map (into {} raw-map)]
    (for [val (vals map)]
      (map-value->sql-value val))))
