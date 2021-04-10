(ns armstrong-numbers)
(require '[clojure.string :as string])

;;; Note: this could be simplified and contained in fewer functions,
;;; but I am fairly new to clojure and I like to break things out!

(defn num-split
  "Takes a number, and converts it to a collection of str digits.  1234 -> ['1' '2' '3' '4']"
  [num] 
  (string/split (str num) #"(?=[0-9])")
)

(defn parse-int
  "Converts from string to int using the java Integer class.  '1' -> 1"
  [s]
  (Integer/parseInt (re-find #"\A-?\d+" s))
)

(defn **
  "Exponent operator (** 2 2) -> 2 ^ 2 -> 4"
  [x n]
  (reduce * (repeat n x))
)

(defn int-coll
  "Takes a str collection and converts it to an int collection"
  [coll]
  (map parse-int coll)
)

(defn map-exp
  "Takes a number, breaks each digit into an individual item in a collection, applies an exponent of the number of digits, then adds each result together creating an Armstrong number"
  [num]
  (->> num
    (num-split)
    (int-coll)
    (map (fn [i] (** i (count (str num)))))
    (reduce +)
  )
)

(defn armstrong? 
  "Tests to see if a number is an Armstrong number"
  [num]   
  (= num (map-exp num))
)
