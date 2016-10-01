(ns permutation.core)

(defn- create-permutation
  "create a permutation by forming tuples over a G-set"
  [tuple gset]
  (let [combine (fn [acc t] (assoc acc (t 0) (t 1)))]
    (reduce combine {} (map tuple gset))))

(defn multiply
  "multiply two permutations to produce a third"
  [g h]
  (create-permutation (fn [x] [x (g (h x))]) (keys g)))

(defn inverse
  "determine the inverse of a permutation"
  [g]
  (create-permutation (fn [x] [(g x) x]) (keys g)))

(defn identity-for
  "return the identity permutation on the same G-set"
  [g]
  (create-permutation (fn [x] [x x]) (keys g)))
