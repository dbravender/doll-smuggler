(ns doll-smuggler.core
  (:require [clojure.string :as str])
  (:gen-class))

(defn parse-int [string]
  (Integer/parseInt string))

(defn get-doll
  [line]
  (let [[name weight value] (str/split line #"\s+")]
    {:name name
     :weight (parse-int weight)
     :value (parse-int value)}))

(defn parse-input
  [contents]
  (let [contents-by-line (str/split contents #"\n")
        max-weight (parse-int (second (str/split (first contents-by-line) #":\s+")))
        dolls (map get-doll (subvec contents-by-line 5))]
    {:max-weight max-weight :dolls dolls}))

(defn -main
  [& args]
  nil)
