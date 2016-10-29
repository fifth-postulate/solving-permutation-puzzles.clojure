(ns permutation.kernel
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
  "determines the cycles of a permutation"
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

(defn cycle-notation
  "determines the cycle notation of a permutation"
  ;; TODO idiomatic way of determining size of list
  ;; TODO idiomatic way of joining strings
  [g]
  (let [cs (cycles g)
        ds (filter (fn [x] (> (.size x) 1)) cs)]
    (if (= (.size ds) 0)
      "Id"
      (let [es (map seq ds)
            fs (map str es)
            b (StringBuilder.)
            bc (reduce (fn [acc v] (doto acc (.append v))) b fs)]
        (str bc)))))
