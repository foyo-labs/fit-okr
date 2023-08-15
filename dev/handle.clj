(ns handle
  (:require [nrepl.server]))

;; repl for handle
(comment
  (require '[core :refer :all]
           '[fitokr.models.users.handler :refer [handle-get-all]])
  (start-interactive)
  (def env {:env {:db (get-repl-db)}})

  (handle-get-all env)
  (restart)
  (halt!!)
  )