(defrule assert-result
    (initial-fact)
  =>
    (printout t "Firing rule assert-result" crlf)
    (assert (result "The answer to life, the universe and everything!"))
)

(defrule send-result
    (result ?result)
  =>
    (printout t "Firing rule send-result" crlf)
    (send-to-java ?result)
)

(defrule do-rest
    (do-rest)
  =>
    (printout t "Firing rule do-rest" crlf)
)
