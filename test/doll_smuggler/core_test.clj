(ns doll-smuggler.core-test
  (:require [clojure.test :refer :all]
            [doll-smuggler.core :refer :all]))

(def small-input
  (str "max weight: 100\n"
       "\n"
       "available dolls:\n"
       "\n"
       "name    weight  value\n"
       "bob      100      10\n"
       "fred      50      20\n"))

(def large-input
  (str "max weight: 400\n"
       "\n"
       "available dolls:\n"
       "\n"
       "name    weight value\n"
       "luke        9   150\n"
       "anthony    13    35\n"
       "candice   153   200\n"
       "dorothy    50   160\n"
       "puppy      15    60\n"
       "thomas     68    45\n"
       "randal     27    60\n"
       "april      39    40\n"))

(deftest parse-a-small-valid-input-file
 (testing "with a small valid input file"
    (is (= {:max-weight 100
            :dolls [{:name "bob" :weight 100 :value 10}
                    {:name "fred" :weight 50 :value 20}]}
           (parse-input small-input)))))

(deftest parse-a-larger-valid-input-file
  (testing "with a large valid input file")
    (is (= {:max-weight 400
            :dolls [{:name "luke" :weight 9 :value 150}
                    {:name "anthony" :weight 13 :value 35}
                    {:name "candice" :weight 153 :value 200}
                    {:name "dorothy" :weight 50 :value 160}
                    {:name "puppy" :weight 15 :value 60}
                    {:name "thomas" :weight 68 :value 45}
                    {:name "randal" :weight 27 :value 60}
                    {:name "april" :weight 39 :value 40}]}
        (parse-input large-input))))
