#!/usr/bin/env bb

;; Data
42        ; integer
-1.5      ; floating point
22/7      ; ratio

"hello"         ; string
\e              ; character
#"[0-9]+"       ; regular expression

map             ; symbol
+               ; symbol - most punctuation allowed
clojure.core/+  ; namespaced symbol
nil             ; null value
true false      ; booleans
:alpha          ; keyword
:release/alpha  ; keyword with namespace

;; Collections
'(1 2 3)     ; list
[1 2 3]      ; vector
#{1 2 3}     ; set
{:a 1, :b 2} ; map

(def m {:a 1 :b 2 "c" 3})

(get m "c")
;; => 3

(:a m)
;; => 1

;; Calculation
(defn add [a b]
  (+ a b))

(add 1 5)

(defn increment [a]
  (inc 1))

(increment 2)
;; => 2
(inc 5)

(defn cube [x]
  (* x x x))

(cube 3)
;; => 27
(cube 2)
;; => 8

(defn get-first-character [s]
  (first s))

(get-first-character "string")

(defn word-length [s]
  (count s))

(defn word-score [s]
  (+ (count s) 1))

(word-length "length")

(comment
  (slurp "https://hacker-news.firebaseio.com/v0/askstories.json")

  ;; https://mixedanalytics.com/blog/list-actually-free-open-no-auth-needed-apis/
  (require '[babashka.http-client :as http])
  (require '[clojure.java.io :as io])
  (require '[clojure.data.json :as json])

  (http/get "https://httpstat.us/200")

  (defn get-story [story-id]
    (http/get (str "https://hacker-news.firebaseio.com/v0/item/" story-id ".json")))

  (->> (http/get "https://hacker-news.firebaseio.com/v0/askstories.json")
       :body
       json/read-str
       (take 2)
       (map get-story)
       (map :body)
       (map #(json/read-str % :key-fn keyword))
       (map :text)
       )
  )
