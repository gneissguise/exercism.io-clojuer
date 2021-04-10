(ns reverse-string
  (:require [clojure.string :as string]
            [taoensso.tufte :as tufte]))

(tufte/add-basic-println-handler!
 {:format-pstats-opts {:columns [:n-calls :p50 :mean :clock :total]
                       :format-id-fn name}})
(defn reverse-string
  "Takes a string, reduces into a list via conj, then appies the result into a string."
  [s]
  (apply str (reduce conj () s)))

(defn reverse-string-thr
  "Thread last the split s, reduce into a list using conj, then apply str to the list."
  [s]
  (->> (string/split s #"")
   (reduce conj ())
   (apply str)))

(comment
  (require '[taoensso.tufte :as tufte :refer (defnp p profiled profile)])

;; We'll request to send `profile` stats to `println`:
(tufte/add-basic-println-handler! {})

;;; Let's define a couple dummy fns to simulate doing some expensive work
(defn get-x [] (reverse-string "Justin")             "x val")
(defn get-y [] (reverse-string-thr "Justin") "y val")

;; How do these fns perform? Let's check:

(profile ; Profile any `p` forms called during body execution
 {} ; Profiling options; we'll use the defaults for now
 (dotimes [_ 50000]
   (p :get-x (get-x))
   (p :get-y (get-y))))

;; The following will be printed to *out*:
;;
;;       pId  nCalls       Min     50% ≤     90% ≤     95% ≤     99% ≤       Max      Mean  MAD  Clock Total
;;
;;    :get-y       5   94.01ms  500.99ms  910.14ms  910.14ms  910.14ms  910.14ms  580.49ms ±45%  2.90s   53%
;;    :get-x       5  503.05ms  504.68ms  504.86ms  504.86ms  504.86ms  504.86ms  504.37ms  ±0%  2.52s   46%
;;
;; Accounted                                                                                     5.42s  100%
;;     Clock                                                                                     5.43s  100%
)