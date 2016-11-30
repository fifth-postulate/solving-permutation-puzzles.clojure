(ns permutation.expression)

(defn multiply
  "returns an expression that multiplies two sub expressions"
  [l r]
  {:type :multiply :left l :right r})

(defn inverse
  "return an expression that is the inverse of a sub expression"
  [e]
  {:type :inverse :expression e})

(defn generator
  "returns an expression for the nth generator"
  [n]
  {:type :generator :index n})
