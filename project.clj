(defproject doll-smuggler "0.1.0-SNAPSHOT"
  :description "Atomic Object programming challenge"
  :url "https://github.com/dbravender/doll-smuggler"
  :dependencies [[org.clojure/clojure "1.6.0"]]
  :main ^:skip-aot doll-smuggler.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
