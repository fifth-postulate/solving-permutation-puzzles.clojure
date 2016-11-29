(ns permutation.bsgs-test
  (:require [permutation.bsgs :refer :all]
            [clojure.test :refer :all]))

(deftest level-test
  (testing "creation of level"
    (let [[l gs] (level [{0 1, 1 0}])]
      (is (= l {:base 0, :generators [{0 1, 1 0}], :orbit [0 1], :transversal [{0 0, 1 1} {0 1, 1 0}]}))
      (is (= gs [])))
    (let [[l gs] (level [{0 1, 1 0, 2 2}, {0 0, 1 2, 2 1}])]
      (is (= l {:base 0, :generators [{0 1, 1 0, 2 2} {0 0, 1 2, 2 1}], :orbit [0 1 2], :transversal [{0 0, 1 1, 2 2} {0 1, 1 0, 2 2} {0 2, 1 0, 2 1}]}))
      (is (= gs [{2 1, 0 0, 1 2} {0 0, 1 2, 2 1}])))))

(deftest sift-test
  (testing "should sift elements"
    (let [s3 [{:base 0,
               :generators [{0 1, 1 2, 2 0} {0 1, 1 0, 2 2}],
               :orbit [0 1 2],
               :transversal [{0 0, 1 1, 2 2} {0 1, 1 0, 2 2} {0 2, 1 0, 2 1}]}
              {:base 1,
               :generators [{0 0, 1 2, 2 1}]
               :orbit [1 2]
               :transversal [{0 0, 1 1, 2 2} {0 0, 1 2, 2 1}]}]
          c3 [{:base 0,
               :generators [{0 1, 1 2, 2 0}],
               :orbit [0 1 2],
               :transversal [{0 0, 1 1, 2 2} {0 1, 1 2, 2 0} {0 2, 1 0, 2 1}]}]]
      (is (= (sift s3 {0 0, 1 1, 2 2}) {0 0, 1 1, 2 2}))
      (is (= (sift s3 {0 1, 1 0, 2 2}) {0 0, 1 1, 2 2}))
      (is (= (sift s3 {0 2, 1 1, 2 0}) {0 0, 1 1, 2 2}))
      (is (= (sift s3 {0 0, 1 2, 2 1}) {0 0, 1 1, 2 2}))
      (is (= (sift s3 {0 1, 1 2, 2 0}) {0 0, 1 1, 2 2}))
      (is (= (sift s3 {0 2, 1 0, 2 1}) {0 0, 1 1, 2 2}))
      (is (= (sift c3 {0 0, 1 1, 2 2}) {0 0, 1 1, 2 2}))
      (is (not (= (sift c3 {0 1, 1 0, 2 2}) {0 0, 1 1, 2 2})))
      (is (not (= (sift c3 {0 2, 1 1, 2 0}) {0 0, 1 1, 2 2})))
      (is (not (= (sift c3 {0 0, 1 2, 2 1}) {0 0, 1 1, 2 2})))
      (is (= (sift c3 {0 1, 1 2, 2 0}) {0 0, 1 1, 2 2}))
      (is (= (sift c3 {0 2, 1 0, 2 1}) {0 0, 1 1, 2 2})))))

(deftest solvable?-test
  (testing "should determine if a permutation is solvable"
    (is (solvable? c3 {0 0, 1 1, 2 2}))
    (is (solvable? c3 {0 1, 1 2, 2 0}))
    (is (solvable? c3 {0 2, 1 0, 2 1}))
    (is (not (solvable? c3 {0 1, 1 0, 2 2})))
    (is (not (solvable? c3 {0 2, 1 1, 2 0})))
    (is (not (solvable? c3 {0 0, 1 2, 2 1})))))
