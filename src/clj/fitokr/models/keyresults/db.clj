(ns fitokr.models.keyresults.db
  (:require [aero.core :as aero]
            [clojure.java.io :as io]
            [fitokr.utils.query :as q]
            [integrant.core :as ig]
            [fitokr.services.db]))


(defn get-all-by-objective-id
  [db oid]
  (print oid)
  (q/db-query! db {:select [:*]
                       :from [:keyresults]
                       :where [:= :oid oid]}))


(defn create [db data]
  (q/db-query-one! db {:insert-into :keyresults
                       :values [data]}))

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
  
  (get-all-by-objective-id db 2)
   ;; [::oid ::name ::position ::weight ::progress]
  (create db {:oid 2 :name "训练不少于5个数据模型" :position 2 :weight 1.0 :progress 0})
  )