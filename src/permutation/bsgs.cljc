(ns permutation.bsgs
  (:use [permutation.kernel :only [multiply inverse identity?]]))

(def s3 [{:base 0,
          :generators [{0 1, 1 2, 2 0} {0 1, 1 0, 2 2}],
          :orbit [0 1 2],
          :transversal [{0 0, 1 1, 2 2} {0 1, 1 0, 2 2} {0 2, 1 0, 2 1}]}
         {:base 1,
          :generators [{0 0, 1 2, 2 1}]
          :orbit [1 2]
          :transversal [{0 0, 1 1, 2 2} {0 0, 1 2, 2 1}]}])

(def c3 [{:base 0,
          :generators [{0 1, 1 2, 2 0}],
          :orbit [0 1 2],
          :transversal [{0 0, 1 1, 2 2} {0 1, 1 2, 2 0} {0 2, 1 0, 2 1}]}])

(defn sift
  "Sifts the element through the base strong generator set"
  [bsgs g]
  (loop [current g
         level-index 0]
    (if (< level-index (count bsgs))
      (let [level (nth bsgs level-index)
            base (level :base)
            image (current base)
            orbit (level :orbit)
            finder (fn [i point] (if (= image point) i nil))
            indices (keep-indexed finder orbit)]
        (if-let [index (first indices)]
          (let [transversal (level :transversal)
                t (nth transversal index)]
            (recur (multiply (inverse t) current) (+ level-index 1)))
          current))
      current)))
