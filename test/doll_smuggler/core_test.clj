(ns doll-smuggler.core-test
  (:require [clojure.test :refer :all]
            [doll-smuggler.core :refer :all]))

(def one-doll-input
  (str "max weight: 10\n"
       "\n"
       "available dolls:\n"
       "\n"
       "name   weight   value\n"
       "onlyone  10       10\n"))

(def one-doll-too-big-input
   (str "max weight: 10\n"
        "\n"
        "available dolls:\n"
        "\n"
        "name   weight   value\n"
        "toobig   100      10\n"))

(def two-dolls-input
  (str "max weight: 100\n"
       "\n"
       "available dolls:\n"
       "\n"
       "name    weight  value\n"
       "bob      100      10\n"
       "fred      50      20\n"))

(def many-dolls-input
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
       "april      39    40\n"
       "nancy      23    30\n"
       "bonnie     52    10\n"
       "marc       11    70\n"
       "kate       32    30\n"
       "tbone      24    15\n"
       "tranny     48    10\n"
       "uma        73    40\n"
       "grumpkin   42    70\n"
       "dusty      43    75\n"
       "grumpy     22    80\n"
       "eddie       7    20\n"
       "tory       18    12\n"
       "sally       4    50\n"
       "babe       30    10\n"))

(deftest parse-a-small-valid-input-file
 (testing "with a small valid input file"
    (is (= {:max-weight 100
            :dolls [{:name "bob" :weight 100 :value 10}
                    {:name "fred" :weight 50 :value 20}]}
           (parse-input two-dolls-input)))))

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
                    {:name "april" :weight 39 :value 40}
                    {:name "nancy" :weight 23 :value 30}
                    {:name "bonnie" :weight 52 :value 10}
                    {:name "marc" :weight 11 :value 70}
                    {:name "kate" :weight 32 :value 30}
                    {:name "tbone" :weight 24 :value 15}
                    {:name "tranny" :weight 48 :value 10}
                    {:name "uma" :weight 73 :value 40}
                    {:name "grumpkin" :weight 42 :value 70}
                    {:name "dusty" :weight 43 :value 75}
                    {:name "grumpy" :weight 22 :value 80}
                    {:name "eddie" :weight 7 :value 20}
                    {:name "tory" :weight 18 :value 12}
                    {:name "sally" :weight 4 :value 50}
                    {:name "babe" :weight 30 :value 10}]}
        (parse-input many-dolls-input))))

(deftest single-input-answer
  (testing "with a single valid input"
    (is (= [{:name "onlyone" :weight 10 :value 10}]
           (find-optimal-packing one-doll-input)))))

(deftest single-input-non-fit-answer
  (testing "with a single input that does not fit"
    (is (= []
           (find-optimal-packing one-doll-too-big-input)))))

(deftest two-options-answer
  (testing "with small input"
    (is (= [{:name "fred" :weight 50 :value 20}]
           (find-optimal-packing two-dolls-input)))))

(deftest human-readable-output
  (testing "response is written in a human readable form"
    (is (= (str "packed dolls:\n"
                "\n"
                "name   weight   value\n"
                "fred 50 20\n")
           (optimal-packing-formatted two-dolls-input)))))
