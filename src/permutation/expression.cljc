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

(defn evaluate
  "evaluates an expression in terms of a multiplier inverter and generator"
  [multiplier inverter generator expression]
  (case (:type expression)
    :generator (generator (:index expression))
    :inverse (inverter (evaluate multiplier inverter generator (:expression expression)))
    :multiply (multiplier
               (evaluate multiplier inverter generator (:left expression))
               (evaluate multiplier inverter generator (:right expression)))))
