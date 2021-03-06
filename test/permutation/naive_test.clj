(ns permutation.naive-test
  (:require [permutation.naive :refer :all]
            [permutation.kernel :refer [identity-for]]
            [clojure.test :refer :all]))

(defn- contain-same?
  "determine if the two sequences contain the same elements"
  [as bs]
  (= (set as) (set bs)))

(deftest elements-generated-by-test
  (testing "should produce all elements generated by generators"
    (is (= (elements-generated-by) []))
    (is (= (elements-generated-by {0 1, 1 0}) [{0 0, 1 1} {0 1, 1 0}]))
    (is (contain-same? (elements-generated-by {0 1, 1 0, 2 2} {0 0, 1 2, 2 1})
                       [{0 0, 1 1, 2 2} {0 1, 1 0, 2 2}
                        {0 0, 1 2, 2 1} {0 2, 1 1, 2 0}
                        {0 1, 1 2, 2 0} {0 2, 1 0, 2 1}]))
    (is (contain-same? (elements-generated-by {0 1, 1 0, 2 2, 3 3} {0 0, 1 1, 2 3, 3 2})
                       [{0 0, 1 1, 2 2, 3 3} {0 1, 1 0, 2 2, 3 3}
                        {0 0, 1 1, 2 3, 3 2} {0 1, 1 0, 2 3, 3 2}]))))

(deftest solvable?-test
  (testing "if we can predict if a permutation is solvable"
    (is (solvable? {0 1, 1 0} {0 1, 1 0}))
    (is (solvable? {0 1, 1 2, 2 0} {0 1, 1 0, 2 2} {0 0, 1 2, 2 1}))
    (is (not (solvable? {0 1, 1 2, 2 0} {0 1, 1 0, 2 2})))))
