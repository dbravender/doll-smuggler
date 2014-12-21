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

(defn find-optimal-packing-naive-inefficient-used-to-build-base-test-cases
 [{max-weight :max-weight dolls :dolls}]
 (let [possible-packings (combo/subsets dolls)
       light-enough (filter #(<= (apply + (map :weight %)) max-weight) possible-packings)
       highest-value (first (reverse (sort-by #(apply + (map :value %)) light-enough)))
       highest-value-weight (apply + (map :weight highest-value))]
    [highest-value-weight highest-value]))

(def recursive-optimal-packing
  (memoize
   (fn
     ([{dolls :dolls max-weight :max-weight}] (recursive-optimal-packing dolls (dec (count dolls)) max-weight []))
     ([dolls item-count max-weight history]
      (if (or (<= max-weight 0) (< item-count 0)) [0 history]
        (let [{weight :weight value :value :as doll} (nth dolls item-count)
              [previous-value previous-history] (recursive-optimal-packing dolls (dec item-count) max-weight history)
              [this-doll-added-value this-doll-added-history] (recursive-optimal-packing dolls (dec item-count) (- max-weight weight) history)]
          (if (> weight max-weight) [previous-value previous-history]
            (if (> previous-value (+ this-doll-added-value value))
              [previous-value previous-history]
              [(+ this-doll-added-value value) (conj this-doll-added-history doll)]))))))))

(defn optimal-packing-formatted
  [dolls-and-max-weight]
  (let [[max-weight dolls] (recursive-optimal-packing dolls-and-max-weight)
        name-width (+ 1 (apply max (map count (into ["name"] (map #(:name %) dolls)))))
        weight-width (+ 1 (apply max (map count (into ["weight"] (map #(str (:weight %)) dolls)))))
        value-width (+ 1 (apply max (map count (into ["value"] (map #(str (:value %)) dolls)))))
        column-formatter (fn [name weight value] (format (str "%-" name-width "s %" weight-width "s %" value-width "s\n") name weight value))
        formatted-dolls (map #(apply column-formatter (map % [:name :weight :value])) (reverse dolls))]
    (str "packed dolls:\n"
         "\n"
         (column-formatter "name" "weight" "value")
         (str/join "" formatted-dolls))))

(defn -main
  [& args]
  (let [in (if (nil? args) *in* (nth args 0))]
    (print (-> in slurp parse-input optimal-packing-formatted))))
