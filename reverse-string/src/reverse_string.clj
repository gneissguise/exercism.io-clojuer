(ns reverse-string
  (:require [clojure.string :as string]))

(defn reverse-string
  [s]
  (string/join (into '() s)))
