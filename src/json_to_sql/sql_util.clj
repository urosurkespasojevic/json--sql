(ns json-to-sql.sql-util
  (:require [clojure.string :as s]))

(def operations
  {"eq" "=",
   "not_eq" "<>",
   "like" "LIKE",
   "gt" ">",
   "gt_eq" ">=",
   "lt" "<",
   "lt_eq" "<="})

(def placeholders
  {:table_name "{table_name}"
   :column_names "{column_names}"
   :column_values "{column_values}"
   :conditions "{conditions}"})

(defn map->sql
  "Formats string replacing placeholder with value value"
  [text placeholder value]
  ;(println text)
  ;(println placeholder)
  ;(println value)
  (s/replace text placeholder value))

(defn map-value->sql-value
  "Converts value of map to SQL value"
  [value]
  (if (string? value)
    (str "'" value "'")
    (if (instance? Boolean value)
      (if value
        1
        0)
      value)))

(defn map->seq-columns
  "Converts map keywords to column names"
  [raw-map]
  (let [map (into {} raw-map)]
    (for [key (keys map)]
      (name key))))

(defn map->seq-values
  "Converts map values to values"
  [raw-map]
  (let [map (into {} raw-map)]
    (for [val (vals map)]
      (map-value->sql-value val))))

(defn seq-of-map->conditions
  "Converts map to conditions"
  [seq-of-maps]
  (seq (for [condition-map seq-of-maps]
         (str (:column condition-map) " " (get operations (:operation condition-map)) " " (map-value->sql-value (:value condition-map))))))

