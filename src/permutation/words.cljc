(ns permutation.words)

(defn normalize
  "normalizes a word in the generators"
  [word]
  (loop [w []
         current (first word)
         index 1]
    (if (>= index (count word))
      (if (and current (not (zero? (second current))))
        (conj w current)
        w)
      (let [element (nth word index)
            current-symbol (first current)
            element-symbol (first element)]
        (if (= current-symbol element-symbol)
          (let [i (second current)
                j (second element)
                sum (+ i j)]
            (if (= sum 0)
              (recur (vec (take (dec (count w)) w)) (last w) (inc index))
              (recur w [current-symbol sum] (inc index))))
          (recur (conj w current) element (inc index)))))))

(defn multiply
  "multiply two words to produce a third"
  [v w]
  (normalize (vec (concat v w))))

(defn inverse
  "determines the inverse word for a given word"
  [w]
  (let [negate #(vec [(first %) (- (second %))])]
    (reverse (map negate w))))

(defn identity-for
  "return the identity word for a given word"
  [w]
  [])

(defn identity?
  "determines if a word is the identity"
  [w]
  (empty? w))
