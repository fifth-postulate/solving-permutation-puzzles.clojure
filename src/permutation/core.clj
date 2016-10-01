(ns permutation.core)

(defn multiply
  "multiply two permutations to produce a third"
  [g h]
  (let [tuple (fn [x] [x (g (h x))])
        combine (fn [acc t] (assoc acc (t 0) (t 1)))]
    (reduce combine {} (map tuple (keys g)))))

(defn inverse
  "determine the inverse of a permutation"
  [g]
  (let [tuple (fn [x] [(g x) x])
        combine (fn [acc t] (assoc acc (t 0) (t 1)))]
    (reduce combine {} (map tuple (keys g)))))

(defn identity-for
  "return the identity permutation on the same G-set"
  [g]
  (let [tuple (fn [x] [x x])
        combine (fn [acc t] (assoc acc (t 0) (t 1)))]
    (reduce combine {} (map tuple (keys g)))))
