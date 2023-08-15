(ns db 
  (:require [nrepl.server]
            [fitokr.services.config :as c]))


;; repl for db query
(comment
  (require '[core :refer :all]
           '[fitokr.models.users.db :refer [get-all]])
  (start-interactive)

  (get-all (get-repl-db))
  (restart)
  (halt!!)
  )