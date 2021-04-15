(ns bob
  (:require [clojure.string :as str]))

(defn alpha?
  [s]
  (re-find #"[A-Za-z]+" s))

(defn question?
  [s]
  (str/ends-with? s "?"))

(defn yelling?
  [s]
  (and
   (alpha? s)
   (= (str/upper-case s) s)))

(defn yelling-question?
  [s]
  (and
   (yelling? s)
   (question? s)))

(defn response-for [s]
  (let [processed-str (str/trim-newline (str/trimr s))]
    (cond
      (yelling-question? processed-str) "Calm down, I know what I'm doing!"
      (question? processed-str) "Sure."
      (yelling? processed-str) "Whoa, chill out!"
      (str/blank? processed-str) "Fine. Be that way!"
      :else "Whatever.")))