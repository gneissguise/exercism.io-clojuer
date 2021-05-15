(ns grade-school)

(defn grade
  [school grade]
  (into [] (get school grade)))

(defn add
  [school name grade]
  (update school grade (fn [n] (conj(vec n) name))))

(defn sorted
  [school]
  (into (sorted-map)
        (map (fn [[k v]] [k (vec (sort v))]))
        school))
