(ns handle
  (:require [nrepl.server]))

;; repl for handle
(comment
  (require '[core :refer [start-interactive, get-repl-db, restart, halt!!]]
           '[fitokr.models.users.handler :as users]
           '[fitokr.models.settings.handler :as settings])
  
  (start-interactive)
  (def params {:env {:db (get-repl-db)}
               :parameters {:company-name "haha" :cycle "Q"}})

  ;; call handle fun..
  (users/handle-get-all params)
  

  (settings/handle-system-initialize params)

  ;; end call.
  (restart)
  (halt!!)
  )