(ns bob
  (:require [clojure.string :as str]))

(defn capitalize-word
  [s]
  (str/replace s #"[a-z]" str/capitalize))

(defn is-alpha?
  [s]
  (re-find #"[A-Za-z]+" s))

(defn is-a-question?
  [s]
  (str/ends-with? s "?"))

(defn is-yelling?
  [s]
  (and
   (is-alpha? s)
   (= (capitalize-word s) s)))

(defn is-a-yelling-question?
  [s]
  (and
   (is-yelling? s)
   (is-a-question? s)))

(defn is-saying-nothing?
  [s]
  (str/blank? s))

(defn response-for [s]
  (let [processed-str (str/trim-newline (str/trimr s))]
    (cond
      (is-a-yelling-question? processed-str) "Calm down, I know what I'm doing!"
      (is-a-question? processed-str) "Sure."
      (is-yelling? processed-str) "Whoa, chill out!"
      (is-saying-nothing? processed-str) "Fine. Be that way!"
      :else "Whatever.")))