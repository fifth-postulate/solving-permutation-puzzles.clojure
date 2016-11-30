(ns permutation.naive
  (:use [permutation.kernel :only [multiply inverse identity-for]]
        [clojure.set :only [union difference]]))

(defn elements-generated-by
  "produces all elements generated by generators"
  ([] [])
  ([& generators]
   (let [gens (flatten (map #(vec [% (inverse %)]) generators))]
     (loop [elements []
            visit [(identity-for (first gens))]]
       (if (empty? visit)
         elements
         (let [current (first visit)
               next-elements (conj elements current)
               neighbours (map #(multiply % current) gens)
               candidates (union (set (rest visit)) neighbours)
               next-visit (vec (difference (set candidates) (set next-elements)))]
           (recur next-elements next-visit)))))))

(defn solvable?
  "determines if a permutation is generated by the generators"
  [g & generators]
  (some #{g} (apply elements-generated-by generators)))
