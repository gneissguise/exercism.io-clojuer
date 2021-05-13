(ns hamming
  (:require [clojure.string :as string]))

(defn distance
  [strand1 strand2]
  (when (= (count strand1)
           (count strand2))
    (let [s1 (string/split strand1 #"")
          s2 (string/split strand2 #"")]
      (->> (map = s1 s2)
           (filter false?)
           count))))