(ns clock)

(defn clock->string
  [clock]
  (format "%02d:%02d" (first clock) (second clock)))

(defn clock
  [hours minutes]
  (let [t-unit (mod (+ (* 60 hours) minutes) 1440)
        mins (mod t-unit 60)
        hrs (quot t-unit 60)]
    (list hrs mins)))

(defn add-time
  [clock time]
  (let [hrs (first clock)
        mins (second clock)]
    (clock/clock hrs (+ mins time))))