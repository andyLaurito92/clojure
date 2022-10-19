(ns clojure-noob.exercises
  (:gen-class))

(+ 1 2)

(defn my-reverse
  "Reverse a sequence"
  ([]
   [])
  ([[first & tail]]
   (if (empty? tail)
     [first]
     (conj (my-reverse tail) first)))
  )
