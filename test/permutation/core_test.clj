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

(deftest cycles-test
  (testing "should correctly return all cycles"
    (is (= (cycles {0 0}) [[0]]))
    (is (= (cycles {0 0, 1 1}) [[0] [1]]))
    (is (= (cycles {0 0, 1 1, 2 2}) [[0] [1] [2]]))
    (is (= (cycles {0 1, 1 0}) [[0 1]]))
    (is (= (cycles {0 1, 1 0, 2 2}) [[0 1] [2]]))
    (is (= (cycles {0 1, 1 2, 2 0}) [[0 1 2]]))
    (is (= (cycles {0 1, 1 0, 2 3, 3 2}) [[0 1] [2 3]]))))

(deftest cycle-notation-test
  (testing "should determine the cycle notation of a permutation"
    (is (= (cycle-notation {0 0, 1 1, 2 2}) "Id"))
    (is (= (cycle-notation {0 1, 1 0, 2 2}) "(0 1)"))
    (is (= (cycle-notation {0 0, 1 2, 2 1}) "(1 2)"))
    (is (= (cycle-notation {0 1, 1 2, 2 0}) "(0 1 2)"))
    (is (= (cycle-notation {0 1, 1 2, 2 0, 3 3}) "(0 1 2)"))
    (is (= (cycle-notation {0 1, 1 0, 2 3, 3 2}) "(0 1)(2 3)"))))
