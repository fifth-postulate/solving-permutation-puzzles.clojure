(ns permutation.core-test
  (:require [clojure.test :refer :all]
            [permutation.core :refer :all]))

(deftest multiply-test
  (testing "should multiply transpositions"
    (is (= (multiply {0 0, 1 2, 2 1} {0 1, 1 0, 2, 2}) {0 2, 1, 0, 2 1}))))
