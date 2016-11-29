(ns permutation.bsgs
  (:use [permutation.kernel :only [multiply inverse identity-for identity?]]))

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

(defn- moved-by
  "returns a point moved by at least one of the generators"
  [generators]
  (let [generator (first generators)
        points (keys generator)
        moved? #(not (= % (generator %)))
        moved (filter moved? points)]
    (first moved)))

(defn add-candidates
  "adds candidate pairs of points and elements to respective collections"
  [candidates orbit transversal]
  (loop [candidates candidates
         orbit orbit
         transversal transversal]
    (if (empty? candidates)
      [orbit transversal]
      (let [[point element] (first candidates)]
        (if (some #{point} orbit)
          (recur (rest candidates) orbit transversal)
          (recur (rest candidates) (conj orbit point) (conj transversal element)))))))

(defn level
  "Determines a level of a base strong generator set, given generators"
  [generators]
  (let [base (moved-by generators)]
    (loop [index 0
           orbit [base]
           transversal [(identity-for (first generators))]]
      (if (>= index (count orbit))
        {:base base
          :generators generators
          :orbit orbit
          :transversal transversal}
        (let [point (nth orbit index)
              current (nth transversal index)
              candidates (map #(cons (% point) [(multiply % current)]) generators)
              [next-orbit next-transversal] (add-candidates candidates orbit transversal)]
          (recur (inc index) next-orbit next-transversal))))))

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

            (recur (multiply (inverse t) current) (inc level-index)))
          current))
      current)))

(defn solvable?
  "determines if the element is solvable in the group defined by the BSGS"
  [bsgs g]
  (identity? (sift bsgs g)))
