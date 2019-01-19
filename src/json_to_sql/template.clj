(ns json-to-sql.template)

(def insert-query-template
  "INSERT INTO {table_name} ({column_names}) VALUES ({column_values})")

(def update-query-template
  "UPDATE {table_name} SET {update_set} WHERE {conditions}" )

(def statements
  {:insert insert-query-template
   :update update-query-template})
(def placeholders
  {:table_name "{table_name}"
   :column_names "{column_names}"
   :column_values "{column_values}"
   :update-set "{update_set}"
   :conditions "{conditions}"})
