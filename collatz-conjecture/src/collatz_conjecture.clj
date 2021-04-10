(ns collatz-conjecture)

(defn collatz
  "Returns the amount of steps for a collatz number to be equal to 1"
  [num]
  (if (> num 0)
    (loop [x num steps 0]
      (if (= x 1)
        steps
        (recur (if (even? x)
                 (/ x 2)
                 (inc (* x 3)))
               (inc steps))))
    (throw (Exception. "Number must be greater than 0"))))
