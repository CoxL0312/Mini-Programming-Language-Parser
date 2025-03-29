(ns cs390interpreter.core
  (:gen-class))

(def symbol-table (atom {}))

(defn assign [var val]
  (swap! symbol-table assoc var val))

(defn lookup [var]
  (get @symbol-table var))

(defn increment [var]
  (assign var (inc (lookup var))))

(defn eval-expr [expr]
  (if (symbol? expr)
    (lookup expr)
    expr))

(defn token-parser [prgm-ex]
  (cond
    ;; Assignment
    (= (first prgm-ex) 'asg) (do (assign (second prgm-ex) (eval-expr (nth prgm-ex 2))))
    ;; Incrementation
    (= (first prgm-ex) '++) (do (increment (second prgm-ex))nil)
    ;; Conditionals
    (= (first prgm-ex) '>?) (> (eval-expr (second prgm-ex)) (eval-expr (nth prgm-ex 2)))
    (= (first prgm-ex) '<?) (< (eval-expr (second prgm-ex)) (eval-expr (nth prgm-ex 2)))
    (= (first prgm-ex) '=?) (= (eval-expr (second prgm-ex)) (eval-expr (nth prgm-ex 2)))
    (= (first prgm-ex) '=/?) (not= (eval-expr (second prgm-ex)) (eval-expr (nth prgm-ex 2)))
    ;; If-Then-Else
    (= (first prgm-ex) 'if) (if (token-parser (second prgm-ex))
                              (token-parser (nth prgm-ex 2))
                              (token-parser (nth prgm-ex 3)))
    ;; For loop
    (= (first prgm-ex) 'for) (let [end (second prgm-ex)
                                   body (nthrest prgm-ex 2)]
                               (while (token-parser end)
                                 (doseq [expr body]
                                   (token-parser expr))))
    ;; Display
    (= (first prgm-ex) 'put) (do (apply println (map eval-expr (rest prgm-ex))))
    ;; Default case to handle the rest of the expressions
    :else (when (seq prgm-ex)
            (doseq [expr prgm-ex]
              (token-parser expr))))
    )

(def prgm-ex1 '((asg numOne 0)
                (++ numOne)
                (if (<? numOne 1)
                    (put numOne "is equal to zero") (put numOne "is more than zero"))
                (for (<? numOne 10)
                     (++ numOne)
                     (put numOne))
                (if (>? numOne 0)
                    (put numOne "these are positive integers") (put "are not positive integers"))))

(def prgm-ex2 '((asg numOne 0)
                (asg numTwo 5)
                (if (<? numOne numTwo)
                    (for (<? numOne numTwo)
                         (put numOne numTwo)
                         (++ numOne)))
                    (put numOne "equals" numTwo))
    )



(token-parser prgm-ex1)
(token-parser prgm-ex2)
