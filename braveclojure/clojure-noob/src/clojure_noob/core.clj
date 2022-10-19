(ns clojure-noob.core
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "I'm a teapot! :)"))

(defn my-print 
  "Testing in runtime"
  [something]
  (println something))

(defn train
  "Testing in runtime"
  []
  (println "New method!"))

(defn another-test
  "Running this in repl"
  []
  (+ 1 1))

(str "hey" "how that going" "hohoho")

(if false 
  (do
    (println "true branch")
    "Hoho")
  (do
    (println "false branch")
    "hehe"))


(when true
  (println "something")
  (* 2 2)
  (+ 2 4))

(if (= 2 "hohoho")
  "True"
  "False")


;; or returns the first truthy value or the last value :)
(or false nil (+ 1 2) (* 2 3))

;; and returns the first falsey value or, if no value are false, the last truthy value
(and (* 2 2) (= 3 3) (= 2 1) (- 2 1))

(and :heey nil false)

;;; Variables
(def list-python-libraries ["numpy", "pandas", "spark", "scikit-learn"])

list-python-libraries

;; Note: Clojure doesn’t have string interpolation

;; Maps are similar to dictionaries or hashes in other languages. They’re a way of associating some value with some other value. The two kinds of maps in Clojure are hash maps and sorted maps. I’ll only cover the more basic hash maps. Let’s look at some examples of map literals. Here’s an empty map:
{}

(def my-first-map {:first-name "Andy"
  :last-name "Laurito"
                   :function +})

(my-first-map :first-name)
((my-first-map :function) 2 3)

(def another-map (hash-map :a 1 :b 2))

(get another-map :b)
; Returns nil if key is not defined :)
(get another-map :5)

;; Or you can specify a default value in case no value is found
(get another-map :5 "No value")

;; The get-in function lets you look up values in nested maps:
(get-in {:a 0 :b {:c "ho hum"}} [:b :c])

;; Keywords --> primarily used as keys in maps :)
;; Note that you can use keywords as functions in a map!!
(:first-name my-first-map)
(:no-keyword my-first-map (+ 2 2))


;; Vectors
;; Note that we are using same function get than before :)
(get [1 2 3] 2)

(get [1 {:a "heey" :b "hoo"} 3] 1 "no value")

(def my-first-vector (vector "this" "is" "clojuree!"))

;; Adding element at the end of the list
(conj my-first-vector 4)


;; Lists
; Lists are similar to vectors in that they’re linear collections of values. But there are some differences. For example, you can’t retrieve list elements with get. To write a list literal, just insert the elements into parentheses and use a single quote at the beginning:
(def my-first-list '(1 2 3 4))
(nth my-first-list 3) ;; This is slower than doing (get my-vector 0)

(def another-list (list 1 "two" (+ 2 2)))
(nth another-list 2)

;; Adding element at the beggining of the list :)
(conj another-list 5)


;; Sets
; 

#{"my" "first" "hashset" 1 2}

(def my-first-set (hash-set 1 1 2 2))

(conj my-first-set 0)

;Creating set from list

(set '(1 2 1 3 3))

(get my-first-set 1) ; returns element
(get my-first-set 0) ; returns nil
(get my-first-set 0 "default value") ; returns nil

(contains? my-first-set 1) ; returns true
(contains? my-first-set 22); returns false

(def math-functions-set (hash-set + - :d :j))

(first [1 2 3 4])

(or + - =)

;; and returns the first falsey value or the last value; Here, the last
;; value is + :)
((and (= 1 1) +) 1 2 3)

(inc 1.3)

;; First-class order functions in Clojure! == I can use functions as value
;; Think that you cannot do this in Java
(map inc [0 1 2 3 4])

;; Clojure evaluates all function arguments recursively before passing them to the function.
;; Here’s how Clojure would evaluate a function call whose arguments are also function calls:

(+ (inc 199) (/ 100 (- 7 2)))
(+ 200 (/ 100 (- 7 2))) ; evaluated "(inc 199)"
(+ 200 (/ 100 5)) ; evaluated (- 7 2)
(+ 200 20) ; evaluated (/ 100 5)
220 ; final evaluation


;; Note on this --> Clojure is not a lazy language, but supports lazineess
;; Read more about this here --> https://clojure-doc.org/articles/language/laziness/



;; Special forms are “special” because, unlike function calls, they don’t always evaluate all of their operands.
;; Example: the if special form

(if true
  (println "Hey")
  (println "Hoo"))
  
;; Another feature that differentiates special forms is that you can’t use them as arguments to functions
;; In general, special forms implement core Clojure functionality that just can’t be implemented with functions. Clojure has only a handful of special forms

;; Macros are similar to special forms in that they evaluate their operands differently from function calls, and they also can’t be passed as arguments to functions

(defn my-first-function
  "Defining my first function in clojure"
  []
  (do (println (+ 2 2))
      (println "A cool function")
      )
  )

;; Use doc to see the documentation of a function
;;(doc map)


;; Functions also support arity overloading. This means that you can define a function
;; so a different function body will run depending on the arity. Here’s the general
;; form of a multiple-arity function definition. Notice that each arity definition is enclosed in parentheses and has an argument list:
(defn pythonise
  "Random function testing multi arity"
  ([]
   (println "No pay, nothing will be said"))
  ([something]
   (println (str "Thanks for " something "traveller")))
  ([one-thing another-thing]
   (println (str "You are really wealthy. Thanks for " one-thing " and " another-thing)))
  ([one-thing two-thing & and-more]
   (println (str "Woow, how many things! You gave me " one-thing " and " two-thing " and " and-more))))

;; Destructuring
;; The basic idea behind destructuring is that it lets you concisely bind names to values within a collection.
;; This sounds like pattern matching :)

(defn my-length
  ([]
   0)
  ([[another-element & tail]]
   (if (empty? tail)
     1
     (+ 1 (my-length tail)))
   ))

(defn destructuring-example
  "This is just an example of destructuring in clojure"
  [[first-element second-element third-element & the-rest]]
  (println (str "Thx for the element " first-element " and " second-element " and the third" third-element " ooh yes, and the rest! " the-rest)))

;; Two ways of destructuring with map ;)

(defn destructuring-map-2
  "In this other function, we destructure a map data structure"
  [{lat :lat lng :lng}]
  (print (str "Lat: " lat " long: " lng)))


(defn destructuring-map
  "In this other function, we destructure a map data structure"
  [{:keys [lat lng]}]
  (print (str "Lat: " lat " long: " lng)))

;; Note: You can retain access to the original map argument by using the :as keyword.

(defn destructuring-map-3
  "In this other function, we destructure a map data structure"
  [{:keys [lat lng] :as my-map}]
  (print (str "Lat: " lat " long: " lng " and the map was: " my-map)))


;; Anonymous functions
(map
 (fn [name] (str "Hi " name))
 ["Bob" "Charly" "Andy"])

(def my-special-function (fn [x] (+ x 1)))

;; Another anonymous functino :O
(#(* % 3) 8) ;; #

(#(str "Hi " %1 " and " %2) "Marta" "Jose") 

(def sum-two-numbers #(+ %1 %2))

(sum-two-numbers 3 5)

(sum-two-numbers 8 0)

(#(identity %&) [1 2 3 5 6 3])

;; Returning functions
;; The returned functions are closures, which means that they can access all the variables that were in scope when the function was created. Here’s a standard example:

(defn inc-maker
  "Returns a function that increments by given numer"
  [x]
  #(+ %1 x))

(def inc-by-3 (inc-maker 3))

(inc-by-3 (inc-by-3 2))


;; Modelling a Hobbit

(def asym-hobbit-body-parts [{:name "head" :size 3}
                             {:name "left-eye" :size 1}
                             {:name "left-ear" :size 1}
                             {:name "mouth" :size 1}
                             {:name "nose" :size 1}
                             {:name "neck" :size 2}
                             {:name "left-shoulder" :size 3}
                             {:name "left-upper-arm" :size 3}
                             {:name "chest" :size 10}
                             {:name "back" :size 10}
                             {:name "left-forearm" :size 3}
                             {:name "abdomen" :size 6}
                             {:name "left-kidney" :size 1}
                             {:name "left-hand" :size 2}
                             {:name "left-knee" :size 2}
                             {:name "left-thigh" :size 4}
                             {:name "left-lower-leg" :size 3}
                             {:name "left-achilles" :size 1}
                             {:name "left-foot" :size 2}])

(defn matching-part
  [part]
  {:name (clojure.string/replace (:name part) #"^left-" "right-")
   :size (:size part)})

(defn symmetrize-body-parts
  "Expects a seq of maps that have a :name and :size"
  [asym-body-parts]
  (loop [remaining-asym-parts asym-body-parts
         final-body-parts []]
    (if (empty? remaining-asym-parts)
      final-body-parts
      (let [[part & remaining] remaining-asym-parts]
        (recur remaining
               (into final-body-parts
                     (set [part (matching-part part)])))))))

(symmetrize-body-parts asym-hobbit-body-parts)

;; let
;; let binds names to values; Difference with def is the scope. Let introduces a new scope!
;; let forms have two main uses. First, they provide clarity by allowing you to name things. Second, they allow you to evaluate an expression only once and reuse the result.

(let [x 3 y 4 z 6]
  (print (str x " and " y " and " z)))

(def x 3) ;; This is a definition for the global context
x
(let [x 1]
  x);; In here we are creating a new scope/context and binding x to 1 in this context!
x

(def x 0)
x


(def y 0)
y
;; In this new context, we bind the name y with the value (inc y); This second y is a value that we will look for in the global context
;; This sounds to 1) look for value in current context; 2) Look for value in global context
(let [y (inc y)] y)

;; x is not defined here
(def dogs ["Mila" "Lara" "Pongo"])

(let [[first-dog & more-dogs] dogs]
  (print (str first-dog " and " more-dogs)))


;; Using loop; This has better performance than the recursive function
(loop [iteration 0]
  (println (str "Iteration " iteration))
  (if (> iteration 3)
    (println "Goodbye!")
    (recur (inc iteration))))

;; This is the same than defining a recursive function!
(defn recursive-printer
  "Defining my recursive printer fun"
  ([]
   (recursive-printer 0))
  ([iteration]
   (println "Iteration" iteration)
   (if (> iteration 3)
     (println "Goodbye!")
     (recursive-printer (inc iteration)))))

(recursive-printer)

;; Using regex: Use #"regular-expression"
(re-find #"^left-" "left-eye")

;; If have any doubt about this, remember you can use (doc reduce)
(reduce + 3 [1 2 3 4])
