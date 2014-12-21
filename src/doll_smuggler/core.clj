(ns doll-smuggler.core
  (:require [clojure.string :as str]
            [clojure.math.combinatorics :as combo])
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

(defn find-optimal-packing
 [contents]
 (let [{max-weight :max-weight dolls :dolls} (parse-input contents)
       possible-packings (combo/subsets dolls)
       light-enough (filter #(<= (apply + (map :weight %)) max-weight) possible-packings)
       highest-value (first (reverse (sort-by #(apply + (map :value %)) light-enough)))]
    highest-value))

(defn optimal-packing-formatted
  [contents]
  (let [dolls (find-optimal-packing contents)
        formatted-dolls (map #(str (:name %1) " " (:weight %1) " " (:value %1)) dolls)]
    (str "packed dolls:\n"
         "\n"
         "name   weight   value\n"
         (str/join "\n" formatted-dolls)
         "\n")))

(defn -main
  [& args]
  nil)
