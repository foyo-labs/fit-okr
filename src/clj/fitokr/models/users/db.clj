(ns fitokr.models.users.db
    (:require [fitokr.utils.query :as q]
              [integrant.core :as ig]
              [crypto.password.scrypt :as password]
              [integrant.repl :as ig-repl :refer [halt]]))

(defn get-all [db]
  (q/db-query! db {:select [:*]
                   :from [:users]}))

(defn create [db data]
  (q/db-query-one! db {:insert-into :users
                       :values [data]}))

(defn find-user-by-email [db email]
  (q/db-query-one! db {:select [:id :password]
                       :from [:users]
                       :where [:= :email email]}))


(defn find-user-by-id [db id]
  (q/db-query-one! db {:select [:*]
                       :from [:users]
                       :where [:= :id id]}))


(comment
  (require '[clojure.spec.alpha :as s]
           '[clojure.spec.gen.alpha :as gen]
           '[fitokr.services.config :refer [read-config]])

  (def db (:postgres/db (ig/init (dissoc (read-config) :reitit/routes :http/server))))

  (def pwd (password/encrypt "a123456"))
  (def user1 {:password pwd :name "AsOne" :email "37505218@qq.com" :actived true})

  (create db user1)

  (find-user-by-email db "37505218@qq.com")
  (find-user-by-id db 1)

  (halt)
  )