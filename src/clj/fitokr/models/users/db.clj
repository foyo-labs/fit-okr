(ns fitokr.models.users.db
    (:require [fitokr.utils.query :as q]
              [taoensso.timbre :as log]
              [integrant.core :as ig]
              [buddy.hashers :refer [encrypt]]))

(defn get-all [db]
  (q/db-query! db {:select [:*]
                   :from [:users]}))

(defn create-user [db data]
  (let [password-hash (encrypt (:password data))
        user (assoc data :password password-hash)]
    (q/db-query-one! db {:insert-into :users
                         :values [user]})))

(defn find-user-by-email [db email]
  (q/db-query-one! db {:select [:id :email :authority :password]
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

  (create-user db user1)

  (find-user-by-email db "37505218@qq.com")
  (find-user-by-id db 1)
  )