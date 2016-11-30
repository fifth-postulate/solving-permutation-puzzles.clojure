(ns permutation.expression-test
  (:require [permutation.expression :refer :all]
            [clojure.test :refer :all]))

(deftest expression-test
  (testing "various operations on expressions"
    (let [g (generator 0)
          h (generator 1)]
      (is (= g {:type :generator :index 0}))
      (is (= h {:type :generator :index 1}))
      (is (= (inverse g) {:type :inverse :expression {:type :generator :index 0}}))
      (is (= (multiply g h) {:type :multiply
                             :left {:type :generator :index 0}
                             :right {:type :generator :index 1}})))))
