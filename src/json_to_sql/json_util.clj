(ns json-to-sql.json-util
  (:require [cheshire.core :as cheshire]))

(defn json->map
  "Converts json from provided file path to map"
  [file-path]
  (cheshire/parse-string (slurp file-path) true))

(defn get-table-name-keyword
  "Gets table name from provided map"
  [map]
  (first
    (filter #(not= % :conditions) (keys map))))

(defn get-conditions-keyword
  "Gets conditions map from provided map"
  [map]
  (first
    (filter #(= % :conditions) (keys map))))
