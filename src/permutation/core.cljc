(ns permutation.core
  (:use [clojure.set :only [difference]]))

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

(defn- cycle-of
  "determines the cycle of an element in a given permutation"
  [g x]
  (loop [cycle []
         element x]
    (if (some #{element} cycle)
      cycle
      (recur (conj cycle element)
             (g element)))))

(defn cycles
  "determines the cycle of a permutation"
  [g]
  (loop [cs []
         visit (keys g)]
    (if (empty? visit)
      cs
      (let [element (apply min visit)
            current (conj cs (cycle-of g element))
            visited (reduce into #{} current)
            rest (into [] (difference (set visit) visited))]
        (recur current rest)))))
