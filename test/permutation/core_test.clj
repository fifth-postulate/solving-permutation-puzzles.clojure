(ns permutation.core-test
  (:require [clojure.test :refer :all]
            [permutation.core :refer :all]))

(deftest swap-test
  (testing "should swap her arguments"
    (is (= (swap 0 1) [1 0]))))
