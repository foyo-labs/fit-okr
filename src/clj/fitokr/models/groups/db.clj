(ns fitokr.models.groups.db
  (:require [aero.core :as aero]
            [clojure.java.io :as io]
            [fitokr.utils.query :as q]
            [integrant.core :as ig]
            [fitokr.services.db]))


(defn get-all-by-own [db own]
  (q/db-query! db {:select [:*]
                   :from [:groups]
                   :where [:= :own own]}))


(defn create [db data]
  (q/db-query-one! db {:insert-into :groups
                       :values [data]}))