(ns handle
  (:require [nrepl.server]))

;; repl for handle
(comment
  (require '[core :refer [start-interactive, get-repl-db, restart, halt!!]]
           '[fitokr.models.users.handler :as users]
           '[fitokr.models.settings.handler :as settings]
           '[fitokr.models.objectives.handler :as objectives])

  (start-interactive)
  (def params-setting {:env {:db (get-repl-db)}
                       :parameters {:company-name "haha" :cycle "Q"}})

  (def params {:env {:db (get-repl-db)}
               :parameters {:company-name "haha" :cycle "Q"}})
  ;; call handle fun..
  (users/handle-get-all params)


  (def params-objective {:env {:db (get-repl-db)}
                         :parameters {:query {:cycle "Q1" :limit 10 :offset 0}}})
  
  (settings/handle-system-initialize params-setting)

  (objectives/handle-get-all params-objective)

  ;; end call.
  (restart)
  (halt!!)
  )