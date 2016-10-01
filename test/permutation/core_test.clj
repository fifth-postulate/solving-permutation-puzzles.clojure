(ns permutation.core-test
  (:require [clojure.test :refer :all]
            [permutation.core :refer :all]))

(deftest multiply-test
  (testing "should multiply transpositions"
    (is (= (multiply {0 0, 1 2, 2 1} {0 1, 1 0, 2, 2}) {0 2, 1 0, 2 1}))
    (is (= (multiply {0 0, 1 2, 2 1} {0 0, 1 2, 2, 1}) {0 0, 1 1, 2 2}))))

(deftest inverse-test
  (testing "should determine inverses"
    (is (= (inverse {0 0, 1 2, 2 1}) {0 0, 1 2, 2 1}))
    (is (= (inverse {0 1, 1 2, 2 3, 3 0}) {0 3, 1 0, 2 1, 3 2}))))

(deftest identity-test
  (testing "should determine identity"
    (is (= (identity-for {0 0, 1 2, 2 1}) {0 0, 1 1, 2 2}))
    (is (= (identity-for {0 1, 1 0}) {0 0, 1 1}))))
