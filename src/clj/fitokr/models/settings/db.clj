(ns fitokr.models.settings.db
  (:require [fitokr.utils.query :as q]
            [integrant.core :as ig]
            [crypto.password.scrypt :as password]))

(defn get-all [db]
  (q/db-query! db {:select [:*]
                   :from [:settings]}))

(defn create [db data]
  (q/db-query-one! db {:insert-into :settings
                       :values [data]}))

(defn find-setting-item-by-label [db label]
  (q/db-query-one! db {:select [:*]
                       :from [:settings]
                       :where [:= :label label]}))


(comment
  (require '[clojure.spec.alpha :as s]
           '[clojure.spec.gen.alpha :as gen]
           '[fitokr.services.config :refer [read-config]])

  (def db (:postgres/db (ig/init (dissoc (read-config) :reitit/routes :http/server))))

  (def setting-item-1 {:label "company-name" :content "花儿与少年工作室" :dtype "string" :section "general"})
  (def setting-item-2 {:label "initialized" :content "1" :dtype "string" :section "general"})

  (create db setting-item-1)
  (create db setting-item-2)
  (find-setting-item-by-label db "company-name")
  (find-setting-item-by-label db "initialized")
  (get-all db)
  )