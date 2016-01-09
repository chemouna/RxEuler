
(ns com.mounacheikhna.euler
  ;(:use [clojure.contrib.lazy-seqs :only (primes)])
  ;(:use [clojure.contrib.math :only (sqrt)])
  )

; solution #1
;(defn greatest-prime-of [number]
;  (reduce max (filter #(zero? (mod number %))
;                      (take-while #(< % (sqrt number)) primes))))
;
;(println (greatest-prime-of 600851475143))


; solution #2
(defn gen-primes "Generates an infinite, lazy sequence of prime numbers"
  []
  (let [reinsert (fn [table x prime]
                   (update-in table [(+ prime x)] conj prime))]
    (defn primes-step [table d]
      (if-let [factors (get table d)]
        (recur (reduce #(reinsert %1 d %2) (dissoc table d) factors)
               (inc d))
        (lazy-seq (cons d (primes-step (assoc table (* d d) (list d))
                                       (inc d))))))
    (primes-step {} 2)))


(println (reduce max (take 600851475143 (gen-primes))))



; range
;(println (take 10 (range)))
;(println (take 5 (drop 5 (range))))

;prime
;(defn prime? [n]
;  (= 2 (reduce +
;               (for [i (range 1 (inc n))]
;                 (if (= 0 (mod n i)) 1 0)))))
