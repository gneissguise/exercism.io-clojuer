(ns beer-song)

(defn pluralize 
  "Makes a string plural based on a passes in count"
  [count s]
  (if-not (= count 1) (str s "s") (str s))
)

(defn num-bottles
  "Returns amount of bottles, singular when needed"
  [num]
  (str
    num " " (pluralize num "bottle")
  )
)

(defn verse-range
  "Allows passing in the high value of the range for the final verse"
  [num hi]
  (if (= num 0)
    (str 
      "No more bottles of beer on the wall, no more bottles of beer.\n"
      "Go to the store and buy some more, " (num-bottles 99) " of beer on the wall.\n")
    (str 
      (num-bottles num) " of beer on the wall, " (num-bottles num) " of beer.\n"
      (if (= num 1)
        "Take it down and pass it around, no more bottles of beer on the wall.\n"
        (str "Take one down and pass it around, " (num-bottles (dec num)) " of beer on the wall.\n")
      )
    )
  ) 
)

(defn verse
  "Returns the nth verse of the song."
  [num] 
  (verse-range num 99)
)

(defn process-song
  "Takes the starting and the ending values and loops through the verses of beer-song"
  [hi lo]
  (loop [i hi result ""]
    (if (= i lo)
      (str result (verse-range i hi))
      (recur (dec i) (str result (verse-range i hi) "\n"))
    )    
  )
)

(defn sing
  "Given a start and an optional end, returns all verses in this interval. If
  end is not given, the whole song from start is sung."
  ([start] (process-song start 0))
  ([start end] (process-song start end))
)