(ns series
  (:require [clojure.math.combinatorics :as combo]))

(defn slices 
  "Takes a string, returns all combinations for length, reduces down further to non repeating subsets"
  [string length]
   (->> (combo/combinations string length)
        (reduce (fn [a b]
                  (if-not (= (first (last a)) (first b)) (conj a b) a))
                (if (= length 0) [""] []))
        (mapv #(apply str %))))
