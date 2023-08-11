(ns fitokr.models.keyresults.db
  (:require [aero.core :as aero]
            [clojure.java.io :as io]
            [fitokr.utils.query :as q]
            [integrant.core :as ig]
            [fitokr.services.db]))


(defn get-all-by-objective-id
  [db objective_id]
  (q/db-query! db {:select [:*]
                       :from [:key_results]
                       :where [:= :objective_id objective_id]}))


(defn create [db data]
  (q/db-query-one! db {:insert-into :key_results
                       :values [data]}))

(comment
  (require '[clojure.spec.alpha :as s]
           '[clojure.spec.gen.alpha :as gen]
           '[fitokr.services.config :refer [read-config]])

  (def db (:postgres/db (ig/init (dissoc (read-config) :reitit/routes :http/server))))

  (get-all-by-objective-id db 2)
   ;; [::oid ::name ::position ::weight ::progress]
  (create db {:objective_id 2 :name "训练不少于5个数据模型" :description "NLP..." :position 1 :weight 1.0 :unit "%" })
  )