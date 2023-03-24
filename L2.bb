#!/usr/bin/env bb

;; Actions
(println "Hi")

(Thread/sleep 1000)

(def x 100)

(rand-int 6)

;; Anonymous functions
(fn [x] (inc x))

((fn [x] (inc x)) 1)

#(inc %)

(#(inc %) 1)

;; SORT
(sort-by count ["clojure" "ada" "java"])
;; => ("ada" "java" "clojure")

(defn count-s [word]
  (let [value (get (frequencies word) \s)]
    (if (= value nil)
      0
      value)))

(sort-by count ["clojure" "ada" "java"])
;; => ("ada" "java" "clojure")

(sort-by count-s ["rust" "ada"])
;; => ("ada" "rust")

(reverse (sort-by identity [5 1 2 4 3]))
;; => (5 4 3 2 1)

(sort-by #(* % -1) [5 1 2 4 3])
;; => (5 4 3 2 1)

(sort-by (fn [x] (* x -1)) [5 1 2 4 3])
;; => (5 4 3 2 1)

(reverse (sort-by count-s ["rust" "ada"]))

(sort-by (fn [x]
           (* -1 (count-s x))) ["rust" "ada"])

;; local bindings
(let [x 1]
  x)

(let [a 1
      b 2]
  (+ a b))

;; "for each" list comprehension
(def alphabets ["a" "b" "c"])

(for [alphabet [1 2 3]]
  (inc alphabet))

;; MAP
(map inc [1 2 3 4 5])

(count "clojure")

(map count ["clojure" "rust" "ada"])
;; => (7 4 3)

(defn o-count [word]
  (let [value (get (frequencies word) \o)]
    (if (= value nil)
      0
      value)))

(map o-count ["doom-emacs" "neovim" "vscode"])
;; => (2 1 1)

(map (fn [x] (* 2 x)) [5 1 2 4 0])
;; => (10 2 4 8 0)

(map (fn [x] (* -1 x)) [5 1 2 4 0])
;; => (-5 -1 -2 -4 0)

;; FILTER
(filter #(< (count %) 5) ["scala", "rust", "ada"])
;; => ("rust" "ada")

(filter (fn [x]
          (< (count x) 5)) ["scala", "rust", "ada"])
;; => ("rust" "ada")

(remove (fn [x]
          (< (count x) 5)) ["scala", "rust", "ada"])

(filter #(> (o-count %) 2) ["rust" "ada"])

(filter odd? [5 1 2 4 0])

(filter #(> % 4) [5 1 2 4 0])

;; REDUCE
(reduce + [5 1 2 4 100])
;; => 112

(reduce
  (fn [accumulator current-item]
    (+ accumulator (count current-item)))
  0
  ["scala" "rust" "ada"])
;; => 12

(reduce (fn [accumulator current-item]
          (+ accumulator (count-s current-item)))
        0
        ["scala" "haskell" "rust" "ada"])
;; => 3

(reduce max [5 1 2 3 15])
;; => 15

;; Example using map, filter, reduce tgt
(->> (range 10)
     (map inc)
     (filter odd?)
     (reduce +)
     )

;; Extra higher order functions if needed
;; comp, partial juxt
(map (juxt dec inc) (range 5))
;; => ([-1 1] [0 2] [1 3] [2 4] [3 5])

(map (comp str inc) (range 5))
;; => ("1" "2" "3" "4" "5")

(map (partial * 10) (range 5))
;; => (0 10 20 30 40)
