(ns fitokr.models.cycles.db
  (:require [fitokr.utils.query :as q]
            [integrant.core :as ig]
            [fitokr.services.db]
            [java-time.api :as time]))
  
  
(defn create [db data]
  (q/db-query-one! db {:insert-into :cycles
                       :values [data]}))
  
  
(comment
  (require '[clojure.spec.alpha :as s]
           '[clojure.spec.gen.alpha :as gen]
           '[fitokr.services.config :refer [read-config]])

  (def db (:postgres/db (ig/init (dissoc (read-config) :reitit/routes :http/server))))

  (def cycle-1 {:name "23-Q1" :description "2023年第一季度" :period "Q" :start_date (time/local-date 2023 10 1) :end_date (time/local-date 2023 12 31)})
  (create db cycle-1)
  )