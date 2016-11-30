(ns permutation.expression-test
  (:require [permutation.kernel :as kernel]
            [permutation.expression :refer :all]
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

(deftest evaluation-test
  (testing "should correctly evaluate an expression"
    (let [g (generator 0)
          h (generator 1)
          t {0 1, 1 2, 2 0}
          s {0 1, 1 0, 2 2}
          generators [t s]
          gens #(nth generators %)
          inv #(kernel/inverse %)
          mult #(kernel/multiply %1 %2)
          ev (partial evaluate mult inv gens)]
      (is (= (ev g) t))
      (is (= (ev h) s))
      (is (= (ev (inverse g)) (kernel/inverse t)))
      (is (= (ev (multiply (inverse g) h)) (kernel/multiply (kernel/inverse t) s))))))
