(ns phone-number
  (:require [clojure.string :as string]))

(def phone-pattern #"^1??\D*([2-9]\d\d)\D*([2-9]\d\d)\D*(\d\d\d\d)$")

(def phone-match (partial re-find phone-pattern))

(defn number
  [num-string]
  (if-let [phonev (phone-match num-string)]
    (-> phonev
        (subvec 1)
        string/join)
    "0000000000"))

(defn area-code
  [num-string]
  (if-let [phonev (phone-match num-string)]
    (phonev 1)
    "000"))

(defn pretty-print
  [num-string]
  (let [phonev (drop 1 (phone-match num-string))]
    (apply format "(%s) %s-%s" phonev)))