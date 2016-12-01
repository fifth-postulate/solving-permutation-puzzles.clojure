(ns permutation.solve-test
  (:require  [permutation.solve :refer :all]
             [clojure.test :refer :all]))

(deftest solve-test
  (testing "should solve states in puzzles")
  (let [t {0 1, 1 2, 2 0}
        s {0 1, 1 0, 2 2}
        sol #(solve [t s] ["t" "s"] %)]
    (is (= (sol {0 0, 1 1, 2 2}) []))
    (is (= (sol s) [["s", -1]]))
    (is (= (sol t) [["t", -1]]))
    (is (= (sol {0 2, 1 1, 2 0}) [["s", -1] ["t", -1]]))))
