(ns permutation.bsgs-test
  (:require [permutation.bsgs :refer :all]
            [clojure.test :refer :all]))

(deftest sift-test
  (testing "should sift elements"
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
    (is (= (sift c3 {0 2, 1 0, 2 1}) {0 0, 1 1, 2 2}))))

(deftest solvable?-test
  (testing "should determine if a permutation is solvable"
    (is (solvable? c3 {0 0, 1 1, 2 2}))
    (is (solvable? c3 {0 1, 1 2, 2 0}))
    (is (solvable? c3 {0 2, 1 0, 2 1}))
    (is (not (solvable? c3 {0 1, 1 0, 2 2})))
    (is (not (solvable? c3 {0 2, 1 1, 2 0})))
    (is (not (solvable? c3 {0 0, 1 2, 2 1})))))
