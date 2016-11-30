(ns permutation.words-test
  (:require  [clojure.test :refer :all]
             [permutation.words :refer :all]))

(deftest normalize-test
  (testing "should normalize words correctly"
    (is (= (normalize []) []))
    (is (= (normalize [["a", 1]]) [["a", 1]]))
    (is (= (normalize [["a", 1] ["a", 1]]) [["a", 2]]))
    (is (= (normalize [["a", 1] ["a", 2]]) [["a", 3]]))
    (is (= (normalize [["a", 1] ["a", -1]]) []))
    (is (= (normalize [["b", -1] ["a", 1], ["a", -1], ["b", 1]]) []))
    (is (= (normalize [["c", 1] ["b", -1] ["a", 1] ["a", -1] ["b", 1] ["c", -1]]) []))
    (is (= (normalize [["a", 1], ["b", 1], ["b", -1]]) [["a", 1]]))))


(deftest multiply-test
  (testing "should multiply words correctly"
    (is (= (multiply [["a", 1]] [["b", 1]]) [["a", 1] ["b", 1]]))
    (is (= (multiply [["a", 1]] [["a", 1]]) [["a", 2]]))
    (is (= (multiply [["a", 1]] [["a", -1]]) []))))

(deftest inverse-test
  (testing "should invert words correctly"
    (is (= (inverse [["a", 1]]) [["a", -1]]))
    (is (= (inverse [["a", 1] ["b", 1]]) [["b", -1] ["a", -1]]))))

(deftest identity-for-test
  (testing "should return the empty word"
    (is (= (identity-for [["a", 1] ["b", 2] ["c", 3]]) []))))

(deftest identity-test
  (testing "should correctly determine if a word is the identity"
    (is (identity? []))
    (is (not (identity? [["a", 1]])))))
