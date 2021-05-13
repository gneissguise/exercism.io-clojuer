(ns nucleotide-count
  (:require [clojure.string :as string]))

(def nucl [\A \C \G \T])
(defn count-of-nucleotide-in-strand
  [nucleotide strand]
  (if-not (re-find 
           (re-pattern (str "[^" (string/join "" nucl) "]"))
           (str nucleotide))
    (-> (str nucleotide)
        re-pattern
        (re-seq strand)
        count)
    (throw (Throwable. "Invalid Nucleotide."))))

(defn nucleotide-counts
  [strand]
  (let [nucl-count #(count-of-nucleotide-in-strand % strand)]
    (->> nucl
         (map nucl-count)
         (zipmap nucl))))