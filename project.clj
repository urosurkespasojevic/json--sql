(defproject json-to-sql "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [cheshire "5.8.0"]]
  :profiles {:dev {:dependencies [[midje/midje "1.9.1"]]
                   :plugins [[lein-midje "3.1.3-RC2"]]}}
  :main json-to-sql.core)
