(ns rna-transcription
  (:require [clojure.string :as string]))

(defn to-rna [dna]
  (if (not (re-find #"[^GCTA]" dna))
   (string/replace dna #"G|C|T|A" {"G" "C"
                                   "C" "G"
                                   "T" "A"
                                   "A" "U"})
    (throw (AssertionError. "Invalid DNA sequence."))))
