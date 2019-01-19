(ns json-to-sql.converter
  (:require [json-to-sql.template :as template]
            [json-to-sql.util :as util]))

(defn get-statement-template
  "Gets SQL statement based on operation"
  [operation]
  (case operation
    "insert" (:insert template/statements)
    "update" (:update template/statements)))

(defn get-placeholder
  "Gets placeholder value from template"
  [key]
  (key template/placeholders))
