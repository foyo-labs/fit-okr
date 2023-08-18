(ns db 
  (:require [nrepl.server]))


;; repl for db query
(comment
  (require '[core :refer [start-interactive, get-repl-db, restart, halt!!]]
           '[fitokr.models.users.db :refer [get-all]]
           '[fitokr.models.cycles.db :refer [get-cycle-by-id]]
           '[fitokr.models.settings.db :as settings.db]
           '[fitokr.models.departments.db :as departments.db])

  (start-interactive false)

  ; call db query fun...
  (get-all (get-repl-db))

  ;; cycle db query
  (get-cycle-by-id (get-repl-db) 1)

  ;; department db query
  (departments.db/get-department-hierarchy (get-repl-db) 0)

  (def res (settings.db/initialize-system (get-repl-db) {:company-name "FIT OKR"} true))
  (println res)
  ;; end call db fun
  (restart)
  (halt!!)
  )