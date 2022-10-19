(ns clojure-noob.exercises-test
  (:require [clojure.test :refer :all]
            [clojure-noob.exercises  :refer :all]))

(deftest should-reverse-sequence
  (testing "reversing a sequence"
    (is (= (my-reverse [1 2 3]) [3 2 1]))))

(deftest should-reverse-list
  (testing "reversing a list"
    (is (= (my-reverse '(1 2 3)) '(3 2 1)))))
