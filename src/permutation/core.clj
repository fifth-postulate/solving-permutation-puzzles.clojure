(ns permutation.core
  (:gen-class)
  (:use [permutation.kernel :only [multiply cycle-notation]]))

(defn -main
  "Print the product of two permutations to standard out"
  [& args]
  (let [g {0 1, 1 0, 2 2}
        h {0 0, 1 2, 2 1}]
    (println (cycle-notation (multiply g h)))))

