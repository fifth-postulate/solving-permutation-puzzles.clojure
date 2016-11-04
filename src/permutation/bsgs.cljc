(ns permutation.bsgs)

(def s3 [{:base 0,
          :generators [{0 1, 1 2, 2 0} {0 1, 1 0, 2 2}],
          :orbit [0 1 2],
          :transversal [{0 0, 1 1, 2 2} {0 1, 1 0, 2 2} {0 2, 1 0, 2 1}]}
         {:base 1,
          :generators [{0 0, 1 2, 2 1}]
          :orbit [1 2]
          :transversal [{0 0, 1 1, 2 2} {0 0, 1 2, 2 1}]}])
