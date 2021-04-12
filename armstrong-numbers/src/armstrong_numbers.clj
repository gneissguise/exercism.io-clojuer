(ns armstrong-numbers
  (:require [clojure.string :as string]))

(defn **
  "Exponent operator (** 2 2) -> 2 ^ 2 -> 4"
  [x n]
  (reduce * (repeat n x)))

(defn parse-ints
  "Maps a coll of strs to their respective int form"
  [coll]
  (map #(Integer/parseInt %) coll))

(defn parse-armstrong
  "Converts num->str, splits by char, converts each char to int, 
   maps the exponent value and adds total via reduce. The result 
   is an Armstrong Number."
  [n]
  (let [s (str n)
        ints (parse-ints (string/split s #""))
        len (count ints)
        exp #(** % len)]
    (reduce + (map exp ints))))

(defn armstrong?
  "Tests to see if a number is an Armstrong number"
  [num]
  (= (parse-armstrong num) num))
