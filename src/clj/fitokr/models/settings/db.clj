(ns fitokr.models.settings.db
  (:require [fitokr.utils.query :as q]
            [integrant.core :as ig]
            [next.jdbc :as jdbc]
            [crypto.password.scrypt :as password]))

(defn get-all [db]
  (q/db-query! db {:select [:*]
                   :from [:settings]}))

(defn find-setting-item-by-label [db label]
  (q/db-query-one! db {:select [:*]
                       :from [:settings]
                       :where [:= :label label]}))

(defn- default-company-setting [data] {:label "company-name" :content (:company-name data) :dtype "string" :section "general"})

(defn- default-interialize-setting [] {:label "initialized" :content "1" :dtype "string" :section "general"})

(defn initialize-system
  "Add a company-name & add initialized"
  [db data initialed]
  (if initialed
    (jdbc/with-transaction [tx db]
      (let [new-company-setting (default-company-setting data)
            new-initialize (default-interialize-setting)]
        (when (and (q/query-one! tx {:insert-into :settings
                                     :values [new-company-setting]})
                   (q/query-one! tx {:insert-into :settings
                                     :values [new-initialize]}))
          :created)))
    :initialed))
  

(defn create [db data]
  (q/db-query-one! db {:insert-into :settings
                       :values [data]}))


(comment
  ;; (require '[clojure.spec.alpha :as s]
  ;;          '[clojure.spec.gen.alpha :as gen]
  ;;          '[fitokr.services.config :refer [read-config]])

  ;; (def db (:postgres/db (ig/init (dissoc (read-config) :reitit/routes :http/server))))

  ;; (def setting-item-1 {:label "company-name" :content "花儿与少年工作室" :dtype "string" :section "general"})
  ;; (def setting-item-2 {:label "initialized" :content "1" :dtype "string" :section "general"})

  ;; (create db setting-item-1)
  ;; (create db setting-item-2)
  ;; (find-setting-item-by-label db "company-name")
  ;; (find-setting-item-by-label db "initialized")
  ;; (get-all db)
  )