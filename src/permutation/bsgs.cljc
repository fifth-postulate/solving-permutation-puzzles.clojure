(ns permutation.bsgs
  (:use [permutation.kernel :only [multiply inverse identity-for identity?]]))

(defn- moved-by
  "returns a point moved by at least one of the generators"
  [generators]
  (let [generator (first generators)
        points (keys generator)
        moved? #(not (= % (generator %)))
        moved (filter moved? points)]
    (first moved)))

(defn- add-candidates
  "adds candidate pairs of points and elements to respective collections"
  [candidates orbit transversal next-generators]
  (loop [candidates candidates
         orbit orbit
         transversal transversal
         next-generators next-generators]
    (if (empty? candidates)
      [orbit transversal next-generators]
      (let [[point element] (first candidates)]
        (if (some #{point} orbit)
          (let [index (first (keep-indexed #(if (= %2 point) %1 nil) orbit))
                t (nth transversal index)
                generator (multiply (inverse t) element)]
            (recur (rest candidates) orbit transversal (conj next-generators generator)))
          (recur (rest candidates) (conj orbit point) (conj transversal element) next-generators))))))

(defn level
  "Determines a level of a base strong generator set, given generators"
  [generators]
  (let [base (moved-by generators)]
    (loop [index 0
           orbit [base]
           transversal [(identity-for (first generators))]
           next-generators []]
      (if (>= index (count orbit))
        [{:base base
          :generators generators
          :orbit orbit
          :transversal transversal}, next-generators]
        (let [point (nth orbit index)
              current (nth transversal index)
              candidates (map #(cons (% point) [(multiply % current)]) generators)
              [next-orbit next-transversal next-generators] (add-candidates candidates orbit transversal next-generators)]
          (recur (inc index) next-orbit next-transversal (filter #(not (identity? %)) next-generators)))))))

(defn bsgs-for
  "Determines a base strong generator set for the given generators"
  [generators]
  (loop [bsgs []
         generators generators]
    (if (empty? generators)
      bsgs
      (let [[l gs] (level generators)]
        (recur (conj bsgs l) gs)))))

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
