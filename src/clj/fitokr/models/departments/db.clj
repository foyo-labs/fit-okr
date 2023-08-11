(ns fitokr.models.departments.db
  (:require [aero.core :as aero]
            [clojure.java.io :as io]
            [fitokr.utils.query :as q]
            [integrant.core :as ig]
            [fitokr.services.db]))


(defn get-all-by-own [db own]
  (q/db-query! db {:select [:*]
                   :from [:departments]
                   :where [:= :own own]}))


(defn create [db data]
  (q/db-query-one! db {:insert-into :departments
                       :values [data]}))


(defn find-child-by-id [db id]
  (q/db-query-one! db {:select [:*]
                       :from [:departments]
                       :where [:= :parent id]}))


(comment
  (require '[clojure.spec.alpha :as s]
           '[clojure.spec.gen.alpha :as gen]
           '[fitokr.services.config :refer [read-config]])

  (def db (:postgres/db (ig/init (dissoc (read-config) :reitit/routes :http/server))))

  (def department-1 {:name "技术中心" :description "策划与研发产品" :parent 0 :position 0 :code "0" :ownid 2})
  (def department-1-1 {:name "产品小姐" :description "策划产品、定制需求" :parent 1 :position 1 :code "0.1" :ownid 2})
  (def department-1-2 {:name "开发小组" :description "开发产品" :parent 1 :position 1 :code "0.2" :ownid 2})
  (def department-1-2-1 {:name "后端研发" :description "后端功能研发" :parent 4 :position 2 :code "0.2.1" :ownid 2})


  (create db department-1)
  (create db department-1-1)
  (create db department-1-2)
  (create db department-1-2-1)
  (find-child-by-id db 1)

  )