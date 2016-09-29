(ns permutation.core)

(defn multiply
  "multiply two permutations to produce a third"
  [g h]
  (let [tuple (fn [x] [x (g (h x))])
        combine (fn [acc t] (assoc acc (t 0) (t 1)))]
    (reduce combine {} (map tuple (keys g)))))
