(ns fitokr.utils.query
  (:require [next.jdbc :as jdbc]
            [next.jdbc.result-set :as rs]
            [honeysql.core :as sql]))

(defn db-query! [db query]
  (jdbc/execute! db 
                 (sql/format query)
                 {:return-keys true
                  :builder-fn rs/as-unqualified-maps
                  :debug true}))

(defn db-query-one! [db query]
  (jdbc/execute-one! db
                     (sql/format query)
                     {:return-keys true
                      :builder-fn rs/as-unqualified-maps}))

(defn db-query-batch! [db query]
  (jdbc/execute-batch! db
                       (sql/format query)
                       {:return-keys true
                        :builder-fn rs/as-unqualified-maps}))