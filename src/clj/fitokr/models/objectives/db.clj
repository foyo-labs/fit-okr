(ns fitokr.models.objectives.db
  (:require [taoensso.timbre :as log]
            [fitokr.utils.query :as q]
            [integrant.core :as ig]
            [fitokr.services.db]
            [fitokr.models.keyresults.db :as krs-db]))

(defn get-all 
  [db limit offset]
  (q/db-query! db {:select [:*]
                   :from [:objectives]
                   :limit limit
                   :offset offset}))

(defn get-by-id
  [db id]
  (q/db-query-one! db {:select [:*]
                       :from [:objectives]
                       :where [:= :id id]}))

(defn create [db data]
  (q/db-query-one! db {:insert-into :objectives
                       :values [data]}))

(defn update-by-id 
  [db id data]
  (q/db-query-one! db {:update :objectives
                       :set data
                       :where [:= :id id]}))

(defn delete-by-id 
  [db id]
  (q/db-query-one! db {:delete-from :objectives
                       :where [:= :id id]}))


(defn find-objectives-by-cycle [db cycle]
  (q/db-query! db {:select [:*]
                       :from [:objectives]
                       :where [:= :cycle cycle]}))

(defn get-all-with-keyresults
  "using map collect assoc sub collect"
  [db query]
  (let [objectives (get-all db (:limit query) (:offset query) )
        results (map #(assoc % :keyresults (krs-db/get-all-by-objective-id db (:id %))) objectives)]
    results))


(comment

  (require '[clojure.spec.alpha :as s]
           '[clojure.spec.gen.alpha :as gen]
           '[fitokr.services.config :refer [read-config]])

  (def db (:postgres/db (ig/init (dissoc (read-config) :reitit/routes :http/server))))

  (get-by-id db 2)
  (create db {:name "实现NLP研发飞跃" :description "test desc" :cycle_id 1 :sequence 1 :dept_id 1 :actived true :parent_id 1 :user_id 0})
  )