(ns handle
  (:require [nrepl.server]
            [fitokr.services.config :as c]))

;; repl for handle
(comment
  (require '[core :refer :all]
           '[fitokr.models.users.handler :refer [handle-get-all]])
  (start-interactive)
  (def env {:env {:db (c/get-repl-db)}})

  (handle-get-all env)
  (restart)
  (halt!!)
  )