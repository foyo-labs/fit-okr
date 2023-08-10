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

  (def config {:system/config
               {:jdbc
                {:host "192.168.0.132"
                 :dbtype "postgres"
                 :port 5432
                 :dbname "okre"
                 :username "okre"
                 :password "okre"}}
               :postgres/db {:config (ig/ref :system/config)}})
  
  (def db (:postgres/db (ig/init config)))


  (get-all db :10 :0)
  (get-all-with-keyresults db {:limit 10 :offset 0 :cycle "M6"})
  (get-by-id db 1)
  (create db {:owner 1 :name "实现NLP研发飞跃" :cycle "M6" :position 1})
  (update-by-id db 2 {:cycle "Q1"})
  (delete-by-id db 2)
  (find-objectives-by-cycle db "M6")
  )