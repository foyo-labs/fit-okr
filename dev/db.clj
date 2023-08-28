(ns db 
  (:require [nrepl.server]
            [fitokr.models.organization.db :as org.db]))


;; repl for db query
(comment
  (require '[core :refer [start-interactive, get-repl-db, restart, halt!!]]
           '[fitokr.models.users.db :as user.db :refer [get-all, create-user]]
           '[fitokr.models.cycles.db :refer [get-cycle-by-id]]
           '[fitokr.models.settings.db :as settings.db]
           '[fitokr.models.departments.db :as departments.db]
           '[fitokr.models.organization.db :as org.db])

  (start-interactive false)

  ; call db query fun...
  (get-all (get-repl-db))

  ;; cycle db query
  (get-cycle-by-id (get-repl-db) 1)

  ;; department db query
  (departments.db/get-department-hierarchy (get-repl-db) 0)


  ;; organization db query
  (def org-entity {:name "花儿与少年"
               :description "组织简介"
               :domain "http://localhost:3000"
               :email "37505218@qq.com"
               :active true})
  (org.db/create-organization (get-repl-db) org-entity)
  (org.db/get-org-by-domain (get-repl-db) "http://localhost:3000")
  (org.db/get-all-organizations (get-repl-db))


  (def user1 {:password "a123456" :name "AsOne" :email "37505218@qq.com" :actived true})
  (create-user (get-repl-db) user1)

  (user.db/find-user-by-id (get-repl-db) 3)

  (def res (settings.db/initialize-system (get-repl-db) {:company-name "FIT OKR"} true))
  (println res)
  ;; end call db fun
  (restart)
  (halt!!)
  )